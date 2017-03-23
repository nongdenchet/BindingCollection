package com.apidez.bindingcollection.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.apidez.bindingcollection.R;
import com.apidez.bindingcollection.TodoApplication;
import com.apidez.bindingcollection.databinding.ActivityTodoListBinding;
import com.apidez.bindingcollection.di.module.ActivityModule;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@SuppressWarnings("ALL")
public class TodoListActivity extends AppCompatActivity implements NewTodoFragment.OnSubmitListener {
    private ActivityTodoListBinding activityTodoListBinding;
    private RecyclerView rvTodos;

    @Inject TodoListViewModel todoListViewModel;
    @Inject TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TodoApplication) getApplication())
                .component()
                .plus(new ActivityModule(this))
                .inject(this);
        activityTodoListBinding = DataBindingUtil.setContentView(this, R.layout.activity_todo_list);
        activityTodoListBinding.setViewModel(todoListViewModel);
        initView();
        initialize();
    }

    private void initialize() {
        todoListViewModel.initialize();
        todoListViewModel.scrollTo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pos -> rvTodos.smoothScrollToPosition(pos));
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        rvTodos = activityTodoListBinding.rvTodos;
        rvTodos.setAdapter(todoListAdapter);
        rvTodos.setLayoutManager(layoutManager);
        rvTodos.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new) {
            showNewDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNewDialog() {
        NewTodoFragment newTodoFragment = NewTodoFragment.newInstance();
        newTodoFragment.show(getSupportFragmentManager(), NewTodoFragment.class.getSimpleName());
    }

    @Override
    protected void onDestroy() {
        todoListViewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSubmit(String title, String dueDate) {
        todoListViewModel.create(title, dueDate);
    }
}
