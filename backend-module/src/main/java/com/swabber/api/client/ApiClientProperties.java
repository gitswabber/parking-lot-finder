package com.swabber.api.client;

import lombok.Getter;
import lombok.Setter;
import okhttp3.logging.HttpLoggingInterceptor;

@Getter
@Setter
public abstract class ApiClientProperties {
    private String baseUrl;
    private boolean retryOnConnectionFailure;
    private long readTimeoutMilliseconds;
    private long connectTimeoutSeconds;
    private int connectPoolMaxIdle;
    private long connectPoolKeepAliveDurationMinutes;
    private HttpLoggingInterceptor.Level logLevel;
}
