package com.example.issomobileapp

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.issomobileapp.databinding.FragmentHomeBinding
import com.example.issomobileapp.databinding.FragmentSalesBinding

class SalesFragment : Fragment() {

    private var binding: FragmentSalesBinding? = null
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
        binding = FragmentSalesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val s1ImageView = binding?.sales1
        val s2ImageView = binding?.sales2
        val s3ImageView = binding?.sales3
        val s4ImageView = binding?.sales4
        val s5ImageView = binding?.sales5
        val s6ImageView = binding?.sales6

        val salesImageArray = arrayOf(
            R.drawable.sevas4,
            R.drawable.evp4,
            R.drawable.pop1,
            R.drawable.pop2,
            R.drawable.saki1,
            R.drawable.sim1,
        )

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {

            var s1randomIndex = (0 until salesImageArray.size).random()
            val s1ResourceId = salesImageArray[s1randomIndex]
            s1ImageView?.setImageResource(s1ResourceId)

            var s2randomIndex = (0 until salesImageArray.size).random()
            val s2ResourceId = salesImageArray[s2randomIndex]
            s2ImageView?.setImageResource(s2ResourceId)

            var s3randomIndex = (0 until salesImageArray.size).random()
            val s3ResourceId = salesImageArray[s3randomIndex]
            s3ImageView?.setImageResource(s3ResourceId)

            var s4randomIndex = (0 until salesImageArray.size).random()
            val s4ResourceId = salesImageArray[s4randomIndex]
            s4ImageView?.setImageResource(s4ResourceId)

            var s5randomIndex = (0 until salesImageArray.size).random()
            val s5ResourceId = salesImageArray[s5randomIndex]
            s5ImageView?.setImageResource(s5ResourceId)

            var s6randomIndex = (0 until salesImageArray.size).random()
            val s6ResourceId = salesImageArray[s6randomIndex]
            s6ImageView?.setImageResource(s6ResourceId)

            swipeRefreshLayout.isRefreshing = false

        }

        var s1randomIndex = (0 until salesImageArray.size).random()
        val s1ResourceId = salesImageArray[s1randomIndex]
        s1ImageView?.setImageResource(s1ResourceId)

        var s2randomIndex = (0 until salesImageArray.size).random()
        val s2ResourceId = salesImageArray[s2randomIndex]
        s2ImageView?.setImageResource(s2ResourceId)

        var s3randomIndex = (0 until salesImageArray.size).random()
        val s3ResourceId = salesImageArray[s3randomIndex]
        s3ImageView?.setImageResource(s3ResourceId)

        var s4randomIndex = (0 until salesImageArray.size).random()
        val s4ResourceId = salesImageArray[s4randomIndex]
        s4ImageView?.setImageResource(s4ResourceId)

        var s5randomIndex = (0 until salesImageArray.size).random()
        val s5ResourceId = salesImageArray[s5randomIndex]
        s5ImageView?.setImageResource(s5ResourceId)

        var s6randomIndex = (0 until salesImageArray.size).random()
        val s6ResourceId = salesImageArray[s6randomIndex]
        s6ImageView?.setImageResource(s6ResourceId)

        s1ImageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.sales_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.sales_expanded_image)
            dialogImageView?.setImageDrawable(s1ImageView.drawable)
            dialog?.show()
        }

        s2ImageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.sales_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.sales_expanded_image)
            dialogImageView?.setImageDrawable(s2ImageView.drawable)
            dialog?.show()
        }

        s3ImageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.sales_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.sales_expanded_image)
            dialogImageView?.setImageDrawable(s3ImageView.drawable)
            dialog?.show()
        }

        s4ImageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.sales_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.sales_expanded_image)
            dialogImageView?.setImageDrawable(s4ImageView.drawable)
            dialog?.show()
        }

        s5ImageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.sales_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.sales_expanded_image)
            dialogImageView?.setImageDrawable(s5ImageView.drawable)
            dialog?.show()
        }

        s6ImageView?.setOnClickListener {
            val dialog = context?.let { it1 -> Dialog(it1) }
            dialog?.setContentView(R.layout.sales_expanded)
            val dialogImageView = dialog?.findViewById<ImageView>(R.id.sales_expanded_image)
            dialogImageView?.setImageDrawable(s6ImageView.drawable)
            dialog?.show()
        }


    }

}