package com.apidez.bindingcollection.support;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class DiffCallBack<E> extends DiffUtil.Callback {
    protected List<E> oldList = new ArrayList<>();
    protected List<E> newList = new ArrayList<>();

    void setData(List<E> oldList, List<E> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }
}
