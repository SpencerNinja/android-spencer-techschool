/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate

import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.MarsApiStatus
import com.example.android.marsrealestate.overview.PhotoGridAdapter

// Add a binding adapter for the listData attribute that calls submitList on the RV adapter
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

// Create the Binding Adapter, converting the imgUrl to a URI with the https scheme
// Uses the Glide library to load an image by URL into an [ImageView]
@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(this)
    }
}

@BindingAdapter("marsApiStatus")
fun ImageView.bindStatus(status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            this.visibility = View.GONE
        }
    }
}