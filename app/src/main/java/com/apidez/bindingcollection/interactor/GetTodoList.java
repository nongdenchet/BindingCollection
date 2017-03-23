package com.apidez.bindingcollection.interactor;

import com.apidez.bindingcollection.data.repo.TodoRepo;
import com.apidez.bindingcollection.data.state.TodoListCollector;

import javax.inject.Inject;

import io.reactivex.Completable;

public class GetTodoList {
    private final TodoRepo todoRepo;
    private final TodoListCollector todoListCollector;

    @Inject
    public GetTodoList(TodoRepo todoRepo, TodoListCollector todoListCollector) {
        this.todoRepo = todoRepo;
        this.todoListCollector = todoListCollector;
    }

    public Completable execute() {
        return Completable.fromAction(() -> todoListCollector.addAll(todoRepo.getTodos()));
    }
}
