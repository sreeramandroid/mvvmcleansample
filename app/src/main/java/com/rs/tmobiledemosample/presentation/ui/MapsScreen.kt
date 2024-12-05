package com.rs.tmobiledemosample.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(onNavigateTo:(String) -> Unit ={}) {

    val locationMarker1 = LatLng(40.9971, 29.1007)
    val locationMarker2 = LatLng(40.9967, 29.0570)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationMarker1, 15f)
    }

    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    //NORMAL,TERRAIN,SATELLITE,HYBRID
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        Marker(
            state = MarkerState(position = locationMarker1),
            title = "Marker 1"
        )
        Marker(
            state = MarkerState(position = locationMarker2),
            title = "Marker 2"
        )
    }

}