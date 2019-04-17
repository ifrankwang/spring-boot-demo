package com.github.ifrankwang.spring.api.facade.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ifrankwang.spring.api.facade.ApiFacade;
import com.github.ifrankwang.spring.exception.InvalidRequestArgumentsException;
import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import com.github.ifrankwang.spring.module.security.service.ApiService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Frank Wang
 */
@Service
public class ApiFacadeImpl implements ApiFacade {
    private final ApiService apiService;

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ApiFacadeImpl(ApiService apiService, @Value("${server.port}") String port,
                         @Value("${server.servlet.context-path}") String contextPath,
                         RestTemplateBuilder restTemplateBuilder) {
        this.apiService = apiService;
        this.restTemplate = restTemplateBuilder.rootUri(String.format("http://localhost:%s%s", port, contextPath))
                                               .build();
    }

    @Override
    public void updateApis() throws InvalidRequestArgumentsException {
        try {
            final String jsonString = restTemplate.getForObject("/v2/api-docs", String.class);
            final JsonNode rootNode = new ObjectMapper().readTree(jsonString);
            Optional.ofNullable(rootNode.get("paths")).map(pathsNode -> {
                Lists.newArrayList(pathsNode.fields()).forEach(pathEntry -> {
                    final String apiPath = pathEntry.getKey();
                    final JsonNode pathNode = pathEntry.getValue();
                    Lists.newArrayList(pathNode.fields()).forEach(methodEntry -> {
                        final String method = methodEntry.getKey().toUpperCase();
                        final String name = methodEntry.getValue().get("summary").asText();
                        final ApiMethod apiMethod = ApiMethod.valueOf(method.toUpperCase());
                        final Optional<ApiEntity> opEntity = apiService.findOptionalByMethodAndPath(apiMethod, apiPath);
                        opEntity.map(entity -> {
                            entity.setName(name);
                            return entity;
                        }).orElseGet(() -> {
                            final ApiEntity apiEntity = ApiEntity.builder().name(name).method(ApiMethod.valueOf(method))
                                                                 .path(apiPath).build();
                            return apiService.create(apiEntity);
                        });
                    });
                });
                return pathsNode;
            }).orElseThrow(InvalidRequestArgumentsException::new);
        } catch (IOException e) {
            logger.error("\n无法解析的json！", e);
            throw new InvalidRequestArgumentsException();
        }
    }
}
