package com.android.appstackassignment.fragment

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.fragment.app.Fragment
import com.android.appstackassignment.R
import com.android.appstackassignment.adapter.ImageAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*

class HomePageFragment : Fragment(R.layout.fragment_gallery) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       galleryGridView.adapter = ImageAdapter(context,getAllShownImagesPath(view.context))

    }

    private fun getAllShownImagesPath(activity: Context): ArrayList<String> {
        val cursor: Cursor
        val listOfAllImages = ArrayList<String>()
        var absolutePathOfImage: String? = null
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )

        cursor = activity.getContentResolver().query(
            uri, projection, null,
            null, null
        )!!

        val column_index_data: Int = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            listOfAllImages.add(absolutePathOfImage)
        }
        return listOfAllImages
    }

}
