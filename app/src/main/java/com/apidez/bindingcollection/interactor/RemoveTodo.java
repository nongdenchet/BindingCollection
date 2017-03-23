package com.apidez.bindingcollection.interactor;

import com.apidez.bindingcollection.data.model.Todo;
import com.apidez.bindingcollection.data.repo.TodoRepo;
import com.apidez.bindingcollection.data.state.TodoListCollector;

import javax.inject.Inject;

import io.reactivex.Completable;

public class RemoveTodo {
    private final TodoRepo todoRepo;
    private final TodoListCollector todoListCollector;

    @Inject
    public RemoveTodo(TodoRepo todoRepo, TodoListCollector todoListCollector) {
        this.todoRepo = todoRepo;
        this.todoListCollector = todoListCollector;
    }

    public Completable execute(Todo todo) {
        return Completable.fromAction(() -> {
            todoRepo.deleteTodo(todo);
            todoListCollector.remove(todo);
        });
    }
}
