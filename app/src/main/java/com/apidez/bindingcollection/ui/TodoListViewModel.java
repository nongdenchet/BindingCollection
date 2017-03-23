package com.apidez.bindingcollection.ui;

import com.apidez.bindingcollection.data.model.Todo;
import com.apidez.bindingcollection.data.state.TodoListProvider;
import com.apidez.bindingcollection.di.scope.ActivityScope;
import com.apidez.bindingcollection.interactor.CreateTodo;
import com.apidez.bindingcollection.interactor.GetTodoList;
import com.apidez.bindingcollection.interactor.RemoveTodo;
import com.apidez.bindingcollection.interactor.UpdateTodo;
import com.apidez.bindingcollection.support.ListBinder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

@ActivityScope
public class TodoListViewModel {
    private final ListBinder<TodoViewModel> todoListBinder;
    private final UpdateTodo updateTodo;
    private final CreateTodo createTodo;
    private final RemoveTodo removeTodo;
    private final GetTodoList getTodoList;
    private final TodoListProvider todoListProvider;
    private List<TodoViewModel> todos = new ArrayList<>();
    private PublishSubject<Integer> scrollTo = PublishSubject.create();
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public TodoListViewModel(ListBinder<TodoViewModel> todoListBinder,
                             TodoListProvider todoListProvider,
                             UpdateTodo updateTodo,
                             CreateTodo createTodo,
                             RemoveTodo removeTodo,
                             GetTodoList getTodoList) {
        this.todoListBinder = todoListBinder;
        this.updateTodo = updateTodo;
        this.createTodo = createTodo;
        this.removeTodo = removeTodo;
        this.getTodoList = getTodoList;
        this.todoListProvider = todoListProvider;
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
        disposables.add(observeTodoList());
        disposables.add(getTodoList.execute()
                .subscribe(() -> {}));
    }

    Disposable observeTodoList() {
        return todoListProvider.provide()
                .map(this::toViewModels)
                .subscribe(todoViewModels -> {
                    todos = todoViewModels;
                    todoListBinder.notifyDataChange(todoViewModels);
                });
    }

    void setCompleted(int position, boolean completed) {
        TodoViewModel viewModel = todos.get(position);
        if (viewModel.completed != completed) {
            viewModel = viewModel.setCompleted(completed);
            disposables.add(updateTodo.execute(viewModel.toModel())
                    .subscribe(() -> {}));
        }
    }

    void create(String title, String dueDate) {
        CreateTodo.Params params = new CreateTodo.Params(title, dueDate);
        disposables.add(createTodo.execute(params)
                .subscribe(() -> scrollTo.onNext(0)));
    }

    void deleteTodo(int position) {
        TodoViewModel viewModel = todos.get(position);
        disposables.add(removeTodo.execute(viewModel.toModel())
                .subscribe(() -> {}));
    }

    @SuppressWarnings("Convert2streamapi")
    private List<TodoViewModel> toViewModels(List<Todo> todos) {
        List<TodoViewModel> viewModels = new ArrayList<>();
        for (Todo todo : todos) {
            viewModels.add(new TodoViewModel(todo));
        }
        return viewModels;
    }

    void onDestroy() {
        disposables.clear();
    }
}
