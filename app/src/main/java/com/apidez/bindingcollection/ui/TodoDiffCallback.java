package com.apidez.bindingcollection.ui;

import com.apidez.bindingcollection.support.DiffCallBack;

import javax.inject.Inject;

public class TodoDiffCallback extends DiffCallBack<TodoViewModel> {

    @Inject
    public TodoDiffCallback() {
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).title.equals(newList.get(newItemPosition).title)
                && oldList.get(oldItemPosition).dueDate.equals(newList.get(newItemPosition).dueDate)
                && oldList.get(oldItemPosition).completed == newList.get(newItemPosition).completed;
    }
}
