package com.nithin.kotlinmvvm.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nithin.kotlinmvvm.R
import com.nithin.kotlinmvvm.callbacks.AlbumCallbacks
import com.nithin.kotlinmvvm.databinding.AlbumItemBinding
import com.nithin.kotlinmvvm.model.AlbumData
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumRecyclerViewAdapter(
    val callbacks: AlbumCallbacks
) : RecyclerView.Adapter<AlbumRecyclerViewAdapter.MyViewHolder>() {

    private lateinit var binding : AlbumItemBinding
    private lateinit var mContext : Context

    private val items = mutableListOf<AlbumData>()

    inner class MyViewHolder(val binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.cardItem.setOnClickListener {
                callbacks.onItemClick(adapterPosition)
            }
        }
    }

    fun updateAllAlbumsList(albums : List<AlbumData>){
        items.clear()
        items.addAll(albums)
        notifyDataSetChanged()
    }

    fun updateImageItem(position: Int){

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.album_item,parent,false)
        mContext = parent.context
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            val albumItem = items[position]
            if(holder.adapterPosition>-1){
                holder.binding.apply {
                    CoroutineScope(Dispatchers.Main).launch {
                        albumId.text = albumItem.albumId.toString()
                        albumurl.text = albumItem.thumbnailUrl.toString()
                    }
                    Picasso.get().load(albumItem.thumbnailUrl).resize(80,80).into(albumImage)
                }
            }
        }catch (t : Exception){
            t.printStackTrace()
        }
    }

}