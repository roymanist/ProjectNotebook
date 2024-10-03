package com.finish.ProjectNotebook;

import com.finish.ProjectNotebook.manager.NoteManager;
import com.finish.ProjectNotebook.object.Note;
import com.finish.ProjectNotebook.repository.DataControllBD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjectNotebookApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(ProjectNotebookApplication.class, args);

	}


}
