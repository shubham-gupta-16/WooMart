package com.shubhamgupta16.woomart.ui.coupon

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubhamgupta16.woomart.R
import kotlinx.android.synthetic.main.activity_coupons.*
import kotlinx.android.synthetic.main.content_coupons.*
import com.shubhamgupta16.woomart.adapter.CouponAdapter
import com.shubhamgupta16.woomart.ui.BaseActivity
import me.gilo.woodroid.models.Coupon
import me.gilo.woodroid.models.filters.CouponFilter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CouponsActivity : BaseActivity() {


    lateinit var adapter : CouponAdapter
    lateinit var coupons: ArrayList<Coupon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupons)
        setSupportActionBar(toolbar)

        title = "Coupons"

        val layoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvCoupons.layoutManager = layoutManager
        rvCoupons.isNestedScrollingEnabled = false

        coupons = ArrayList()

        adapter = CouponAdapter(coupons)
        rvCoupons.adapter = adapter

        coupons()

        fab.setOnClickListener{
            startActivity(Intent(baseContext, AddCouponActivity::class.java))
        }

    }

    //Not best practise, but works for purposes of demo
    private fun coupons() {
        val filter = CouponFilter()
        filter.setSearch("FEB")

        woocommerce.CouponRepository().coupons(filter).enqueue(object : Callback<List<Coupon>> {
            override fun onResponse(call: Call<List<Coupon>>, response: Response<List<Coupon>>) {
                val couponResponse = response.body()
                for (coupon in couponResponse!!) {
                    coupons.add(coupon)
                }

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Coupon>>, t: Throwable) {

            }
        })
    }

}
