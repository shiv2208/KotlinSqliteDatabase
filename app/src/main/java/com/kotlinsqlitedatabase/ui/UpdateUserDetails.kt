package com.kotlinsqlitedatabase.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.deeksha.kotlinsqlitedatabase.database.KtSqliteDatabase
import com.kotlinsqlitedatabase.R
import com.kotlinsqlitedatabase.Utils.ForEmpty
import com.kotlinsqlitedatabase.datamodel.UserModel
import kotlinx.android.synthetic.main.fragment_update_user_details.view.*
import java.util.*

class UpdateUserDetails : Fragment() {

    lateinit var username: EditText
    lateinit var useradddress: EditText
    lateinit var userphone: EditText
    lateinit var userid: EditText
    lateinit var update: Button
    lateinit var userModel: UserModel
    var forEmpty: ForEmpty=ForEmpty()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_update_user_details, container, false)
        username = view.update_name
        useradddress = view.update_address
        userphone = view.update_phone
        userid = view.update_id
        update = view.update_btn

        val ktSqliteDatabase = KtSqliteDatabase(activity!!)

        userid.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length > 0) {
                    userModel = ktSqliteDatabase.getSingleUserData(s.toString().toInt())
                    setUserData(userModel)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        update.setOnClickListener { View ->
            fieldValidation()
        }

        return view;
    }

    companion object {
        fun newInstance() = UpdateUserDetails()
    }

    fun setUserData(userModel: UserModel) {
        username.setText(userModel.name)
        userphone.setText("" + userModel.phone)
        useradddress.setText(userModel.address)
    }


    fun fieldValidation() {
        if (forEmpty.emptyEditText(username) && forEmpty.emptyEditText(userid)) {
            UserModel(
                username.text.toString(),
                useradddress.text.toString(),
                userphone.text.toString().toLong(),
                userid.text.toString().toInt()
            )
        } else {
            Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
        }


    }

}
