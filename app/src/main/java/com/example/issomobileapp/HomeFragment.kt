package com.example.issomobileapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.issomobileapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // MARK: Private Properties
    private var binding: FragmentHomeBinding? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    // MARK: Override Functions
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

        val evptitleView = binding?.evpnewsTitle
        val sevastitleView = binding?.sevasnewsTitle
        val titlesArray = arrayOf(
            "Только у нас",
            "Выгодный трейд-ин",
            "Экологичное строительство",
            "Лучшие проекты года",
            "Инновации в строительстве",
            "Комфортное жильё для всех",
            "Реконструкция зданий"
        )

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {

            var evprandomIndex = (0 until evpimageArray.size).random()
            val evpresourceId = evpimageArray[evprandomIndex]
            evpimageView?.setImageResource(evpresourceId)

            var sevasrandomIndex = (0 until sevasimageArray.size).random()
            val sevasresourceId = sevasimageArray[sevasrandomIndex]
            sevasimageView?.setImageResource(sevasresourceId)

            val evpTitlerandomIndex = (0 until titlesArray.size).random()
            val evptitleId = titlesArray[evpTitlerandomIndex]
            val sevasTitlerandomIndex = (0 until titlesArray.size).random()
            val sevastitleId = titlesArray[sevasTitlerandomIndex]
            evptitleView?.text = evptitleId
            sevastitleView?.text = sevastitleId

            swipeRefreshLayout.isRefreshing = false
        }

        val evprandomIndex = (0 until evpimageArray.size).random()
        val evpresourceId = evpimageArray[evprandomIndex]
        evpimageView?.setImageResource(evpresourceId)

        val sevasrandomIndex = (0 until sevasimageArray.size).random()
        val sevasresourceId = sevasimageArray[sevasrandomIndex]
        sevasimageView?.setImageResource(sevasresourceId)

        val evpTitlerandomIndex = (0 until titlesArray.size).random()
        val evptitleId = titlesArray[evpTitlerandomIndex]
        val sevasTitlerandomIndex = (0 until titlesArray.size).random()
        val sevastitleId = titlesArray[sevasTitlerandomIndex]
        evptitleView?.text = evptitleId
        sevastitleView?.text = sevastitleId

        evpimageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.news_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.dialog_image_view)
            val dialogTextView = dialog?.findViewById<TextView>(R.id.dialog_text_view)
            dialogTextView?.text = getString(R.string.news_text_evp1)
            dialogImageView?.setImageDrawable(evpimageView.drawable)
            dialog?.show()
        }

        sevasimageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.news_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.dialog_image_view)
            val dialogTextView = dialog?.findViewById<TextView>(R.id.dialog_text_view)
            dialogTextView?.text = getString(R.string.news_text_sevas1)
            dialogImageView?.setImageDrawable(sevasimageView.drawable)
            dialog?.show()
        }

        val favouriteIcon = binding?.favouriteButton
        var flag = false

        favouriteIcon?.setOnClickListener {
            if (!flag) {
                favouriteIcon?.setImageResource(R.drawable.favourite_icon_active)
                flag = true
            } else {
                favouriteIcon?.setImageResource(R.drawable.favourite_icon)
                flag = false
            }
        }


    }


}