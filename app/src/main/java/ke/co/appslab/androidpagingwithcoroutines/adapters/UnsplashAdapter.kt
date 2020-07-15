package ke.co.appslab.androidpagingwithcoroutines.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import ke.co.appslab.androidpagingwithcoroutines.R
import ke.co.appslab.androidpagingwithcoroutines.models.unsplash.UnsplashApiResponseItem
import ke.co.appslab.androidpagingwithcoroutines.utils.UnsplashDiffUtilCallback
import kotlinx.android.synthetic.main.adapter_row.view.*

class UnsplashAdapter :
    PagingDataAdapter<UnsplashApiResponseItem, UnsplashAdapter.UnsplashViewHolder>(
        UnsplashDiffUtilCallback()
    ) {

    class UnsplashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val scoreText: TextView = itemView.score

        fun bind(unsplashApiResponseItem: UnsplashApiResponseItem) {
            with(unsplashApiResponseItem) {
                scoreText.text = altDescription
            }
        }

    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return UnsplashViewHolder(view)
    }
}