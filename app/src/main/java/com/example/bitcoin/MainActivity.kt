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
        supportActionBar?.hide()
        getData()

        Glide
            .with(this)
            .load(R.drawable.icons8_bitcoin_gif)
            .into(ivBitcoin);


        ivRefresh.setOnClickListener {
            getData()
        }

    }
    fun getData(){
        val retrofit = RetrofitClient.getClient("https://api.coindesk.com/").create(API::class.java)
        retrofit.getBitcoin().enqueue(object : Callback<Bitcoin> {
            override fun onResponse(call: Call<Bitcoin>, response: Response<Bitcoin>) {
                if (response.body() != null) {
                    tvBitcoin.text = response.body()?.chartName
                    tvDeclaimer.text = response.body()?.disclaimer

                    tvUSDcode.text = response.body()?.bpi?.USD?.code
                    tvUSDdescription.text = response.body()?.bpi?.USD?.description
                    tvUSDrate.text = response.body()?.bpi?.USD?.rate

                    tvEURcode.text = response.body()?.bpi?.EUR?.code
                    tvEURdescription.text = response.body()?.bpi?.EUR?.description
                    tvEURrate.text = response.body()?.bpi?.EUR?.rate

                    tvGBRcode.text = response.body()?.bpi?.GBP?.code
                    tvGBRdescription.text = response.body()?.bpi?.GBP?.description
                    tvGBRrate.text = response.body()?.bpi?.GBP?.rate

                    tvTimeUpdated.text = response.body()?.time?.updated
                    tvTimeUpdateduk.text = response.body()?.time?.updateduk


                    Log.d("MyLOG", "onRespose")
                }
            }

            override fun onFailure(call: Call<Bitcoin>, t: Throwable) {
                Log.d("MyLOG", "Fauler")
            }
        }
        )
    }
}