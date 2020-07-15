package ke.co.appslab.androidpagingwithcoroutines.utils

import androidx.recyclerview.widget.DiffUtil
import ke.co.appslab.androidpagingwithcoroutines.models.RedditPost
import ke.co.appslab.androidpagingwithcoroutines.models.unsplash.UnsplashApiResponseItem

class UnsplashDiffUtilCallback : DiffUtil.ItemCallback<UnsplashApiResponseItem>() {
    override fun areItemsTheSame(
        oldItem: UnsplashApiResponseItem,
        newItem: UnsplashApiResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UnsplashApiResponseItem,
        newItem: UnsplashApiResponseItem
    ): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.likes == newItem.likes &&
                oldItem.altDescription == newItem.altDescription
    }
}