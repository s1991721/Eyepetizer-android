package com.ljf.eyepetizer.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.ljf.eyepetizer.BaseActivity
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.http.Requester
import com.ljf.eyepetizer.model.Category
import com.ljf.eyepetizer.utils.SpUtils
import com.ljf.eyepetizer.utils.SpUtils.Companion.KEY_CATEGORY_LIST
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

    private var currentFragmentTag: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    var categorys = ArrayList<Category>()

    private fun initData() {
        Requester.apiService().getCategoryList().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                toCategory(response.body()!!.string())
            }
        })
    }

    fun toCategory(json: String) {
        var jsonArry = JSONObject(json).getJSONObject("tabInfo").getJSONArray("tabList")
        for (i in 0 until jsonArry.length()) {
            var jsonObject = jsonArry.getJSONObject(i)
            var category = Category(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("apiUrl"))
            categorys.add(category)
        }
        SpUtils.setList(KEY_CATEGORY_LIST, categorys)
    }

    private fun initView() {
        homeTab.setOnClickListener(onTabItemClickListener)
        findTab.setOnClickListener(onTabItemClickListener)
        hotTab.setOnClickListener(onTabItemClickListener)
        mineTab.setOnClickListener(onTabItemClickListener)

        val params: LinearLayout.LayoutParams? = LinearLayout.LayoutParams(0, MATCH_PARENT)
        params?.weight = 1f

        homeTab.callOnClick()
    }

    private var onTabItemClickListener = View.OnClickListener { v ->
        clearTabItemsState()
        showFragment(Class.forName(v.tag.toString()) as Class<BaseFragment>)
    }

    private fun clearTabItemsState() {
        homeTab.isSelect(false)
        findTab.isSelect(false)
        hotTab.isSelect(false)
        mineTab.isSelect(false)
    }

    private fun showFragment(fragmentClass: Class<BaseFragment>?) {
        val trasaction = supportFragmentManager.beginTransaction()
        val hideFragment = supportFragmentManager.findFragmentByTag(currentFragmentTag)
        if (hideFragment != null) {
            trasaction.hide(hideFragment)
        }

        var showFragment = supportFragmentManager.findFragmentByTag(fragmentClass?.simpleName)

        if (showFragment == null) {
            showFragment = fragmentClass?.newInstance()
            trasaction.add(R.id.frame_layout, showFragment, fragmentClass?.simpleName)
        } else {
            trasaction.show(showFragment)
        }
        trasaction.commit()
        currentFragmentTag = fragmentClass?.simpleName
    }

}
