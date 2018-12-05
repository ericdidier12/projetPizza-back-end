package eu.busi.projetpizza.filter;

import eu.busi.projetpizza.dataAcces.entity.Authority;
import eu.busi.projetpizza.model.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <br/>
 * <p>  Classe qui va intervenir pour chaque requete dont ( toute Demande une resource necessitant l'autgentification)
 * <br/> a chaque fois  vous envoyez une requette ce filtre va analayse cette requête.</p>
 * <br/>
 * <p>
 * created by  eric.nyandwi on dec,01/11/2018
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger LOG = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * </br>
     * </br>   Permet d'intervenir (verifier) si le token est correct ou pas  pour donner acces aux donnnées
     * </br>
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader("Authorization");
        LOG.info("token reçu : " + header);
        if (header == null || !header.startsWith("Bearer ")) { // on verifier si le token est null ou ne contient pas le prefix defini je quitte
            chain.doFilter(request, response);
            return;
        }

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
       else {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
            SecurityContextHolder.getContext().setAuthentication(authentication); // dans le context de security de spring je vais charge utilisateur authentifier

           chain.doFilter(request, response);

        }


    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {

        Claims claims = Jwts.parser()
                .setSigningKey(Constants.SECRET)
                .parseClaimsJws(header.replace("Bearer ", ""))
                .getBody();// il me recupere le contenu de mon token

        String username = claims.getSubject();// recupere username
        List<String> authorities = claims.get("authorities", List.class);//  je les roles qui a été cupèré à partir de mon token

        List<Authority> auths = authorities.stream().map(Authority::new).collect(Collectors.toList());// je le parcour pour le recupe chaque role
        return new UsernamePasswordAuthenticationToken(username, null, auths);
    }
}