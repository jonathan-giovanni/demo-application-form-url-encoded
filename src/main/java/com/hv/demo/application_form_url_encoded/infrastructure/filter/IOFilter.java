package com.hv.demo.application_form_url_encoded.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 13/08/2020
 */
@Component
public class IOFilter extends OncePerRequestFilter {

    private static Logger logback = LoggerFactory.getLogger(IOFilter.class);

    @Autowired
    private FilterUtil loggingFilterUtil;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        chain.doFilter(wrappedRequest, wrappedResponse);

        IOContent requestContent = loggingFilterUtil.getContentRequest(wrappedRequest);
        logback.info("REQUEST: {}",mapper.writeValueAsString(requestContent) );

        IOContent responseContent = loggingFilterUtil.getContentResponse(wrappedResponse);
        logback.info("RESPONSE: {}",mapper.writeValueAsString(responseContent) );
    }

}

