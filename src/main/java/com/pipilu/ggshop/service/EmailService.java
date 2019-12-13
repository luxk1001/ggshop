package com.pipilu.ggshop.service;

import com.pipilu.ggshop.bean.User;

/**
 * @ClassName EmailService
 * @Description TODO
 * @Author 陆显坤
 * @Date 2019/12/12 14:14
 * @Version 1.0
 **/
public interface EmailService {
    /**
     * 发送邮箱激活,入参为需要接收邮件的地址
     * */
    boolean sendSimpleMail(String email, User user) throws Exception ;
    /**
     * 获取uuid并且存入redis缓存中，用于对比是否成功激活
     * */
    String getEmailToken(String username);
    /**
     * 检查uuid是相同，相同则激活成功
     * */
    boolean balanceToken(String emailToken);
}
