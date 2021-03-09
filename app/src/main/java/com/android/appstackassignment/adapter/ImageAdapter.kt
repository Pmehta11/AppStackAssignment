package com.android.appstackassignment.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.android.appstackassignment.R
import com.android.appstackassignment.utils.showToast
import com.bumptech.glide.Glide

class ImageAdapter(var context: Context?, var images: ArrayList<String>) : BaseAdapter() {

    override fun getCount(): Int{
        if(!images.isNullOrEmpty())
            return images!!.size
        else
            context?.let { showToast(it,"Its null") }
            return 0
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val picturesView: ImageView
        if (convertView == null) {
            picturesView = ImageView(context)
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER)
            picturesView
                .setLayoutParams(AbsListView.LayoutParams(270, 270))
        } else {
            picturesView = convertView as ImageView
        }

        context?.let {
            Glide.with(it).load(images!![position])
                .placeholder(R.drawable.ic_baseline_image_24).centerCrop()
                .into(picturesView)
        }

        return picturesView
    }

}