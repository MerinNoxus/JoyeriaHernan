
package utilerias;

public class pendejo {
    private String codID, name, ClientFre, lastname;

    public pendejo(String codID, String name, String ClientFre, String lastname) {
        this.codID = codID;
        this.name = name;
        this.ClientFre = ClientFre;
        this.lastname = lastname;
    }

    public String getCodID() {
        return codID;
    }

    public void setCodID(String codID) {
        this.codID = codID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientFre() {
        return ClientFre;
    }

    public void setClientFre(String ClientFre) {
        this.ClientFre = ClientFre;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
}
