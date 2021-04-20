package com.example.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dashboard.data.CartTransactions;
import com.example.dashboard.data.SharedProductData;
import com.example.dashboard.model.Product;
import com.example.dashboard.utility.MathUtility;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity{
    private TextView productNameText, productCostText, productTotalText, productDescriptionText, sellerInfoText;
    private EditText productAddedQuantity;
    private ImageView productImage;
    private MaterialButton addToCartButton;


    private CartTransactions cartTransactions;

    private SharedProductData sharedProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productNameText = findViewById(R.id.activity_product_name_text);
        productCostText = findViewById(R.id.activity_product_cost);
        productTotalText = findViewById(R.id.activity_product_total);
        productDescriptionText = findViewById(R.id.activity_product_description_text);
        sellerInfoText = findViewById(R.id.activity_product_seller_info);
        productAddedQuantity = findViewById(R.id.activity_product_add_quantity_edit_text);
        productImage = findViewById(R.id.activity_product_image);
        addToCartButton = findViewById(R.id.activity_product_add_to_cart_button);

        cartTransactions = new CartTransactions(ProductActivity.this);

        sharedProduct = SharedProductData.getInstance();
        showProductDetailsAndAddListeners(sharedProduct.getProduct());
    }

    private void showProductDetailsAndAddListeners(Product product) {
        productNameText.setText(product.name);
        productCostText.append(MathUtility.truncateFloatToTwoDecimalPlaces(product.price));

        productAddedQuantity.setText("1");
        productAddedQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(productAddedQuantity.getText().toString().isEmpty()) {
                    productTotalText.setText("Total: Rs 0");
                }
                else {
                    Float currentTotalCost = product.price * Float.parseFloat(productAddedQuantity.getText().toString());
                    productTotalText.setText("Total: Rs " + MathUtility.truncateFloatToTwoDecimalPlaces(currentTotalCost));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Float totalCost = product.price * Float.parseFloat(productAddedQuantity.getText().toString());
        productTotalText.append(MathUtility.truncateFloatToTwoDecimalPlaces(totalCost));

        if(product.description != null && product.description.text != null)
            productDescriptionText.setText(product.description.text);

        if(product.seller != null) {
            sellerInfoText.setText("Sold by " + product.seller.storeName + "\nat\n" + product.seller.address.addressText + "\nPhone: " + product.seller.phone);
        }

        Picasso.Builder builder = new Picasso.Builder(ProductActivity.this);
        builder.build().load("https://homepages.cae.wisc.edu/~ece533/images/fruits.png")
                .placeholder(R.drawable.ic_categories_nav)
                .error(R.drawable.ic_categories_nav)
                .into(productImage);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.addedQuantity = Float.parseFloat(productAddedQuantity.getText().toString());
                addProductToCart(product);
            }
        });
    }

    private void addProductToCart(Product product) {
        cartTransactions.updateOrInsertInCart(product);
    }
}
