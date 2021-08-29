import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class ImgurApiTest extends BaseApiTest{

    public ImgurApiTest() throws IOException {
    }

    @Test
    @DisplayName("Get Account Information: ")
    void testGetAccountInfo() {


        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .baseUri(getBaseUri())
                .expect()
                .log()
                .all()
                .statusCode(200)
                .when()
                .get("3/account/{username}", getUserName());

    }

    @Test
    @DisplayName("Get Album information: ")
    void testGetAlbum() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .baseUri(getBaseUri())
                .expect()
                .body("data",notNullValue())
                .body("data.id",is(getAlbumHash()))
                .body("data.images[0].id",is(getImageHash()))
                .log()
                .all()
                .statusCode(200)
                .when()
                .get("3/account/{username}/album/{albumHash}", getUserName(), getAlbumHash());
    }

    @Test
    @DisplayName("Get Account images: ")
    void testGetAccountImages() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .baseUri(getBaseUri())
                .expect()
                .body("data",notNullValue())
                .body("data[0].id",is(getImageHash()))
                .log()
                .all()
                .statusCode(200)
                .when()
                .get("3/account/me/images");

    }

    @Test
    @DisplayName("Get avatar image")
    void testGetAvatarImage()  {

        given()
                .auth()
                .oauth2(getToken())
                .when()
                .header(new Header("content-type", "multipart/form-data"))
                .expect()
                .statusCode(200)
                .body("data.avatar", is(notNullValue()))
                .log()
                .all()
                .when()
                .get("3/account/{username}/avatar", getUserName())
                .jsonPath();
    }

    @Test
    @DisplayName("Uploading image")
    void testImageUploading() throws Exception {

        currentImageId = given()
                .auth()
                .oauth2(getToken())
                .when()
                .header(new Header("content-type", "multipart/form-data"))
                .multiPart("image", new File("./src/main/resources/rainbow.jpg"))
                .expect()
                .statusCode(200)
                .body("data.id", is(notNullValue()))
                .body("data.deletehash", is(notNullValue()))
                .log()
                .all()
                .when()
                .post("3/upload")
                .jsonPath()
                .getString("data.id");
    }

    @Test
    @DisplayName("Create Comment: ")
    void testCreateAComment() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .baseUri(getBaseUri())
                .log()
                .all()
                .when()
                .formParam("image_id", getImageHash())
                .formParam("comment", "here we are creating any comment")
                .post("3/comment");

    }

    @Test
    @DisplayName("Delete comment")
    public void testDeleteComment() {
        String commentId = currentCommentId;

        given()
                .auth()
                .oauth2(getToken())
                .when()
                .header(new Header("content-type", "multipart/form-data"))
                .expect()
                .log()
                .all()
                .when()
                .get("comment/{commentId}", commentId)
                .jsonPath();
    }
}