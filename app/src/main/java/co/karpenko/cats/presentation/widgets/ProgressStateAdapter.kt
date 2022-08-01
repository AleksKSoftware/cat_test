package co.karpenko.cats.presentation.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import co.karpenko.cats.R

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

class ProgressStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        LoadStateViewHolder(parent)
}

class LoadStateViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.load_state_item, parent, false)
) {
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)

    fun bind(loadState: LoadState) {
        progressBar.visibility = toVisibility(loadState is LoadState.Loading)
    }

    private fun toVisibility(constraint: Boolean): Int = if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
