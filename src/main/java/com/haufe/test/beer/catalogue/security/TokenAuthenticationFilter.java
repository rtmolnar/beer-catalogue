package com.haufe.test.beer.catalogue.security;

import com.haufe.test.beer.catalogue.domain.authentication.User;
import com.haufe.test.beer.catalogue.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter
{

    private TokenService tokenService;
    private UserRepository adminRepository;

    public TokenAuthenticationFilter(TokenService tokenService, UserRepository adminRepository)
    {
        this.tokenService = tokenService;
        this.adminRepository = adminRepository;
    }


    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String token = retrieveToken(request);
        boolean isValid = tokenService.isTokenValid(token);

        if (isValid)
        {
            authClient(token);
        }

        filterChain.doFilter(request, response);
    }


    private void authClient(String token)
    {
        Long adminId = tokenService.getUserId(token);
        User admin = adminRepository.findById(adminId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private String retrieveToken(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty())
        {
            return null;
        }

        return token.substring(7);
    }
}
