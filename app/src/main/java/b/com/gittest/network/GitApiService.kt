package b.com.gittest.network

import b.com.gittest.data.model.GitUserApiModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiService {
    @GET("/search/users")
    fun searchUser(@Query("q") query: String, @Query("page") page: Int) : Single<GitUserApiModel>
}