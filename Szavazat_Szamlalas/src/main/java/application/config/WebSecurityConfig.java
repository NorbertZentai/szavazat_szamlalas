package application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import application.service.CustomUserDetailsService; 


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      // Engedélyezett URL-ek a nem bejelentkezettek számára
      .antMatchers("/", "/login", "/register", "/css/**").permitAll()
      
      // Admin oldal elérése csak admin szerepkörűeknek
      .antMatchers("/admin/**").hasRole("ADMIN")
      
      // Bejelentkezett felhasználóknak elérhető oldal
      .antMatchers("/profil/**", "/szavazas/**", "/szavazasok/**", "/ujszavazas", "/ujjelolt").authenticated()
      
      // Alapértelmezett bejelentkezés
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login") // Bejelentkezés oldalt itt állítjuk be
      .permitAll()
      .and()
      .logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).clearAuthentication(true).permitAll();
  }

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {}

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

}
