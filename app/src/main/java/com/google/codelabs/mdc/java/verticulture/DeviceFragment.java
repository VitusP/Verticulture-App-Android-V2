package com.google.codelabs.mdc.java.verticulture;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.codelabs.mdc.java.verticulture.ProductCardRecyclerViewAdapter;
import com.google.codelabs.mdc.java.verticulture.ProductGridItemDecoration;
import com.google.codelabs.mdc.java.verticulture.R;
import com.google.codelabs.mdc.java.verticulture.network.ProductEntry;

import io.particle.android.sdk.cloud.ParticleCloudSDK;

public class DeviceFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.ver_device_fragment, container, false);

        // Set up the toolbar
        setUpToolbar(view);
        final MaterialButton logout_Button = view.findViewById(R.id.logout_Button);

        logout_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParticleCloudSDK.getCloud().logOut();
                ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false);
            }
        });

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(), view.findViewById(R.id.product_grid)));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.ver_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

}
