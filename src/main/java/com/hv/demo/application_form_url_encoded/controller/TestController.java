package com.hv.demo.application_form_url_encoded.controller;

import com.hv.demo.application_form_url_encoded.dto.TestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jonathan Giovanni Hernandez
 * @company TEST
 * @created 01/10/2020
 */
@RestController
@RequestMapping("/")
public class TestController {

    private Logger logback = LoggerFactory.getLogger(getClass());

    /**
     * Consumiendo cualquier tipo de content
     * @param httpEntity
     * @return
     */
    @PostMapping(value="/test",consumes= MediaType.ALL_VALUE)
    public @ResponseBody ResponseEntity<String> test(HttpEntity<String> httpEntity) {
        logback.info("Headers: {}",httpEntity.getHeaders().toString());
        logback.info("Body: {}",httpEntity.getBody());
        return new ResponseEntity<>(httpEntity.getBody(), HttpStatus.OK);
    }

    /**
     * Consumiendo MediaType.APPLICATION_FORM_URLENCODED_VALUE en MultiValueMap
     * Parametros deben ir en body
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/test2", method = RequestMethod.POST , consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public @ResponseBody ResponseEntity<String> test2(@RequestBody MultiValueMap<String,String> paramMap) {
        logback.info("Parametros: {}",paramMap.toString());
        return new ResponseEntity<>(paramMap.toString(), HttpStatus.OK);
    }

    /**
     * Consumiendo MediaType.APPLICATION_FORM_URLENCODED_VALUE en String como parametro
     * Parametros deben ir en URI
     * @param msisdn
     * @return
     */
    @PostMapping(value="/test3",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
    public @ResponseBody ResponseEntity<String> test3(@RequestParam("msisdn") final String msisdn) {
        logback.info("Parametros: {}",msisdn);
        return new ResponseEntity<>(msisdn, HttpStatus.OK);
    }

    /**
     * Consumiendo MediaType.APPLICATION_FORM_URLENCODED_VALUE en Dto
     * Parametros deben ir en body
     * @param dto
     * @return
     */
    @PostMapping(value="/test4",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
    public @ResponseBody ResponseEntity<String> test4(TestDto dto) {
        logback.info("Parametros: {}",dto.toString());
        return new ResponseEntity<>(dto.toString(), HttpStatus.OK);
    }

}
