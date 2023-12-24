package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SingletonConnection {
    private static final String url = "jdbc:mysql://localhost:3306/gestions";
    private static final String user = "root";
    private static final String password = "";
    private static Connection connection = null;



    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection(url, user, password);//telecharger le classe en mémoire
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Erreur de connexion à la base de données.");
                throw new RuntimeException(e);
            }
        }
            return connection;
        }
}
