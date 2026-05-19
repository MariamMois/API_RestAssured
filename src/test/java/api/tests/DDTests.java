package api.tests;

import api.Utilities.DataProviders;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUsers(String userID, String Username, String fname,String lname, String useremail, String pwd, String ph )
    {
        User userPayload=new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(Username);
        userPayload.setFirstname(fname);
        userPayload.setLastname(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
    public void deleteUsername(String Username)
    {
        Response response = UserEndPoints.deleteUser(Username);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
