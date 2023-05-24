package com.example.issomobileapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.issomobileapp.databinding.FragmentBuildsBinding
import com.example.issomobileapp.databinding.FragmentHomeBinding

class BuildsFragment : Fragment() {

    // MARK: Private Properties
    private var binding: FragmentBuildsBinding? = null
    private val adapter = BuildAdapter()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val imageList = listOf(
        R.drawable.evp1,
        R.drawable.evp2,
        R.drawable.evp3,
        R.drawable.evp4
    )
    private val titleList = listOf(
        "Дом 1",
        "Дом 2",
        "Дом 3",
        "Дом 4",
    )

    // TODO: По этим трём нужно добавить фильтр
    private val roomList = listOf(
        1,
        3,
        4,
        2,
    )
    private val priceList = listOf(
        1500000,
        7000000,
        25000000,
        10000000
    )
    private val sizeList = listOf(
        120,
        160,
        280,
        135
    )


    // MARK: Override Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBuildsBinding.inflate(layoutInflater)
        init()
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    // MARK: Private Functions
    private fun init() {
        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            for (i in imageList.indices) {
                val build =
                    Build(imageList[i], titleList[i], roomList[i], priceList[i], sizeList[i])
                adapter.addBuild(build)
            }

        }
    }


}