package com.example.wallet.service;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class EsftAccountStatusImpl implements EsftAccountStatusService {
    @Override
    public void updateAccountStatus(String username, String status) throws Exception{

           URL url = new URL("http://www.example.com/resource");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("Resource content");
            out.close();
            httpCon.getInputStream();
        }
}
