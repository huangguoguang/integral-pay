package com.acl.pay.authentication.provider;

import com.acl.pay.authentication.SecurityUser;
import com.acl.pay.enums.PaymentExceptionEnum;
import com.acl.pay.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-25 14:19
 */
public class PaymentAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

//    private UserDetailsChecker preAuthenticationChecks = new PaymentAuthenticationProvider.DefaultPreAuthenticationChecks();
//    private UserDetailsChecker postAuthenticationChecks = new PaymentAuthenticationProvider.DefaultPostAuthenticationChecks();
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getPrincipal() == null ? "" : authentication.getPrincipal().toString();
        String password = authentication.getCredentials() == null ? "" : authentication.getCredentials().toString();
        SecurityUser user = null;
        if (user == null) {
            try {
                user = (SecurityUser) retrieveUser (userId, (PreAuthenticatedAuthenticationToken) authentication);
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
//
//
//        postAuthenticationChecks.check(user);

        Object principalToReturn = user;

        return createSuccessAuthentication(principalToReturn, authentication, user);
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        PreAuthenticatedAuthenticationToken result = new PreAuthenticatedAuthenticationToken(principal, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    protected final UserDetails retrieveUser(String userId, PreAuthenticatedAuthenticationToken authentication) throws CustomException {
        UserDetails loadedUser;
        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(userId);
        } catch (Exception ex) {
            throw new CustomException(PaymentExceptionEnum.USERINFO_IS_ERROR);
        }
        if (loadedUser == null) {
            throw new CustomException(PaymentExceptionEnum.USERINFO_IS_ERROR);
        }
        return loadedUser;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
        private DefaultPostAuthenticationChecks() {
        }

        public void check(UserDetails user) {
            if(!user.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(PaymentAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
            }
        }
    }

//    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
//        private DefaultPreAuthenticationChecks() {
//        }
//
//        public void check(UserDetails user) {
//            if(!user.isAccountNonLocked()) {
//                throw new LockedException(PaymentAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
//            } else if(!user.isEnabled()) {
//                throw new DisabledException(PaymentAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
//            } else if(!user.isAccountNonExpired()) {
//                throw new AccountExpiredException(PaymentAuthenticationProvider.this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
//            }
//        }
//    }
//
//    protected void additionalAuthenticationChecks(UserDetails userDetails) throws AuthenticationException {
//    }
}
