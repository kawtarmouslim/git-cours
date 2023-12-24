package dao;

import metier.Product;
import metier.User;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;


public class ProductDaoImp implements IProductDao {



    @Override
   public Product save(Product p) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO produits (DESIGNATION, PRIX, QUANTITE) VALUES (?, ?, ?)");
            ps.setString(1, p.getDesignation());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getQuantite());
            ps.executeUpdate();
            //pour recuper id
            PreparedStatement ps2 = connection.prepareStatement
                    ("SELECT MAX(ID) as MAX_ID FROM produits");
            ResultSet rs = ps2.executeQuery();//pour execution de query
            if (rs.next()) {
                p.setId(rs.getInt("MAX_ID"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception (à adapter en fonction de votre logique)
        }

        return p;
    }

    @Override
    public Product selectProduct(int id) {
        Connection connection = SingletonConnection.getConnection();
        Product product = null;
        try (
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement("select id,designation,prix,quantite from products where id =?")) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String designation = rs.getString("designation");
                double prix = rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                product = new Product(id, designation, prix, quantite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        Connection connection = SingletonConnection.getConnection();

        List < Product > products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement("select * from products")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String designation = rs.getString("designation");
                double prix= rs.getDouble("prix");
                int quantite = rs.getInt("quantite");
                products.add(new Product(id, designation, prix, quantite));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean rowDeleted;
        Connection connection = SingletonConnection.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement("delete from products where id = ?" +
                        "")) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection connection = SingletonConnection.getConnection();
        boolean rowUpdated = false;
        try (
                PreparedStatement statement = connection.prepareStatement("update products set designation = ?,prix= ?, quantite=? where id = ?")) {
            statement.setString(1, product.getDesignation());
            statement.setDouble(2,product.getPrix() );
            statement.setInt(3, (product.getQuantite()));
            statement.setInt(4, product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowUpdated;
    }
    }



