public class Client {
    private int clientId;
    private String clientname;
    private String phone;

    public Client(int clientId, String name, String phone) {
        this.clientId = clientId;
        this.clientname = name;
        this.phone = phone;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return clientname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Client ID: " + clientId + ", Client Name: " + clientname + ", Phone: " + phone;
    }
}
