# demo-application-form-url-encoded

### Primer ejemplo que acepta todo tipo de datos
```java
@PostMapping(value="/test",consumes=MediaType.ALL_VALUE)
public @ResponseBody ResponseEntity<String> test(HttpEntity<String> httpEntity) {
    logback.info("Headers: {}",httpEntity.getHeaders().toString());
    logback.info("Body: {}",httpEntity.getBody());
    return new ResponseEntity<>(httpEntity.getBody(), HttpStatus.OK);
}
```
Petición
```
curl --location --request POST 'http://localhost:9080/test' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'key=value'
```

### Segundo ejemplo que acepta APPLICATION_FORM_URLENCODED_VALUE como MultiValueMap<String,String> en el BODY
```java
@RequestMapping(value = "/test2", method = RequestMethod.POST , consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public @ResponseBody ResponseEntity<String> test2(@RequestBody MultiValueMap<String,String> paramMap) {
    logback.info("Parametros: {}",paramMap.toString());
    return new ResponseEntity<>(paramMap.toString(), HttpStatus.OK);
}
```
Petición
```
curl --location --request POST 'http://localhost:9080/test2' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'key=value'
```
### Tercer ejemplo que acepta APPLICATION_FORM_URLENCODED_VALUE en la URI
```java
@PostMapping(value="/test3",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
public @ResponseBody ResponseEntity<String> test3(@RequestParam("msisdn") final String msisdn) {
    logback.info("Parametros: {}",msisdn);
    return new ResponseEntity<>(msisdn, HttpStatus.OK);
}
```
Petición
```
curl --location --request POST 'http://localhost:9080/test3?msisdn=623368' --header 'Content-Type: application/x-www-form-urlencoded'
```
### Cuarto ejemplo que acepta APPLICATION_FORM_URLENCODED_VALUE como Dto en el BODY
```java
@PostMapping(value="/test4",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
public @ResponseBody ResponseEntity<String> test4(TestDto dto) {
    logback.info("Parametros: {}",dto.toString());
    return new ResponseEntity<>(dto.toString(), HttpStatus.OK);
}
```
Petición
```
curl --location --request POST 'http://localhost:9080/test4' --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'msisdn=645'
```
