package com.example.springvaadin.layout;

import com.example.springvaadin.model.Todo;
import com.example.springvaadin.repository.TodoRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TodoLayout extends HorizontalLayout {

    Checkbox done = new Checkbox();
    TextField task = new TextField();
    Button deleteButton = new Button("Delete");


    private TodoRepository todoRepository;

    public TodoLayout(TodoView todoView, Todo todo, TodoRepository todoRepository) {
        add(done, task, deleteButton);
        setDefaultVerticalComponentAlignment(Alignment.CENTER); // Set alignment

        Binder<Todo> binder = new Binder<>(Todo.class); // binds the user model
        binder.bindInstanceFields(this); // binds the field with model
        binder.setBean(todo); // binds it to provided object

        binder.addValueChangeListener(valueChangeEvent -> todoRepository.save(binder.getBean())); // Listener operation on value change


        deleteButton.addClickListener(buttonClickEvent -> {
            todoRepository.deleteById(binder.getBean()
                                            .getId());
            todoView.refreshTodos();
        });
    }
}
