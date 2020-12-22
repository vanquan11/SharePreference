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
import kotlinx.android.synthetic.main.activity_add_merchandise.*
import kotlinx.android.synthetic.main.activity_data_preference.*
import java.lang.Exception

class DataPreferenceActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HHAdapter
    var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    private var REQUEST_CODE_ADD  : Int = 1
    private var REQUEST_CODE_EDIT  : Int = 2
    lateinit var obj : Result
    private val db  = DataBaseHelper(this)
    private lateinit var userDetail : DataModel
    private var listData = mutableListOf<Result>()

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
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        registerForContextMenu(recyclerView)
        compositeDisposable!!.add(
                APIClient.getClient.getData("ss-id=${userDetail.sessionId}")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                         onNext = {
                             Log.e("vq","On next Get " )
                             db.deleteDatabase()
                             db.writeData(it.results as MutableList<Result>)
                             listData.addAll(db.readData())
                             adapter = HHAdapter(listData){view, resultob ->
                                 openContextMenu(view)
                                 obj = resultob
                             }
                             adapter.apply {
                                 notifyDataSetChanged()
                             }
                             recyclerView.adapter = adapter
                         },
                         onComplete = {
                             Log.e("vq","CompleteGet")
                          },
                         onError = {
                             Log.e("vq","onErrorGet ${it.message}")
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
                bundle.putSerializable("key", obj)
                intent.putExtra("bundle", bundle)
                startActivityForResult(intent, REQUEST_CODE_EDIT)
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
                                            db.deleteData(obj.id)
                                            listData.clear()
                                            listData.addAll(db.readData())
                                            adapter = HHAdapter(listData){view, resultob ->
                                                openContextMenu(view)
                                                obj = resultob
                                            }
                                            adapter.apply {
                                                notifyItemRemoved(obj.id)
                                            }
                                            recyclerView.adapter = adapter
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
                startActivityForResult(intent, REQUEST_CODE_ADD)
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
            try {
                var product = bundle?.getSerializable("key") as ProductToPost
                if (requestCode == REQUEST_CODE_ADD){
                    compositeDisposable!!.add(
                            APIClient.getClient.postData("ss-id=${userDetail.sessionId}", product)
                                    .map {
                                        it
                                    }
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeBy(
                                            onNext = {
                                                Log.e("vq","onNextPost" )
                                                db.insertData(it)
//                                                listData.clear()
                                                listData.addAll(db.readData())
                                                adapter = HHAdapter(listData){view, resultob ->
                                                    openContextMenu(view)
                                                    obj = resultob
                                                }
                                                adapter.apply {
                                                    notifyDataSetChanged()
                                                }
                                                recyclerView.adapter = adapter
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
                if (requestCode == REQUEST_CODE_EDIT){
                    product.productPost.id = obj.id
                    compositeDisposable!!.add(
                            APIClient.getClient.postData("ss-id=${userDetail.sessionId}", product)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeBy(
                                            onNext = {
                                                Log.e("vq","onNextPut" )
                                                db.updateData(it, obj.id)
                                                listData.clear()
                                                listData.addAll(db.readData())
                                                adapter = HHAdapter(listData){view, resultob ->
                                                    openContextMenu(view)
                                                    obj = resultob
                                                }
                                                adapter.apply {
                                                    notifyItemChanged(obj.id)
                                                }
                                                recyclerView.adapter = adapter
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
            }catch (e : Exception){
                Log.e("vq",e.toString())
            }
        }else{
            Log.e("vq","fail result")
        }
    }
}