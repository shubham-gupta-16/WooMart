package com.shubhamgupta16.woomart.adapter.viewholder

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.utils.DateUtils
import me.gilo.woodroid.models.ProductReview

class ProductReviewViewHolder(val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun renderView(review: ProductReview) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        val rbRating = itemView.findViewById<RatingBar>(R.id.rbRating)
        val tvMessage = itemView.findViewById<TextView>(R.id.tvMessage)
        val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)

        tvName.text = review.reviewer
        tvMessage.text = Html.fromHtml(review.review)

        rbRating.rating = review.rating.toFloat()
        tvDate.text = DateUtils.getDateString_shortAndSmart(review.date_created)

        if (review.reviewer_avatar_urls["96"]!!.isNotEmpty()){
            Picasso.with(context).load(review.reviewer_avatar_urls["96"]).into(ivImage)
        }

    }


}