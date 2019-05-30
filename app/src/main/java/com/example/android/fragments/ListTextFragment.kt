package com.example.android.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import com.example.android.OnFragmentInteractionListener
import com.example.android.R
import com.example.android.StartFragment
import com.example.android.utils.getFileList

class ListTextFragment : ListFragment() {
   private var mOnStartFragmentListener: StartFragment? = null
    private var mOnFragmentInteractionListener: OnFragmentInteractionListener? = null
   private lateinit var textList: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_text, container, false)

        val listView = view.findViewById(android.R.id.list) as ListView
        textList = getFileList("data//com.example.android//files")
        val adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_list_item_1, textList
        )
        listView.adapter = adapter

        val buttonAdd = view.findViewById(R.id.buttonAdd) as Button
        buttonAdd.setOnClickListener {
            mOnStartFragmentListener?.startFragment()
        }
        return view
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        mOnFragmentInteractionListener?.onFragmentInteraction(textList[position])
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnStartFragmentListener = context as StartFragment
            mOnFragmentInteractionListener = context as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context должен реализовывать интерфейс StartFragment")
        }
    }

    override fun onDetach() {
        mOnStartFragmentListener = null
        mOnFragmentInteractionListener = null
        super.onDetach()
    }

}

