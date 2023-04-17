package com.example.issomobileapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.issomobileapp.databinding.FragmentProfileBinding
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.RequestPasswordResetCallback
import kotlin.math.log

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding


    //region Override Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            logout.setOnClickListener {
                logOut()
            }
        }

    }
    //endregion

    private fun logOut() {
        ParseUser.logOutInBackground { e: ParseException? ->
            if (e == null) {
                val intent = Intent(context, SignInActivity::class.java)
                startActivity(intent)
            }
            else Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

}