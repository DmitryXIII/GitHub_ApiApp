package com.ineedyourcode.githubapiapp.ui.screens.usersearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ineedyourcode.githubapiapp.databinding.FragmentUserSearchRecyclerViewItemBinding
import com.ineedyourcode.githubapiapp.domain.entity.UserProfile

class UserSearchRecyclerViewAdapter(val onItemClickListener: OnUserSearchItemClickListener) :
    RecyclerView.Adapter<UserSearchRecyclerViewAdapter.UserSearchResultViewHolder>() {
    private var userList = listOf<UserProfile>()

    fun setData(mUserList: List<UserProfile>) {
        userList = mUserList
    }

    fun clearData() {
        notifyItemRangeRemoved(0, userList.size)
        userList = listOf()
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
        fun bind(user: UserProfile) {
            FragmentUserSearchRecyclerViewItemBinding.bind(itemView).apply {
                userLoginTextView.text = user.login
                userAvatarImageView.load(user.avatar)
                itemView.setOnClickListener {
                    onItemClickListener.onUserSearchItemClickListener(
                        userList[adapterPosition].login)
                }
            }
        }
    }
}