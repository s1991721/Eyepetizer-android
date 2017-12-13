package com.ljf.eyepetizer.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R

/**
 * Created by mr.lin on 2017/12/13.
 * 方便管理Fragment的TabItem
 */
class TabItem<T : BaseFragment>(context: Context, attrs: AttributeSet?, defStyleAttr: Int, fragmentClass: Class<T>?) : LinearLayout(context, attrs, defStyleAttr) {

    private var tip: String? = null
    private var selectColor: Int? = null
    private var unselectColor: Int? = null
    private var selectIcon: Drawable? = null
    private var unselectIcon: Drawable? = null

    private var fragmentClass: Class<T>? = null

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)
    constructor(context: Context, fragmentClass: Class<T>) : this(context, null, 0, fragmentClass)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_tabitem, this, true)

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TabItem)
        tip = typeArray?.getString(R.styleable.TabItem_tip)
        selectColor = typeArray?.getColor(R.styleable.TabItem_selectColor, 0)
        unselectColor = typeArray?.getColor(R.styleable.TabItem_unselectColor, 0)
        selectIcon = typeArray?.getDrawable(R.styleable.TabItem_selectIcon)
        unselectIcon = typeArray?.getDrawable(R.styleable.TabItem_unselectIcon)
        typeArray?.recycle()

        this.fragmentClass = fragmentClass
        tag = fragmentClass

    }

}