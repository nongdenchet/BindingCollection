package com.apidez.bindingcollection.ui;

import com.apidez.bindingcollection.data.model.Todo;
import com.apidez.bindingcollection.data.repo.TodoRepo;
import com.apidez.bindingcollection.di.scope.ActivityScope;
import com.apidez.bindingcollection.support.ListBinder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@ActivityScope
public class TodoListViewModel {
    private final ListBinder<TodoViewModel> todoListBinder;
    private final TodoRepo todoRepo;
    private final List<TodoViewModel> todos = new ArrayList<>();
    private PublishSubject<Integer> scrollTo = PublishSubject.create();

    @Inject
    TodoListViewModel(ListBinder<TodoViewModel> todoListBinder, TodoRepo todoRepo) {
        this.todoListBinder = todoListBinder;
        this.todoRepo = todoRepo;
    }

    public Observable<Integer> scrollTo() {
        return scrollTo.hide();
    }

    public ListBinder<TodoViewModel> getTodoListBinder() {
        return todoListBinder;
    }

    List<TodoViewModel> getTodos() {
        return todos;
    }

    void initialize() {
        todos.addAll(toViewModels(todoRepo.getTodos()));
        todoListBinder.notifyDataChange(todos);
    }

    void setCompleted(int position, boolean completed) {
        TodoViewModel viewModel = todos.get(position);
        if (viewModel.completed != completed) {
            viewModel = viewModel.setCompleted(completed);
            todoRepo.updateTodo(viewModel.toModel());
            todos.set(position, viewModel);
            todoListBinder.notifyDataChange(todos);
        }
    }

    void create(String title, String dueDate) {
        Todo todo = todoRepo.createTodo(title, dueDate);
        TodoViewModel viewModel = new TodoViewModel(todo);
        todos.add(0, viewModel);
        todoListBinder.notifyDataChange(todos);
        scrollTo.onNext(0);
    }

    void deleteTodo(int position) {
        TodoViewModel viewModel = todos.get(position);
        todoRepo.deleteTodo(viewModel.toModel());
        todos.remove(position);
        todoListBinder.notifyDataChange(todos);
    }

    @SuppressWarnings("Convert2streamapi")
    private List<TodoViewModel> toViewModels(List<Todo> todos) {
        List<TodoViewModel> viewModels = new ArrayList<>();
        for (Todo todo : todos) {
            viewModels.add(new TodoViewModel(todo));
        }
        return viewModels;
    }
}
