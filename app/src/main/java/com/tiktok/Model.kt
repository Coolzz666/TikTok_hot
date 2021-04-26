package com.tiktok

data class Video(
    val hotindex: String,
    val title: String,
    val author: String,
    val coverurl: String,
    val shareurl: String,
    val avatar: String
)


data class VideoResponse(val code: Int, val msg: String, val newslist: List<Video>)

