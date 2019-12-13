package com.pipilu.ggshop.service.impl;

import com.pipilu.ggshop.bean.User;
import com.pipilu.ggshop.redis.MybatisRedisCache;
import com.pipilu.ggshop.service.EmailService;
import com.pipilu.ggshop.service.UserService;
import com.pipilu.ggshop.util.GGUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
@Transactional
public class EmailServiceImpl implements EmailService {

    private MybatisRedisCache mybatisRedisCache = new MybatisRedisCache("");

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Override
    public String getEmailToken(String username){
        String uuid = UUID.randomUUID().toString();
        mybatisRedisCache.putObject("UUID",uuid);
        mybatisRedisCache.putObject("username",username);
        return uuid;
    }

    @Override
    public boolean balanceToken(String emailToken) {
        if(mybatisRedisCache.getObject("UUID").equals(emailToken)){
            mybatisRedisCache.clear("UUID");
            String username = (String) mybatisRedisCache.getObject("username");
            userService.updateUserPara1(username);
            mybatisRedisCache.clear("username");
            return true;
        }
        return false;
    }

    @Override
    public boolean sendSimpleMail(String email, User user) throws Exception {
        user.setRole(GGUtil.ROLE_1);
        user.setPara1(GGUtil.STATE_0);
        userService.insertUser(user);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom("lu_x_k@163.com");
        helper.setTo(email);
        helper.setSubject("主题：邮件激活码");
        String uuid = getEmailToken(user.getUsername());
        String url = "<a href='http://localhost:8080/activateMail?emailToken="+uuid+"'>http://localhost:8080/激活?emailToken=激活"+"</a></br>" +
                "<h1>如果以上超连接无法访问，请将以下网址复制到浏览器地址栏中</h1><h2>http://localhost:8088/activateMail?emailToken="+uuid+"</h2>";
        helper.setText(url,true);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚，try-catch中需要手动回滚
            return false;
        }
        return true;
    }
}
