package com.gs.devops.sslserver.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.InetAddress;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecureResponse {

    private String username;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date timestamp;

    private String version;

    private InetAddress host;

    private String ipAddress;


}
