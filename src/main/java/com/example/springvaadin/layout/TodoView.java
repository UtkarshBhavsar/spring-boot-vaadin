package com.example.springvaadin.layout;

import com.example.springvaadin.model.Todo;
import com.example.springvaadin.repository.TodoRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class TodoView extends VerticalLayout {

    private final TodoRepository todoRepository;
    private TextField taskField = new TextField();
    private Button addButton = new Button("Add");
    private VerticalLayout todoList = new VerticalLayout();
    private Button clearButton = new Button("Clear Completed");


    public TodoView(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
        add(
                new H1("Important Stuff:"),
                new HorizontalLayout(taskField, addButton),
                todoList,
                clearButton
        );
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addClickShortcut(Key.ENTER);

        addButton.addClickListener(buttonClickEvent -> {
            todoRepository.save(new Todo(taskField.getValue()));
            taskField.clear();
            taskField.focus();

            refreshTodos();
        });

        clearButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clearButton.addClickListener(buttonClickEvent -> {

            todoRepository.deleteByDone(Boolean.TRUE);
            refreshTodos();
        });
        refreshTodos();
    }

    void refreshTodos() {
        todoList.removeAll();
        todoRepository.findAll()
                      .stream()
                      .map(todo -> new TodoLayout(this, todo, todoRepository))
                      .forEach(todoList::add);

    }
}
