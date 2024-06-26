package com.rapalmac.eapp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class KeyclockLogoutHandler implements LogoutHandler {
    private static final Logger logger = LoggerFactory.getLogger(KeyclockLogoutHandler.class);

    @Value("${keycloack.logouthandler.post_redirect_uri}")
    private String postRedirectUri;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication==null) {
            return;
        }

        //Propagate to keycloak
        try (var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build()) {

            var user = (OidcUser) authentication.getPrincipal();

            var endSessionEndpoint = user.getIssuer() + "/protocol/openid-connect/logout";
            var uri = UriComponentsBuilder
                    .fromUriString(endSessionEndpoint)
                    .queryParam("id_token_hint", user.getIdToken().getTokenValue())
                    .queryParam("post_logout_redirect_uri", postRedirectUri)
                    .build(new Object[]{});

            System.out.println(uri.toString());

            var req = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            var resp = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (resp.statusCode() == 200) {
                logger.info("Successfully logged out from Keycloak");
            } else {
                logger.error("Could not propagate logout to Keycloak");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
