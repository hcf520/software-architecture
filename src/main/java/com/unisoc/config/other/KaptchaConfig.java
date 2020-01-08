package com.unisoc.config.other;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class KaptchaConfig {

	@Bean
	public DefaultKaptcha producer() {
		Properties properties = new Properties();
		properties.put("kaptcha.border", "no");
		properties.put("kaptcha.textproducer.font.color", "0,150,136");
		properties.put("kaptcha.textproducer.char.space", "5");//文字间隔
		// 如果需要生成算法验证码加上一下配置
		properties.put("kaptcha.textproducer.char.string", "1234567890");
		// 如果需要去掉干扰线
		properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
		properties.put("kaptcha.image.width", "150");
		properties.put("kaptcha.image.height", "38");
		properties.put("kaptcha.textproducer.font.size", "30");
		Config config = new Config(properties);
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}

}
