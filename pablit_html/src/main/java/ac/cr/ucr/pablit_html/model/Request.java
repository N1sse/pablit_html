package ac.cr.ucr.pablit_html.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.List;

@Entity //Para hacer la clase Request en una entidad
@Table(name = "tb_Request") //Para que se cree la tabla que une la tabla Friends & User
public class Request{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para hacer el id que se genere de forma auto
    private Integer id;

    // Usuario que envía la solicitud
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Usuario que la recibe
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Friends> friends;
    @Column(name = "friend_count", nullable = false) //Columna para la cantidad de amigos
    private int friendCount;


    private String status; //Para indicar el estado de la Request


    //Contructor sin parametros
    public Request() {
    }

    //Contructor con parametros
    public Request(Integer id, User sender, User receiver, List<Friends> friends, int friendCount, String status) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.friends = friends;
        this.friendCount = friendCount;
        this.status = status;
    }

    //Sets & Gets
    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}//fin Request