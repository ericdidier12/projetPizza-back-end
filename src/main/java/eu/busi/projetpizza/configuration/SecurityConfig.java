package eu.busi.projetpizza.configuration;

import eu.busi.projetpizza.filter.JwtAuthenticationFilter;
import eu.busi.projetpizza.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_REQUEST = "/login";

    private static final String[] AUTHORIZED_REQUESTS_ANYBODY = new String[]{
            "/home",
            "/pizza/ajouterAuPanier",
            "/pizza",
            "/cart",
            "/cart/sendDelete",
            "/cart/sendSubstract",
            "/cart/sendAdd",
            "/user/register/send",
            "/user/register",
            "/pizza/trieCategorieByName/american",
            "/pizza/trieCategorieByName/of-the-sea",
            "/pizza/trieCategorieByName/normal",
            "/pizza/ajouterAuPanierPizzaCustom",
            "/admin/ajouterAuPanier"

            };


    private static final String[] AUTHORIZED_REQUESTS_ADMIN = new String[]{
            "/home",
            "/admin",
            "/pizza/",
            "/pizza/ajouterAuPanier",
            "/users",
            "/user/register",
            "/admin/manage-stock" ,
            "/admin/manage-order",
            "/pizza/trieCategorieByName/normal",
            "/pizza/trieCategorieByName/of-the-sea",
            "/pizza/trieCategorieByName/american",
            "/pizza/ajouterAuPanierPizzaCustom",
            "/admin/ajouterAuPanier"
    };
    private static final String[] AUTHORIZED_REQUESTS_USER = new String[]{
            "/home",
            "/pizza",
            "/user/paiement",
            "/user/commande",
            "/user/pizza",
            "/pizza/ajouterAuPanier",
            "/pizza/trieCategorieByName/normal",
            "/pizza/trieCategorieByName/of-the-sea",
            "/pizza/trieCategorieByName/american",
            "pizza/ajouterAuPanierPizzaCustom",
                   "/admin/ajouterAuPanier"
    };
    String[] staticResources = {
            "/css/**",
            "/jquery-ui/**",
            "/images/**",
            "/fonts/**",
            "/vendor/**",
            "/scripts/**",
    };

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


       http.csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers(AUTHORIZED_REQUESTS_ADMIN).hasRole("ADMIN")
//                .antMatchers(AUTHORIZED_REQUESTS_USER).hasRole("USER")
//                .antMatchers(AUTHORIZED_REQUESTS_ANYBODY).permitAll()
//                .antMatchers(staticResources).permitAll()
//                .anyRequest().authenticated()
//
//                .and()
//                .formLogin()
//                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
//                .loginPage(LOGIN_REQUEST)
//                .defaultSuccessUrl("/home")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/home")
//                .permitAll();

        http
                .authorizeRequests()

                .antMatchers("/api/user/**").hasAnyRole("ROLE_USER")
                .antMatchers("/api/admin/**").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
               .authenticationEntryPoint((request, response, e) -> response.sendError(401))
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))

                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
                http.csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // important permet de desactive la session.
                .and().headers().frameOptions().disable();


    }






    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}