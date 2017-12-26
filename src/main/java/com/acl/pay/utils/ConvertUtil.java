package com.acl.pay.utils;

import com.acl.pay.entity.FrontUserInfo;
import com.acl.pay.entity.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 15:14
 */
public class ConvertUtil {

    public static UserInfo convertUserInfo(FrontUserInfo userInfo) {
        UserInfo rstUser = new UserInfo();
        rstUser.setCh_id(userInfo.getCh_id());
        rstUser.setCh_name(userInfo.getCh_name());
        rstUser.setCe_id(userInfo.getCe_id());
        rstUser.setCe_name(userInfo.getCe_name());
        rstUser.setSettle_id(userInfo.getSettle_id());
        rstUser.setSettle_name(userInfo.getSettle_name());
        rstUser.setAgent_id(userInfo.getAgent_id());
        rstUser.setAgent_name(userInfo.getAgent_name());
        rstUser.setAgent_code(userInfo.getAgent_code());
        rstUser.setUser_id(userInfo.getUser_id());
        rstUser.setUser_name(userInfo.getUser_name());
        rstUser.setLogin_id(userInfo.getUser_login_id());
        rstUser.setMobile(MySecurity.decoderMobile(userInfo.getUser_mobile()));
        rstUser.setPassword(userInfo.getUser_password());
        rstUser.setIs_use(userInfo.getIs_use());
        rstUser.setYijipay_user_id(userInfo.getYijipay_user_id());
        return rstUser;
    }
}
