package com.prishan.aplabusiness.ui.bottomnav.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.prishan.aplabusiness.R;
import com.prishan.aplabusiness.data.model.Product;
import com.prishan.aplabusiness.databinding.ProductFragmentBinding;

public class ProductFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ProductFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        binding.setProductViewModel(productViewModel);
        binding.setLifecycleOwner(this);

        productViewModel.getProduct().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product s) {
                //textView.setText(s);
            }
        });
        return binding.getRoot();
    }
}