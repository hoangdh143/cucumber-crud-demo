package com.mitrais.demo.stepdefs;

import com.mitrais.demo.CucumberTestContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

public abstract class AbstractSteps {
    private CucumberTestContext CONTEXT = CucumberTestContext.CONTEXT;

    @LocalServerPort
    private int port;

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    protected CucumberTestContext testContext() {
        return CONTEXT;
    }

    protected void post(String apiPath) {
        post(apiPath, null, null);
    }

    protected void post(String apiPath, Map<String, String> pathParams, Map<String, String> queryParams) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();
        final String url = baseUrl() + apiPath;

        setPayload(request, payload);
        setPathParams(request, pathParams);
        setQueryParams(request, queryParams);

        Response response  = request.accept(ContentType.JSON).post(url);
        CONTEXT.setResponse(response);
    }

    private void setPayload(RequestSpecification request, Object payload) {
        if (null != payload) {
            request.contentType(ContentType.JSON).body(payload);
        }
    }

    private void setPathParams(RequestSpecification request, Map<String, String> pathParams) {
        if (null != pathParams) {
            request.pathParams(pathParams);
        }
    }

    private void setQueryParams(RequestSpecification request, Map<String, String> queryParams) {
        if (null != queryParams) {
            request.queryParams(queryParams);
        }
    }
}
