package com.example.bitcoin.network

import com.example.bitcoin.model.Bitcoin
import retrofit2.Call
import retrofit2.http.GET

interface API {

//    @GET("v1/bpi/currentprice.json/")
//    fun getTime(): Call<Time>

    @GET("v1/bpi/currentprice.json/")
    fun getBitcoin(): Call<Bitcoin>

//    @GET("v1/bpi/currentprice.json/")
//    fun getEUR(): Call<EUR>
}