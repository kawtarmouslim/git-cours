package dao;

import metier.Product;
import metier.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements IUserDao {


    @Override
    public User insertUser(User user) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO users (name, email, country) VALUES (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.executeUpdate();
            //pour recuper id
            PreparedStatement ps2 = connection.prepareStatement
                    ("SELECT MAX(ID) as MAX_ID FROM produits");
            ResultSet rs = ps2.executeQuery();//pour execution de query
            if (rs.next()) {
                user.setId(rs.getInt("MAX_ID"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception (à adapter en fonction de votre logique)
        }

        return user;
    }

    @Override
    public User selectUser(int id) {
        Connection connection = SingletonConnection.getConnection();
        User user = null;
        try (
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement("select id,name,email,country from users where id =?")) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name= rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        Connection connection = SingletonConnection.getConnection();

        List < User > users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement("select * from Users")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("designation");
                String email= rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id,name, email, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted;
        Connection connection = SingletonConnection.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement("delete from iusers where id = ?" +
                        "")) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) {

        Connection connection = SingletonConnection.getConnection();
        boolean rowUpdated = false;
        try (
                PreparedStatement statement = connection.prepareStatement("update Users set name = ?,:email= ?, country=? where id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2,user.getEmail() );
            statement.setString(3,user.getCountry());
            statement.setInt(4,user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
