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
import kotlinx.android.synthetic.main.activity_data_preference.*
import java.lang.Exception

class DataPreferenceActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HHAdapter
    var compositeDisposable: CompositeDisposable? = null
    var REQUES_CODE : Int = 1
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
        adapter = HHAdapter(listData){
            openContextMenu(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)

        Log.e("vq","${userDetail.sessionId}")

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
                startActivityForResult(intent, REQUES_CODE)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUES_CODE && resultCode == RESULT_OK){
            val bundle: Bundle? = data?.getBundleExtra("bundle")
            var idAdd : Int = bundle?.getInt("idAdd")!!
            var nameAdd : String = bundle?.getString("nameAdd")!!
            var priceAdd : Int = bundle?.getInt("priceAdd")
            var priceLUAdd : Int = bundle?.getInt("priceLUAdd")
            var createDateAdd : Int = bundle?.getInt("createDateAdd")
            var modifDateAdd : Int = bundle?.getInt("modifDateAdd")
            var maxquantityAdd : Int = bundle?.getInt("maxquantityAdd")
            var unitAdd : Int = bundle?.getInt("unitAdd")

            var resultOb = Result(id = idAdd, name = nameAdd, price =  priceAdd, priceLargeUnit = priceLUAdd,
                createdDate = createDateAdd.toString(), modifiedDate = modifDateAdd.toString(), maxQuantity = maxquantityAdd, unit = unitAdd.toString())

            val db : DataBaseHelper = DataBaseHelper(this)
            db.insertData(resultOb)

//            try {
//                merchandise = bundle?.getSerializable("resultOb") as Result
//                Log.e("vq", "merchandise: ${merchandise.id}, ${merchandise.name}")
//
//            }catch (e : Exception){
//                Log.e("vq",e.toString())
//            }
        }
        else{
            Log.e("vq","fail result")
        }
    }

}