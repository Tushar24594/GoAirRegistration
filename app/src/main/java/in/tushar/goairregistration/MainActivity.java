package in.tushar.goairregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import in.tushar.goairregistration.CSV;

public class MainActivity extends AppCompatActivity {
    public static String TAG="--MainActivity";
    EditText name,email,mobile;
    TextView nameText,emailText,mobileText;
    Button btn;
    Typeface medium;
    CSV csv = new CSV();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        }
        medium = Typeface.createFromAsset(getAssets(), "fonts/Teko-Medium.ttf");
        nameText = findViewById(R.id.nameText);
        nameText.setTypeface(medium);
        emailText = findViewById(R.id.emailText);
        emailText.setTypeface(medium);
        mobileText = findViewById(R.id.mobileText);
        mobileText.setTypeface(medium);
        name = findViewById(R.id.name);
        name.setTypeface(medium);
        email = findViewById(R.id.email);
        email.setTypeface(medium);
        mobile = findViewById(R.id.mobile);
        mobile.setTypeface(medium);
        btn = findViewById(R.id.btn);
        btn.setTypeface(medium);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setScaleX((float)0.8);
                btn.setScaleY((float)0.8);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn.setScaleX((float)1.0);
                        btn.setScaleY((float)1.0);
                        saveDataToCsv(name.getText().toString().trim(),email.getText().toString().trim(),mobile.getText().toString().trim());
                    }
                },200);
            }
        });
    }
    public void saveDataToCsv(String userName, String userEmail, String userMobile){
        Log.e(TAG,userName+userEmail+userMobile);
        if(userMobile.length()<10){
            mobile.setError("Enter your Moblie Number");
            mobile.requestFocus();
            return;
        }else{
            boolean isSaved =  csv.saveDatatoCSV(userName,userMobile,userEmail);
            if(isSaved){
                Intent intent = new Intent(getApplicationContext(),thanks.class);
                startActivity(intent);
                finish();
            }else{
                Log.e(TAG,"Saving Error....");
            }
        }
    }
}
