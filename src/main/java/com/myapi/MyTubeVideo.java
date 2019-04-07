package com.myapi;

import com.google.api.client.util.Lists;
import java.util.List;

public class MyTubeVideo {
    private List<Comment> comments = Lists.newArrayList();
    public List<Comment> getComments() {
        return comments;
    }
}
