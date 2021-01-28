package com.prishan.apnabusiness.ui.bottomnav.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prishan.apnabusiness.R;
import com.prishan.apnabusiness.data.model.Order;
import com.prishan.apnabusiness.data.model.ToolbarAttribute;

public class OrderViewModel extends ViewModel {
    public Order order;
    public ToolbarAttribute toolbarAttribute;

    public MutableLiveData<Order> orderMutableLiveData;
    public MutableLiveData<Order> getOrder() {
        getBusy().setValue(0);
        if (orderMutableLiveData == null) {
            orderMutableLiveData = new MutableLiveData<>();
            if(toolbarAttribute == null)
            {
                toolbarAttribute = new ToolbarAttribute(R.drawable.ic_back, 8, "Order", R.drawable.ic_round_search_24,0);
            }
            order = new Order();
            order.setToolbarAttribute(toolbarAttribute);
            orderMutableLiveData.setValue(order);
        }
        return orderMutableLiveData;
    }

    /*public OrderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is order fragment");

        //getOrder();

    }*/


    public MutableLiveData<Integer> busy;
    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(0);
        }

        return busy;
    }
}