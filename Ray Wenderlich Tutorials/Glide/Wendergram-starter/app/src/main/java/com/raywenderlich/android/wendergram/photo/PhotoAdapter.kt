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

/**
 * This is a RecylerView Adapter. Use onBindViewHolder to bind the data to PhotoViewHolder.
 * Open the declaration of holder.bind(photoUrl) by holding Command (Ctrl key on Windows)
 * and clicking on the word bind. Android Studio opens PhotoViewHolder.kt where you won’t
 * find anything implemented yet in bind(photoUrl: String?).
 */
class PhotoAdapter(private val list: List<String?>)
  : RecyclerView.Adapter<PhotoViewHolder>() {

  private var recyclerViewItemListener: ((String?) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return PhotoViewHolder(inflater, parent)
  }

  override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
    val photoUrl = list[position]
    holder.itemView.setOnClickListener {
      recyclerViewItemListener?.invoke(photoUrl)
    }
    holder.bind(photoUrl)
  }

  fun setItemClickListener(listener: (photoUrl: String?) -> Unit) {
    recyclerViewItemListener = listener
  }

  override fun getItemCount(): Int = list.size

}


