package com.example.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_merchandise.*
import java.lang.Exception

class AddMerchandiseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_merchandise)

        val bundle: Bundle? = intent.getBundleExtra("bundleEdit")
        if (bundle !== null){
            val idAdd : Int? = bundle?.getInt("idAdd")
            val nameAdd : String = bundle?.getString("nameAdd").toString()
            val priceAdd : Int? = bundle?.getInt("priceAdd")
            val priceLUAdd : Int? = bundle?.getInt("priceLUAdd")
            val createDateAdd : String = bundle?.getInt("createDateAdd").toString()
            val modifDateAdd : String = bundle?.getInt("modifDateAdd").toString()
            val maxquantityAdd : Int? = bundle?.getInt("maxquantityAdd")
            val unitAdd : String = bundle?.getInt("unitAdd").toString()

            edtIdAdd.setText("${idAdd}")
            edtNameAdd.setText("${nameAdd}")
            edtPriceAdd.setText("${priceAdd}")
            edtPriceLargeUnitAdd.setText("${priceLUAdd}")
            edtCreateDateAdd.setText("${createDateAdd}")
            edtModifDateAdd.setText("${modifDateAdd}")
            edtMaxQuanAdd.setText("${maxquantityAdd}")
            edtUnitAdd.setText("${unitAdd}")
        }

        btnSave.setOnClickListener{

            val nameAdd = edtNameAdd.text.toString()
            val priceAdd = edtPriceAdd.text.toString().toInt()
            val priceLargeUnitAdd = edtPriceLargeUnitAdd.text.toString().toInt()
            val createDateAdd = edtCreateDateAdd.text.toString()
            val modifDateAdd  = edtModifDateAdd.text.toString()
            val maxQuantityAdd = edtMaxQuanAdd.text.toString().toInt()
            val unitAdd  = edtUnitAdd.text.toString()

            val bundle = Bundle()

            bundle.putString("nameAdd", nameAdd)
            bundle.putInt("priceAdd", priceAdd)
            bundle.putInt("priceLUAdd", priceLargeUnitAdd)
            bundle.putString("createDateAdd", createDateAdd)
            bundle.putString("modifDateAdd", modifDateAdd)
            bundle.putInt("maxquantityAdd", maxQuantityAdd)
            bundle.putString("unitAdd", unitAdd)
            intent.putExtra("bundle", bundle)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}