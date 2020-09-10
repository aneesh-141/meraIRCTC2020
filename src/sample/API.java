package sample;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class API {

    private static HttpURLConnection connection;
    public String fetchDataFromApi(String newURL){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(newURL);

            connection = (HttpURLConnection) url.openConnection();
            // to create http request, we need a connection, and we create that from the url.
            //using the url object, we are creating a new connection object
            //url.openConnection() is a method which returns a object, which we are mapping to the connection

            connection.setRequestMethod("GET");
            //doing a get request
            //WHENEVER we requesting data from http, there are two ways to get it, one is POST and the other is GET
            // Post is used for forms (hidden) and via the url(sending data over url), we use GET, here we access data via
            // passing data over url, so we use GET

            //Against doing the request, we get a response code, 200 for connection perfect, 404 for not found

            int status = connection.getResponseCode();

            if(status == 200){

                Scanner fetch = new Scanner( new InputStreamReader(connection.getInputStream()));

                while (fetch.hasNextLine()){
                    sb.append(fetch.nextLine());
                }
                connection.disconnect();
            }
            return (sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ("error");
        } catch (IOException e) {
            e.printStackTrace();
            return ("error");
        }
    }
}
