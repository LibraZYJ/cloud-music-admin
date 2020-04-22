package com.soft1851.music.admin.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Yujie_Zhao
 * @ClassName CaptchaConfig
 * @Description Captcha配置类
 * @Date 2020/4/21  11:02
 * @Version 1.0
 **/
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties  properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.length","6");
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color","105,180,90");
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.textproducer.font.size","30");
        properties.setProperty("kaptcha.textproducer.font.names","微软雅黑,楷体,宋体");
        properties.setProperty("kaptcha.image.width","120");
        properties.setProperty("kaptcha.image.height","45");
        properties.setProperty("kaptcha.session.key", "code");
        Config config = new Config(properties);
        //set注入
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
