package com.example.android.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.android.R
import com.example.android.utils.*
import kotlinx.android.synthetic.main.fragment_text_refactor.*

class TextRefactorFragment : Fragment() {
    var fileName = "fileName"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_text_refactor, container, false)
        setText(view)
        return view
    }

    private fun setText(view: View) {
        val editTextRefactor = view.findViewById<EditText>(R.id.editTextRefactor)
        editTextRefactor.setText("")
        readFileEditText(fileName, editTextRefactor, view.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            fileName = arguments!!.getString("FILENAME")
        }
    }

    fun sendData(data: String): TextRefactorFragment {
        val fragment = TextRefactorFragment()
        val args = Bundle()
        args.putString("FILENAME", data)
        fragment.arguments = args
        return fragment
    }

    override fun onPause() {
        super.onPause()
        val fileNameWithoutNumber = if (fileName.substring(fileName.length - 5) == ").txt") {
            fileName.dropLast(7)
        } else {
            fileName.dropLast(4)
        }
        var newName = editTextRefactor.text.toString().substringBefore("\n")
        if (!editTextRefactor.text.isEmpty() && "$fileNameWithoutNumber.txt" != "$newName.txt") {
            if (isExists("$newName.txt"))
                newName = increaseDefaultValueOfFile(newName)
            renameFile(fileName, newName, editTextRefactor, view!!.context)
        }
        if ("$fileNameWithoutNumber.txt" == "$newName.txt") {
            saveFile(fileNameWithoutNumber, editTextRefactor, view!!.context)
        }
    }
}