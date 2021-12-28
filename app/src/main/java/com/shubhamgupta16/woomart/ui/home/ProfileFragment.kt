package com.shubhamgupta16.woomart.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.shubhamgupta16.woomart.R
import com.shubhamgupta16.woomart.ui.customer.ProfileActivity
import com.shubhamgupta16.woomart.ui.onboarding.SignUpActivity
import com.shubhamgupta16.woomart.ui.order.MyOrdersActivity
import com.shubhamgupta16.woomart.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    val viewModel: UserViewModel by viewModels()
    val TAG = "ProfileFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llMyProfile.setOnClickListener{
            if (FirebaseAuth.getInstance().currentUser != null) {
                startActivity(Intent(activity, ProfileActivity::class.java))
            }else{
                startActivity(Intent(activity, SignUpActivity::class.java))
            }
        }

        llLogout.setOnClickListener{
           viewModel.logout()
            startActivity(Intent(activity, SignUpActivity::class.java))
            Log.d(TAG, "onViewCreated: signout")
        }

        llMyOrders.setOnClickListener{
            startActivity(Intent(activity, MyOrdersActivity::class.java))
        }


    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ProfileFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

}
