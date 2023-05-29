package com.example.issomobileapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
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
        R.drawable.sevas_build1,
        R.drawable.evp_build1,
        R.drawable.mirn1,
        R.drawable.popovka_build1
    )
    private val titleList = listOf(
        "Дом 1",
        "Дом 2",
        "Дом 3",
        "Дом 4",
    )

    private val cityList = listOf(
        "Севастополь",
        "Евпатория",
        "Мирный",
        "Поповка",
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
        20000000,
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
        filters()
        apply_filters()
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false

        }

    }

    // MARK: Private Functions
    private fun init() {
        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter

            for (i in imageList.indices) {
                val build = Build(
                    imageList[i],
                    titleList[i],
                    cityList[i],
                    roomList[i],
                    priceList[i],
                    sizeList[i]
                )

                adapter.addBuild(build)

            }


            adapter.onBuildClick = {
                val intent = Intent(context, BuildExpanded::class.java)
                intent.putExtra("build", it)
                startActivity(intent)

            }

        }
    }

    private fun filters() {
        binding?.apply {
            filter.setOnClickListener {
                if (linearLayout2.visibility == View.VISIBLE) {
                    linearLayout2.visibility = View.GONE

                } else {
                    linearLayout2.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun apply_filters() {
        binding?.apply {
            searchButton.setOnClickListener {
                for (i in imageList.indices) {
                    if ((cityList[i] == spinner.selectedItem || spinner.selectedItem == "Все") &&
                        (priceMin.text.isEmpty() || priceList[i].toInt() >= priceMin.text.toString()
                            .toInt()) &&
                        (priceMax.text.isEmpty() || priceList[i].toInt() <= priceMax.text.toString()
                            .toInt()) &&
                        (numRooms.text.isEmpty() || roomList[i].toInt() == numRooms.text.toString()
                            .toInt()) &&
                        (size.text.isEmpty() || sizeList[i].toInt() == size.text.toString().toInt())
                    ) {
                        val build = Build(
                            imageList[i],
                            titleList[i],
                            cityList[i],
                            roomList[i],
                            priceList[i],
                            sizeList[i]
                        )
                        adapter.addBuild(build)
                    } else {
                        val build = Build(
                            imageList[i],
                            titleList[i],
                            cityList[i],
                            roomList[i],
                            priceList[i],
                            sizeList[i]
                        )
                        adapter.removeBuild(build)
                    }

                }

            }
        }
    }


}