package ke.co.appslab.androidpagingwithcoroutines.repositories

import androidx.paging.PagingSource
import ke.co.appslab.androidpagingwithcoroutines.models.unsplash.UnsplashApiResponseItem
import ke.co.appslab.androidpagingwithcoroutines.networking.ApiClient
import ke.co.appslab.androidpagingwithcoroutines.networking.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource : PagingSource<Int, UnsplashApiResponseItem>() {
    private val apiService = ApiClient.getClient().create(ApiService::class.java)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashApiResponseItem> {
        return try {
            val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
            val response = apiService.fetchPhotos(
                position,
                params.loadSize,
                "latest",
                ""
            )
            val unsplash = response.body()!!
            LoadResult.Page(
                data = unsplash,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (unsplash.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}