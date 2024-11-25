package Application.config;

import Application.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Új lambda alapú szintaxis a hozzáférés-szabályozáshoz
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/login", "/register", "/registerAdd").permitAll()  // Engedélyezzük a nyilvános URL-eket
                .requestMatchers("/admin/**").hasRole("ADMIN")            // Csak adminok férhetnek hozzá az admin URL-ekhez
                .requestMatchers("/profil/**").hasRole("USER")           // Csak felhasználók férhetnek hozzá a profilhoz
                .requestMatchers("/szavazas/**", "/szavazasok/**").hasRole("USER")  // Szavazáshoz csak felhasználók férhetnek hozzá
                .anyRequest().authenticated()                            // Minden más kérés autentikációt igényel
            )
            // Bejelentkezési konfiguráció
            .formLogin(form -> form
                .loginPage("/login")    // Bejelentkezési oldal
                .defaultSuccessUrl("/") // Sikeres bejelentkezés után az alapértelmezett oldal
                .permitAll()            // A bejelentkezéshez mindenki hozzáfér
            )
            // Kijelentkezési konfiguráció
            .logout(logout -> logout
                .logoutSuccessUrl("/")  // Kijelentkezés után az alapértelmezett oldalra visszairányít
                .permitAll()            // A kijelentkezéshez mindenki hozzáfér
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
