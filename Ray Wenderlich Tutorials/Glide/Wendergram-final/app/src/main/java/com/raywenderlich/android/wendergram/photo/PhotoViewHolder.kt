/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.wendergram.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.android.wendergram.R
import kotlinx.android.synthetic.main.view_holder_photo.view.*

class PhotoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(
        R.layout.view_holder_photo, parent, false)) {

  /**
   * 1. Append ?w=360 to the URL if the URL is not null. This value assumes that the device screen
   * has 1080px in width. You can set this value dynamically to be one-third of the device’s screen width.
   * 2. Provide a view, fragment or activity to Glide as the life-cycle context.
   * 3. Load the URL.
   * 4. Apply the centerCrop transformation so that the photo fully fills the ImageView.
   * 5. Provide an image resource as a placeholder before Glide starts loading the image.
   * 6. Provide an image resource as an error placeholder when Glide is unable to load the image.
   * This will be shown for the non-existing-url.
   * 7. Use fallback image resource when the url can be null.
   * 8. Set the ImageView of PhotoViewHolder as the target.
   */
  fun bind(photoUrl: String?) {
    val url = if (photoUrl != null) "$photoUrl?w=360" else null //1
    Glide.with(itemView)  //2
        .load(url) //3
        .centerCrop() //4
        .placeholder(R.drawable.ic_image_place_holder) //5
        .error(R.drawable.ic_broken_image) //6
        .fallback(R.drawable.ic_no_image) //7
        .into(itemView.ivPhoto) //8
  }
}
