package com.example.bitcoin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = RetrofitClient.getClient("https://api.coindesk.com/").create(API::class.java)
        retrofit.getBitcoin().enqueue(object : Callback<Bitcoin> {
            override fun onResponse(call: Call<Bitcoin>, response: Response<Bitcoin>) {
                if (response.body() != null){
                    tvTime.text =  response.body()?.chartName
                    tvDiclaimer.text = response.body()?.disclaimer
                    Log.d("MyLOG", "onRespose"+response.body()?.chartName)
                }
            }
            override fun onFailure(call: Call<Bitcoin>, t: Throwable) {
                Log.d("MyLOG", "Fauler")
            }
        }
        )


        retrofit.getEUR().enqueue(object : Callback<EUR> {
            override fun onResponse(call: Call<EUR>, response: Response<EUR>) {
                if (response.body() != null){
                    tvEURcode.text =  response.body()?.code
//                    tvEURdescription.text = response.body()?.description
                    tvEURrate.text = response.body()?.rate
                    tvEURrate_float.text = response.body()?.rate_float.toString()
//                    tvEURsymbol.text = response.body()?.symbol
                    Log.d("MyLOG", "onRespose")
                }
            }

            override fun onFailure(call: Call<EUR>, t: Throwable) {
                Log.d("MyLOG", "Fauler")
            }
        }
        )
    }
}