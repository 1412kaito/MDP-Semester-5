package com.example.p04;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static int REQUEST_CODE = 21711;
    TextView labelHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        labelHasil= findViewById(R.id.tvResult);
        Button tblMove = findViewById(R.id.btnPindah);
        Button tblMoveData = findViewById(R.id.btnPindahDenganData);
        Button tblMoveObject = findViewById(R.id.btnPindahDenganObject);
        Button tblMoveResult = findViewById(R.id.btnPindahUntukResult);
        Button tblTelpon = findViewById(R.id.btnTelpon);
        Button tblGoogle = findViewById(R.id.btnKeenam);
        Button tblMap = findViewById(R.id.btnMap);

        tblMove.setOnClickListener(this);
        tblMoveData.setOnClickListener(this);
        tblMoveObject.setOnClickListener(this);
        tblMoveResult.setOnClickListener(this);
        tblTelpon.setOnClickListener(this);
        tblGoogle.setOnClickListener(this);
        tblMap.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==MoveResult.RESULT_CODE){
            labelHasil.setText(R.string.pilihan_anda);
            labelHasil.setText(labelHasil.getText()+data.getStringExtra(MoveResult.EXTRA_NAMA_MAJOR));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnPindah){
            Intent pindahTanpaData = new Intent(MainActivity.this, ActivityBaru.class);
            startActivity(pindahTanpaData);
        }
        else if (view.getId()==R.id.btnPindahDenganData){
            Intent pindahDenganData = new Intent(MainActivity.this, ActivityBaruDenganData.class);
            pindahDenganData.putExtra(ActivityBaruDenganData.EXTRA_NRP, Long.valueOf("217116609"));
            pindahDenganData.putExtra(ActivityBaruDenganData.EXTRA_NAMA, "IVAN MARCELLINO");
            startActivity(pindahDenganData);
        }
        else if (view.getId()==R.id.btnPindahDenganObject){
            Intent pindahDenganObject = new Intent(MainActivity.this, MoveObj.class);
            pindahDenganObject.putExtra(MoveObj.EXTRA_MAHASISWA, new Mahasiswa(217116609, "IVAN MARCELLINO", "ivangoivan@gmail.com"));
            startActivity(pindahDenganObject);
        }
        else if (view.getId()==R.id.btnPindahUntukResult) {
            Intent pindahResult = new Intent(MainActivity.this, MoveResult.class);
            startActivityForResult(pindahResult, REQUEST_CODE);

        }
        else if (view.getId()==R.id.btnTelpon){
            Intent telpon = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:081216282188"));
            startActivity(telpon);
        }
        else if (view.getId()==R.id.btnKeenam){
            Intent bukaGoogle = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
            startActivity(bukaGoogle);
        } else if (view.getId()==R.id.btnMap){
            Intent temp = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Dharmahusada+Mas+BG" +
                    "+18"));
            temp.setPackage("com.google.android.apps.maps");
            if (temp.resolveActivity(getPackageManager())!=null){
                startActivity(temp);
            }
            else {
                Toast.makeText(this, "Google Maps not installed", Toast.LENGTH_SHORT).show();
            }
        }

//        atau
//        switch (view.getId()){
//            case R.id.btnPindah:
//              break;
//            case R.id.btnPindahDenganData:
//              break;
//            case R.id.btnPindahUntukResult:
//              break;
//            case R.id.btnPindahDenganObject:
//              break;
//            case R.id.btnTelpon:
//              break;
//            case R.id.btnKeenam:
//              break;
//        }
    }
}
