package com.prishan.apnabusiness.ui.bottomnav.product;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prishan.apnabusiness.R;
import com.prishan.apnabusiness.data.model.Product;
import com.prishan.apnabusiness.data.model.ToolbarAttribute;

public class ProductViewModel extends ViewModel {

    public Product product;
    public ToolbarAttribute toolbarAttribute;

    public MutableLiveData<Integer> busy;
    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }

    public ProductViewModel() {

    }

    public MutableLiveData<Product> productMutableLiveData;
    public MutableLiveData<Product> getProduct() {
        getBusy().setValue(0);
        if (productMutableLiveData == null) {
            productMutableLiveData = new MutableLiveData<>();

        }

        return productMutableLiveData;
    }

}