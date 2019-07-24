package POST_PUT_DELETE;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class post_put_delete
{
    public String url = "http://localhost:9000";
    public RequestSpecification request;
    public Response response;

    @BeforeClass
    public void init()
    {
        RestAssured.baseURI=url;
        request=RestAssured.given();
        request.header("Content-Type","application/json");
    }

    @Test
    public void Test01_Post()
    {
        JSONObject reqParam=new JSONObject();
        reqParam.put("id","101");
        reqParam.put("firstName","Shay");
        reqParam.put("lastName","gazit");
        reqParam.put("email","aaa@bbb.com");
        reqParam.put("programme","InfoSys");
        request.body(reqParam.toJSONString());
        response=request.post("/student");
        assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void addNewStudent()
    {
        JSONObject reqParam=new JSONObject();
        List<String> CourseList=new ArrayList<>();
        CourseList.add("Python");
        CourseList.add("Java");
        CourseList.add("Ruby");
        reqParam.put("id","102");
        reqParam.put("firstName","AAA");
        reqParam.put("lastName","BBB");
        reqParam.put("email","ccc@bbb.com");
        reqParam.put("programme","InfoSys1");
        reqParam.put("courses",CourseList);
        request.body(reqParam.toJSONString());
        response=request.post("/student");
        assertEquals(response.getStatusCode(),201);
    }

    @Test
    public void update_put()
    {
        JSONObject reqParam=new JSONObject();
        reqParam.put("id","101");
        reqParam.put("firstName","Shay");
        reqParam.put("lastName","gazit");
        reqParam.put("email","aaa@bbb.com");
        reqParam.put("programme","hhhhhhhhhhh");
        request.body(reqParam.toJSONString());
        response=request.put("/student");
        assertEquals(response.getStatusCode(),200);
        response=request.delete("/stident/101");
        assertEquals(response.getStatusCode(),204);
    }



}
