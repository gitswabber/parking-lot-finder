package com.swabber.api.client;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
public final class RetrofitApiClient {

    private RetrofitApiClient() {
    }

    public static <T> T executeAndGetBody(Call<T> retrofitCall) {
        try {
            final Response<T> response = retrofitCall.execute();

            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (Exception e) {
            // todo handling
        }
        return null;
    }
}
