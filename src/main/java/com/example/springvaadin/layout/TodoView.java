package com.example.springvaadin.layout;

import com.example.springvaadin.repository.TodoRepository;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class TodoView extends VerticalLayout {

    private final TodoRepository todoRepository;

    public TodoView(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        add(new H1("Hello World!"));
    }
}
