package Spring_secur_data.Pract_data_mySQl.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class securConfigure {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);  // Set UserDetailsService to load user data
        provider.setPasswordEncoder(passwordEncoder());      // Set PasswordEncoder to hash passwords
        return provider;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
                .csrf().disable()// Enables CSRF protection
                .authorizeRequests()
                .requestMatchers("api/control/register","api/control/login").permitAll()
                .requestMatchers("/api/control/csrf").permitAll()// Permit access to the "register" endpoint
                .anyRequest().authenticated() // Require authentication for all other requests
                .and().httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)// Use Basic Authentication
                .build();


    }


    @Bean

   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customuserDetails)
//                .passwordEncoder(passwordEncoder());
//    }
}
