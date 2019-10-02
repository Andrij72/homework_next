package com.myapi;

import java.util.ArrayList;
import java.util.List;

public class MyTubeApplication {
    public static void main(String[] args) {
        MyTubeParser parser = new MyTubeParser();
        parser.init();
        List<String> listOfVideoId = new ArrayList<>();
        listOfVideoId.add("XR0YXH0ue2I");
        parser.getCommentsFromVideos(listOfVideoId);

        for (MyTubeVideo video : parser.getListOfVideos()) {
            for (Comment comment : video.getComments()) {
                System.out.println("[Author]" + comment.getAuthorName());
                System.out.println("[Likes] " + comment.getLike());
                System.out.println("[Comment] " + comment.getMessageText());
                System.out.println("[isEditind] " + comment.isEditing());
                System.out.println("[DateLastModified] " + comment.getLastModified());
                System.out.println("****************************************");
            }
        }
    }
}
