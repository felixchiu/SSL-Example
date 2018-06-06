package com.gs.devops.sslserver.controller;

import com.gs.devops.sslserver.connector.SecuredServerConnector;
import com.gs.devops.sslserver.data.dto.SecureResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Slf4j
@RequestMapping(value="/api")
@RestController
class SecuredServerController{

	@Value("${app.system.version:0.1}")
	private String version;

	@Autowired
	private SecuredServerConnector securedServerConnector;


	@RequestMapping(value = "/secured", method = RequestMethod.GET)
	public SecureResponse secured(HttpServletRequest request) throws UnknownHostException {
		log.info("Inside secured()");
		String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "user";
		InetAddress host = Inet4Address.getLocalHost();
		return SecureResponse.builder().timestamp(new Date()).username(username).version(version).host(host).ipAddress(host.getHostAddress()).build();
	}

	@RequestMapping(value = "/connect", method = RequestMethod.GET)
	public SecureResponse secured(@RequestParam(name="url", required = false) String url) {
		if (url == null) {
			url = "https://127.0.0.1:8443/api/secured";
		}
		return securedServerConnector.remoteSecured(url);
	}



}
