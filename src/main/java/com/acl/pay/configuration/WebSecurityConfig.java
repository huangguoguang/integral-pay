package com.acl.pay.configuration;

import com.acl.pay.authentication.CustomUserService;
import com.acl.pay.authentication.filter.PaymentFilter;
import com.acl.pay.authentication.handler.PaymentFailureHandler;
import com.acl.pay.authentication.handler.PaymentSuccessHandler;
import com.acl.pay.authentication.handler.PaymentEntryPoint;
import com.acl.pay.authentication.provider.PaymentAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Athos on 2016-10-16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Bean
	PaymentAuthenticationProvider authenticationProvider() {
		PaymentAuthenticationProvider authenticationProvider = new PaymentAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserService());
		return authenticationProvider;
	}

	/**
	 * 认证进入点
	 * @return
	 */
	@Bean
	PaymentEntryPoint entryPoint() {
		PaymentEntryPoint entryPoint = new PaymentEntryPoint("/pay/login");
		return entryPoint;
	}

	@Bean
	PaymentSuccessHandler successHandler() {
		return new PaymentSuccessHandler();
	}

	@Bean
	PaymentFailureHandler failureHandler() {
		return new PaymentFailureHandler();
	}

	/**
	 * 自定义过滤器
	 * @return
	 * @throws Exception
	 */
	@Bean
	PaymentFilter paymentFilter() throws Exception {
		PaymentFilter paymentFilter = new PaymentFilter();
		paymentFilter.setAuthenticationManager(authenticationManager());
		paymentFilter.setFilterProcessesUrl("/pay/index");
		paymentFilter.setAuthenticationSuccessHandler(successHandler());
		paymentFilter.setAuthenticationFailureHandler(failureHandler());
		return paymentFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(paymentFilter(), BasicAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/pay/login").permitAll().anyRequest().authenticated();
		http.exceptionHandling().authenticationEntryPoint(entryPoint());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 自定义UserDetailsService
		auth.authenticationProvider(authenticationProvider());
		auth.eraseCredentials(false);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
