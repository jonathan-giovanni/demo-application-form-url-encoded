package com.hv.demo.application_form_url_encoded.infrastructure.filter;

import io.micrometer.core.instrument.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 13/08/2020
 */
@Component
public class FilterUtil {

    private static final String T_REQUEST	    = "REQUEST";
    private static final String T_RESPONSE      = "RESPONSE";

    private static final String ERROR_REQ       = "ERROR GET CONTENT REQUEST: {}";
    private static final String ERROR_RES       = "ERROR GET CONTENT RESPONSE: {}";

    private static Logger logback = LoggerFactory.getLogger(FilterUtil.class);

    private Map<String, String> headers = new HashMap<>();


    private int maxPayloadLength = 1000;

    private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, this.maxPayloadLength);
        try {
            return new String(buf, 0, length, charsetName);
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    public IOContent getContentRequest(ContentCachingRequestWrapper request) {
        StringBuffer buffer = new StringBuffer();
        IOContent requestContent = new IOContent(T_REQUEST);
        try {
            //limpiando los headers
            headers.clear();
            //la url que se invoco
            requestContent.setUri(request.getMethod()+" "+request.getRequestURI());
            //asignando los headers
            Collections.list(request.getHeaderNames()).forEach(resHeader->headers.put(resHeader, request.getHeader(resHeader)));
            requestContent.setHeaders(headers);
            //tipo de encoding
            requestContent.setContentEncoding(requestContent.getContentEncoding());
            //body
            String requestBody = getContentAsString(request.getContentAsByteArray() , this.maxPayloadLength, request.getCharacterEncoding());
            requestContent.setBody(JsonUtils.prettyPrint(requestBody));
        } catch (Exception e) {
            logback.error(ERROR_REQ,e);
        }
        return requestContent;
    }

    public IOContent getContentResponse(ContentCachingResponseWrapper response) {
        IOContent responseContent = new IOContent(T_RESPONSE);
        try {
            headers.clear();
            //http status
            responseContent.setStatusCode(response.getStatus());
            //headers
            response.getHeaderNames().forEach(resHeader->headers.put(resHeader, response.getHeader(resHeader)));
            responseContent.setHeaders(headers);
            //tipo de encoding
            responseContent.setContentEncoding(responseContent.getContentEncoding());
            //body
            String responseBody = getContentAsString(response.getContentAsByteArray() , this.maxPayloadLength, response.getCharacterEncoding());
            responseContent.setBody(JsonUtils.prettyPrint(responseBody));
            //copiar
            response.copyBodyToResponse();
        } catch (Exception e) {
            logback.error(ERROR_RES,e);
        }
        return responseContent;
    }

}
