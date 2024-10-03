package com.finish.ProjectNotebook.repository;

import com.finish.ProjectNotebook.object.Note;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

@Data
@Repository
public class DataControllBD {

    private static DataControllBD instance;
    private static final Object lock = new Object();/// не сильно понял этот локер. по теории он блочит создание дубля в многопоточности
    private Connection connection;
    private Statement SQL;
    private DataControllBD() throws SQLException {
        // connection = DriverManager.getConnection("jdbc:h2:E:/JAVA_LRN/II/PROJ/NEW-NOTE-V2/src/main/java/dbase/NoteDBase");
        connection = DriverManager.getConnection(GetURL());
        SQL = connection.createStatement();
    }

    public static DataControllBD getDBData() throws SQLException {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DataControllBD();
                }
            }
        }
        return instance;
    }

    public String GetURL() {


        String relativePath = "src/main/java/resources/NoteDBase";

        // Преобразование относительного пути в абсолютный
        Path path = Paths.get(relativePath).toAbsolutePath();
        String URL = "jdbc:h2:" + path;
        return URL;
    }

    public void createDbase(String NameDB,String Fields) throws SQLException {
        SQL.execute("CREATE TABLE IF NOT EXISTS "+NameDB+"("+Fields+")");
        //  "CREATE TABLE IF NOT EXISTS notes (id INT AUTO_INCREMENT PRIMARY KEY, nameNote VARCHAR(10), textNote VARCHAR(255), dtCreate TIMESTAMP, dtChange TIMESTAMP)";

    }

    public void insertDbase(String nameDB, String args,String values) throws SQLException {
        SQL.execute( "INSERT INTO "+nameDB+ "("+args+ ") SELECT " +values);
        // INSERT INTO notes (nameNote, textNote, dtCreate, dtChange  ) SELECT 'Тестовая','привет мир','2024-05-16','2024-05-16'
    }

    public void readDbase(List<Note> notes) throws SQLException {
        ResultSet resultSet = SQL.executeQuery("SELECT * FROM notes");

        while (resultSet.next()) {
            Note note = Note.builder().build();
            note.setId(resultSet.getInt("id"));
            note.setName(resultSet.getString("name").trim());
            note.setTxt(resultSet.getString("txt").trim());


            if (!notes.contains(note)) {
                notes.add(note);

            }
        }
    }
}
