package com.example.android.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.android.OnFragmentInteractionListener
import com.example.android.R

class ContentFragment : Fragment() {
    private var mOnFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false)

        val buttonAbout = view.findViewById(R.id.buttonAbout) as Button
        val buttonAboutMe = view.findViewById(R.id.buttonAboutMe) as Button
        val buttonExit = view.findViewById(R.id.buttonExit) as Button

        buttonAbout.setOnClickListener {
            mOnFragmentInteractionListener?.onFragmentInteraction(R.id.buttonAbout)
        }
        buttonAboutMe.setOnClickListener {
            mOnFragmentInteractionListener?.onFragmentInteraction(R.id.buttonAboutMe)
        }
        buttonExit.setOnClickListener {
            mOnFragmentInteractionListener?.onFragmentInteraction(R.id.buttonExit)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnFragmentInteractionListener = context as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context должен реализовывать интерфейс OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        mOnFragmentInteractionListener = null
        super.onDetach()
    }
}