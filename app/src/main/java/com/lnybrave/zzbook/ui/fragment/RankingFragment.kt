package com.lnybrave.zzbook.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnybrave.zzbook.R

class RankingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_ranking, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String): RankingFragment {
            val fragment = RankingFragment()
            return fragment
        }
    }
}
