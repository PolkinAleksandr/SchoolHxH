package ru.panmin.lesson9

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.image.ImageProvider
import ru.panmin.lesson9.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {
    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, MapActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMapBinding

    private val placemarkTapListener = MapObjectTapListener { _, point ->
        Toast.makeText(
            this@MapActivity,
            "Нажал на точку: (${point.longitude}, ${point.latitude})",
            Toast.LENGTH_SHORT
        ).show()
        true
    }

    private val mapTapListener = GeoObjectTapListener {
        val selectionMetadata: GeoObjectSelectionMetadata = it
            .geoObject
            .metadataContainer
            .getItem(GeoObjectSelectionMetadata::class.java)
        binding.mapView.mapWindow.map.selectGeoObject(selectionMetadata)
        Toast.makeText(
            this@MapActivity,
            "Нажал на точку: (${it.geoObject.name})",
            Toast.LENGTH_SHORT
        ).show()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.initialize(this)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMapMove.setOnClickListener {
            binding.mapView.mapWindow.map.move(
                CameraPosition(
                    Point(55.751225, 37.629540),
                    /* zoom = */ 17.0f,
                    /* azimuth = */ 150.0f,
                    /* tilt = */ 30.0f
                ),
                Animation(Animation.Type.SMOOTH, 3f),
            ) {
                Toast.makeText(
                    this,
                    "Move прекращен!",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

        binding.buttonMapAdd.setOnClickListener {
            val imageProvider = ImageProvider.fromResource(this, R.drawable.placemark_icon)

            val placemark = binding.mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                geometry = Point(59.935493, 30.327392)
                setIcon(imageProvider)
                direction
            }

            placemark.addTapListener(placemarkTapListener)

            binding.mapView.mapWindow.map.move(
                CameraPosition(
                    Point(59.935493, 30.327392),
                    /* zoom = */ 17.0f,
                    /* azimuth = */ 0.0f,
                    /* tilt = */ 0.0f
                ),
                Animation(Animation.Type.LINEAR, 3f),
                null,
            )
        }
        binding.mapView.mapWindow.map.addTapListener {
            val selectionMetadata: GeoObjectSelectionMetadata = it
                .geoObject
                .metadataContainer
                .getItem(GeoObjectSelectionMetadata::class.java)
            binding.mapView.mapWindow.map.selectGeoObject(selectionMetadata)
            Toast.makeText(
                this@MapActivity,
                "Нажал на точку: (${it.geoObject.name})",
                Toast.LENGTH_SHORT
            ).show()
            true
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

}
