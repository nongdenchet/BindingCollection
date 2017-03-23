package com.apidez.bindingcollection.interactor;

import com.apidez.bindingcollection.data.model.Todo;
import com.apidez.bindingcollection.data.repo.TodoRepo;
import com.apidez.bindingcollection.data.state.TodoListCollector;

import javax.inject.Inject;

import io.reactivex.Completable;

public class CreateTodo {
    private final TodoRepo todoRepo;
    private final TodoListCollector todoListCollector;

    @Inject
    public CreateTodo(TodoRepo todoRepo, TodoListCollector todoListCollector) {
        this.todoRepo = todoRepo;
        this.todoListCollector = todoListCollector;
    }

    public Completable execute(Params params) {
        return Completable.fromAction(() -> {
            Todo todo = todoRepo.createTodo(params.title, params.dueDate);
            todoListCollector.add(todo);
        });
    }

    public static final class Params {
        public final String title;
        public final String dueDate;

        public Params(String title, String dueDate) {
            this.title = title;
            this.dueDate = dueDate;
        }
    }
}
