package com.apidez.bindingcollection.data.repo;

import com.apidez.bindingcollection.data.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepoImpl implements TodoRepo {
    private List<Todo> todos = new ArrayList<>();

    public TodoRepoImpl() {
        for (int i = 0; i < 20; i++) {
            todos.add(new Todo(i, "Task " + (i + 1), "0" + (i + 1) + "/12/2010", i % 4 == 0));
        }
    }

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    @Override
    public void updateTodo(Todo todo) {
        int index = indexOf(todo);
        if (index != -1) {
            todos.set(index, todo);
        }
    }

    @Override
    public void deleteTodo(Todo todo) {
        todos.remove(todo);
    }

    @Override
    public Todo createTodo(String title, String dueDate) {
        Todo todo = new Todo(nextId(), title, dueDate, false);
        todos.add(0, todo);
        return todo;
    }

    private long nextId() {
        return todos.get(todos.size() - 1).id + 1;
    }

    private int indexOf(Todo todo) {
        return todos.indexOf(todo);
    }
}
