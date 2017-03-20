package com.apidez.bindingcollection.support;

import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import static io.reactivex.android.MainThreadDisposable.verifyMainThread;

public class ListBinder<E> {
    private final DiffCallBack<E> diffCallBack;
    private List<E> current = new ArrayList<>();
    private OnDataChangeListener onDataChangeListener;

    public ListBinder(DiffCallBack<E> diffCallBack) {
        this.diffCallBack = diffCallBack;
    }

    interface OnDataChangeListener {
        void onChange(DiffUtil.DiffResult diffResult);
    }

    void setOnDataChangeListener(OnDataChangeListener onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }

    public void notifyDataChange(List<E> data) {
        verifyMainThread();
        DiffUtil.DiffResult diffResult = calculateDiff(data);
        if (onDataChangeListener != null) {
            onDataChangeListener.onChange(diffResult);
        }
    }

    private DiffUtil.DiffResult calculateDiff(List<E> data) {
        List<E> newList = new ArrayList<>(data);
        diffCallBack.setData(current, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallBack);
        current = newList;
        return diffResult;
    }
}
