package Application.service;

import Application.dao.FelhasznaloDAO;
import Application.model.Felhasznalo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private FelhasznaloDAO felhasznaloDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByEmail(username);
        
        if (felhasznalo == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        
        return (UserDetails) felhasznalo;
    }
}
