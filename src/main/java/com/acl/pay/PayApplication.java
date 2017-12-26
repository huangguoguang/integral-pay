package com.acl.pay;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * Description:
 * User : huangguang
 * DATE : 2017-12-18 11:09
 */

@SpringBootApplication
public class PayApplication extends AbstractSecurityWebApplicationInitializer{
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PayApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }
}
