/* Jordi Hernandez i Gerard Lopez */

public class Client {

    private int id;
    private String nif;
    private String name;
    private String lasname;
    private String adress;
    private String town;

    public Client(int id, String nif, String name, String lasname, String adress, String town) {
        this.id = id;
        this.nif = nif;
        this.name = name;
        this.lasname = lasname;
        this.adress = adress;
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLasname() {
        return lasname;
    }

    public void setLasname(String lasname) {
        this.lasname = lasname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
