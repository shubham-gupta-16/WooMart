package com.shubhamgupta16.woomart.adapter.viewholder

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.ui.coupon.CouponActivity
import me.gilo.woodroid.models.Coupon

class CouponViewHolder(val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun renderView(coupon: Coupon) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        tvTitle.text = coupon.code?.toUpperCase()
        tvDescription.text = Html.fromHtml(coupon.description)

        itemView.setOnClickListener{
            val intent = Intent(context, CouponActivity::class.java)
            intent.putExtra("couponId", coupon.id)

            context.startActivity(intent)
        }
    }


}