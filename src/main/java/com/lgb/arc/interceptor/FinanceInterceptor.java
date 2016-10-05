package com.lgb.arc.interceptor;

import com.google.common.base.Optional;
import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.login.AdminUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FinanceInterceptor implements HandlerInterceptor {
    private List<String> excludedUris;

    public void setExcludedUris(List<String> excludedUris) {

        this.excludedUris = excludedUris;
    }

    public List<String> getExcludedUris() {

        return excludedUris;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestUri = httpServletRequest.getRequestURI();

        for (String uri : excludedUris) {
            if (requestUri.endsWith(uri)) {
                return true;
            }
        }

        HttpSession session = httpServletRequest.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute(ConstantFields.SESSION_ADMIN_KEY);
        Optional<AdminUser> optional = Optional.fromNullable(adminUser);
        /* 判断用户是不是财务，只有财务才可以*/
        if (optional.isPresent()) {
            if (adminUser.getAdminRole() != ConstantFields.ADMIN_LOGIN_CAIWU_ROLE) {
                session.removeAttribute(ConstantFields.SESSION_ADMIN_KEY);

                String redirectLocation = httpServletRequest.getContextPath() + "/admin/routeFinanceLogin.action";
                httpServletResponse.sendRedirect(redirectLocation);
                return false;
            } else {
                return true;
            }
            /* 用exceptionMapping中确定重定向 */
//            throw new LoginException("管理员没有登陆,请登录管理员.");
        }

        String redirectLocation = httpServletRequest.getContextPath() + "/admin/routeLogin.action";
        httpServletResponse.sendRedirect(redirectLocation);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
