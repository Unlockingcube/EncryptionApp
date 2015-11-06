package com.example.unlockingcube.encryptionapp;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
 String type = "Caesors";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSpinners(this);
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
            case "Caesors": EncrptCaesors();//calls Caesors encrypt algorithom.
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
    public void EncrptCaesors(){
        TextView ptextview = (TextView)findViewById(R.id.PlainText);
        String plaintext ="";
        plaintext = ptextview.getText().toString();
        String encryptedText ="";
        TextView PwordText = (TextView)findViewById(R.id.password);
        int  k = Integer.parseInt(PwordText.getText().toString());
        ArrayList<Character> List = new ArrayList<Character>(52);
        ArrayList<Character> Etext = new ArrayList<Character>(plaintext.length());
        List.add('A');
        List.add('a');
        List.add('B');
        List.add('b');
        List.add('C');
        List.add('c');
        List.add('D');
        List.add('d');
        List.add('E');
        List.add('e');
        List.add('F');
        List.add('f');
        List.add('G');
        List.add('g');
        List.add('H');
        List.add('h');
        List.add('I');
        List.add('i');
        List.add('J');
        List.add('j');
        List.add('K');
        List.add('k');
        List.add('L');
        List.add('l');
        List.add('M');
        List.add('m');
        List.add('N');
        List.add('n');
        List.add('O');
        List.add('o');
        List.add('P');
        List.add('p');
        List.add('Q');
        List.add('q');
        List.add('R');
        List.add('r');
        List.add('S');
        List.add('s');
        List.add('T');
        List.add('t');
        List.add('W');
        List.add('w');
        List.add('X');
        List.add('x');
        List.add('Y');
        List.add('y');
        List.add('Z');
        List.add('z');

        for(int i = 0; i<= plaintext.length(); i++ )
        {
            plaintext.charAt(i);
            for(int j = 0; j < List.size(); j++)
            {
                int shift = k + j;
                if(shift > 52)
                {
                    shift -= 52;
                }
                if(List.equals(plaintext.charAt(i)))
                {
                  Etext.add(List.get(shift));

                }
            }

        }
        for(int i =0; i < Etext.size(); i++)
        {
          encryptedText += Etext.get(i);
        }
        TextView Etextview = (TextView)findViewById(R.id.EncryptedText);
        Etextview.setText(encryptedText);

    }
}
