package io.mafenandaup.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // PELO AMOR DE DEUS TIRA ISSO QUANDO ESTIVER EM PROD
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/users/**").permitAll() //alterar essas autorizações conforme o projeto se desenvolve
                        .requestMatchers("/pedidos/**").permitAll() //não permitir acesso à rotas de edição pra certos models (tipo DELETE usuários)
                        .requestMatchers("/produto/**").permitAll()


                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/cliente/**").hasRole("CLIENTE")
                        .requestMatchers("/representante/**").hasRole("REPRESENTANTE")
                        .anyRequest().permitAll() // Tudo liberado por enquanto

//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // depois descomentar quando existir uma auth real
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
                );
        return http.build();
    }
}
