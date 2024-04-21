package com.appli_banking.bankingAPP.config;


import com.appli_banking.bankingAPP.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {
    /*  OncePerRequestFilter c'est a dire un filtre par requete   */
    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;
    private static final  String AUHTORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    @Override
    /*
    * filtrer toute requete entrée
    * */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUHTORIZATION);
        String userEmail = null;
        String jwt = null;

        SecurityContextHolder.getContext().setAuthentication(null);

        if (authHeader == null || !authHeader.startsWith(BEARER)){
           filterChain.doFilter(request, response);
           return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userRepository.findByEmail(userEmail)
                    .orElseThrow( ()-> new EntityNotFoundException("User not found while validation JWT"));
            /*
            * une fois on valide que le token est valide, on doit mettre à jours le context de securite
            * */
            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    //un filtre par requete


}
