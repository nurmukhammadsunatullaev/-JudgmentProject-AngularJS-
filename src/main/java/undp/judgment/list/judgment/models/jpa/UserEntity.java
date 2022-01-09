package undp.judgment.list.judgment.models.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "usert")
public class UserEntity implements UserDetails {
    @Id
    @Column(name="id_")
    private Integer userId;

    @Column(name = "password_")
    private String password;

    @Column(name = "login_")
    private String username;

    @ManyToOne
    @JoinColumn(name = "roleid_")
    private RoleEntity role;

    @Column(name = "datetermination_")
    private Timestamp blockedDate;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getBlockedDate() {
        return blockedDate;
    }

    public void setBlockedDate(Timestamp blockedDate) {
        this.blockedDate = blockedDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ImmutableList.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return blockedDate==null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return blockedDate==null;
    }

    @Override
    public String toString() {
        return String.format("%s, %s",username,password);
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public RoleEntity getRole() {
        return role;
    }
}
