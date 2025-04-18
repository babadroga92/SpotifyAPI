package com.spotify.oauth2.tests;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Listeners({AllureTestNg.class})
@Epic("Spotify OAuth 2.0")
@Feature("Playlist API")
public class PlaylistTest {

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
    }

    @Story("Create a playlist story")
    @Link("https:/example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("Testing if the user can create a playlist.")
    @Test(description = "Should be able to create a playlist.")
    public void shouldBeAbleToCreatePlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.getStatusCode(), StatusCode.CODE_201.getCode());

       // Playlist responsePlaylist = response.as(Playlist.class); // deserializing the JSON
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }
    @Description("Testing if the user can open the desired playlist.")
    @Test(description = "Should be able to fetch the playlist.")
    public void shouldBeAbleToFetchThePlaylist(){
        Playlist requestPlaylist = playlistBuilder("Elizabeth Playlist", "Music for her soul", true);
        Response deserializedResponsePlaylist = PlaylistApi.get(DataLoader.getInstance().getPlaylistId("get_playlist_id"));
        Playlist serializedResponsePlaylist = deserializedResponsePlaylist.as(Playlist.class);
        assertPlaylistEqual(serializedResponsePlaylist, requestPlaylist);
    }
    @Description("Testing if the user can update the playlist.")
    @Test(description = "Should be able to Update playlist.")
    public void shouldBeAbleToUpdatePlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response deserializedResponse = PlaylistApi.update(requestPlaylist, DataLoader.getInstance().getPlaylistId("update_playlist_with_id"));
        assertStatusCode(deserializedResponse.getStatusCode(), StatusCode.CODE_200.getCode());
    }
    @Description("Testing if the user can create a playlist without providing the name.")
    @Test(description = "Should Not be able to create a playlist without name")
    public void shouldNotBeAbleToCreateAPlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());
        assertError(response.as(Error.class), StatusCode.CODE_400.getCode(),StatusCode.CODE_400.getMessage());
    }
    @Description("Testing if the user can create a playlist with expired token.")
    @Test(description = "Should not be able to create a playlist with expired token.")
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(), false);
        Response response = PlaylistApi.post("randomValue",requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401.getCode());
        assertError(response.as(Error.class), StatusCode.CODE_401.getCode(),StatusCode.CODE_401.getMessage());
    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }
    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }
    @Step
    public void assertError(Error responseErr, int expectedStatusCode, String expectedMsg){
        assertThat(responseErr.getInnerError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseErr.getInnerError().getMessage(), equalTo(expectedMsg));
    }
}
