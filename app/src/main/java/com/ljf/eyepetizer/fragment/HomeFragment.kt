package com.ljf.eyepetizer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljf.eyepetizer.BaseFragment
import com.ljf.eyepetizer.R

/**
 * Created by mr.lin on 2017/12/13.
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater?.inflate(R.layout.fragment_home, container, false)
        return view
    }
}