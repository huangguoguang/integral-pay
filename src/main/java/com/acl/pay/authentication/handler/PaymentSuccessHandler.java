package com.acl.pay.authentication.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-25 11:35
 */
public class PaymentSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private static final Logger logger = LoggerFactory.getLogger(PaymentSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
		System.out.println("success");

		redirectStrategy.sendRedirect(request, response, "/pay/success");
		Map<String, String> rstMap = new HashMap<String, String>();
		rstMap.put("statusCode", "000000");
		rstMap.put("message", "index");

		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(JSONObject.toJSONString(rstMap));
		response.getWriter().flush();
	}

}
