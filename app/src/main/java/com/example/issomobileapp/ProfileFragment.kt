package com.example.issomobileapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.issomobileapp.databinding.FragmentProfileBinding
import com.parse.ParseException
import com.parse.ParseUser


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    // MARK: Override Functions
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
            contact.setOnClickListener {
                sendEmail(message.text.toString(), spinner2.selectedItem.toString())
            }
        }

    }

    // MARK: Private Functions
    private fun logOut() {
        ParseUser.logOutInBackground { e: ParseException? ->
            if (e == null) {
                val intent = Intent(context, SignInActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendEmail(message: String, subject: String) {
        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        val recipient = "oknaumo@yandex.ru"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

}