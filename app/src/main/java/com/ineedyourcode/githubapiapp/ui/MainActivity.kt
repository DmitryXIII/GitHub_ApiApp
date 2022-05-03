package com.ineedyourcode.githubapiapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ineedyourcode.githubapiapp.databinding.ActivityMainBinding
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.mainFragmentContainerView.id, UserSearchFragment())
                .commit()
        }
    }
}