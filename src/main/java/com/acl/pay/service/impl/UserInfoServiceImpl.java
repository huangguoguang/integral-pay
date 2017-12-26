package com.acl.pay.service.impl;

import com.acl.pay.entity.FrontUserInfo;
import com.acl.pay.entity.UserInfo;
import com.acl.pay.enums.PaymentExceptionEnum;
import com.acl.pay.exception.CustomException;
import com.acl.pay.mongodb.dao.MongodbBaseDao;
import com.acl.pay.service.UserInfoService;
import com.acl.pay.utils.ConvertUtil;
import com.acl.pay.utils.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-19 11:57
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MongodbBaseDao<FrontUserInfo> userMongodbDao;

    @Override
    public void refreshUserInfo(UserInfo cacheUser) {
        String walletText = redisTemplate.opsForValue().get("wallet_" + cacheUser.getUser_id()); // 用户钱包
        if (walletText == null) {
            cacheUser.setBalance(Double.valueOf(0));
        } else {
            cacheUser.setBalance(NumberUtil.ROUND_DOWN(2, Double.valueOf(walletText)));
        }
    }

    @Override
    public UserInfo queryUserInfoInMongodb(String userId) throws Exception {
        List<FrontUserInfo> userInfos = null;
        if (StringUtils.isBlank(userId)) {
            throw new CustomException(PaymentExceptionEnum.PARAM_IS_NULL);
        }
        // 手机号查询
        userInfos = userMongodbDao.find("user_id", userId, FrontUserInfo.class, "t_wp_front_user_login");
        if (userInfos == null || userInfos.isEmpty()) {
            throw new CustomException(PaymentExceptionEnum.USERINFO_IS_ERROR);
        }
        return ConvertUtil.convertUserInfo(userInfos.get(0));
    }

    @Override
    public Map<String, Object> getDefaultAddr(String userId) {
        return null;
    }

    @Override
    public String getTitle(UserInfo userInfo) throws Exception {
        return null;
    }

    @Override
    public String userInfoByAccessToken(String accessToken) {
        return null;
    }
}
