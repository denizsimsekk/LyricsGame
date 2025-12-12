package com.example.lyricsgame.ui.base

import androidx.lifecycle.ViewModel
import com.example.lyricsgame.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _baseUiState = MutableStateFlow(BaseUiState())
    val baseUiState = _baseUiState.asStateFlow()

    fun <T> Flow<Resource<T>>.getData(onDataReceived: ((T?) -> Unit), onError: (() -> Unit)? = null): Flow<Resource<T>> {
        return this.onEach { response ->
            when (response) {
                is Resource.Success -> {
                    onDataReceived.invoke(response.data)
                }

                is Resource.Failure -> {
                    onError?.invoke() ?: _baseUiState.update {
                        it.copy(errorMessage = response.errorMessage)
                    }
                }
            }
        }
    }

}