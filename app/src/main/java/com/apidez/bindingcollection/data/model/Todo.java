package com.apidez.bindingcollection.data.model;

public class Todo {
    public final long id;
    public final String title;
    public final String dueDate;
    public final boolean completed;

    public Todo(long id, String title, String dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo other = (Todo) obj;
            return id == other.id;
        }
        return super.equals(obj);
    }
}
