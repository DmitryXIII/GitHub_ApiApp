package com.ineedyourcode.githubapiapp.ui.screens.userdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.githubapiapp.databinding.FragmentUserDetailsRepositoriesListRecyclerViewItemBinding
import com.ineedyourcode.githubapiapp.domain.entity.UserProjectRepository

class UserDetailsRecyclerViewAdapter(val onItemClickListener: OnRepositoryItemClickListener) :
    RecyclerView.Adapter<UserDetailsRecyclerViewAdapter.UserDetailsViewHolder>() {
    private var repositoriesList = listOf<UserProjectRepository>()

    fun setData(mRepositoriesList: List<UserProjectRepository>) {
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
        fun bind(repository: UserProjectRepository) {
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