package com.kotlinsqlitedatabase.dialog

import android.app.Dialog
import android.content.Context
import com.kotlinsqlitedatabase.R

class UserInfoDialog(val context: Context) {

    fun insertUserInfo() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.userinfo)
        dialog.show()
    }
}