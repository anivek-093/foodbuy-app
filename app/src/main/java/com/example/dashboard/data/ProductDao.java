package com.example.dashboard.data;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dashboard.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("Select * from product")
    LiveData<List<Product>> getProductList();

    @Query("Select total(price*addedQuantity) from product")
    LiveData<Float> getTotalCost();

    @Insert
    void insertProduct(Product product);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);
    
    @Query("Delete from product")
    void deleteAllProducts();
}
