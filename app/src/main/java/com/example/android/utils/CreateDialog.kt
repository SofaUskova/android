package com.example.android.utils

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AlertDialog

fun onCreateDialogAbout(context: Context): Dialog {
    val builder = AlertDialog.Builder(context)
    return builder
        .setTitle("О программе")
        .setMessage("Тут крч есть текстовый редактор и калькулятор...")
        .setPositiveButton("OK", null)
        .create()
}

fun onCreateDialogAboutMe(context: Context): Dialog {
    val builder = AlertDialog.Builder(context)
    return builder
        .setTitle("Об аворе")
        .setMessage("*крик чаек*")
        .setPositiveButton("OK", null)
        .create()
}

fun onCreateDialogExit(context: Context): Dialog {
    val builder = AlertDialog.Builder(context)
    return builder
        .setMessage("Медленно нажал на отмену...")
        .setNegativeButton("Отмена", null)
        .setPositiveButton("OK", null)
        .create()
}