package com.deqode.android.surveybaru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        Button tombol = (Button) findViewById(R.id.buttonLogin);

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataEmail = email.getText().toString();
                String dataPass = password.getText().toString();

                sentDataLogin(dataEmail, dataPass);
            }
        });
    }

    private void sentDataLogin(final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://api.pksjabar.com/api/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject dataSemua = new JSONObject(response);
                            String status = dataSemua.getString("status");
                            if (status.equals("OK")) {
                                Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Gagal Woyy", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                } catch (Exception e) {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                try {
                    header.put("Content-Type", "application/json");
                    header.put("Authorization", "LPlRaf1b1x2NHzTRRIWKjyj8fx778u");
                    return header;
                } catch (Exception e) {
                    Toast.makeText(Login.this, "header error", Toast.LENGTH_SHORT).show();
                    return header;
                }
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
