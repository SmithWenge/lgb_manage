package com.lgb.arc.utils;

public class ConstantFields {
    /**
     * 验证码默认设置
     */
    public static final String KAPTCHA_IMAGE_WITH_VALUE = "150";
    public static final String KAPTCHA_IMAGE_HEIGHT_VALUE = "50";
    public static final String KAPTCHA_TEXTPRODUCER_CHAR_STRING_VALUE = "abcdefgrstuvw0123456789hijklmnopqxyz";
    public static final String KAPTCHA_TEXTPRODUCER_CHAR_LENGTH_VALUE = "5";
    public static final String KAPTCHA_SESSION_KEY_VALUE = "KAPTCHA_SESSION_KEY";

    /**
     * 默认邮件配置项
     */
    public static final String MAIL_DEFAULT_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_DEFAULT_HOST = "smtp.163.com";
    public static final String MAIL_DEFAULT_AUTH_ENABLE_KEY = "mail.smtp.auth";
    public static final String MAIL_DEFAULT_AUTH_ENABLE = "true";
    public static final String MAIL_DEFAULT_PORT_KEY = "mail.smtp.port";
    public static final String MAIL_DEFAULT_PORT = "587";
    public static final String MAIL_DEFAULT_USER_KEY = "mail.smtp.user";
    public static final String MAIL_DEFAULT_USER = "1D8Z/peW+xIx4KnZnhOhRO/Gdfcjr7q6";
    public static final String MAIL_DEFAULT_USER_PASS_KEY = "mail.smtp.pass";
    public static final String MAIL_DEFAULT_USER_PASS = "uBGDLbYySJp3p5vx0UU0eH/a9Zgrnc0G";
    public static final String MAIL_DEFAULT_STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";
    public static final String MAIL_DEFAULT_STARTTLS_ENABLE = "true";

    /**
     * 管理员登陆
     */
    public static final String ADMIN_LOGIN_SESSION_KEY = "adminLogin";

    /* 管理员登陆页面地址 */
    public static final String ADMIN_LOGIN_ROUTER_LOCATION = "/adminLogin?method=routeLogin";
    /* 管理员激活状态数字 */
    public static final int ADMIN_ACTIVE_STATE_CODE = 1;
}
