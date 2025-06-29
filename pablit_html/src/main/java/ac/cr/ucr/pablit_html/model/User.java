package ac.cr.ucr.pablit_html.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 25)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "weight", nullable = false)
    private double weight;
    @Column(name = "level", nullable = true)
    private int level;
    @Column(name = "age", nullable = false, length = 10)
    private int age;
    @Column(name = "sex", nullable = false, length = 25)
    private String sex;
    private  String rol;

    public User()
    {
    }

    public User(int id, String username, String password, double weight, int level, int age, String sex,String rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.level = level;
        this.age = age;
        this.sex = sex;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getRol() {   return rol;   }

    public void setRol(String rol) {  this.rol = rol;  }
}

