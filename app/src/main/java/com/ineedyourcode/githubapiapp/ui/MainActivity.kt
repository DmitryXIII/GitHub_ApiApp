package com.ineedyourcode.githubapiapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.databinding.ActivityMainBinding
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsController
import com.ineedyourcode.githubapiapp.ui.screens.userdetails.UserDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.userrepositorydetails.UserRepositoryDetailsFragment
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchController
import com.ineedyourcode.githubapiapp.ui.screens.usersearch.UserSearchFragment

class MainActivity : AppCompatActivity(), UserSearchController, UserDetailsController {
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

    override fun showUserDetails(login: String) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.mainFragmentContainerView.id, UserDetailsFragment.newInstance(login))
            .addToBackStack(getString(R.string.empty_text))
            .commit()
    }

    override fun showRepositoryDetails(repositoryOwnerLogin: String, repositoryName: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container_view,
                UserRepositoryDetailsFragment.newInstance(
                    repositoryOwnerLogin,
                    repositoryName))
            .addToBackStack("")
            .commit()
    }
}