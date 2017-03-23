package com.apidez.bindingcollection.data.state;

import com.apidez.bindingcollection.data.model.Todo;

import java.util.List;

public interface TodoListCollector {
    void addAll(List<Todo> todos);
    void add(Todo todo);
    void remove(Todo todo);
    void update(Todo todo);
}
