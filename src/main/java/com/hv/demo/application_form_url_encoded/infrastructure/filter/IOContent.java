package com.hv.demo.application_form_url_encoded.infrastructure.filter;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 13/08/2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IOContent {

    private String type;
    private String uri;
    private Integer statusCode;
    private Map<String, String> headers;
    private String body;
    private String contentEncoding;

    public IOContent() {
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public IOContent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IOContent{");
        sb.append("type='").append(type).append('\'');
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", statusCode=").append(statusCode);
        sb.append(", headers=").append(headers);
        sb.append(", contentEncoding='").append(contentEncoding).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
