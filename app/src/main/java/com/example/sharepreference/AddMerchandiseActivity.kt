package com.example.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_merchandise.*
import java.lang.Exception

class AddMerchandiseActivity : AppCompatActivity() {

    private var mProduct = Result()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_merchandise)

        val bundle: Bundle? = intent.getBundleExtra("bundle")
        if (bundle !== null){
            try {
                mProduct = bundle.getSerializable("key") as Result
                edtIdAdd.setText("${mProduct.id}")
                edtNameAdd.setText("${mProduct.name}")
                edtPriceAdd.setText("${mProduct.price}")
            }catch (e : Exception){
                Log.e("vq",e.toString())
            }

        }

        btnSave.setOnClickListener{
            val product = mProduct.cloneToProductPost()
            product.productPost.name = edtNameAdd.text.toString()
            product.productPost.price = edtPriceAdd.text.toString().toLong()

            val bundle = Bundle()
            bundle.putSerializable("key", product)
            intent.putExtra("bundle", bundle)
            setResult(RESULT_OK, intent)
            finish()


        }

    }
}