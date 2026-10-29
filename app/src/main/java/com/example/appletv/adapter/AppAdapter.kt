package com.example.appletv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appletv.R
import com.example.appletv.model.AppItem

class AppAdapter(
    private val context: Context,
    private val apps: List<AppItem>,
    private val onClickListener: (AppItem) -> Unit
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_app_tile, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.icon.setImageResource(app.iconResId)
        holder.label.text = app.label

        // Scale animation on focus
        holder.itemView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                holder.itemView.animate().scaleX(1.1f).scaleY(1.1f).start()
            } else {
                holder.itemView.animate().scaleX(1f).scaleY(1f).start()
            }
        }

        holder.itemView.setOnClickListener {
            onClickListener(app)
        }
    }

    override fun getItemCount(): Int = apps.size

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.tile_icon)
        val label: TextView = view.findViewById(R.id.tile_label)
    }
}