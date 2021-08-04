package com.example.bitcoin

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface API {

//    @GET("v1/bpi/currentprice.json/")
//    fun getTime(): Call<Time>

    @GET("v1/bpi/currentprice.json/")
    fun getBitcoin(): Call<Bitcoin>

    @GET("v1/bpi/currentprice.json/")
    fun getEUR(): Call<EUR>
}