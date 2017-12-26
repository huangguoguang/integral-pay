package com.acl.pay.authentication;

import com.acl.pay.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-18 15:54
 */
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = -3228542575988611250L;

    private UserInfo userInfo;

    private final boolean accountNonExpired; // 帐户是否未过期
    private final boolean accountNonLocked; // 帐户是否未锁定
    private final boolean credentialsNonExpired; // 凭据是否未过期
    private final boolean enabled; // 是否可用

    public SecurityUser(UserInfo userInfo) {
        this(userInfo, true, true, true ,true);
    }

    public SecurityUser(UserInfo userInfo, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.userInfo = userInfo;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    /**
     * 配置用户和角色的关系
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths =new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auths;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
