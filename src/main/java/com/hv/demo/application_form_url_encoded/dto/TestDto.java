package com.hv.demo.application_form_url_encoded.dto;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 01/10/2020
 */
public class TestDto {
    private String msisdn;
    public TestDto() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TestDto{");
        sb.append("msisdn='").append(msisdn).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
