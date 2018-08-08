package com.google.codelabs.mdc.java.verticulture;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView productImage;
    public TextView productTitle;
    public TextView productPrice;
    public LinearLayout ll;
    private Activity activity;

    public ProductCardViewHolder(@NonNull View itemView, Activity activity) {
        super(itemView);
        //TODO: Find and store views from itemView
        this.activity = activity;
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productPrice = itemView.findViewById(R.id.product_price);

        ll = (LinearLayout) itemView.findViewById(R.id.ll_layout);
        ll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //cardClicked(getAdapterPosition());
                int pos = getAdapterPosition();
                if(pos == 0){
                    System.out.println(pos);
                    ((NavigationHost) activity).navigateTo(new DeviceFragment(), true);
                }else if(pos == 1){
                    System.out.println(pos);
                }else{
                    System.out.println(pos);
                }

            }
        });

    }

    //Home tab click handler


}
