package com.lgb.function.support.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailUtils {
    @Value("${mail.host}")
    public String mailHost;
    @Value("${mail.user}")
    public String mailUser;
    @Value("${mail.pass}")
    public String mailPass;

    private final static Logger LOG = LoggerFactory.getLogger(MailUtils.class);

    private final static Properties PROPERTIES = new Properties();

    /**
     * 向用户发送邮件
     *
     * @param toEmail 用户的邮件地址
     * @param subject 邮件标题
     * @param content 邮件包含的内容
     */
    public void mailTo(String toEmail, String subject, String content) {
        Session session = Session.getDefaultInstance(PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, mailPass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailHost));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);

            if (LOG.isInfoEnabled())
                LOG.info("[OK] Send mail to {} to reset password.", toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            if (LOG.isErrorEnabled())
                LOG.error("[ERROR] Send mail to {} to reset password.", toEmail);
        }
    }
}
