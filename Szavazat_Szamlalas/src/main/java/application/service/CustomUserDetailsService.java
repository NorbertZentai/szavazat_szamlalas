package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import application.dao.FelhasznaloDAO;
import application.model.Felhasznalo;

public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private FelhasznaloDAO felhasznaloDAO;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByUsername(username);
    if (felhasznalo == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return User.builder()
                .username(felhasznalo.getName())
                .password(felhasznalo.getPassword())
                .roles(felhasznalo.getRole())
                .build();
  }
}
