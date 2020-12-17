package com.example.sharepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class DataPreferenceActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HHAdapter
    var compositeDisposable: CompositeDisposable? = null
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

        recyclerView = findViewById(R.id.rcvHangHoa)

        Log.e("vq","${userDetail.sessionId}")

        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(
                APIClient.getClient.getData("ss-id=${userDetail.sessionId}")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                         onNext = {
                             Log.e("vq","On next" )
                             adapter = HHAdapter(it.results)
                             recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                             recyclerView.adapter = adapter
                             adapter.notifyDataSetChanged()
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
}