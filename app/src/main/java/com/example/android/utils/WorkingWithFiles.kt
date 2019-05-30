package com.example.android.utils

import android.content.Context
import android.os.Environment
import android.widget.EditText
import android.widget.TextView
import java.io.*

private val PATH = "data//com.example.android//files//"

fun renameFile(fileName: String, newFilename: String, editText: EditText, context: Context) {
    saveFile(newFilename, editText, context)
    deleteFiles(fileName)
}

fun deleteFiles(fileName: String) {
    try {
        val path = "$PATH$fileName"
        val getDirectory = Environment.getDataDirectory()
        val file = File("$getDirectory//$path")
        file.delete()
    } catch (e: Exception) {
    }
}

fun saveFile(fileName: String, editText: EditText, context: Context) {
    val osw = OutputStreamWriter(context.openFileOutput("$fileName.txt", 0))
    osw.use {
        osw.write(editText.text.toString())
    }
}

fun readFileText(fileName: String, textView: TextView, context: Context) {
    val br = BufferedReader(InputStreamReader(context.openFileInput(fileName)))
    br.use {
        var str: String?
        var i = 0
        do {
            str = br.readLine()
            if (str != null)
                textView.append("${i++}. $str\n")
        } while (str != null)
    }
}

fun readFileEditText(fileName: String, editText: EditText, context: Context) {
    val br = BufferedReader(InputStreamReader(context.openFileInput(fileName)))
    br.use {
        var str: String?
        do {
            str = br.readLine()
            if (str != null)
                editText.append("$str\n")
        } while (str != null)
    }
}

fun isExists(fileName: String): Boolean {
    return File("//data//$PATH$fileName").exists()
}

fun increaseDefaultValueOfFile(fileName: String): String {
    val newFileName: String
    if (fileName.substring(fileName.length - 1) == ")") {
        newFileName = checkFileName(fileName)
    } else
        newFileName = checkFileName("$fileName(1)")
    return newFileName
}

fun checkFileName(fileName: String): String {
    var number = fileName.substringAfter('(').substringBefore(')').toInt()
    var newFileName = fileName
    while (isExists("$newFileName.txt")) {
        var fileNameWithoutNumber = fileName.dropLast(2)
        newFileName = "$fileNameWithoutNumber${number++})"
    }
    return newFileName
}

fun getFileList(path: String): List<String> {
    val stringFileName = ArrayList<String>()
    val getDirectory = Environment.getDataDirectory()
    val file = File("$getDirectory//$path")
    if (file.listFiles() != null) {
        val listOfFiles = file.listFiles()
        for (i in listOfFiles.indices) {
            if (listOfFiles[i].name.toString().substring
                    ((listOfFiles[i].name.toString()).length - 4) == ".txt"
            )
                stringFileName.add(listOfFiles[i].name.toString())
        }
    }
    return stringFileName
}