package eu.busi.projetpizza.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import eu.busi.projetpizza.dataAcces.entity.UserEntity;
import eu.busi.projetpizza.model.Constants;
import eu.busi.projetpizza.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * <p>    Classe qui permets de définir des filtres ( custom filter dans la configuration http de spring-security) qui vont être activés en fonction du choix du
 * <br/>  type d’authentification fait pendant la configuration de Spring.</p>
 * <p>
 * <br/>  posseder deux methodes attemptAuthentication, successfulAuthentication et on  hérite de
 * <br/>  UsernamePasswordAuthenticationFilter ( Ce filtre traite la soumission du formulaire d’authentification.
 * <br/> Ce formulaire doit présenter deux paramètres username et password) et aussi verifier si la requete contient le token(JWT) qui sera signé.
 * <br/>
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private  String secret;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        setFilterProcessesUrl("/api/login");
        this.authenticationManager = authenticationManager;
    }

    /**
     * <br/> Effectue l'authentification réelle (Au moment ou l'utilisateur tente des authentifier)
     * <br/> recupere username+passWord sous forme d'objet Authentication
     *
     * @param request
     * @param response <br/>
     * @return L'objet Authentication, contenant les informations de l’utilisateur ayant demandé la ressource, est authentifié grâce à l’AuthenticationManager
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        User user = null ;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            System.out.println("******************************");
            System.out.println("username :" + user.getUsername());
            System.out.println("password :" + user.getPassword());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.emptyList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <br/> Comportement par défaut pour une authentification réussie.
     * <br/> genere le token , le temps d'expiration de ce token  et qui sera signé(certifier) par code secret.
     * <p>
     * <br/>  les etapes : call build JWt en lui donnant le claims quon fait (le role)
     * <br/>  associe à une date d'expiration et un mot de passe
     * <br/>  dans la reponse http on va add une entete Appelé "Authorization", et 1 prefixe "Bearer" + le token
     * <br/>
     *
     * @param request
     * @param  response
     * @param chain      Objet qui accompagne sringSecurity
     * @param authResult l'objet renvoyé par la méthode attemptAuthentication .
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((UserEntity) authResult.getPrincipal()).getUsername()) // cest quoi le sujet de token, je recupere username grace à l'ojet Authentication
                .claim("authorities", authResult.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())) // comme claims son nom est "authorities"  je recupere le role
                .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME)) //delait expiration
                .signWith(SignatureAlgorithm.HS512,Constants.SECRET) // Je vais signe se token avec le mot de passe HS512 + le mot de passe
                .compact();
        response.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX+token);
    }
}
