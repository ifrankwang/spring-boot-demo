package com.github.ifrankwang.spring.api.facade.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ifrankwang.spring.api.converter.security.ApiConverter;
import com.github.ifrankwang.spring.api.dto.security.ApiDto;
import com.github.ifrankwang.spring.api.facade.ApiFacade;
import com.github.ifrankwang.spring.exception.InternalServerError;
import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;
import com.github.ifrankwang.spring.module.security.service.ApiService;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
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
    public void updateApis() throws InternalServerError {
        try {
            final String jsonString = restTemplate.getForObject("/v2/api-docs", String.class);
            final JsonNode rootNode = new ObjectMapper().readTree(jsonString);
            Optional.ofNullable(rootNode.get("paths")).map(pathsNode -> {
                // 所有接口相关的信息在paths下
                Lists.newArrayList(pathsNode.fields()).forEach(pathEntry -> {
                    // paths的格式为：{"paths": {"/api": {...}}}}
                    final String apiPath = pathEntry.getKey();
                    final JsonNode pathNode = pathEntry.getValue();
                    Lists.newArrayList(pathNode.fields()).forEach(methodEntry -> {
                        // paths下每个参数都是单个api的信息，结构为：{"/api": {"put": {"summary": "接口说明"}}
                        final String method = methodEntry.getKey().toUpperCase();
                        final String name = methodEntry.getValue().get("summary").asText();
                        final ApiMethod apiMethod = ApiMethod.valueOf(method.toUpperCase());
                        apiService.findOptionalByMethodAndPath(apiMethod, apiPath).map(entity -> {
                            // 已记录的api信息则更新接口说明
                            entity.setName(name);
                            return entity;
                        }).orElseGet(() -> {
                            // 未记录的api信息则增加记录
                            final ApiEntity apiEntity = ApiEntity.builder().name(name).method(ApiMethod.valueOf(method))
                                                                 .path(apiPath).build();
                            return apiService.create(apiEntity);
                        });
                    });
                });
                return pathsNode;
            }).orElseThrow(InternalServerError::new);
        } catch (IOException e) {
            logger.error("\n无法解析的json！", e);
            throw new InternalServerError();
        }
    }

    @Override
    public Page<ApiDto> getApiPage(Pageable pageable) {
        final Page<ApiEntity> apiEntityPage = apiService.findAll(pageable);
        return ApiConverter.INSTANCE.toPage(apiEntityPage);
    }

    @Override
    public void deleteApi(Long id) throws ApiNotFoundException {
        final ApiEntity entity = apiService.findById(id);
        apiService.delete(entity);
    }
}
