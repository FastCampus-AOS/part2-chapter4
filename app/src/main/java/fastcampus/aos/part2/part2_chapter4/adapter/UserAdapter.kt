package fastcampus.aos.part2.part2_chapter4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aos.part2.part2_chapter4.databinding.ItemUserBinding
import fastcampus.aos.part2.part2_chapter4.model.User

class UserAdapter(val onClick: (User) -> Unit) : ListAdapter<User, UserAdapter.UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class UserViewHolder(private val viewBinding: ItemUserBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: User) {
            viewBinding.usernameTextView.text = item.username
            viewBinding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =  oldItem == newItem
        }
    }
}