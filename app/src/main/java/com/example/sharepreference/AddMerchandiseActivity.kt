package com.example.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_merchandise.*

class AddMerchandiseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_merchandise)

        btnSave.setOnClickListener{
            var idAdd: Int = edtIdAdd.text.toString().toInt()
            var nameAdd : String = edtNameAdd.text.toString()
            var priceAdd : Int = edtPriceAdd.text.toString().toInt()
            var priceLargeUnitAdd : Int = edtPriceLargeUnitAdd.text.toString().toInt()
            var createDateAdd : String = edtCreateDateAdd.text.toString()
            var modifDateAdd : String = edtModifDateAdd.text.toString()
            var maxQuantityAdd : Int = edtMaxQuanAdd.text.toString().toInt()
            var unitAdd : String = edtUnitAdd.text.toString()

            var bundle = Bundle()
//            var resultOb = Result(id = idAdd, name = nameAdd, price =  priceAdd, priceLargeUnit = priceLargeUnitAdd,
//                createdDate = createDateAdd, modifiedDate = modifDateAdd, maxQuantity = maxQuantityAdd, unit = unitAdd)
//            bundle.putSerializable("resultOb", resultOb)

            bundle.putInt("idAdd", idAdd)
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