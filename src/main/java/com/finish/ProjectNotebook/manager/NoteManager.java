package com.finish.ProjectNotebook.manager;

import com.finish.ProjectNotebook.ProjectNotebookApplication;
import com.finish.ProjectNotebook.object.Note;
import com.finish.ProjectNotebook.repository.DataControllBD;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Data
@Service
@AllArgsConstructor
public class NoteManager {
    private static NoteManager instance;
    private static final Object lock = new Object();/// не сильно понял этот локер. по теории он блочит создание дубля в многопоточности
    private List<Note> notebook;
    private DataControllBD sqlCore;

    private NoteManager() throws SQLException {
        notebook = new ArrayList<>();
        sqlCore = DataControllBD.getDBData();
        sqlCore.readDbase(notebook);

    }
    public static NoteManager getNoteData() throws SQLException {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new NoteManager();
                }
            }
        }
        return instance;
    }

   /* public List<Note> createNote(int id, String name, String txt) throws SQLException{

        sqlCore.insertDbase("notes","id,name,txt",""+id+",'"+name+"','"+txt+"'");
        sqlCore.readDbase(notebook);
        return notebook;
    }*/

    public List<Note> createNote(int id, String name, String txt) throws SQLException{

        Note note = Note.builder().id(id).name(name).txt(txt).build();
        sqlCore.readDbase(notebook);
        if (!notebook.contains(note)) {
            sqlCore.insertDbase("notes","id,name,txt",""+id+",'"+name+"','"+txt+"'");
            sqlCore.readDbase(notebook);
            return notebook;
        }else{
            sqlCore.readDbase(notebook);
            return notebook;
        }

    }
}
