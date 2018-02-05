package com.ljf.eyepetizer.views.jsonview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ljf.eyepetizer.R
import com.ljf.eyepetizer.utils.CommonUtils
import com.ljf.eyepetizer.utils.glide.GlideUtils
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

        var user = json.optJSONObject("user")
        var reply = json.optJSONObject("reply")
        var simpleVideo = json.optJSONObject("simpleVideo")

        GlideUtils.loadCircleImage(context, user.optString("avatar"), avatarIv)

        nicknameTv.text = user.optString("nickname")

        textTv.text = json.optString("text")

        messageTv.text = reply.optString("message")

        GlideUtils.loadImage(context, simpleVideo.optJSONObject("cover").optString("feed"), coverIv)

        titleTv.text = simpleVideo.optString("title")

        categoryTv.text = simpleVideo.optString("category")

        likecountTv.text = String.format("%s %s", context.getString(R.string.fabulous), reply.optString("likeCount"))

        createdateTv.text = CommonUtils.showCurrentTime(json.optString("createDate").toLong())
    }
}