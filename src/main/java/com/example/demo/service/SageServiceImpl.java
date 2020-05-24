package com.example.demo.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SageServiceImpl implements SageService {

    private RestTemplate restTemplate = new RestTemplate();

    private String baseUrl = "https://oauth.accounting.sage.com/token";
    private String clientId = "8865a7c5-1ba8-4df9-b4b5-0e8dd264b503/49f58f58-0c98-478f-a4f4-a659fb04677f";
    private String client_secret = "q1CDO:vnkDvdW^%23EN?F/";
    private String grant_type = "authorization_code";
    private String redirect_uri = "http://localhost:9060/callback";

    @Override
    public void getToken(String code) {

        String url = baseUrl.concat("?code=").concat(code);
        url = url.concat("&client_id=").concat("8865a7c5-1ba8-4df9-b4b5-0e8dd264b503/49f58f58-0c98-478f-a4f4-a659fb04677f");
        url = url.concat("&grant_type=").concat("authorization_code");
        url = url.concat("&redirect_uri=").concat("http://localhost:9060/callback");
        //url = url.concat("&client_secret=").concat("q1CDO:vnkDvdW^%23EN?F/");
        url = url.concat("&client_secret=").concat("q1CDO%3AvnkDvdW%5E%2523EN%3FF%2F");

        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Object var = restTemplate.exchange(url, HttpMethod.POST, null, new ParameterizedTypeReference<Object>() {
        }).getBody();

        System.out.println("done");
    }

    public void getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // if you need to pass form parameters in request with headers.
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", clientId);
        map.add("client_secret", client_secret);
        map.add("grant_type", grant_type);
        map.add("redirect_uri", redirect_uri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<Object> responses = restTemplate.postForEntity(baseUrl, request, Object.class);

        System.out.println("done");
    }
}
