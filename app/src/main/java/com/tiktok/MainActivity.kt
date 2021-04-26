package com.tiktok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.tiktok.util.showToast
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

     private lateinit var videoRecyclerView:RecyclerView

    private val VideoList = ArrayList<Video>()

    private fun refresh(){
        thread {
            val request = Request.Builder()
                .url("http://api.tianapi.com/txapi/dyvideohot/index?key=" + MyApplication.KEY).build()
            val reponse = OkHttpClient().newCall(request).execute()
            val json = reponse.body?.string()
            val videoResponse = Gson().fromJson(json, VideoResponse::class.java)
            if (videoResponse != null) {
                val data = videoResponse.newslist
                VideoList.clear()
                VideoList.addAll(data)
                runOnUiThread() {
                    videoRecyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoRecyclerView = findViewById<RecyclerView>(R.id.VideoRecycleView) as RecyclerView
        videoRecyclerView.layoutManager = LinearLayoutManager(this)

        videoRecyclerView.adapter = VideoAdapter(VideoList)

        refresh()
    }

    inner class VideoAdapter(private val VideoList: List<Video>) :
        RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

        inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.video_title)
            val author: TextView = itemView.findViewById(R.id.video_author)
            val image: ImageView = itemView.findViewById(R.id.video_image)
            val icon: ImageView = itemView.findViewById(R.id.video_upicon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return VideoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
            val video = VideoList[position]
            holder.title.text = video.title
            holder.author.text = video.author
            //图片加载
            Glide.with(this@MainActivity).load(video.coverurl).into(holder.image)
            Glide.with(this@MainActivity).load("https://s1.hdslb.com/bfs/static/jinkela/popular/assets/icon_up.png").into(holder.icon)
            holder.itemView.setOnClickListener {
                val intent = Intent(this@MainActivity,DetailActivity::class.java)
                intent.putExtra("url=",VideoList[holder.adapterPosition].shareurl)
                startActivity(intent)
            }

        }

        override fun getItemCount(): Int {
            return VideoList.size
        }

    }
}



