package metier;

public class Product {
    private int id;
    private String designation;
    private  Double prix;
    private int quantite;

    public Product(String designation, double prix, int quantite) {

    }

    public Product(int id, String désignation, Double prix, int quantite) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_produit) {
        this.id = id_produit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    @Override public String toString() {
    return "Produit{" +
            "id_produit=" + id +
            ", désignation='" + designation + '\'' +
            ", prix=" + prix +
            ", quantite=" + quantite +
            '}';
}}
