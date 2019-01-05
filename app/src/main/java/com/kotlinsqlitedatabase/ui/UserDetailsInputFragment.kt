package com.kotlinsqlitedatabase.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.deeksha.kotlinsqlitedatabase.database.KtSqliteDatabase
import com.kotlinsqlitedatabase.R
import com.kotlinsqlitedatabase.datamodel.UserModel
import com.kotlinsqlitedatabase.ktinterface.RecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_user_details_input.view.*


class UserDetailsInputFragment : Fragment() {

    lateinit var onItemClick: RecyclerItemClickListener
    lateinit var username: EditText
    lateinit var useradddress: EditText
    lateinit var userphone: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_details_input, container, false)
        username = view.user_name
        useradddress = view.user_address
        userphone = view.user_phone

        onItemClick = context as RecyclerItemClickListener
        val ktSqliteDatabase = KtSqliteDatabase(activity!!)

        view.save_btn.setOnClickListener { view ->
            //Toast.makeText(activity, "hello", Toast.LENGTH_LONG).show()
            var sucess = ktSqliteDatabase.insertUserData(
                UserModel(
                    username.text.toString(),
                    useradddress.text.toString(),
                    userphone.text.toString().toLong()

                )
            )
            if (sucess) {
                username.setText("")
                useradddress.setText("")
                userphone.setText("")
                onItemClick.onItemClick(0)
            }

        }
        return view
    }


    companion object {
        fun newInstance() = UserDetailsInputFragment()
    }
}
