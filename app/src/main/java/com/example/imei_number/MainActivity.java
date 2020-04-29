package com.example.imei_number;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imei_number.data.model.Post;
import com.example.imei_number.data.model.remote.APIService;
import com.example.imei_number.data.model.remote.ApiUtils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_READ_PHONE_STATE = 1;
    private Button submitButton;
    private APIService mApiService;
    private EditText name;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton =findViewById(R.id.submit_button);
        name = findViewById(R.id.name);

        mApiService= ApiUtils.getApiService();

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                } else {
                    String imei = telephonyManager.getImei();
                    String phoneNumbers = telephonyManager.getLine1Number();

                    sendPost( phoneNumbers,imei,userName);
                }
            }
        });
    }

    public void sendPost(String phoneNumber,String IMEI,String name){
        mApiService.saveData(phoneNumber,IMEI,name).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                // mResponse.setText(response.IMEI().getTitle()+" ".concat(response.IMEI().getBody()+" ").concat(response.IMEI().getId().toString()+" ").concat(response.IMEI().getUserId().toString())+" ");
                //Toast.makeText(MainActivity.this,"Post işlemi tamamlandı.",Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.i("sudhanshu",t.toString());
                Toast.makeText(MainActivity.this,"Done2",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    assert telephonyManager != null;
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Request Not granted", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String imei = telephonyManager.getImei();
                    String phoneNumbers = telephonyManager.getLine1Number();
                    String userName = name.getText().toString();
                    sendPost( phoneNumbers,imei,userName);
                }
        }
    }

    private static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // Not implemented
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                // Not implemented
            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
