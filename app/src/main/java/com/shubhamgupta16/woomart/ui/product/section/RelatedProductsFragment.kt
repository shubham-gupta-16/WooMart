package com.shubhamgupta16.woomart.ui.product.section

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.section_related_products.*
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.events.ProductEvent
import com.shubhamgupta16.woomart.viewmodels.ProductViewModel
import com.shubhamgupta16.woomart.adapter.HomeProductAdapter
import me.gilo.woodroid.callback.Status
import me.gilo.woodroid.models.Product
import me.gilo.woodroid.models.filters.ProductFilter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class RelatedProductsFragment : Fragment() {


    val viewModel: ProductViewModel by viewModels()
    val TAG = "RelatedProductFragment"

    lateinit var adapter: HomeProductAdapter
    private lateinit var products: ArrayList<Product>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.section_related_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //similarProducts()
    }

    private fun similarProducts(product: Product) {
        val layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rvShop.layoutManager = layoutManager
        rvShop.isNestedScrollingEnabled = false

        products = ArrayList()

        adapter = HomeProductAdapter(products)
        rvShop.adapter = adapter

        val filter = ProductFilter()
        filter.setInclude(product.related_ids.toIntArray())

        viewModel.products(filter).observe(this, androidx.lifecycle.Observer { response ->
            when (response!!.status()) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    products.clear()
                    val productsResponse = response.data()
                    for (product in productsResponse) {
                        products.add(product)
                    }

                    adapter.notifyDataSetChanged()

                }

                Status.ERROR -> {

                }

                Status.EMPTY -> {

                }
            }

        })
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ProductEvent) {
        similarProducts(event.product)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            RelatedProductsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}
