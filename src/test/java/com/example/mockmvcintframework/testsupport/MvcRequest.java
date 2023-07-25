package com.example.mockmvcintframework.testsupport;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Data
@Builder
public class MvcRequest {
    @NonNull
    private HttpMethod method;
    @NonNull
    private String path;
    @Builder.Default
    private MediaType contentType = APPLICATION_JSON;
    private String content;
    private Headers headers;
    private Attributes requestAttributes;
    private Parameters queryParams;

    public static class MvcRequestBuilder {
        public MvcRequestBuilder httpGet(String path) {
            return method(GET).path(path);
        }
    }

    @Data
    @Builder
    public static class Headers {
        private MultiValueMap<String, Object> headers;

        public static class HeadersBuilder {
            public HeadersBuilder add(String key, Object value) {
                headers.add(key, value);

                return this;
            }
        }
    }

    @Data
    @Builder
    public static class Attributes {
        private Map<String, Object> attributes;

        public static class AttributesBuilder {
            public AttributesBuilder add(String key, Object value) {
                attributes.put(key, value);

                return this;
            }
        }
    }

    @Data
    @Builder
    public static class Parameters {
        private MultiValueMap<String, String> parameters;

        public static class ParametersBuilder {
            public ParametersBuilder add(String key, String value) {
                parameters.add(key, value);

                return this;
            }
        }
    }
}
