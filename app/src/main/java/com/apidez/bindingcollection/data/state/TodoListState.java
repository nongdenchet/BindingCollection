package com.apidez.bindingcollection.data.state;

import com.apidez.bindingcollection.data.model.Todo;
import com.apidez.bindingcollection.di.scope.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

@ApplicationScope
public class TodoListState implements TodoListProvider, TodoListCollector {
    private final BehaviorSubject<List<Todo>> todoListSubject;

    @Inject
    TodoListState() {
        todoListSubject = BehaviorSubject.createDefault(new ArrayList<>());
    }

    private interface Transaction {
        void execute(List<Todo> todos);
    }

    @Override
    public void addAll(List<Todo> newTodos) {
        doTransaction(todos -> {
            todos.clear();
            todos.addAll(newTodos);
        });
    }

    @Override
    public void add(Todo todo) {
        doTransaction(todos -> todos.add(0, todo));
    }

    @Override
    public void remove(Todo todo) {
        doTransaction(todos -> todos.remove(todo));
    }

    @Override
    public void update(Todo todo) {
        doTransaction(todos -> todos.set(indexOf(todo), todo));
    }

    private void doTransaction(Transaction transaction) {
        List<Todo> todos = todoListSubject.getValue();
        transaction.execute(todos);
        todoListSubject.onNext(todos);
    }

    private int indexOf(Todo todo) {
        return todoListSubject.getValue().indexOf(todo);
    }

    @Override
    public Observable<List<Todo>> provide() {
        return todoListSubject.hide();
    }

    @Override
    public List<Todo> current() {
        return todoListSubject.getValue();
    }
}
