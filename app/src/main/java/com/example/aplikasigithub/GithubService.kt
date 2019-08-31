package com.example.aplikasigithub

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("users")
    fun getUsers() : Call<List<GithubUser>>


}