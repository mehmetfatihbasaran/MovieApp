package com.example.movieapp.common.base

import com.example.movieapp.common.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiState<T>> = flow {
        emit(ApiState.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(ApiState.Success(body))
                }
            } else {
                emit(ApiState.Failure(Throwable(response.message())))
            }
        } catch (e: Exception) {
            emit(ApiState.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

}