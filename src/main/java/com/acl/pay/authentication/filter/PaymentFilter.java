package com.acl.pay.authentication.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-22 13:53
 */
public class PaymentFilter extends AbstractAuthenticationProcessingFilter  {

    public PaymentFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        PreAuthenticatedAuthenticationToken authRequest = new PreAuthenticatedAuthenticationToken("94BF70B561904A5994F8022856718BE2", "111111");
        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return authentication;
    }
}
