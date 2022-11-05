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

public class Main {
    private static Setting setting;

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        setting = Configuration.getConfiguration().getSetting();

//        N1
//        getUser(1);
//      -------------
//        N2
//        getUser(14);
//      -------------
//        N3
//        PostFillBalance ps = new PostFillBalance("126",3,150);
//        fillBalance(ps);
//      -------------
//        N4
//        PostFillBalance ps = new PostFillBalance("1006", 30, 150);
//        fillBalance(ps);

//        N5
//       -------------
//        PostFillBalance ps = new PostFillBalance("1009", 3, -150);
//        fillBalance(ps);

//        N6
//       -------------
//        PostFillBalance ps = new PostFillBalance("126", 3, 150);
//        fillBalance(ps);

//        N7
//       -------------
//        PostFillBalance ps = new PostFillBalance("119", 3, 0); !!!!!!!!!!!
//        fillBalance(ps);

//        N8
//       -------------
//        PostFillBalance ps = new PostFillBalance("94", 1, -1000);
//        fillBalance(ps);


//        N9
//       -------------
//        PostFillBalance ps = new PostFillBalance("106", 2, 500);
//        fillBalance(ps);

//        N10
//       -------------
//        PostFillBalance ps = new PostFillBalance("106", 3, 50);
//        fillBalance(ps);

    }

    public static void getUser(int id) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(
                new URI(setting.getUrl1() + id)).GET().build();

        HttpResponse<String> response = HttpClient.newHttpClient().
                send(request, HttpResponse.BodyHandlers.ofString());
        if (!(response.body().contains("User not found"))) {
            System.out.println(response.body());

        } else {
            System.out.println(response.statusCode());
        }
    }

    public static void fillBalance(PostFillBalance ps) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(ps);
        HttpRequest request = HttpRequest.newBuilder().uri(
                        new URI(setting.getUrl2())).headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json)).build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.statusCode());

    }
}
