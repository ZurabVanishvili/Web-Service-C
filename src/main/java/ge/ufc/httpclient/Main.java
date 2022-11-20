package ge.ufc.httpclient;

import com.google.gson.Gson;
import ge.ufc.httpclient.model.Configuration;
import ge.ufc.httpclient.model.PostFillBalance;
import ge.ufc.httpclient.model.Setting;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


public class Main {
    private static Setting setting;

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        setting = Configuration.getConfiguration().getSetting();

//        N1
        getUser(1);    //success
//      -------------
//        N2
//        getUser(14);   //User not found
//      -------------
//        N3
//        PostFillBalance ps = new PostFillBalance("171",1,50);
//        System.out.println( fillBalance(ps));  //success

//      -------------
//        N4
//        PostFillBalance ps = new PostFillBalance("1001", 30, 150);
//        System.out.println(fillBalance(ps));   //404(User not found)
//       -------------

//        N5
//        PostFillBalance ps = new PostFillBalance("1003", 3, -150);
//        System.out.println(fillBalance(ps));     //404 unacceptable amount

//        N6
//       -------------
//        PostFillBalance ps = new PostFillBalance("126", 3, 150);
//        System.out.println(fillBalance(ps));     //200

//        N7
//       -------------
//        PostFillBalance ps = new PostFillBalance("156", 3, 200);
//        System.out.println( fillBalance(ps));       //200

//        N8
//       -------------
//        PostFillBalance ps = new PostFillBalance("94", 1, -1000);
//        System.out.println(fillBalance(ps));             //status code 400


//        N9
//       -------------
//        PostFillBalance ps = new PostFillBalance("106", 2, 500);
//        System.out.println(fillBalance(ps));        //status code = 409

//        N10
//       -------------
//        PostFillBalance ps = new PostFillBalance("106", 3, 50);
//        System.out.println(fillBalance(ps));

    }

    public static void getUser(int id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(
                new URI(setting.getUrl1() + id)).GET() .timeout(Duration.of(setting.getTimeout(), ChronoUnit.SECONDS)).build();

        HttpResponse<String> response = HttpClient.newHttpClient().
                send(request, HttpResponse.BodyHandlers.ofString());
        if (!(response.body().contains("User not found"))) {
            System.out.println(response.body());

        } else {
            System.out.println(response.statusCode());
        }
    }

    public static String fillBalance(PostFillBalance ps) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(ps);
        HttpRequest request = HttpRequest.newBuilder().uri(
                        new URI(setting.getUrl2())).headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json)).timeout(Duration.of(setting.getTimeout(), ChronoUnit.SECONDS)).build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status == 200) {
            return "Response status code: " + response.statusCode() + "\n" + response.body();
        }
        return response.body();


    }
}
