package com.prishan.aplabusiness.ui.bottomnav.order;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.prishan.aplabusiness.R;
import com.prishan.aplabusiness.data.model.Order;
import com.prishan.aplabusiness.databinding.OrderFragmentBinding;
import com.prishan.aplabusiness.databinding.ProductFragmentBinding;

public class OrderFragment extends Fragment {

    private OrderViewModel orderViewModel;
    public OrderFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_fragment, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        binding.setOrderViewModel(orderViewModel);
        binding.setLifecycleOwner(this);

        orderViewModel.getOrder().observe(getViewLifecycleOwner(), new Observer<Order>() {
            @Override
            public void onChanged(@Nullable Order s) {
                //textView.setText(s);
            }
        });
        return binding.getRoot();
    }


}