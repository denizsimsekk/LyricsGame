package com.example.lyricsgame.data.model

data class ResponseDto<T>(val isSuccess: Boolean? = null, val data: T?)
