package web.filter;

import org.apache.log4j.Logger;
import util.Constant;
import util.Util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;

public class LocaleFilter implements Filter {

    private List<String> supportedLanguages;
    private String defaultLanguage;
    private ServletContext servletContext;

    private final Logger LOGGER = Logger.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.debug("Filter initialized");
        supportedLanguages = asList(filterConfig.getInitParameter("supportedLocales"));
        defaultLanguage = filterConfig.getInitParameter("defaultLocale");
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.debug("Filter starts working");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession();
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);

        String localeStr = httpRequest.getParameter("language");
        Locale locale = (Locale) httpSession.getAttribute("localeClass");
        LOGGER.trace("Session locale --> " + locale);
        if (Objects.isNull(locale)) {
            locale = util.toLocale(defaultLanguage);
            httpSession.setAttribute("localeClass", locale);
            LOGGER.trace("Set default locale: " + "en");
        }
        if (Objects.nonNull(localeStr)) {
            if (supportedLanguages.contains(localeStr)) {
                httpSession.setAttribute("localeClass", util.toLocale(localeStr));
                LOGGER.trace("Set default locale: " + localeStr);
            } else {
                httpSession.setAttribute("localeClass", util.toLocale(defaultLanguage));
                LOGGER.trace("Set default locale: " + "en");
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Filter destroyed");
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }
}