package com.finish.ProjectNotebook.object;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {
    private int id;
    private String name;
    private String txt;
}
