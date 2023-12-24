package dao;

import metier.Order;
import metier.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoImp implements IOrderDao {
    @Override
    public void save(Order order) {
        Connection connection = SingletonConnection.getConnection();
        try {
            // Utilisez une requête SQL pour insérer la commande dans la base de données
            String sql = "INSERT INTO orders (name_produit, id_user, name_client, status, date_creation) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, String.join(",", order.getOrderedItems()));
                statement.setString(2, String.valueOf(order.getId_user()));  // Assuming id_user is a single value, not a list
                statement.setString(3, order.getName_client());
                statement.setString(4, order.getStatus());
                statement.setDate(5, new java.sql.Date(order.getDate_creation()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions SQL correctement dans une application réelle
        }
    }
     class InsufficientStockException extends RuntimeException {
        public InsufficientStockException(String message) {
            super(message);
        }
    }

    public abstract class OrderService {
        private StockService  stockService = new  StockService();
        private OrderDaoImp orderDAO; // Ne pas instancier directement

        // Constructeur pour injecter OrderDAO
        public OrderService(OrderDaoImp orderDAO) {
            this.orderDAO = orderDAO;
        }

        // Méthode pour ajouter une commande
        public void addOrder(Order order) {
            // Vérifiez si les articles commandés sont disponibles en stock
            for (String orderedItem : order.getOrderedItems()) {
                int productId = Integer.parseInt(orderedItem);
                int availableQuantity = stockService.getAvailableQuantity(productId);

                if (availableQuantity <= 0) {
                    // Gérez le cas où le stock est insuffisant
                    throw new InsufficientStockException("Stock insuffisant pour le produit avec l'ID : " + productId);
                }
            }

            // Si le stock est suffisant, ajoutez la commande
            orderDAO.save(order);

            // Mettez à jour les stocks après la commande
            for (String orderedItem : order.getOrderedItems()) {
                int productId = Integer.parseInt(orderedItem);
                stockService.updateStock(productId, -1); // Diminue la quantité en stock
            }
        }
    }}










