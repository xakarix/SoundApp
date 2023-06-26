package com.example.projekt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static  final int cat = R.raw.cat;
    public static  final int cow = R.raw.cow;
    public static  final int dog = R.raw.dog;
    public static  final int duck = R.raw.duck;
    public static  final int frog = R.raw.frog;
    public static  final int horse = R.raw.horse;

    static final int READ_BLOCK_SIZE = 100;

    Button sound1;
    Button sound2;
    Button sound3;
    Button sound4;
    Button sound5;
    Button sound6;
    Button btnPlay;
    EditText textView;

    List<String> list = new ArrayList<String>();
    List<String> listHelper = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound1 = (Button) findViewById(R.id.button);
        sound2 = (Button) findViewById(R.id.button2);
        sound3 = (Button) findViewById(R.id.button3);
        sound4 = (Button) findViewById(R.id.button4);
        sound5 = (Button) findViewById(R.id.button5);
        sound6 = (Button) findViewById(R.id.button6);
        btnPlay = (Button) findViewById(R.id.button9);
        textView = (EditText) findViewById(R.id.textView);

    }

    public void playSound(Context context, int soundID)
    {
            MediaPlayer mp = MediaPlayer.create(context,soundID);
            mp.start();
    }

    public void playSounds(View v){
        int sound=0;
        int value=0;
        if (sound1.getId() == v.getId()){
            value=1;
        } else if (sound2.getId() == v.getId()) {
            value=2;
        } else if (sound3.getId() == v.getId()) {
            value=3;
        } else if (sound4.getId() == v.getId()) {
            value=4;
        } else if (sound5.getId() == v.getId()) {
            value=5;
        } else if (sound6.getId() == v.getId()) {
            value=6;
        }

        switch(value){
            case 1:
            {
                sound = cat;
                list.add("1");
                sound1.setBackgroundColor(Color.GREEN);
                sound2.setBackgroundColor(Color.RED);
                sound3.setBackgroundColor(Color.RED);
                sound4.setBackgroundColor(Color.RED);
                sound5.setBackgroundColor(Color.RED);
                sound6.setBackgroundColor(Color.RED);
                playSound(this,sound);
                break;
            }
            case 2:
            {
                sound = cow;
                list.add("2");
                sound1.setBackgroundColor(Color.RED);
                sound2.setBackgroundColor(Color.GREEN);
                sound3.setBackgroundColor(Color.RED);
                sound4.setBackgroundColor(Color.RED);
                sound5.setBackgroundColor(Color.RED);
                sound6.setBackgroundColor(Color.RED);
                playSound(this,sound);
                break;
            }
            case 3:
            {
                sound = dog;
                list.add("3");
                sound1.setBackgroundColor(Color.RED);
                sound2.setBackgroundColor(Color.RED);
                sound3.setBackgroundColor(Color.GREEN);
                sound4.setBackgroundColor(Color.RED);
                sound5.setBackgroundColor(Color.RED);
                sound6.setBackgroundColor(Color.RED);
                playSound(this,sound);
                break;
            }
            case 4:
            {
                sound = duck;
                list.add("4");
                sound1.setBackgroundColor(Color.RED);
                sound2.setBackgroundColor(Color.RED);
                sound3.setBackgroundColor(Color.RED);
                sound4.setBackgroundColor(Color.GREEN);
                sound5.setBackgroundColor(Color.RED);
                sound6.setBackgroundColor(Color.RED);
                playSound(this,sound);
                break;
            }
            case 5:
            {
                sound = frog;
                list.add("5");
                sound1.setBackgroundColor(Color.RED);
                sound2.setBackgroundColor(Color.RED);
                sound3.setBackgroundColor(Color.RED);
                sound4.setBackgroundColor(Color.RED);
                sound5.setBackgroundColor(Color.GREEN);
                sound6.setBackgroundColor(Color.RED);
                playSound(this,sound);
                break;
            }
            case 6:
            {
                sound = horse;
                list.add("6");
                sound1.setBackgroundColor(Color.RED);
                sound2.setBackgroundColor(Color.RED);
                sound3.setBackgroundColor(Color.RED);
                sound4.setBackgroundColor(Color.RED);
                sound5.setBackgroundColor(Color.RED);
                sound6.setBackgroundColor(Color.GREEN);
                playSound(this,sound);
                break;
            }

        }
    }

    public void Write(View v){
        try{
            String fileName = textView.getText().toString();
            FileOutputStream file_out = openFileOutput(fileName + ".txt", MODE_PRIVATE);
            OutputStreamWriter out_writer = new OutputStreamWriter((file_out));
            out_writer.write(list.toString());
            Toast.makeText(getBaseContext(), fileName,Toast.LENGTH_SHORT).show();
            out_writer.close();
            list.clear();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Read(View v){
        try{
            String fileName = textView.getText().toString();
            FileInputStream fileIn = openFileInput(fileName + ".txt");
            InputStreamReader input_read = new InputStreamReader((fileIn));
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            StringBuilder sb = new StringBuilder();
            int charRead;
            while((charRead = input_read.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                sb.append(readString);

            }
            input_read.close();
            Toast.makeText(getBaseContext(),sb,Toast.LENGTH_SHORT).show();
            String[] animals = sb.toString().replaceAll("\\[|\\]|\\s", "").split(",");
            listHelper.clear();
            listHelper.addAll(Arrays.asList(animals));



        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Play(View v){
        try{

            int sound=0;
            for (String x: listHelper
            ) {
                switch (x){
                    case "1":
                        sound = cat;
                        break;

                    case "2":
                        sound = cow;
                        break;
                    case "3":
                        sound = dog;
                        break;
                    case "4":
                        sound = duck;
                        break;
                    case "5":
                        sound = frog;
                        break;
                    case "6":
                        sound = horse;
                        break;
                    default:
                        break;


                }
                playSound(MainActivity.this,sound);
                TimeUnit.SECONDS.sleep(2);


            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
