package com.example.issomobileapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.issomobileapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    //region Override Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Евпатория
        val evpimageView = binding?.evpcardNewsImage
        val evpimageArray = arrayOf(
            R.drawable.evp1,
            R.drawable.evp2,
            R.drawable.evp3
        )
        val sevasimageView = binding?.sevascardNewsImage
        val sevasimageArray = arrayOf(
            R.drawable.sevas1,
            R.drawable.sevas2,
            R.drawable.sevas3
        )

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
            var evprandomIndex = (0 until evpimageArray.size).random()
            val evpresourceId = evpimageArray[evprandomIndex]
            evpimageView?.setImageResource(evpresourceId)
            var sevasrandomIndex = (0 until sevasimageArray.size).random()
            val sevasresourceId = sevasimageArray[sevasrandomIndex]
            sevasimageView?.setImageResource(sevasresourceId)
            swipeRefreshLayout.isRefreshing = false
        }

        val evprandomIndex = (0 until evpimageArray.size).random()
        val evpresourceId = evpimageArray[evprandomIndex]
        evpimageView?.setImageResource(evpresourceId)

        val sevasrandomIndex = (0 until sevasimageArray.size).random()
        val sevasresourceId = sevasimageArray[sevasrandomIndex]
        sevasimageView?.setImageResource(sevasresourceId)

        evpimageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.news_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.dialog_image_view)
            dialogImageView?.setImageDrawable(evpimageView.drawable)
            dialog?.show()
        }

        sevasimageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.news_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.dialog_image_view)
            dialogImageView?.setImageDrawable(sevasimageView.drawable)
            dialog?.show()
        }

    }
    //endregion

}