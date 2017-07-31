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

public class Register extends AppCompatActivity {
    ProgressDialog pd;
    EditText name,id,pass1,pass2;
    String RName,Remail,Rpass,Rpass2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pd = new ProgressDialog(this);

        name = (EditText) findViewById(R.id.txtuser_name);
        id = (EditText) findViewById(R.id.txtuser_email);
        pass1 = (EditText) findViewById(R.id.txt_user_password);
        pass2 = (EditText) findViewById(R.id.txt_user_repassword);
    }

    public void userregister(View view) {
        RName = String.valueOf(name.getText());
        Remail = String.valueOf(id.getText());
        Rpass = String.valueOf(pass1.getText());
        Rpass2 = String.valueOf(pass2.getText());

                new BackgroundTask().execute();

    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String urls = "http://192.168.1.107/arun/register.php";
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
                String data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(String.valueOf(Remail),"UTF-8")
                        +"&"+URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(String.valueOf(Rpass),"UTF-8")
                        +"&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(String.valueOf(RName),"UTF-8")
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
            name.setText("");
            id.setText("");
            pass1.setText("");
            pass2.setText("");
            pd.dismiss();
        }

    }


    public void gotologin(View view)
    {
        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);

    }

}
