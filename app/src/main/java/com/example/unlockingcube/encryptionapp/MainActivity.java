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
 String type = "Caesors";
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
        String[] items = new String[]{"Caesar Cipher", "DES", "3DES", "AES"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }
    public void EncryptType(){
        switch(type)
        {
            case "Caesors":
                AESEncrypt();//calls Caesors encrypt algorithom. - this is not implemented right now
                break;
            case "AES": //calls AES encrypt
                break;
            case "DES": //calls DES
                break;
            case "3DES"://calls 3DES
                break;
            default: //calls caesors encrpyt;

        }

    }
//    public void EncrptCaesors(){
//
//        TextView ptextview = (TextView)findViewById(R.id.PlainText);
//        String plaintext ="";
//        plaintext = ptextview.getText().toString();
//        String encryptedText ="";
//        //This needs to be checked for null pointers
//        TextView PwordText = (TextView)findViewById(R.id.password);
//        int  k = Integer.parseInt(PwordText.getText().toString());
//        ArrayList<Character> List = new ArrayList<Character>(52);
//        ArrayList<Character> Etext = new ArrayList<Character>(plaintext.length());
//        List.add('A');
//        List.add('a');
//        List.add('B');
//        List.add('b');
//        List.add('C');
//        List.add('c');
//        List.add('D');
//        List.add('d');
//        List.add('E');
//        List.add('e');
//        List.add('F');
//        List.add('f');
//        List.add('G');
//        List.add('g');
//        List.add('H');
//        List.add('h');
//        List.add('I');
//        List.add('i');
//        List.add('J');
//        List.add('j');
//        List.add('K');
//        List.add('k');
//        List.add('L');
//        List.add('l');
//        List.add('M');
//        List.add('m');
//        List.add('N');
//        List.add('n');
//        List.add('O');
//        List.add('o');
//        List.add('P');
//        List.add('p');
//        List.add('Q');
//        List.add('q');
//        List.add('R');
//        List.add('r');
//        List.add('S');
//        List.add('s');
//        List.add('T');
//        List.add('t');
//        List.add('W');
//        List.add('w');
//        List.add('X');
//        List.add('x');
//        List.add('Y');
//        List.add('y');
//        List.add('Z');
//        List.add('z');
//
//        for(int i = 0; i< plaintext.length(); i++ )
//        {
//            plaintext.charAt(i);
//            for(int j = 0; j < List.size(); j++)
//            {
//                int shift = k + j;
//                while(shift > 52)
//                {
//                    shift -= 52;
//                }
//                if(List.equals(plaintext.charAt(i)))
//                {
//                  Etext.add(List.get(shift));
//
//                }
//            }
//
//        }
//        for(int i =0; i < Etext.size(); i++)
//        {
//          encryptedText += Etext.get(i);
//        }
//        TextView Etextview = (TextView)findViewById(R.id.EncryptedText);
//        //fails somewhere in the shifting process and outputs a blank string
////        Etextview.setText(encryptedText);
//
//        Etextview.setText("SOMETHING");
//
//    }

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
