package com.spotify.oauth2.api.applicationApi;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {
    @Step
    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, getToken(), requestPlaylist);
    }
    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlist_id){
        return RestResource.get(PLAYLISTS + "/" + playlist_id, getToken());
    }

    public static Response update(Playlist requestPlaylist, String playlist_id){
        return RestResource.update(PLAYLISTS + "/" + playlist_id, getToken(), requestPlaylist);
    }
}
