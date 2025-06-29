package ac.cr.ucr.pablit_html.model.DTO;


//clase para recibirel nombre de el receptor y emisor de la solicitud
public class FriendsDTO {
    private String senderUsername;   // Usuario que envió la solicitud
    private String receiverUsername; // Usuario que recibió la solicitud
    private Integer friendId;        // Id del objeto Friends o relación

    public FriendsDTO(String senderUsername, String receiverUsername, Integer friendId) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.friendId = friendId;
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

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}
