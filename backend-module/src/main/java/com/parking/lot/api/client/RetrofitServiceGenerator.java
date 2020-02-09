package com.parking.lot.api.client;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.util.Assert;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public final class RetrofitServiceGenerator {

    private RetrofitServiceGenerator() {
    }

    public static <T> T createService(ApiClientProperties apiClientProperties, Class<T> serviceClass) {
        Assert.notNull(apiClientProperties, "'apiClientProperties' must not be null.");

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiClientProperties.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getOkHttpClientBuilder(apiClientProperties).build())
                .build();

        return retrofit.create(serviceClass);
    }

    private static OkHttpClient.Builder getOkHttpClientBuilder(ApiClientProperties apiClientProperties) {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(apiClientProperties.getLogLevel());

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(apiClientProperties.isRetryOnConnectionFailure())
                .readTimeout(apiClientProperties.getReadTimeoutMilliseconds(), TimeUnit.MILLISECONDS)
                .connectTimeout(apiClientProperties.getConnectTimeoutSeconds(), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(apiClientProperties.getConnectPoolMaxIdle(), apiClientProperties.getConnectPoolKeepAliveDurationMinutes(), TimeUnit.MINUTES));
    }
}
