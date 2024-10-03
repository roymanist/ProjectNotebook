package com.finish.ProjectNotebook.controller;

import com.finish.ProjectNotebook.manager.NoteManager;
import com.finish.ProjectNotebook.object.Note;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor
public class NoteController {


    @GetMapping
    public List<Note> findAllNotes() throws SQLException
    {
        NoteManager x = NoteManager.getNoteData();
        return x.getNotebook();

    }

    @GetMapping("CreateNote")
    public List<Note> createNote(@RequestParam int id, @RequestParam String name, @RequestParam String txt) throws SQLException {
        NoteManager x = NoteManager.getNoteData();
        return x.createNote(id,name,txt);
    }
}
