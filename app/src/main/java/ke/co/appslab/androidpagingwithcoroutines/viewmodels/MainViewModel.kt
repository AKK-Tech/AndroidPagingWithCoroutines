package ke.co.appslab.androidpagingwithcoroutines.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ke.co.appslab.androidpagingwithcoroutines.repositories.PostsPagingSource
import ke.co.appslab.androidpagingwithcoroutines.repositories.UnsplashPagingSource

class MainViewModel : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 1, enablePlaceholders = false)
    ) {
        PostsPagingSource()
    }.flow.cachedIn(viewModelScope)

    val unsplashFlow = Pager(
        PagingConfig(pageSize = 10, enablePlaceholders = false)
    ) {
        UnsplashPagingSource()
    }.flow.cachedIn(viewModelScope)


}