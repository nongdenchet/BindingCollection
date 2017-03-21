package com.apidez.bindingcollection.support;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

public class BindingUtils {

    @BindingAdapter("listBinder")
    public static <E> void bindItems(RecyclerView recyclerView, ListBinder<E> listBinder) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            listBinder.setOnDataChangeListener(diffResult ->
                    diffResult.dispatchUpdatesTo(adapter));
        }
    }

    @BindingAdapter("android:checked")
    public static void setChecked(CheckBox checkBox, boolean checked) {
        if (checked != checkBox.isChecked()) {
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(checked);
            checkBox.jumpDrawablesToCurrentState();
        }
    }
}
