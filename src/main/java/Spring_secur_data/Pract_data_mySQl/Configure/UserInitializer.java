package Spring_secur_data.Pract_data_mySQl.Configure;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInitializer {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTservice jwTservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User addUsers(User user) {
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(User user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
        {
            return jwTservice.generateToken(user.getUsername(), user);
        }

        return "failure";
    }
}

