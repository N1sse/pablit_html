package ac.cr.ucr.pablit_html.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_Friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String age;
    private String sex;
    private Integer level;


    @ManyToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private Request request;  // Un amigo esta en una solicitud


    public Friends() {
    }

    public Friends(Integer id, String name, String age, String sex, Integer level) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.level = level;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Request getRequest() { return request; }

    public void setRequest(Request request) { this.request = request;}
}
