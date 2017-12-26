package com.acl.pay.controller;

import com.acl.pay.authentication.SecurityUser;
import com.acl.pay.entity.UserInfo;
import com.acl.pay.service.UserInfoService;
import com.acl.pay.utils.PaymentUtil;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
 * DATE : 2017-12-18 11:44
 */
@Slf4j
@RestController
@RequestMapping(value = "pay")
public class PaymentController {
    private String PAGE_ROOT = "/debug/";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "login")
    public void entryLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SecurityUser) {
                log.info("用户认证成功");
                UserInfo userinfo = ((SecurityUser) principal).getUserInfo();
                response.sendRedirect("http://www.baidu.com");
                return;
            }
        }
        response.sendRedirect("/wp_pay_3.0/pay/index");
    }


    @RequestMapping(value = "success")
    public String success() {
        return "success";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/p/main")
    public String getLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store");
        return "forward:" + PAGE_ROOT + "index.html";
    }

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/user/info")
    public Object getQueryUserInfo() {
        // 判断用户是否登录
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SecurityUser) {
                UserInfo cacheUser = ((SecurityUser) principal).getUserInfo();
                // 刷新用户信息
                userInfoService.refreshUserInfo(cacheUser);
                Map<String, Object> infoMap = cacheUser.convertToMap();
                Map<String, Object> addrMap = userInfoService.getDefaultAddr(cacheUser.getUser_id());
                if (addrMap == null) {
                    infoMap.put("userAddr", "");
                } else {
                    infoMap.put("userAddr", addrMap);
                }
                return infoMap;
            }
        }
        return new HashMap<String, Object>();
    }
}
