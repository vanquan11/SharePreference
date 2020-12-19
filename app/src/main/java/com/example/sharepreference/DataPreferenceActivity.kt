package com.example.sharepreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_merchandise.*
import kotlinx.android.synthetic.main.activity_data_preference.*
import java.lang.Exception

class DataPreferenceActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HHAdapter
    var compositeDisposable: CompositeDisposable? = null
    var REQUES_CODE_ADD  : Int = 1
    var REQUES_CODE_EDIT  : Int = 2
    lateinit var obj : Result
    private lateinit var userDetail : DataModel
    private lateinit var merchandise : Result
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_preference)

//        val context = this
//        val db : DataBaseHelper = DataBaseHelper(context)
        val bundle: Bundle? = intent.getBundleExtra("bundle")
        try {
            userDetail = bundle?.getSerializable("key") as DataModel
        }catch (e : Exception){
            Log.e("vq",e.toString())
        }

        recyclerView = rcvHangHoa
        val listData = mutableListOf<Result>()
        adapter = HHAdapter(listData){ view, resultob ->
            openContextMenu(view)
            obj = resultob
        }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)

//        Log.e("vq","${userDetail.sessionId}")

        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(
                APIClient.getClient.getData("ss-id=${userDetail.sessionId}")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                         onNext = {
                             Log.e("vq","On next" )
                             listData.addAll(it.results)
                             adapter.apply {
                                 notifyDataSetChanged()
                             }
                         },
                         onComplete = {
                             Log.e("vq","Complete")
                          },
                         onError = {
                             Log.e("vq","onError ${it.message}")
                         }
                    )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select Option")
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.editMenu  -> {
                intent = Intent(this, AddMerchandiseActivity::class.java)
                val bundle = Bundle()
//                bundle.putSerializable("objEdt", obj)
//                intent.putExtra("bundleEdit", bundle)
                bundle.putInt("idAdd", obj.id)
                bundle.putString("nameAdd", obj.name)
                bundle.putInt("priceAdd", obj.price)
                bundle.putInt("priceLUAdd", obj.priceLargeUnit)
                bundle.putString("createDateAdd", obj.createdDate)
                bundle.putString("modifDateAdd", obj.modifiedDate)
                bundle.putInt("maxquantityAdd", obj.maxQuantity)
                bundle.putString("unitAdd", obj.unit)
                intent.putExtra("bundleEdit", bundle)
                startActivityForResult(intent, REQUES_CODE_EDIT)
                return  true
            }
            R.id.delelteMenu  -> {
                val db : DataBaseHelper = DataBaseHelper(this)

                return  true
            }
            else -> return super.onContextItemSelected(item)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_menu -> {
                intent = Intent(this, AddMerchandiseActivity::class.java)
                startActivityForResult(intent, REQUES_CODE_ADD)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUES_CODE_ADD && resultCode == RESULT_OK){
////            val bundle: Bundle? = data?.getBundleExtra("bundle")
////            var idAdd : Int = bundle?.getInt("idAdd")!!
////            var nameAdd : String = bundle?.getString("nameAdd")!!
////            var priceAdd : Int = bundle?.getInt("priceAdd")
////            var priceLUAdd : Int = bundle?.getInt("priceLUAdd")
////            var createDateAdd : Int = bundle?.getInt("createDateAdd")
////            var modifDateAdd : Int = bundle?.getInt("modifDateAdd")
////            var maxquantityAdd : Int = bundle?.getInt("maxquantityAdd")
////            var unitAdd : Int = bundle?.getInt("unitAdd")
////
////            var resultOb = Result(id = idAdd, name = nameAdd, price =  priceAdd, priceLargeUnit = priceLUAdd,
////                createdDate = createDateAdd.toString(), modifiedDate = modifDateAdd.toString(), maxQuantity = maxquantityAdd, unit = unitAdd.toString())
////            val db = DataBaseHelper(this)
////            db.insertData(resultOb)
//            var bundle : Bundle? = data?.getBundleExtra("bundle")
//            try {
//                var resultOb = bundle?.getSerializable("resultOb") as Result
//                val db = DataBaseHelper(this)
//                db.insertData(resultOb)
//            }catch (e : Exception){
//                Log.e("vq",e.toString())
//            }
//        }
//        if (requestCode == REQUES_CODE_EDIT && resultCode == RESULT_OK){
//
//        }
//        else{
//            Log.e("vq","fail result")
//        }
        if (resultCode == RESULT_OK){
            var bundle : Bundle? = data?.getBundleExtra("bundle")
            var idAdd : Int = bundle?.getInt("idAdd")!!
            var nameAdd : String = bundle?.getString("nameAdd")!!
            var priceAdd : Int = bundle?.getInt("priceAdd")
            var priceLUAdd : Int = bundle?.getInt("priceLUAdd")
            var createDateAdd : String = bundle?.getString("createDateAdd")!!
            var modifDateAdd : String = bundle?.getString("modifDateAdd")!!
            var maxquantityAdd : Int = bundle?.getInt("maxquantityAdd")
            var unitAdd : String = bundle?.getString("unitAdd")!!

            var resultOb = Result(id = idAdd, name = nameAdd, price =  priceAdd, priceLargeUnit = priceLUAdd,
                createdDate = createDateAdd.toString(), modifiedDate = modifDateAdd.toString(), maxQuantity = maxquantityAdd, unit = unitAdd.toString())
            var db = DataBaseHelper(this)
            if (requestCode == REQUES_CODE_ADD){
                db.insertData(resultOb)
            }
            if (requestCode == REQUES_CODE_EDIT){
                db.updateData(resultOb, obj.id)
            }
//            try {
//                var resultOb = bundle?.getSerializable("resultOb") as Result
//                val db = DataBaseHelper(this)
//
//                if (requestCode == REQUES_CODE_ADD){
//                    db.insertData(resultOb)
//                }
//                if (requestCode == REQUES_CODE_EDIT){
//                    db.updateData(resultOb, obj.id)
//                }
//            }catch (e : Exception){
//                Log.e("vq",e.toString())
//            }

        }else{
            Log.e("vq","fail result")
        }
    }

}