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

        btnSave.setOnClickListener{
            val idAdd = edtIdAdd.text.toString().toInt()
            val nameAdd = edtNameAdd.text.toString()
            val priceAdd = edtPriceAdd.text.toString().toInt()
            val priceLargeUnitAdd = edtPriceLargeUnitAdd.text.toString().toInt()
            val createDateAdd = edtCreateDateAdd.text.toString()
            val modifDateAdd  = edtModifDateAdd.text.toString()
            val maxQuantityAdd = edtMaxQuanAdd.text.toString().toInt()
            val unitAdd  = edtUnitAdd.text.toString()

            val bundle = Bundle()

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
//        try {
//            var resultOb = bundle?.getSerializable("objEdt") as Result
//            edtIdAdd.setText("${resultOb.id}")
//            edtNameAdd.setText("${resultOb.name}")
//            edtPriceAdd.setText("${resultOb.price}")
//            edtPriceLargeUnitAdd.setText("${resultOb.priceLargeUnit}")
//            edtCreateDateAdd.setText("${resultOb.createdDate}")
//            edtModifDateAdd.setText("${resultOb.modifiedDate}")
//            edtMaxQuanAdd.setText("${resultOb.maxQuantity}")
//            edtUnitAdd.setText("${resultOb.unit}")
//
//        }catch (e : Exception){
//            Log.e("vq",e.toString())
//        }
//        finally {
//            btnSave.setOnClickListener{
//                var idAdd = edtIdAdd.text.toString().toInt()
//                var nameAdd = edtNameAdd.text.toString()
//                var priceAdd = edtPriceAdd.text.toString().toInt()
//                var priceLargeUnitAdd = edtPriceLargeUnitAdd.text.toString().toInt()
//                var createDateAdd = edtCreateDateAdd.text.toString()
//                var modifDateAdd  = edtModifDateAdd.text.toString()
//                var maxQuantityAdd = edtMaxQuanAdd.text.toString().toInt()
//                var unitAdd  = edtUnitAdd.text.toString()
//
//                var bundle = Bundle()
////                var resultOb = Result(id = idAdd, name = nameAdd, price =  priceAdd, priceLargeUnit = priceLargeUnitAdd,
////                createdDate = createDateAdd, modifiedDate = modifDateAdd, maxQuantity = maxQuantityAdd, unit = unitAdd)
////                bundle.putSerializable("resultOb", resultOb)
////                intent.putExtra("bundle", bundle)
//
//                bundle.putInt("idAdd", idAdd)
//                bundle.putString("nameAdd", nameAdd)
//                bundle.putInt("priceAdd", priceAdd)
//                bundle.putInt("priceLUAdd", priceLargeUnitAdd)
//                bundle.putString("createDateAdd", createDateAdd)
//                bundle.putString("modifDateAdd", modifDateAdd)
//                bundle.putInt("maxquantityAdd", maxQuantityAdd)
//                bundle.putString("unitAdd", unitAdd)
//                intent.putExtra("bundle", bundle)
//                setResult(RESULT_OK, intent)
//                finish()
//            }
//        }
    }
}