package com.ljf.eyepetizer.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.CommonUtils
import com.ljf.eyepetizer.R
import kotlinx.android.synthetic.main.view_tabitem.view.*

/**
 * Created by mr.lin on 2017/12/13.
 * 方便管理Fragment的TabItem
 */
class TabItem<T : BaseFragment>(context: Context, attrs: AttributeSet?, defStyleAttr: Int, fragmentClass: Class<T>?) : LinearLayout(context, attrs, defStyleAttr) {

    private var tip: String? = null
    private var selectColor: Int
    private var unselectColor: Int
    private var selectIcon: Drawable? = null
    private var unselectIcon: Drawable? = null

    private var isSelect = false

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, fragmentClass: Class<T>) : this(context, null, 0, fragmentClass)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_tabitem, this, true)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TabItem)
        tip = typeArray?.getString(R.styleable.TabItem_tip)
        selectColor = typeArray.getColor(R.styleable.TabItem_selectColor, Color.BLACK)
        unselectColor = typeArray.getColor(R.styleable.TabItem_unselectColor, ContextCompat.getColor(context, R.color.gray))
        selectIcon = typeArray?.getDrawable(R.styleable.TabItem_selectIcon)
        unselectIcon = typeArray?.getDrawable(R.styleable.TabItem_unselectIcon)
        typeArray?.recycle()

        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(CommonUtils.dpTopx(8f), CommonUtils.dpTopx(8f), CommonUtils.dpTopx(8f), CommonUtils.dpTopx(8f))

        if (fragmentClass != null) {
            tag = fragmentClass.name
        }

        show()
    }

    fun setTip(str: String) {
        tip = str
        show()
    }

    fun setColor(select: Int, unselect: Int) {
        selectColor = select
        unselectColor = unselect
        show()
    }

    fun setIcon(select: Drawable, unselect: Drawable) {
        selectIcon = select
        unselectIcon = unselect
        show()
    }

    fun isSelect(select: Boolean) {
        isSelect = select
        show()
    }

    private fun show() {
        name.text = tip
        if (isSelect) {
            name.setTextColor(selectColor)
            image.setImageDrawable(selectIcon)
        } else {
            name.setTextColor(unselectColor)
            image.setImageDrawable(unselectIcon)
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener({ v ->
            if (!isSelect) {
                l?.onClick(v)
                isSelect = !isSelect
                show()
            }
        })
    }

}