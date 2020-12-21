package com.example.sharepreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
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
    var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    var REQUES_CODE_ADD  : Int = 1
    var REQUES_CODE_EDIT  : Int = 2
    lateinit var obj : Result
    val db  = DataBaseHelper(this)
    private lateinit var userDetail : DataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_preference)

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
                             db.deleteDatabase()
                             db.writeData(listData)
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
                compositeDisposable!!.add(
                        APIClient.getClient.deleteData("ss-id=${userDetail.sessionId}", obj.id)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribeBy(
                                        onComplete = {
                                            Log.e("vq","CompleteDelete")
                                        },
                                        onError = {
                                            Log.e("vq","onErrorDelete ${it.message}")
                                        }
                                )
                )
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
            R.id.sync_menu -> {
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            var bundle : Bundle? = data?.getBundleExtra("bundle")

            var nameAdd : String = bundle?.getString("nameAdd")!!
            var priceAdd : Long = bundle?.getLong("priceAdd")
            var priceLUAdd : Int = bundle?.getInt("priceLUAdd")
            var createDateAdd : String = bundle?.getString("createDateAdd")!!
            var modifDateAdd : String = bundle?.getString("modifDateAdd")!!
            var maxquantityAdd : Int = bundle?.getInt("maxquantityAdd")
            var unitAdd : String = bundle?.getString("unitAdd")!!

//            var resultOb = Result(id = idAdd, name = nameAdd, price =  priceAdd, priceLargeUnit = priceLUAdd,
//                createdDate = createDateAdd, modifiedDate = modifDateAdd, maxQuantity = maxquantityAdd, unit = unitAdd)

            var productPost = ProductPostB(name = nameAdd, price = priceAdd)
            var productPut = ProductPostB(name =  nameAdd, price = priceAdd)

            var resultPost = ProductToPost(maxQuantity = maxquantityAdd, productPost = productPost )
            var resultPut = ProductToPost(maxQuantity =  maxquantityAdd, productPost = productPut)

            if (requestCode == REQUES_CODE_ADD){
                compositeDisposable!!.add(
                        APIClient.getClient.postData("ss-id=${userDetail.sessionId}", resultPost)
                                .map {
                                    it
                                }
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribeBy(
                                        onNext = {
                                            Log.e("vq","onNextPost" )
//                                            db.insertData(resultOb)
                                        },
                                        onComplete = {
                                            Log.e("vq","CompletePost")
                                        },
                                        onError = {
                                            Log.e("vq","onErrorPost ${it.message}")
                                        }
                                )
                )
            }
            if (requestCode == REQUES_CODE_EDIT){
                compositeDisposable!!.add(
                        APIClient.getClient.putData("ss-id=${userDetail.sessionId}", resultPut)
                                .map {
                                    it
                                }
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribeBy(
                                        onNext = {
                                            Log.e("vq","onNextPut" )
//                                            db.updateData(resultOb, obj.id)
                                        },
                                        onComplete = {
                                            Log.e("vq","CompletePut")
                                        },
                                        onError = {
                                            Log.e("vq","onErrorPut ${it.message}")
                                        }
                                )
                )

            }

        }else{
            Log.e("vq","fail result")
        }
    }
}