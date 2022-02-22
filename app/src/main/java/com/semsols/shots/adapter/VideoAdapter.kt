package com.semsols.shots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.semsols.shots.R
import com.semsols.shots.model.VideoModel


class VideoAdapter(options: FirebaseRecyclerOptions<VideoModel?>):
    FirebaseRecyclerAdapter<VideoModel?,VideoAdapter.MyViewHolder>(options){


    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: VideoModel) {
        holder.setData(model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_video_row,parent,false)

        return MyViewHolder(view)
    }



    inner class MyViewHolder(v: View): RecyclerView.ViewHolder(v){

        var videoView: VideoView
        var title: TextView
        var desc: TextView
        var pbar: ProgressBar
        var fav: ImageView
        var isFav = false

        fun setData(obj: VideoModel){

            videoView.setVideoPath(obj.url)
            title.setText(obj.title)
            desc.setText(obj.desc)
            videoView.setOnPreparedListener { mediaPlayer->
                pbar.visibility = View.GONE
                mediaPlayer.start()
            }

            videoView.setOnCompletionListener { mediaPlayer-> mediaPlayer.start() }

            fav.setOnClickListener {

                if(!isFav){
                    fav.setImageResource(R.drawable.favorite_filled)
                    isFav = true;
                }else{
                    fav.setImageResource(R.drawable.favorite_border)
                    isFav = false
                }
            }


        }
        init {

            videoView = v.findViewById(R.id.videoView) as VideoView
            title = v.findViewById(R.id.videoTitleText) as TextView
            desc = v.findViewById(R.id.videoTitleDesc) as TextView
            pbar = v.findViewById(R.id.videoProgressBar) as ProgressBar
            fav = v.findViewById(R.id.favoriteBtn) as ImageView


        }

    }


}