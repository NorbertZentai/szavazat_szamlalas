package application.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public class Felhasznalo implements UserDetails{
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Date legutobbiBelepes;

    public Felhasznalo() {}

    public Felhasznalo(String nev, String email, String jelszo) {
        this.name = nev;
        this.email = email;
        this.password = jelszo;
        this.role = "ROLE_USER";
    }

    public Felhasznalo(int id, String felhasznalonev, String email, String jelszo) {
        this.id = id;
        this.name = felhasznalonev;
        this.email = email;
        this.role = "ROLE_USER";
    }

    // Getters és setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String felhasznalonev) { this.name = felhasznalonev; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String jelszo) { this.password = jelszo; }

    public Date getLegutobbiBelepes() { return legutobbiBelepes; }
    public void setLegutobbiBelepes(Date legutobbiBelepes) { this.legutobbiBelepes = legutobbiBelepes; }

    public String getRole() { return role; }
    public void setRole(String szerep) { this.role = szerep; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
