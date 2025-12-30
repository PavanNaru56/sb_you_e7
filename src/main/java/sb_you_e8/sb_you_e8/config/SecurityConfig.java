package sb_you_e8.sb_you_e8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
// @PreAuthorize,@PostAuthorize, @PreFilter, @PostFilter
//@Secured, @RolsAllowed
public class SecurityConfig {

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        return  http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests( c -> c.anyRequest().authenticated())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

        var u1 = User.withUsername("billy").password(passwordEncoder().encode("1234")).authorities("read").build();
        var u2 = User.withUsername("bill").password(passwordEncoder().encode("12345")).authorities("write").build();


        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

}
