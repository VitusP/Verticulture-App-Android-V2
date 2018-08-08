package com.google.codelabs.mdc.java.verticulture;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.Button;
import android.widget.Toast;

import com.google.codelabs.mdc.java.verticulture.network.ProductEntry;

import java.util.List;

import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.utils.ui.Toaster;

public class ProductGridFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.ver_product_grid_fragment, container, false);

        // Set up the toolbar and listener
        setUpToolbar(view);


        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(ProductEntry.initProductEntryList(getResources()), getActivity());
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.ver_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.ver_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));


        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        //setup navigation listener from NavigationIconClickListener.java
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(), view.findViewById(R.id.product_grid)));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.ver_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);


    }


}
