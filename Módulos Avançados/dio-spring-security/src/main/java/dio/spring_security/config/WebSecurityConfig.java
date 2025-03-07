package dio.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                .authorizeHttpRequests(authorize -> {
                            try {
                                authorize
                                        .requestMatchers("/").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                        .requestMatchers("/managers").hasAnyRole("MANAGERS")
                                        .requestMatchers("/users").hasAnyRole("USERS", "MANAGERS")
                                        .anyRequest().authenticated().and().formLogin();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) throws Exception{
        UserDetails user = User.withUsername("user")
                .password("{noop}user123") // {noop} para indicar que a senha não será criptografada
                .roles("USERS") // Papel do usuário
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}master123")
                .roles("MANAGERS") // Papel do administrador
                .build();

        // Retorna o InMemoryUserDetailsManager com os dois usuários configurados.
        return new InMemoryUserDetailsManager(user, admin);
    }
     */
}