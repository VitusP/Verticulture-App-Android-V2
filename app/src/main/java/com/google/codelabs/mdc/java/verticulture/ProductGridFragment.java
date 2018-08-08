package com.google.codelabs.mdc.java.verticulture;

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
import android.widget.Toast;

import com.google.codelabs.mdc.java.verticulture.network.ProductEntry;

import java.util.List;

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

        // Set up the toolbar
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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.ver_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void UiButtonClicked(int pos){
        if(pos == 0){
            System.out.println(pos);
            ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false);
        }else if(pos == 1){
            System.out.println(pos);
        }else{
            System.out.println(pos);
        }
    }


}
