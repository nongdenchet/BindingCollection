package com.apidez.bindingcollection.data.repo;

import com.apidez.bindingcollection.data.model.Todo;

import java.util.List;

public interface TodoRepo {
    List<Todo> getTodos();
    void updateTodo(Todo todo);
    void deleteTodo(Todo todo);
    Todo createTodo(String title, String dueDate);
}
