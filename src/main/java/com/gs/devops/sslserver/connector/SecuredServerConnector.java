package com.gs.devops.sslserver.connector;


import com.fasterxml.jackson.databind.JsonNode;
import com.gs.devops.sslserver.data.dto.SecureResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SecuredServerConnector {

    private final static RestTemplate restTemplate = new RestTemplate();

    public SecureResponse remoteSecured(String url) {

        ResponseEntity<SecureResponse> response  = restTemplate.exchange(url, HttpMethod.GET, ConnectorHelper.getHttpEntity(), SecureResponse.class);
        log.info("Response: {}", response.getBody());

        return response.getBody();
    }

}
