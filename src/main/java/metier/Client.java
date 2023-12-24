package metier;

public class Client {
    private  int id_client;
    private  String name;
    private String email;
    private  String tel;

    public Client(int id_client, String name, String email, String tel) {
        this.id_client = id_client;
        this.name = name;
        this.email = email;
        this.tel = tel;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
