package it.seiton.oauth2.data.api;

import android.text.TextUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by lukasw44 on 16.01.16.
 */
public class Oauth2Config {

    private final String clientId;
    private final String clientSecret;
    private final String endpoint;
    private final String scopes;
    private final String port;

    private Oauth2Config(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.endpoint = builder.endpoint;
        this.scopes = TextUtils.join(" ", builder.scopes);
        this.port = builder.port;
    }

    public String getOauth2Url() {
        return endpoint + "oauth/token";

    }

    public String getScopes() {
        return scopes;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Oauth2Config{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", scopes='" + scopes + '\'' +
                '}';
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String endpoint;
        private String port;
        private HashSet<String> scopes;

        public Builder() {
            scopes = new HashSet<>();
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public Builder scopes(String... scopes) {
            this.scopes.addAll(Arrays.asList(scopes));
            return this;
        }

        public Oauth2Config build() {
            return new Oauth2Config(this);
        }
    }
}
