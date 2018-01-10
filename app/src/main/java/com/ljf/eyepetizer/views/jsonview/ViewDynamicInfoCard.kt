package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.view_dynamicinfocard.view.*
import org.json.JSONObject

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewDynamicInfoCard(context: Context, json: JSONObject, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context, json: JSONObject, attrs: AttributeSet?) : this(context, json, attrs, 0)
    constructor(context: Context, json: JSONObject) : this(context, json, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_dynamicinfocard, this, true)

        var user = json.getJSONObject("user")
        var reply = json.getJSONObject("reply")
        var simpleVideo = json.getJSONObject("simpleVideo")

        Glide.with(context).load(user.getString("avatar")).into(avatarIv)

        nicknameTv.text = user.getString("nickname")

        textTv.text = json.getString("text")

        messageTv.text = reply.getString("message")

        Glide.with(context).load(simpleVideo.getJSONObject("cover").getString("feed")).into(coverIv)

        titleTv.text = simpleVideo.getString("title")

        categoryTv.text = simpleVideo.getString("category")

        likecountTv.text = reply.getString("likeCount")

        createdateTv.text = json.getString("createDate")

    }

}