package b.com.gittest.ui.main

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import b.com.gittest.R
import b.com.gittest.data.model.User
import com.facebook.drawee.view.SimpleDraweeView

class UserAdapter(private var onLikeClick: (Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val userList: ArrayList<User> = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = UserHolder(LayoutInflater.from(p0.context).inflate(R.layout.holder_user_view, p0, false))

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when(p0) {
            is UserHolder -> {
                p0.run {
                    userList[p1].run user@{

                        if (avatarUrl.isNullOrEmpty()) {
                            avatarSimpleDraweeView.setActualImageResource(R.mipmap.ic_launcher_round)
                        } else {
                            avatarSimpleDraweeView.setImageURI(avatarUrl)
                        }
                        nameTextView.text = name

                        likeView.apply {
                            setBackgroundColor(if (isLike) {
                                Color.RED
                            } else {
                                Color.GREEN
                            })
                            setOnClickListener {
                                onLikeClick(this@user.userId)
                            }
                        }
                    }
                }
            }
        }
    }

    fun clearData() {
        userList.clear()
        notifyDataSetChanged()
    }

    fun addUserData(listData: List<User>) {
        userList.run {
            var startPosition = size
            addAll(listData)
            notifyItemRangeInserted(startPosition, listData.size)
        }
    }

    fun updateData(data: User) {
        userList.forEachIndexed { index, user ->
            if (data.userId == user.userId) {
                userList[index] = data
                notifyItemChanged(index)
                return@forEachIndexed
            }
        }
    }

    fun update() {
        notifyDataSetChanged()
    }

    fun removeUser(id: Int) {
        var user = userList.filterIndexed { index, user ->
            if (user.userId == id) {
                notifyItemRemoved(index)
                return@filterIndexed true
            }
            return@filterIndexed  false
        }.first()
        userList.remove(user)
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarSimpleDraweeView: SimpleDraweeView = itemView.findViewById(R.id.avatar_simple_drawee_view)
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        val likeView: View = itemView.findViewById(R.id.like_view)
    }

}