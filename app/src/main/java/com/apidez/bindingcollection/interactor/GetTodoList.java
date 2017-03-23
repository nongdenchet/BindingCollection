package com.apidez.bindingcollection.interactor;

import com.apidez.bindingcollection.data.repo.TodoRepo;
import com.apidez.bindingcollection.data.state.TodoListCollector;
import com.apidez.bindingcollection.data.state.TodoListProvider;

import javax.inject.Inject;

import io.reactivex.Completable;

public class GetTodoList {
    private final TodoRepo todoRepo;
    private final TodoListCollector todoListCollector;
    private final TodoListProvider todoListProvider;

    @Inject
    public GetTodoList(TodoRepo todoRepo,
                       TodoListCollector todoListCollector,
                       TodoListProvider todoListProvider) {
        this.todoRepo = todoRepo;
        this.todoListCollector = todoListCollector;
        this.todoListProvider = todoListProvider;
    }

    public Completable execute(boolean passState) {
        return Completable.fromAction(() -> {
            if (isCurrentListEmpty() && !passState) {
                todoListCollector.addAll(todoRepo.getTodos());
            }
        });
    }

    private boolean isCurrentListEmpty() {
        return todoListProvider.current().isEmpty();
    }
}
