package com.example.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.R;
import com.example.dashboard.model.Product;
import com.example.dashboard.utility.MathUtility;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewCartProductsAdapter extends RecyclerView.Adapter<RecyclerViewCartProductsAdapter.CartProductsCustomViewHolder>{
    private Context context;
    private List<Product> productList;

    public RecyclerViewCartProductsAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    class CartProductsCustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView productNameText;
        TextView productAddedText;
        TextView productSellerText;
        TextView productCost;
        ImageView productImage;

        CartProductsCustomViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            productNameText = mView.findViewById(R.id.cart_product_name_text);
            productAddedText = mView.findViewById(R.id.cart_product_ordered_text);
            productSellerText = mView.findViewById(R.id.cart_product_seller_text);
            productCost = mView.findViewById(R.id.cart_product_cost);
            productImage = mView.findViewById(R.id.cart_product_image);
        }
    }

    @NonNull
    @Override
    public CartProductsCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.cart_product_card, parent, false);

        return new CartProductsCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductsCustomViewHolder holder, int position) {
        holder.productNameText.append(productList.get(position).name);
        holder.productAddedText.append(MathUtility.truncateFloatToTwoDecimalPlaces(productList.get(position).addedQuantity));

        String cost = MathUtility.truncateFloatToTwoDecimalPlaces(productList.get(position).addedQuantity * productList.get(position).price);
        holder.productCost.append(cost);

        if(productList.get(position).seller != null) {
            holder.productSellerText.append(productList.get(position).seller.storeName);
        }

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load("https://homepages.cae.wisc.edu/~ece533/images/fruits.png")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
