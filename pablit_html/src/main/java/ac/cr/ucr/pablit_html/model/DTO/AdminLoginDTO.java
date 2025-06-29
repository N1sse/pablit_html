package ac.cr.ucr.pablit_html.model.DTO;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

public class AdminLoginDTO{

    private String username;
    private String password;

    public AdminLoginDTO() {
    }

    public AdminLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
