package com.kotlinsqlitedatabase.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.kotlinsqlitedatabase.R
import kotlinx.android.synthetic.main.fragment_delete_details.view.*


class DeleteDetailsFragment : Fragment() {

    lateinit var username: EditText
    lateinit var useradddress: TextView
    lateinit var userphone: TextView
    lateinit var userid: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_delete_details, container, false)

        username = view.delete_name
        useradddress = view.delete_address
        userphone = view.delete_phone
        userid = view.delete_id
        return view
    }

    companion object {
        fun newInstance() = DeleteDetailsFragment()
    }

}
