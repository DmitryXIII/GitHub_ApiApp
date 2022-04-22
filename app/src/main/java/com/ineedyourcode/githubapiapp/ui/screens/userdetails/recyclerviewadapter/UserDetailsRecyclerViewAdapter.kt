package com.ineedyourcode.githubapiapp.ui.screens.userdetails.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.githubapiapp.data.dto.GitHubUserRepositoryDto
import com.ineedyourcode.githubapiapp.databinding.FragmentUserDetailsRepositoriesListRecyclerViewItemBinding

class UserDetailsRecyclerViewAdapter(val onItemClickListener: OnRepositoryItemClickListener) :
    RecyclerView.Adapter<UserDetailsRecyclerViewAdapter.UserDetailsViewHolder>() {
    private var repositoriesList = listOf<GitHubUserRepositoryDto>()

    fun setData(mRepositoriesList: List<GitHubUserRepositoryDto>) {
        repositoriesList = mRepositoriesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        val binding = FragmentUserDetailsRepositoriesListRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return UserDetailsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        holder.bind(repositoriesList[position])
    }

    override fun getItemCount() = repositoriesList.size

    inner class UserDetailsViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(repository: GitHubUserRepositoryDto) {
            FragmentUserDetailsRepositoriesListRecyclerViewItemBinding.bind(itemView).apply {
                repositoryNameTextView.text = repository.name
                repositoryLanguageTextView.text = repository.language
                itemView.setOnClickListener {
                    onItemClickListener.onUserSearchItemClickListener(
                        repositoriesList[adapterPosition].name)
                }
            }
        }
    }
}