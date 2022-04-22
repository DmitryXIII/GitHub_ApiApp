package com.ineedyourcode.githubapiapp.ui.screens.usersearch.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserProfileDto

class UserSearchRecyclerViewAdapter(val onItemClickListener: OnUserSearchItemClickListener) :
    RecyclerView.Adapter<UserSearchRecyclerViewAdapter.UserSearchResultViewHolder>() {
    private var userList = mutableListOf<GitHubUserProfileDto>()

    fun setData(mUserList: List<GitHubUserProfileDto>) {
        userList = mUserList as MutableList<GitHubUserProfileDto>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchResultViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_user_search_recycler_view_item, parent, false)
        return UserSearchResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserSearchResultViewHolder, position: Int) {
        holder.userLogin.text = userList[position].login
        holder.userAvatar.load(userList[position].avatarUrl)
    }

    override fun getItemCount() = userList.size

    inner class UserSearchResultViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onUserSearchItemClickListener(userList[adapterPosition].login)
            }
        }

        val userLogin: TextView = itemView.findViewById(R.id.user_login_text_view)
        val userAvatar: ImageView = itemView.findViewById(R.id.user_avatar_image_view)
    }
}