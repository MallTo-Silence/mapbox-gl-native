package com.mapbox.mapboxsdk.testapp.activity.camera;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.MapboxConstants;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.UiSettings;
import com.mapbox.mapboxsdk.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class LatLngBoundsActivity extends AppCompatActivity {

    private static final LatLng LOS_ANGELES = new LatLng(34.053940, -118.242622);
    private static final LatLng NEW_YORK = new LatLng(40.712730, -74.005953);

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible_bounds);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.setStyleUrl(Style.DARK);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                UiSettings uiSettings = mapboxMap.getUiSettings();
                uiSettings.setAllGesturesEnabled(false);

                mapboxMap.addMarker(new MarkerOptions()
                        .title("Los Angeles")
                        .snippet("City Hall")
                        .position(LOS_ANGELES));

                mapboxMap.addMarker(new MarkerOptions()
                        .title("New York")
                        .snippet("City Hall")
                        .position(NEW_YORK));

                List<LatLng> points = new ArrayList<>();
                points.add(NEW_YORK);
                points.add(LOS_ANGELES);

                // Create Bounds
                final LatLngBounds bounds = new LatLngBounds.Builder()
                        .includes(points)
                        .build();

                // Add map padding
                int mapPadding = (int) getResources().getDimension(R.dimen.fab_margin);
                mapboxMap.setPadding(mapPadding, mapPadding, mapPadding, mapPadding);

                // Move camera to the bounds with added padding
                int padding = (int) getResources().getDimension(R.dimen.coordinatebounds_margin);
                mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));

                // Call mapboxMap.getProjection().getVisibleRegion().latLngBounds to retrieve the bounds
                Log.v(MapboxConstants.TAG, mapboxMap.getProjection().getVisibleRegion().latLngBounds.toString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
