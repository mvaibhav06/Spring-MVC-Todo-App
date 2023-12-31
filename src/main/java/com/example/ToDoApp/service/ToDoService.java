package com.example.ToDoApp.service;

import com.example.ToDoApp.dao.ToDoRepository;
import com.example.ToDoApp.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDOItems(){
        return toDoRepository.findAll();
    }

    public ToDo getToDoItemById(int id){
        return toDoRepository.findById(id).get();
    }

    public boolean updateStatus(int id){
        ToDo toDo = getToDoItemById(id);
        toDo.setStatus("completed");

        return saveOrUpdateTodo(toDo);
    }

    public boolean saveOrUpdateTodo(ToDo toDo){
        ToDo updatedTodo = toDoRepository.save(toDo);

        if (getToDoItemById(updatedTodo.getId()) != null){
            return true;
        }
        return false;
    }

    public boolean deleteTodoItem(int id){
        toDoRepository.deleteById(id);

        if (getToDoItemById(id) == null){
            return true;
        }
        return false;
    }

}
