package com.lgb.arc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    @Value("${mail.smtp.host}")
    private static String maillHost;
    @Value("${mail.smtp.port}")
    private static String mailPort;
    @Value("${mail.smtp.starttls.enable}")
    private static boolean mailStarttlsEnable;
    @Value("${mail.smtp.auth}")
    private static String mailAuth;
    @Value("${mail.smtp.user}")
    private static String mailUser;
    @Value("${mail.smtp.pass}")
    private static String mailPass;

    private final static Logger LOG = LoggerFactory.getLogger(MailUtils.class);

    private final static Properties PROPERTIES = new Properties();

    /**
     * 向用户发送邮件
     *
     * @param toEmail 用户的邮件地址
     * @param subject 邮件标题
     * @param content 邮件包含的内容
     */
    public static void mailTo(String toEmail, String subject, String content) {
        Session session = Session.getDefaultInstance(PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUser, mailPass);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailPass));
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
