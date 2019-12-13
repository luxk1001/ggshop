package com.pipilu.ggshop.service.impl;

import com.pipilu.ggshop.redis.MybatisRedisCache;
import com.pipilu.ggshop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.UUID;

/**
 * @ClassName EmailServiceImpl
 * @Description TODO
 * @Author 陆显坤
 * @Date 2019/12/12 14:17
 * @Version 1.0
 **/
@Service
public class EmailServiceImpl implements EmailService {

    private MybatisRedisCache mybatisRedisCache = new MybatisRedisCache("");

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String getEmailToken(){
        String uuid = UUID.randomUUID().toString();
        mybatisRedisCache.putObject("UUID",uuid);
        return uuid;
    }

    @Override
    public boolean balanceToken(String emailToken) {
        if(mybatisRedisCache.getObject("UUID").equals(emailToken)){
            mybatisRedisCache.clear("UUID");
            return true;
        }
        return false;
    }

    @Override
    public void sendSimpleMail(String email) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("lu_x_k@163.com");
        helper.setTo(email);
        helper.setSubject("主题：邮件激活码");
        String uuid = getEmailToken();
        String url = "<a href='http://localhost:8080/activateMail?emailToken="+uuid+"'>http://localhost:8080/激活?emailToken=激活"+"</a></br>" +
                "<h1>如果以上超连接无法访问，请将以下网址复制到浏览器地址栏中</h1><h2>http://localhost:8088/activateMail?emailToken="+uuid+"</h2>";
        helper.setText(url,true);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("出错啦出错啦");
        }
    }
}
