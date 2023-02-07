package com.epam.testing.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ProtocolException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

/**
 * Verify reCAPTCHA util
 *
 * @author rom4ik
 */
public class VerifyRecaptcha {
    private static final Logger LOGGER = LogManager.getLogger(VerifyRecaptcha.class);
    public static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET_KEY = "6Lf3vl4kAAAAACXVO5rTc72bGt7Oh0lbYBSpKLlm";
    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Don't let anyone instantiate this class.
     */
    private VerifyRecaptcha() {}

    /**
     * Sends request to google verify service
     *
     * @param gRecaptchaResponse response from html page
     * @return verification success
     */
    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try {
            URL obj = new URL(VERIFY_URL);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            addRequestHeader(con);
            sendPostRequest(con, gRecaptchaResponse);

            int responseCode = con.getResponseCode();
            LOGGER.info("Sending 'POST' request to URL : {}", VERIFY_URL);
            LOGGER.info("Response Code : {}", responseCode);

            String response = getResponse(con);
            return parseResponseAndGetSuccessValue(response);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
            return false;
        }
    }

    private static void addRequestHeader(HttpsURLConnection connection) throws ProtocolException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
    }

    private static void sendPostRequest(HttpsURLConnection connection, String gRecaptchaResponse) throws IOException {
        String postParams = "secret=" + SECRET_KEY + "&response="
                + gRecaptchaResponse;

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
    }

    private static String getResponse(HttpsURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    private static boolean parseResponseAndGetSuccessValue(String response) {
        JsonObject jsonObject;
        try(JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
            jsonObject = jsonReader.readObject();
        }
        return jsonObject.getBoolean("success");
    }
}
