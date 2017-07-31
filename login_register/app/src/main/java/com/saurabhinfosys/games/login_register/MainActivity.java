package com.saurabhinfosys.games.login_register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    ProgressDialog pd;
    EditText id,pass;
    String SID,Spass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(this);
        id = (EditText) findViewById(R.id.txtusername);
        pass = (EditText) findViewById(R.id.txtpassword);

    }


    public void loginnow(View view) {

        if(id.getText().toString().equals(""))
        {
            id.setError("UserName is Required");
        }else if(pass.getText().toString().equals(""))
        {
            pass.setError("Password is Required");
        }
        else{
            SID=id.getText().toString();
            Spass = pass.getText().toString();
            new BackgroundTask().execute();
        }


    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String urls = "http://192.168.1.107/arun/login.php";
        Context ctx;
        Activity activity;

        public BackgroundTask(Context ctx) {
            this.ctx = ctx;
            activity = (Activity) ctx;
        }

        public BackgroundTask() {

        }

        @Override
        protected void onPreExecute() {
            pd.setMessage("Logging in");
            pd.show();
            //super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL(urls);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(String.valueOf(SID),"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(String.valueOf(Spass),"UTF-8")
                        ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();


                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            Toast.makeText(getApplicationContext(),"Lid: "+aVoid,Toast.LENGTH_LONG).show();


            pd.dismiss();
        }


    }


    public void gotoreg(View view)
    {
        Intent i =new Intent(this,Register.class);
        startActivity(i);

    }
}
