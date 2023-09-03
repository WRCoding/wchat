package com.longjunwang.wchathttp.service.mail;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.longjunwang.wchatcommon.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MailService {

    private static final MailAccount mailAccount = new MailAccount();
    private static final ThreadPoolExecutor sendMailExecutor = new ThreadPoolExecutor(2, 2, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    static {
        mailAccount.setHost("smtp.qq.com");
        mailAccount.setPort(465);
        mailAccount.setSslEnable(true);
        mailAccount.setSocketFactoryClass("javax.net.ssl.SSLSocketFactory");
        mailAccount.setFrom("pepsic@qq.com");
        mailAccount.setUser("pepsic@qq.com");
        mailAccount.setPass("aeyckdxsekntbeec");
    }

    public void sendMail(String email, String checkCode) {
        log.info("email: {}, checkCode: {}", email, checkCode);
        sendMailExecutor.execute(() -> {
            try {
                MailUtil.send(mailAccount, email, "WCHAT验证码", String.format("验证码: %s", checkCode), false);
            } catch (Exception e) {
                log.info("send mail[{}] error: {}",email, e.getMessage());
            }
        });
        System.out.println("aaa");
    }
}
