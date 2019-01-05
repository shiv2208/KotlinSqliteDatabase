package com.kotlinsqlitedatabase.Utils

import android.widget.EditText

class ForEmpty {

    fun emptyEditText(name: EditText): Boolean {
        if (name.text.toString().isNotBlank() || name.text.toString().isNotEmpty())
            return true
        else
            return false
    }
}