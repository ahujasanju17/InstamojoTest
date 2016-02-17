package haftaaa.voidmain.com.instamojotest;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by sanju on 11-01-2016.
 */
public class AppServer {

    public String register(String name , String email , String password)
    {

        String ret_result="";
        String result = "";
        URL serverUrl;
        OutputStream out = null;
        HttpURLConnection httpCon = null;
        Map<String , String > paramsMap = new HashMap<>();
        paramsMap.put("action","register");
        paramsMap.put("username",name);
        paramsMap.put("email",email);
        paramsMap.put("password",password);

        try {
            serverUrl = new URL(Config.URL_REGISTER);
            StringBuilder postBody = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                postBody.append(param.getKey()).append('=')
                        .append(param.getValue());

                if (iterator.hasNext()) {
                    postBody.append('&');

                }
            }
            String body = postBody.toString();
            byte[] bytes = body.getBytes();
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(bytes.length);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            out = httpCon.getOutputStream();

            out.write(bytes);

            InputStream is = null;

            try
            {

                is = httpCon.getInputStream();
                int ch;
                StringBuffer sb = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }

                ret_result = sb.toString();
                Log.d("Register result",ret_result);

            }
            catch (Exception e)
            {

            }

        }catch (Exception e)
        {

        }

        return ret_result;

    }

    public String putContacts(String email, String names , String phone_numbers)
    {

        String ret_result="";
        String result = "";
        URL serverUrl;
        OutputStream out = null;
        HttpURLConnection httpCon = null;
        Map<String , String > paramsMap = new HashMap<>();
        paramsMap.put("action","putrequests");
        paramsMap.put("email",email);
        paramsMap.put("names",names);
        paramsMap.put("phones",phone_numbers);

        try {
            serverUrl = new URL(Config.URL_REGISTER);
            StringBuilder postBody = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                postBody.append(param.getKey()).append('=')
                        .append(param.getValue());

                if (iterator.hasNext()) {
                    postBody.append('&');

                }
            }
            String body = postBody.toString();
            byte[] bytes = body.getBytes();
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(bytes.length);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            out = httpCon.getOutputStream();

            out.write(bytes);

            InputStream is = null;

            try
            {

                is = httpCon.getInputStream();
                int ch;
                StringBuffer sb = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }

                ret_result = sb.toString();
                Log.d("Send result",ret_result);

            }
            catch (Exception e)
            {

            }

        }catch (Exception e)
        {

        }

        return ret_result;


    }


    public String getRequests(String email)
    {

        String ret_result="";
        String result = "";
        URL serverUrl;
        OutputStream out = null;
        HttpURLConnection httpCon = null;
        Map<String , String > paramsMap = new HashMap<>();
        paramsMap.put("action","getrequests");
        paramsMap.put("email",email);

        try {
            serverUrl = new URL(Config.URL_REGISTER);
            StringBuilder postBody = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                postBody.append(param.getKey()).append('=')
                        .append(param.getValue());

                if (iterator.hasNext()) {
                    postBody.append('&');

                }
            }
            String body = postBody.toString();
            byte[] bytes = body.getBytes();
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(bytes.length);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            out = httpCon.getOutputStream();

            out.write(bytes);

            InputStream is = null;

            try
            {

                is = httpCon.getInputStream();
                int ch;
                StringBuffer sb = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }

                ret_result = sb.toString();
                Log.d("Get result",ret_result);

            }
            catch (Exception e)
            {

            }

        }catch (Exception e)
        {

        }

        return ret_result;


    }




    public String login(String email , String password)
    {

        String ret_result="";
        String result = "";
        URL serverUrl;
        OutputStream out = null;
        HttpURLConnection httpCon = null;
        Map<String , String > paramsMap = new HashMap<>();
        paramsMap.put("action","login");
        paramsMap.put("email",email);
        paramsMap.put("password",password);

        try {
            serverUrl = new URL(Config.URL_REGISTER);
            StringBuilder postBody = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                postBody.append(param.getKey()).append('=')
                        .append(param.getValue());

                if (iterator.hasNext()) {
                    postBody.append('&');

                }
            }
            String body = postBody.toString();
            byte[] bytes = body.getBytes();
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setUseCaches(false);
            httpCon.setFixedLengthStreamingMode(bytes.length);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            out = httpCon.getOutputStream();

            out.write(bytes);

            InputStream is = null;

            try
            {

                is = httpCon.getInputStream();
                int ch;
                StringBuffer sb = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }

                ret_result = sb.toString();
                Log.d("Login result",ret_result);

            }
            catch (Exception e)
            {
                Log.d("Error is",e.toString());

            }

        }catch (Exception e)
        {

        }

        return ret_result;

    }





    public String request(Map<String,String> paramsMap)
        {
            String result = "";
            URL serverUrl;
            OutputStream out = null;
            HttpURLConnection httpCon = null;
            try
            {
                serverUrl = new URL(Config.URL_REGISTER);
                StringBuilder postBody = new StringBuilder();
                Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet()
                        .iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> param = iterator.next();
                    postBody.append(param.getKey()).append('=')
                            .append(param.getValue());
                    if (iterator.hasNext()) {
                        postBody.append('&');
                    }
                }
                String body = postBody.toString();
                byte[] bytes = body.getBytes();
                httpCon = (HttpURLConnection) serverUrl.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setUseCaches(false);
                httpCon.setFixedLengthStreamingMode(bytes.length);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                out = httpCon.getOutputStream();

                out.write(bytes);

                int status = httpCon.getResponseCode();

                if(status == 200)
                {
                    result = "success";
                }else
                    result = "failure";


            }
            catch(Exception e)
            {
                Log.d("Server error is ", e.toString());
                result = "Error";
            }

            return result;
        }


}
