package ba.fit.api.is_klinika.helper;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model.AutentifikacijaProvjeraVM;
import ba.fit.api.is_klinika.helper.url.MojRazultat;
import ba.fit.api.is_klinika.helper.url.config;

public class HttpManager {
    public static <T>MojRazultat<T> get(String URL,Class<T> outPutType, NameValuePair... param){
        String UrlParam = URLEncodedUtils.format(Arrays.asList(param) ,"utf-8");
        HttpGet httpGet = new HttpGet(config.URL+ URL+"?"+UrlParam);
        DefaultHttpClient client = new DefaultHttpClient();

        MojRazultat<T> x = new MojRazultat<>();

        try {
            HttpResponse response = client.execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();
            String strJSON= ConvertInputStreamToStream(inputStream);
            T value = MyGSON.build().fromJson(strJSON,outPutType);
            x.value=value;
            x.IsError=false;
            x.errorMSG=null;
        } catch (IOException e) {
            Log.e("errorMessage",e.getMessage());
            x.errorMSG = e.getMessage();
            x.IsError = true;
            x.value=null;
        }
        return x;

    }

    private static String ConvertInputStreamToStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String Line = null;
        StringBuilder stringBuilder =new StringBuilder();

        while ((Line = bufferedReader.readLine()) != null){
            stringBuilder.append(Line+"\n");
        }
        return stringBuilder.toString();

    }
}
