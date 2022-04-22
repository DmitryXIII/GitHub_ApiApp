package com.ineedyourcode.githubapiapp.ui.screens.userdetails.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.githubapiapp.R
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto

class UserDetailsRecyclerViewAdapter(val onItemClickListener: OnRepositoryItemClickListener) :
    RecyclerView.Adapter<UserDetailsRecyclerViewAdapter.UserSearchResultViewHolder>() {
    private var repositoriesList = listOf<GitHubUserRepositoryDto>()

    fun setData(mRepositoriesList: List<GitHubUserRepositoryDto>) {
        repositoriesList = mRepositoriesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchResultViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_user_details_repositories_list_recycler_view_item,
                    parent,
                    false)
        return UserSearchResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserSearchResultViewHolder, position: Int) {
        holder.repositoryName.text = repositoriesList[position].name
        holder.repositoryLanguage.text = repositoriesList[position].language
    }

    override fun getItemCount() = repositoriesList.size

    inner class UserSearchResultViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onUserSearchItemClickListener(repositoriesList[adapterPosition].name)
            }
        }

        val repositoryName: TextView = itemView.findViewById(R.id.repository_name_text_view)
        val repositoryLanguage: TextView = itemView.findViewById(R.id.repository_language_text_view)
    }
}