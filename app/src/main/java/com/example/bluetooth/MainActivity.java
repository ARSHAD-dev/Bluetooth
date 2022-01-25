package com.example.bluetooth;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/*
created by arshad khan
 */

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    BluetoothAdapter mybluetoothAdapter;
    Intent btenablingIntent;
    int requestcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        mybluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btenablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestcode = 1;
        bluetoothOn();
        bluetoothoff();
    }

    private void bluetoothoff() {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mybluetoothAdapter.isEnabled()) {
                    mybluetoothAdapter.disable();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestcode) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Device is Enable", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Device is not pair", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void bluetoothOn() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mybluetoothAdapter == null) {
                    Toast.makeText(getApplicationContext(), "Does not support bluetooth", Toast.LENGTH_SHORT).show();
                } else {
                    if (!mybluetoothAdapter.isEnabled()) {
                        startActivityForResult(btenablingIntent, requestcode);
                    }
                }
            }
        });

    }
}