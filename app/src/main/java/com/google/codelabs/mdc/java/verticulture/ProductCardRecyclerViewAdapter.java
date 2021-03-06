package com.google.codelabs.mdc.java.verticulture;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.codelabs.mdc.java.verticulture.network.ImageRequester;
import com.google.codelabs.mdc.java.verticulture.network.ProductEntry;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Adapter used to show a simple grid of products.
 */
public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private Activity activity;
    private List<ProductEntry> productList;
    private ImageRequester imageRequester;

    ProductCardRecyclerViewAdapter(List<ProductEntry> productList, Activity activity) {
        this.productList = productList;
        this.activity = activity;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_product_card, parent, false);
        return new ProductCardViewHolder(layoutView, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102


        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
