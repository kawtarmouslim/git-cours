package metier;

import java.util.List;

public class Order {
    private int id_commande;
   private String status;
    private   String name_client;
    private List<String> orderedItems;
    private   String id_user;
    private int date_creation;

    public Order(int id_commande, String status, String name_client, String id_user, int date_creation) {
        this.id_commande = id_commande;
        this.status = status;
        this.name_client = name_client;
        this.id_user = id_user;
        this.date_creation = date_creation;
    }

    public Order(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public List<String> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String id_client) {
        this.name_client = name_client;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(int date_creation) {
        this.date_creation = date_creation;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id_commande=" + id_commande +
                ", status='" + status + '\'' +
                ", name_client='" + name_client + '\'' +
                ", orderedItems=" + orderedItems +
                ", id_user='" + id_user + '\'' +
                ", date_creation=" + date_creation +
                '}';
    }
}
