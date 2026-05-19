package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;

public class UserTests {
    Faker faker;
    User userPayload;
    public static Logger logger;

    @BeforeClass
    public void Setup(){
    faker=new Faker();
    userPayload =new User();

    userPayload.setId(faker.idNumber().hashCode());
    userPayload.setUsername(faker.name().username());
    userPayload.setFirstname(faker.name().firstName());
    userPayload.setLastname(faker.name().lastName());
    userPayload.setEmail(faker.internet().safeEmailAddress());
    userPayload.setPassword(faker.internet().password());
    userPayload.setPhone(faker.phoneNumber().cellPhone());

    logger= LogManager.getLogger(this.getClass());
    }

     @Test(priority = 1)
    public void testPostUser(){
        logger.info("***Creating User***");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
         logger.info("***User Created***");
    }
    @Test(priority = 2)
    public void getUserByName(){
        logger.info("***Getting User***");
        Response response =UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***User Displaying***");
    }
    @Test(priority = 3)
    public void updateUserName(){
        logger.info("***Updating User***");
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***User Updated***");

    }
    @Test(priority = 4)
    public void deleteUserName(){
        logger.info("***Deleting User***");
        Response response =UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***User Deleted***");
    }
}

