package com.acl.pay.authentication;

import com.acl.pay.entity.FrontUserInfo;
import com.acl.pay.entity.UserInfo;
import com.acl.pay.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-18 15:46
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try {
            UserInfo userInfo = userInfoService.queryUserInfoInMongodb(userId);
            SecurityUser securityUser = new SecurityUser(userInfo);
            return securityUser;
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户信息错误");
        }
    }
}
