package com.myapi;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.List;


public class MyTubeParser {
    private static final Logger LOGGER = Logger.getLogger(MyTubeParser.class.getName());
    private static YouTube youtube;
    private static List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.force-ssl");
    private List<MyTubeVideo> listOfVideos = Lists.newArrayList();

    public void init() {
        Credential credential = null;
        try {
            credential = Authorization.authorize(scopes, "commentThreads");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        youtube = new YouTube.Builder(Authorization.HTTP_TRANSPORT, Authorization.JSON_FACTORY, credential)
                .setApplicationName("youtube comments parser").build();
    }

    public void getCommentsFromVideos(List<String> videoId) {
        for (String video : videoId) {
            List<CommentThread> videoComments = getCommentThreads(video);
            CommentSnippet snippet;
            MyTubeVideo youtubeVideo = new MyTubeVideo();

            for (CommentThread videoComment : videoComments) {
                snippet = videoComment.getSnippet().getTopLevelComment().getSnippet();
                Comment comment = new Comment(snippet.getAuthorDisplayName(),
                        snippet.getTextDisplay(),
                        snippet.getLikeCount(),
                        snippet.getPublishedAt(),
                        !(snippet.getUpdatedAt().equals(snippet.getPublishedAt())));
                youtubeVideo.getComments().add(comment);
            }
            listOfVideos.add(youtubeVideo);
        }
    }

    private static List<CommentThread> getCommentThreads(String videoId) {
        CommentThreadListResponse videoCommentsListResponse = null;
        try {
            videoCommentsListResponse = youtube.commentThreads().list("snippet")
                    .setVideoId(videoId).setTextFormat("plainText").execute();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return videoCommentsListResponse.getItems();
    }

    public List<MyTubeVideo> getListOfVideos() {
        return listOfVideos;
    }
}
