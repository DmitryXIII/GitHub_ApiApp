package com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchRecyclerViewItemBinding

class UserSearchRecyclerViewAdapter(val onItemClickListener: OnUserSearchItemClickListener) :
    RecyclerView.Adapter<UserSearchRecyclerViewAdapter.UserSearchResultViewHolder>() {
    private var userList = mutableListOf<GitHubUserProfileDto>()

    fun setData(mUserList: List<GitHubUserProfileDto>) {
        userList = mUserList as MutableList<GitHubUserProfileDto>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchResultViewHolder {
        val binding =
            FragmentUserSearchRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return UserSearchResultViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserSearchResultViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size

    inner class UserSearchResultViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(user: GitHubUserProfileDto) {
            FragmentUserSearchRecyclerViewItemBinding.bind(itemView).apply {
                userLoginTextView.text = user.login
                userAvatarImageView.load(user.avatarUrl)
                itemView.setOnClickListener {
                    onItemClickListener.onUserSearchItemClickListener(userList[adapterPosition].login)
                }
            }
        }
    }
}