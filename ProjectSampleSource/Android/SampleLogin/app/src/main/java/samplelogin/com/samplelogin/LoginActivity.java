package samplelogin.com.samplelogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    Button signInButton;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.buttonSignInId);
        emailEditText = (EditText) findViewById(R.id.editTextEmailId);
        passwordEditText = (EditText) findViewById(R.id.editTextPasswordId);

        signInButton.setOnClickListener(authUserHandler);

    }

    View.OnClickListener authUserHandler = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            JSONObject authUserJSONObject = new JSONObject();

            try {
                authUserJSONObject.put("userId", emailEditText.getText());
                authUserJSONObject.put("userPwd", passwordEditText.getText());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            sendDataToServer(authUserJSONObject);

        }
    };

    public void sendDataToServer(JSONObject jsonObject) {

        if (jsonObject.length() > 0) {
            new SendJsonDataToServer().execute(String.valueOf(jsonObject));
        }

    }

    private class SendJsonDataToServer extends AsyncTask<String, String, String> {


        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://androidfragmentsamples.com/webapi/login");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "text/plain");
                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);
                writer.close();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");
                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
                JsonResponse = buffer.toString();
                return JsonResponse;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Debug 2", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "Contacting Server",
                    "Wait!");
        }
        @Override
        protected void onPostExecute(String s) {

            progressDialog.dismiss();

            if(s != null && s.trim().equals("User Verified")){

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Error: User or Password is incorrect. Please verify!" , Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);

        }


    }
}
