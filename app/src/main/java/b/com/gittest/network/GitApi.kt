package b.com.gittest.network

import b.com.gittest.data.model.GitUserApiModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GitApi : GitApiService {
    private const val HOST = "https://api.github.com"
    private val gson: Gson by lazy {
        GsonBuilder().run {
            create()
        }
    }

    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder().run {
            build()
        }
        Retrofit.Builder().run {
            baseUrl(HOST)
            client(client)
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build()
        }
    }

    private fun request(): GitApiService = retrofit.create(GitApiService::class.java)

    override fun searchUser(query: String, page: Int): Single<GitUserApiModel> {
        return request().searchUser(query, page).subscribeOn(Schedulers.io())
    }
}