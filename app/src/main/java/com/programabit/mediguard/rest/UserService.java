package com.programabit.mediguard.rest;

import android.util.Log;

import com.programabit.mediguard.DashboardActivity;
import com.programabit.mediguard.LoginRequest;
import com.programabit.mediguard.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("api/api_login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @GET("/mis_guardias")
    Call<List<GuardDto>> getMyGuards(@Header("Authorization") String token);

    @GET("/guardias_disponibles")
    Call<List<GuardDto>> getAvailableGuardsGuards(@Header("Authorization") String token);


    @GET("/medico_datos")
    Call<MedicDto> getMedic(@Header("Authorization") String token);

}