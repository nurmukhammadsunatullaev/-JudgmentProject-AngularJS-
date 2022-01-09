package undp.judgment.list.judgment.models.jpa;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rolet")
public class RoleEntity implements GrantedAuthority {
    @Id
    @Column(name="id_")
    private Integer id;

    @Column(name = "name_")
    private String authority;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
