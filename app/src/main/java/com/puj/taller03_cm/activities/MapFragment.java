package com.puj.taller03_cm.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.puj.taller03_cm.R;
import com.puj.taller03_cm.App;
import com.puj.taller03_cm.databinding.FragmentMapBinding;
import com.puj.taller03_cm.services.GeoInfoFromJsonService;
import com.puj.taller03_cm.services.GeocoderService;
import com.puj.taller03_cm.utils.BitmapUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class MapFragment extends Fragment {

    @Inject
    GeoInfoFromJsonService geoInfoFromJsonService;

    @Inject
    GeocoderService geocoderService;

    FragmentMapBinding binding;

    GoogleMap googleMap;
    static final int INITIAL_ZOOM_LEVEL = 18;
    private final LatLng JAVERIANA = new LatLng(4.6287662, -74.0636298647595);
    Marker userPosition;
    Polyline userRoute;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap map) {
            //Setup the map
            googleMap = map;
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(INITIAL_ZOOM_LEVEL));
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            googleMap.getUiSettings().setCompassEnabled(true);
            // Change map style
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_day_style));
            //Setup the user marker with a default position
            userPosition = googleMap.addMarker(new MarkerOptions()
                    .position(JAVERIANA)
                    .icon(BitmapUtils.getBitmapDescriptor(getContext(), R.drawable.ic_baseline_circle_24))
                    .anchor(0.5f, 0.5f)
                    .zIndex(1.0f));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(JAVERIANA));
            //Setup the route line
            userRoute = googleMap.addPolyline(new PolylineOptions()
                    .color(R.color.light_blue_400)
                    .width(15.0f)
                    .geodesic(true)
                    .zIndex(0.5f));
            //Setup the rest of the markers based in a json file
            loadGeoInfo();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((App) requireActivity().getApplicationContext()).getAppComponent().inject(this);
        binding = FragmentMapBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        /*binding.materialButton.setOnClickListener(view1 -> findPlaces(binding.textInputLayout.getEditText().getText().toString()));
        binding.textInputLayout.getEditText().setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        binding.textInputLayout.getEditText().setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_SEARCH){
                findPlaces(binding.textInputLayout.getEditText().getText().toString());
                return true;
            }
            return false;
        });*/
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        List<LatLng> points = userRoute.getPoints();
        points.clear();
        userRoute.setPoints(points);
    }

    public void updateUserPositionOnMap(@NotNull LocationResult locationResult) {
        userPosition.setPosition(new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude()));
        List<LatLng> points = userRoute.getPoints();
        points.add(userPosition.getPosition());
        userRoute.setPoints(points);
        if (binding.isCameraFixedToUser.isChecked()) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(userPosition.getPosition()));
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userPosition.getPosition(), INITIAL_ZOOM_LEVEL));
        }
    }

    private void loadGeoInfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            geoInfoFromJsonService.getGeoInfoList().forEach(geoInfo -> {
                MarkerOptions newMarker = new MarkerOptions();
                newMarker.position(new LatLng(geoInfo.getLatitude(), geoInfo.getLongitude()));
                newMarker.title(geoInfo.getName());
                newMarker.icon(BitmapUtils.getBitmapDescriptor(getContext(), R.drawable.ic_baseline_person_pin_24));
                googleMap.addMarker(newMarker);
            });
        }
    }

    public void findPlaces(String search) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<Address> results = geocoderService.finPlacesByNameInRadious(search, userPosition.getPosition());
                Log.d("TAG", "findPlaces: results = " + results.size());
                results.forEach(address -> googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(address.getLatitude(), address.getLongitude()))
                        .title(address.getFeatureName())
                        .snippet(address.getAddressLine(0) != null ? address.getAddressLine(0) : "")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}