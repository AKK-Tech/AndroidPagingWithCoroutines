package ke.co.appslab.androidpagingwithcoroutines.networking

import ke.co.appslab.androidpagingwithcoroutines.models.RedditApiResponse
import ke.co.appslab.androidpagingwithcoroutines.models.unsplash.UnsplashApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>

    @GET("/photos")
    suspend fun fetchPhotos(
        @Query("") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String,
        @Query("client_id") clientId: String
    ): Response<UnsplashApiResponse>
}