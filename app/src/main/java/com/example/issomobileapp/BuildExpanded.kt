package com.example.issomobileapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Address
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouter
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.Error
import java.util.*
import javax.security.auth.callback.PasswordCallback
import kotlin.collections.ArrayList

class BuildExpanded : AppCompatActivity(), DrivingSession.DrivingRouteListener, LocationListener {

    // MARK: Private Properties

    private val SEVAS_END_POINT = Point(44.577717, 33.447058)
    private val EVP_END_POINT = Point(45.193404, 33.348098)
    private val POPOVKA_END_POINT = Point(45.299776, 33.029554)
    private val MIRNYI_END_POINT = Point(45.325155, 33.037628)

    private var CURRENT_LOCATION = Point()

    private var EVP_SCREEN_CENTER = Point(
        (CURRENT_LOCATION.latitude + EVP_END_POINT.latitude) / 2,
        (CURRENT_LOCATION.longitude + EVP_END_POINT.longitude) / 2
    )
    private var SEVAS_SCREEN_CENTER = Point(
        (CURRENT_LOCATION.latitude + SEVAS_END_POINT.latitude) / 2,
        (CURRENT_LOCATION.longitude + SEVAS_END_POINT.longitude) / 2
    )
    private var POPOVKA_SCREEN_CENTER = Point(
        (CURRENT_LOCATION.latitude + POPOVKA_END_POINT.latitude) / 2,
        (CURRENT_LOCATION.longitude + POPOVKA_END_POINT.longitude) / 2
    )
    private var MIRNYI_SCREEN_CENTER = Point(
        (CURRENT_LOCATION.latitude + MIRNYI_END_POINT.latitude) / 2,
        (CURRENT_LOCATION.longitude + MIRNYI_END_POINT.longitude) / 2
    )

    private lateinit var mapObjects: MapObjectCollection
    private lateinit var drivingRouter: DrivingRouter
    private lateinit var drivingSession: DrivingSession
    private lateinit var mapView: MapView
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    private lateinit var goBackButton: Button
    private lateinit var cityTV: TextView
    private lateinit var roomsTV: TextView
    private lateinit var sizeTV: TextView
    private lateinit var priceTV: TextView


    private val evpImageList = listOf(
        R.drawable.evp_build1,
        R.drawable.evp_build2,
        R.drawable.evp_build3
    )

    private val sevasImageList = listOf(
        R.drawable.sevas_build1,
        R.drawable.sevas_build2,
        R.drawable.sevas_build3
    )
    private val mirnyiImageList = listOf(
        R.drawable.mirn1,
        R.drawable.mirn2,
        R.drawable.mirn3,
        R.drawable.mirn4,
        R.drawable.mirn5
    )
    private val popovkaImageList = listOf(
        R.drawable.popovka_build1,
        R.drawable.popovka_build2,
        R.drawable.popovka_build3,
        R.drawable.popovka_build4
    )

    // MARK: Override Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.build_expanded)

        // MARK : MapKit

        // Initialize
        mapView = findViewById(R.id.mapview)
        MapKitFactory.initialize(this)

        // Permission request && getting current location
        requestLocationPermission()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                onLocationChanged(location)
            }
        }

        // Smooth move to point
        mapView.map.move(CameraPosition(Point(CURRENT_LOCATION.latitude, CURRENT_LOCATION.longitude), 15.0f, 0.0f, 0.0f))

        // Building routes
        var mapKit: MapKit = MapKitFactory.getInstance()

        var locationOnAMap = mapKit.createUserLocationLayer(mapView.mapWindow)
        locationOnAMap.isVisible = true

        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
        mapObjects = mapView.map.mapObjects.addCollection()

        val build = intent.getParcelableExtra<Build>("build")
        if (build != null) {
            when(build.city) {
                "Севастополь" -> submitRequest(SEVAS_END_POINT)
                "Евпатория" -> submitRequest(EVP_END_POINT)
                "Мирный" -> submitRequest(MIRNYI_END_POINT)
                "Поповка" -> submitRequest(POPOVKA_END_POINT)
            }
        }

        // MARK: Other

        cityTV = findViewById(R.id.cityTV)
        roomsTV = findViewById(R.id.roomsTV)
        sizeTV = findViewById(R.id.sizeTV)
        priceTV = findViewById(R.id.priceTV)

        goBackButton = findViewById(R.id.goBack)

        goBackButton.setOnClickListener {
            finish()
        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }

        if (build != null) {
            when(build.city) {
                "Севастополь" ->  {
                    cityTV.text = build.city
                    roomsTV.text = "Количество комнат: " + build.roomCount.toString()
                    sizeTV.text = "Квадратура: " + build.size.toString() + "м²"
                    priceTV.text = "Цена: " + build.price.toString() + "₽"
                    viewPager.adapter = BuildCarouselAdapter(sevasImageList)
                }
                "Евпатория" ->  {
                    cityTV.text = build.city
                    roomsTV.text = "Количество комнат: " + build.roomCount.toString()
                    sizeTV.text = "Квадратура: " + build.size.toString() + "м²"
                    priceTV.text = "Цена: " + build.price.toString() + "₽"
                    viewPager.adapter = BuildCarouselAdapter(evpImageList)
                }
                "Мирный" -> {
                    cityTV.text = build.city
                    roomsTV.text = "Количество комнат: " + build.roomCount.toString()
                    sizeTV.text = "Квадратура: " + build.size.toString() + "м²"
                    priceTV.text = "Цена: " + build.price.toString() + "₽"
                    viewPager.adapter = BuildCarouselAdapter(mirnyiImageList)
                }
                "Поповка" ->  {
                    cityTV.text = build.city
                    roomsTV.text = "Количество комнат: " + build.roomCount.toString()
                    sizeTV.text = "Квадратура: " + build.size.toString() + "м²"
                    priceTV.text = "Цена: " + build.price.toString() + "₽"
                    viewPager.adapter = BuildCarouselAdapter(popovkaImageList)
                }
            }
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        viewPager.setPageTransformer(compositePageTransformer)

    }

    override fun onLocationChanged(p0: android.location.Location) {
        CURRENT_LOCATION = Point(p0.latitude, p0.longitude)

    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        for (route in p0) {
            mapObjects!!.addPolyline(route.geometry)
        }
    }

    override fun onDrivingRoutesError(p0: Error) {
        var errorMessage = "Error"
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    // MARK: Private Functions

    private fun requestLocationPermission() {
        if ((ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
            && (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            return
        }
    }

    private fun submitRequest(END_POINT: Point) {
        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()
        val requestPoint: ArrayList<RequestPoint> = ArrayList()

        requestPoint.add(RequestPoint(Point(CURRENT_LOCATION.latitude, CURRENT_LOCATION.longitude), RequestPointType.WAYPOINT, null))
        requestPoint.add(RequestPoint(Point(END_POINT.latitude, END_POINT.longitude), RequestPointType.WAYPOINT, null))
        drivingSession =
            drivingRouter!!.requestRoutes(requestPoint, drivingOptions, vehicleOptions, this)
    }

}