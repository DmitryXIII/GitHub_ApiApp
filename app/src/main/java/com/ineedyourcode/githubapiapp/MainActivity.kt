package com.ineedyourcode.githubapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container_view, UserSearchFragment())
                .commit()
        }
    }
}