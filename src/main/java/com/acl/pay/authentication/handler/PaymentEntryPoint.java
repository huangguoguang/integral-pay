package com.acl.pay.authentication.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-25 11:35
 */
public class PaymentEntryPoint implements AuthenticationEntryPoint{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String entryUrl;

    public PaymentEntryPoint(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    public String getEntryUrl() {
        return entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        redirectStrategy.sendRedirect(request, response, entryUrl);
    }
}
