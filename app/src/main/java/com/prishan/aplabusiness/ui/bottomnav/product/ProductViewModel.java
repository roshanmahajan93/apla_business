package com.prishan.aplabusiness.ui.bottomnav.product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prishan.aplabusiness.data.model.Product;
import com.prishan.aplabusiness.data.model.ToolbarAttribute;

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