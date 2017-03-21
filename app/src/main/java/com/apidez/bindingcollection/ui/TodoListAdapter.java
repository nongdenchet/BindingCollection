package com.apidez.bindingcollection.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.apidez.bindingcollection.databinding.ItemTodoBinding;
import com.apidez.bindingcollection.di.scope.ActivityScope;
import com.apidez.bindingcollection.di.scope.ForActivity;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private final TodoListViewModel todoListViewModel;
    private final LayoutInflater layoutInflater;

    @Inject
    TodoListAdapter(@ForActivity Context context, TodoListViewModel todoListViewModel) {
        this.todoListViewModel = todoListViewModel;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTodoBinding itemTodoBinding = ItemTodoBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemTodoBinding);
    }

    private List<TodoViewModel> getTodos() {
        return todoListViewModel.getTodos();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoViewModel todo = getTodos().get(position);
        holder.itemTodoBinding.setTodo(todo);
        holder.itemTodoBinding.executePendingBindings();
        holder.itemTodoBinding.cbCompleted.setOnCheckedChangeListener((buttonView, isChecked) ->
                todoListViewModel.setCompleted(holder.getAdapterPosition(), isChecked));
        holder.itemTodoBinding.tvRemove.setOnClickListener(v ->
                todoListViewModel.deleteTodo(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return getTodos().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ItemTodoBinding itemTodoBinding;

        ViewHolder(ItemTodoBinding itemTodoBinding) {
            super(itemTodoBinding.getRoot());
            this.itemTodoBinding = itemTodoBinding;
        }
    }
}
