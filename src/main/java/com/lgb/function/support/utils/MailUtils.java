package com.lgb.function.support.utils;

import com.lgb.arc.utils.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource("classpath:mail/mail.properties")
public class MailUtils {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value(value = "${mail.host}")
    private String host;
    @Value(value = "${mail.user}")
    private String user;
    @Value(value = "${mail.pass}")
    private String pass;
    @Value(value = "${mail.port}")
    private String port;
    @Value(value = "${mail.starttls.enable}")
    private String starttls;
    @Value(value = "${mail.auth}")
    private String auth;

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
        PROPERTIES.setProperty(ConstantFields.MAIL_DEFAULT_HOST_KEY, host);
        PROPERTIES.setProperty(ConstantFields.MAIL_DEFAULT_PORT_KEY, port);
        PROPERTIES.setProperty(ConstantFields.MAIL_DEFAULT_USER_KEY, user);
        PROPERTIES.setProperty(ConstantFields.MAIL_DEFAULT_AUTH_ENABLE_KEY, auth);
        PROPERTIES.setProperty(ConstantFields.MAIL_DEFAULT_STARTTLS_ENABLE_KEY, starttls);
        PROPERTIES.setProperty(ConstantFields.MAIL_DEFAULT_USER_PASS_KEY, pass);

        Session session = Session.getDefaultInstance(PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
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
