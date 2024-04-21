package com.appli_banking.bankingAPP.config;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //pour indiquer que cette classe est une configuration de securite(activer le web security)
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final  JwtAuthentificationFilter jWtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable() //désactiver le csrf
                .authorizeRequests((request) -> {
                    try {
                        request.antMatchers("/**/authenticate", "/**/register")//requetes autoriser sans authentification
                                .permitAll()//les URLs spécifiées précédemment seront accessibles a tous les users(authentifiés ou non)
                                .anyRequest()  //toute autre requete
                                .authenticated() //cela indique que toutes les autres requetes necissite authentification
                                .and() // une methode qui permet de combiner plusieurs configurations de sécurité
                                .sessionManagement() //configurer la gestion des sessions
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //authentification est valide pour une seule requete
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ).authenticationProvider(authenticationProvider()) //le fournisseur d'authentification
                .addFilterBefore(jWtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                //indiquer a Spring qu'on veut ajouter un filtre avant d'executer usernamepassword pour authentification
        return httpSecurity.build();
    }

  //  @Bean
    public CorsFilter corsFilter() {
        //todo to be implemented
        return null;
    }

    @Bean
    //AuthenticationManager pour assurer l'authentification au niveau de
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() { //chercher les informtations des utilisateur et l'encodage de password
        DaoAuthenticationProvider authenticationProvider1 = new DaoAuthenticationProvider();
        authenticationProvider1.setUserDetailsService(userDetailsService);
        authenticationProvider1.setPasswordEncoder(passwordEncoder());
        return authenticationProvider1;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
