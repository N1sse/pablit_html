package ac.cr.ucr.pablit_html.model.DTO;


//clase para recibirel nombre de el receptor y emisor de la solicitud
public class FriendRequestDTO {
    private String senderUsername;
    private String receiverUsername;

    public FriendRequestDTO(String senderUsername, String receiverUsername) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
