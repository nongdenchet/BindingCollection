package com.apidez.bindingcollection.data.state;

import com.apidez.bindingcollection.data.model.Todo;

import java.util.List;

import io.reactivex.Observable;

public interface TodoListProvider {
    Observable<List<Todo>> provide();
}
