package application.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.dao.FelhasznaloDAO;
import application.model.Felhasznalo;

@Controller
public class AuthController {

    @Autowired
    private FelhasznaloDAO felhasznaloDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/customlogin")
    public String loginUser(@RequestParam("username") String username, 
                            @RequestParam("password") String password, 
                            HttpSession session) {
        Felhasznalo user = felhasznaloDao.getFelhasznaloByUsername(username);

        if (user != null && passwordEncoder.matches(password.trim(), user.getPassword())) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            session.setAttribute("user", user);

            felhasznaloDao.updateLastLogin(user.getId(), new Date(System.currentTimeMillis()));

            return "redirect:/";
        } else {
            return "redirect:/login?error=invalid_credentials";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping(value = "/registeruser")
    public String registerUser(@RequestParam("name") String name, 
                                @RequestParam("email") String email, 
                                @RequestParam("password") String password, 
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "A két jelszó nem egyezik!");
            return "register";
        }
        
        Felhasznalo existingUser = felhasznaloDao.getFelhasznaloByUsername(name);
        if (existingUser != null) {
            model.addAttribute("error", "Ez az email cím már regisztrálva van!");
            return "register";
        }
        
        Felhasznalo newUser = new Felhasznalo(name, email, passwordEncoder.encode(password));
        felhasznaloDao.insertFelhasznalo(newUser);

        model.addAttribute("success", "Sikeresen regisztráltál! Jelentkezz be.");
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
    public String logoutUser(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/login?logout";
    }
}
