package com.example.unlockingcube.encryptionapp;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;


import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends Activity {
 String type = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSpinners(this);

        Button encryptButton = (Button)findViewById(R.id.ebutton);

        encryptButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                    AESEncrypt();
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public String encryptText(String t)
    {
        //TextView answerLabel = (TextView) activity(R.id.answer);
        return "yes";
    }
    public void loadSpinners(Activity activity) {
        Spinner dropdown = (Spinner) activity.findViewById(R.id.eTypes);
        String[] items = new String[]{"AES", "DES", "3DES", "Caesors Cipher"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }
    //This method would allow you to choose which encryption technique to use
    public void EncryptType() {
        switch (type) {
            case "AES":
                AESEncrypt();//calls Caesors encrypt algorithom. - this is not implemented right now
                break;
            case "Caesors": //calls AES encrypt
                break;
            case "DES": //calls DES
                break;
            case "3DES"://calls 3DES
                break;
            default: //calls caesors encrpyt;

        }

    }

    //uses AES 128
    public void AESEncrypt() {
    // Original text
    //String theTestText = "This is just a simple test";
    TextView plainTextView = (TextView)findViewById(R.id.PlainText);
        String plainText = plainTextView.getText().toString();

    // Set up secret key spec for 128-bit AES encryption and decryption
    SecretKeySpec sks = null;
    try {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed("any data used as random seed".getBytes());
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128, sr);
        sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
    } catch (Exception e) {
//        Log.e(TAG, "AES secret key spec error");
    }

    // Encode the original data with AES
    byte[] encodedBytes = null;
    try {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, sks);
        encodedBytes = c.doFinal(plainText.getBytes());
    } catch (Exception e) {
//        Log.e(TAG, "AES encryption error");
    }
   TextView encryptedText = (TextView)findViewById(R.id.EncryptedText);
    encryptedText.setText("[ENCODED]:\n" +
            Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

    // Decode the encoded data with AES
    byte[] decodedBytes = null;
    try {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, sks);
        decodedBytes = c.doFinal(encodedBytes);
    } catch (Exception e) {
//        Log.e(TAG, "AES decryption error");
    }
//    TextView tvdecoded = (TextView)findViewById(R.id.tvdecoded);
        TextView decryptedText = (TextView)findViewById(R.id.DecryptedText);
        decryptedText.setText("[DECODED]:\n" + new String(decodedBytes) + "\n");
  }
}
