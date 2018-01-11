package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.ViewData
import com.ljf.eyepetizer.utils.JsonViewUtils
import com.ljf.eyepetizer.views.ViewEnd
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by mr.lin on 2017/12/13.
 * 首页fragment
 */
class HomeFragment : BaseFragment() {

    var json: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Requester.apiService().getTabDiscovery().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                json = response.body()!!.string()
                if (linearlayout != null) {
                    toModel(json)
                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_home, container, false)
        //NullPointerException 此时控件没有初始化
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!TextUtils.isEmpty(json)) {
            toModel(json)
        }
    }

    fun toModel(string: String) {
        var json = JSONObject(string)
        var jsonArray = json.getJSONArray("itemList")
        for (i in 0 until jsonArray.length()) {
            var viewData = ViewData(jsonArray.getJSONObject(i))
            var view = JsonViewUtils.viewDataToView(context, viewData)
            if (view != null) {
                linearlayout.addView(view)
            }
        }

        linearlayout.addView(ViewEnd(context))
    }

}