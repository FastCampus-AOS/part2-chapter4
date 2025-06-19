package fastcampus.aos.part2.part2_chapter4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aos.part2.part2_chapter4.databinding.ItemRepoBinding
import fastcampus.aos.part2.part2_chapter4.model.Repo

class RepoAdapter : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    inner class RepoViewHolder(private val viewBinding: ItemRepoBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Repo) {
            viewBinding.repoNameTextView.text = item.name
            viewBinding.repoDescriptionTextView.text = item.description
            viewBinding.starCountTextView.text = item.starCount.toString()
            viewBinding.forkCountTextView.text = "${item.forkCount}"
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean = oldItem == newItem
        }
    }
}