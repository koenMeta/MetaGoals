package com.training.MetaGoals.qr_code;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.training.MetaGoals.R;

public class QrCodeActicity extends AppCompatActivity {
    private Button btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_reader_activity);

        btn_scan = findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(QrCodeActicity.this);

                intentIntegrator.setPrompt("Für Licht Lautstärke erhöhen");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(com.training.MetaGoals.qr_code.Capture.class);
                intentIntegrator.initiateScan();
            }
        });
        configureBackButton();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QrCodeActicity.this);
            builder.setTitle("Ergebnis");
            String qr_result = intentResult.getContents();
            builder.setMessage(qr_result);

            // copy result to clipboard if api version >= 23
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(qr_result, qr_result);
                clipboard.setPrimaryClip(clip);
            }

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            builder.show();
        } else {
            Toast.makeText(getApplicationContext(), "Du hast nicht gescannt", Toast.LENGTH_SHORT).show();
        }

    }

    private void configureBackButton() {
        ImageView backButton = (ImageView) findViewById(R.id.arrowBackQr);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}