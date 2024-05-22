package com.microsService_Project.Order_service.external.client.intercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

public class OAuthRequestInerceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager clientManager;
    @Override
    public void apply(RequestTemplate template) {

        template.header("Authorization","Bearer" + clientManager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("internal-client")
                        .principal("internal").build()).getAccessToken().getTokenValue());

    }
}
