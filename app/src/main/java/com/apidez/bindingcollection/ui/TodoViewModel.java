package com.apidez.bindingcollection.ui;

import android.view.View;

import com.apidez.bindingcollection.data.model.Todo;

public class TodoViewModel {
    public final long id;
    public final String title;
    public final String dueDate;
    public final boolean completed;

    public TodoViewModel(Todo todo) {
        this.id = todo.id;
        this.title = todo.title;
        this.dueDate = todo.dueDate;
        this.completed = todo.completed;
    }

    public TodoViewModel(TodoViewModel todo, boolean completed) {
        this.id = todo.id;
        this.title = todo.title;
        this.dueDate = todo.dueDate;
        this.completed = completed;
    }

    public TodoViewModel setCompleted(boolean completed) {
        return new TodoViewModel(this, completed);
    }

    public int removeVisibility() {
        return completed ? View.VISIBLE : View.INVISIBLE;
    }

    public Todo toModel() {
        return new Todo(id, title, dueDate, completed);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoViewModel) {
            TodoViewModel other = (TodoViewModel) obj;
            return id == other.id;
        }
        return super.equals(obj);
    }
}
