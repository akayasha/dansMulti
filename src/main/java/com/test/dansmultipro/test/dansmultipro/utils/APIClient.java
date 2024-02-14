package com.test.dansmultipro.test.dansmultipro.utils;
import com.test.dansmultipro.test.dansmultipro.dto.PayloadData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APIClient {

    public PayloadData findById(String id) {
        String apiUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            StringBuilder response = new StringBuilder();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("id").equals(id)) {
                        PayloadData payloadData = new PayloadData();
                        payloadData.setCreated_at(jsonObject.getString("created_at"));
                        payloadData.setDescription(jsonObject.getString("description"));
                        payloadData.setCompany(jsonObject.getString("company"));
                        payloadData.setCompany_url(jsonObject.getString("company_url"));
                        payloadData.setLocation(jsonObject.getString("location"));
                        payloadData.setId(jsonObject.getString("id"));
                        payloadData.setType(jsonObject.getString("type"));
                        payloadData.setTitle(jsonObject.getString("title"));
                        payloadData.setUrl(jsonObject.getString("url"));
                        return payloadData;
                    }
                }
            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PayloadData> hitApi() {
        String apiUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

        List<PayloadData> payloadDataList = new ArrayList<>();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            StringBuilder response = new StringBuilder();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    PayloadData payloadData = new PayloadData();
//                    payloadData.setCreated_at(jsonObject.getString("created_at"));
//                    payloadData.setDescription(jsonObject.getString("description"));
//                    payloadData.setCompany(jsonObject.getString("company"));
//                    payloadData.setCompany_url(jsonObject.getString("company_url"));
//                    payloadData.setLocation(jsonObject.getString("location"));
//                    payloadData.setId(jsonObject.getString("id"));
//                    payloadData.setType(jsonObject.getString("type"));
//                    payloadData.setTitle(jsonObject.getString("title"));
//                    payloadData.setUrl(jsonObject.getString("url"));


                    if (jsonObject.has("created_at")) {
                        payloadData.setCreated_at(jsonObject.getString("created_at"));
                    }
                    if (jsonObject.has("description")) {
                        payloadData.setDescription(jsonObject.getString("description"));
                    }
                    if (jsonObject.has("company")) {
                        payloadData.setCompany(jsonObject.getString("company"));
                    }
                    if (jsonObject.has("company_url")) {
                        Object companyUrlObj = jsonObject.get("company_url");
                        if (companyUrlObj instanceof String) {
                            payloadData.setCompany_url((String) companyUrlObj);
                        } else {

                            payloadData.setCompany_url("");
                        }
                    }
                    if (jsonObject.has("location")) {
                        payloadData.setLocation(jsonObject.getString("location"));
                    }
                    if (jsonObject.has("id")) {
                        payloadData.setId(jsonObject.getString("id"));
                    }
                    if (jsonObject.has("type")) {
                        payloadData.setType(jsonObject.getString("type"));
                    }
                    if (jsonObject.has("title")) {
                        payloadData.setTitle(jsonObject.getString("title"));
                    }
                    if (jsonObject.has("url")) {
                        payloadData.setUrl(jsonObject.getString("url"));
                    }

                    payloadDataList.add(payloadData);
                }

            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
            return payloadDataList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
