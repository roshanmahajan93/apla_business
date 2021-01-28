package com.prishan.apnabusiness.ui.bottomnav.product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prishan.apnabusiness.R;
import com.prishan.apnabusiness.data.model.Product;
import com.prishan.apnabusiness.data.model.ToolbarAttribute;

public class ProductMainViewModel extends ViewModel {
    public Product product;
    public ToolbarAttribute toolbarAttribute;

    public ProductMainViewModel() {

    }

    public MutableLiveData<ToolbarAttribute> toolbarAttributeMutableLiveData;
    public MutableLiveData<ToolbarAttribute> getToolbarAttribute() {
        if (toolbarAttributeMutableLiveData == null) {
            toolbarAttributeMutableLiveData = new MutableLiveData<>();
            if(toolbarAttribute == null)
            {
                toolbarAttribute = new ToolbarAttribute(R.drawable.ic_back, 8, "Product", R.drawable.ic_round_search_24,0);
            }
            toolbarAttributeMutableLiveData.setValue(toolbarAttribute);
        }

        return toolbarAttributeMutableLiveData;
    }
}