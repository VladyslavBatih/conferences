package web.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Objects;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("Filter initialized");
        encoding = filterConfig.getInitParameter("encoding");
        LOGGER.info("Encoding from web.xml -->" + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.info("Filter starts working");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOGGER.info("Request URI -->" + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (Objects.isNull(requestEncoding)) {
            LOGGER.error("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }
        LOGGER.info("Filter finished work");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter destroyed");
    }
}