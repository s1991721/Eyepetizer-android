package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.view_dynamicinfocard.view.*

/**
 * Created by mr.lin on 2018/1/10.
 */
class ViewDynamicInfoCard(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : BaseJsonView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_dynamicinfocard, this, true)
    }

    override fun initView() {
        var json = data.json

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