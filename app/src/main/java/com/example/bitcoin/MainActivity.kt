package com.example.bitcoin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide
            .with(this)
            .load(R.drawable.icons8_bitcoin_gif)
            .into(ivBitcoin);



        val retrofit = RetrofitClient.getClient("https://api.coindesk.com/").create(API::class.java)
        retrofit.getBitcoin().enqueue(object : Callback<Bitcoin> {
            override fun onResponse(call: Call<Bitcoin>, response: Response<Bitcoin>) {
                if (response.body() != null) {
                    tvTime.text = response.body()?.chartName
                    // не нужен, про безопасность или куда то вниз
//                    tvDiclaimer.text = response.body()?.disclaimer
//
                    tvEURcode.text = response.body()?.bpi?.EUR?.code
                    tvEURdescription.text = response.body()?.bpi?.EUR?.description
                    tvEURrate.text = response.body()?.bpi?.EUR?.rate
                    tvEURrate_float.text = response.body()?.bpi?.EUR?.rate_float.toString()
                    tvEURsymbol.text = response.body()?.bpi?.EUR?.symbol

                    Log.d("MyLOG", "onRespose" + response.body()?.chartName)
                }
            }

            override fun onFailure(call: Call<Bitcoin>, t: Throwable) {
                Log.d("MyLOG", "Fauler")
            }
        }
        )
    }
}