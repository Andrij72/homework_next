package com.myapi;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Comment {
    private String authorName;
    private String messageText;
    private long like;
    private DateTime lastModified;
    private boolean editing;

    public boolean isEditing() {
        return editing;
    }
}
