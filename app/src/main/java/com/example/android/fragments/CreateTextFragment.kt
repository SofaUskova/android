package com.example.android.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.R
import com.example.android.utils.increaseDefaultValueOfFile
import com.example.android.utils.isExists
import com.example.android.utils.saveFile
import kotlinx.android.synthetic.main.fragment_create_text.*

class CreateTextFragment : Fragment() {
    private var fileName: String = "Безымянный"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_text, container, false)
    }

    override fun onPause() {
        super.onPause()
        if (!editTextCreate.text.isEmpty()) {
            fileName = editTextCreate.text.toString().substringBefore("\n")
            if (isExists("$fileName.txt"))
                fileName = increaseDefaultValueOfFile(fileName)
        } else {
            if (isExists("$fileName.txt"))
                fileName = increaseDefaultValueOfFile(fileName)
        }

        saveFile(fileName, editTextCreate, view!!.context)
    }

}