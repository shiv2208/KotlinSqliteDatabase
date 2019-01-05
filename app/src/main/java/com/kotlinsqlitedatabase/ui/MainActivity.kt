package com.kotlinsqlitedatabase.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.deeksha.kotlinsqlitedatabase.database.KtSqliteDatabase
import com.kotlinsqlitedatabase.R
import com.kotlinsqlitedatabase.R.id.recyclerView
import com.kotlinsqlitedatabase.R.id.toolbar
import com.kotlinsqlitedatabase.datamodel.UserModel
import com.kotlinsqlitedatabase.dialog.UserInfoDialog
import com.kotlinsqlitedatabase.ktinterface.RecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.floaing_menu.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.OvershootInterpolator
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity(), RecyclerItemClickListener {

    //for fab menu
    private var fabOpenAnimation: Animation? = null
    private var fabCloseAnimation: Animation? = null
    private var isFabMenuOpen = false

    private val linearLayoutManager = LinearLayoutManager(this)
    private var userList: ArrayList<UserModel> = ArrayList<UserModel>()
    private var recyclerAdapter: RecyclerAdapter? = null
    private val ktSqliteDatabase = KtSqliteDatabase(this)
    val userInfoDialog = UserInfoDialog(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        replaceFragement(UserDetailsInputFragment.newInstance())
        //for database call
        getAnimations()
        loadData()
        //for click listener
        fab_create.setOnClickListener(onItenClick)
        fab_update.setOnClickListener(onItenClick)
        fab_delete.setOnClickListener(onItenClick)
        fab.setOnClickListener(onItenClick)
        //for adapter initilizer

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val recyclerView = recyclerView
        recyclerView.layoutManager = linearLayoutManager


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun loadData() {
        userList = ktSqliteDatabase.getAllUser()
        recyclerAdapter = RecyclerAdapter(this, userList, this)
        recyclerView.adapter = recyclerAdapter
    }

    fun replaceFragement(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commitNow()
    }

    override fun onItemClick(position: Int) {
        loadData()
    }

    //for fab menu
    private fun getAnimations() {
        fabOpenAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        fabCloseAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        collapseFabMenu()
    }


    private fun expandFabMenu() {
        ViewCompat.animate(fab).rotation(45.0f).withLayer().setDuration(300)
            .setInterpolator(OvershootInterpolator(10.0f)).start()
        fab_create.startAnimation(fabOpenAnimation)
        fab_update.startAnimation(fabOpenAnimation)
        fab_delete.startAnimation(fabOpenAnimation)
        isFabMenuOpen = true
    }

    private fun collapseFabMenu() {

        ViewCompat.animate(fab).rotation(0.0f).withLayer().setDuration(300)
            .setInterpolator(OvershootInterpolator(10.0f)).start()
        fab_create.startAnimation(fabCloseAnimation)
        fab_update.startAnimation(fabCloseAnimation)
        fab_delete.startAnimation(fabCloseAnimation)
        isFabMenuOpen = false
    }

    override fun onBackPressed() {

        if (isFabMenuOpen)
            collapseFabMenu()
        else
            super.onBackPressed()
    }

    val onItenClick: View.OnClickListener = View.OnClickListener { View ->
        when (View.id) {
            R.id.fab_create -> {
                replaceFragement(UserDetailsInputFragment.newInstance())
            }
            R.id.fab_update -> {
                replaceFragement(UpdateUserDetails.newInstance())
            }
            R.id.fab_delete -> {
                replaceFragement(DeleteDetailsFragment.newInstance())
            }
            R.id.fab -> {
                if (isFabMenuOpen)
                    collapseFabMenu()
                else
                    expandFabMenu()
            }
        }
    }

}
