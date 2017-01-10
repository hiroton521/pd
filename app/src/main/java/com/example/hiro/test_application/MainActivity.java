package com.example.hiro.test_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String[]> quizset = new ArrayList<String[]>();

    private TextView scoreText;
    private TextView qText;
    private Button a0Button;
    private Button a1Button;
    private Button a2Button;
    private Button nextButton;
    private int currentQuiz=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadQuizSet();

        getView();

        setQuiz();
    }

    private void setQuiz(){
        qText.setText(quizset.get(currentQuiz)[0]);

        ArrayList<String> answers = new ArrayList<String>();
        for(int i=1;i<-3;i++){
            answers.add(quizset.get(currentQuiz)[1-3]);
        }
        Collections.shuffle(answers);

        a0Button.setText(answers.get(0));
        a1Button.setText(answers.get(1));
        a2Button.setText(answers.get(2));

        nextButton.setEnabled(false);
    }

    private void getView(){
        scoreText= (TextView)findViewById(R.id.scoreText);
        qText= (TextView)findViewById(R.id.qText);
        a0Button= (Button)findViewById(R.id.a0button);
        a1Button= (Button)findViewById(R.id.a1button);
        a2Button= (Button)findViewById(R.id.a2button);
        nextButton=(Button)findViewById(R.id.nextButton);
    }

    private void loadQuizSet(){
        InputStream inputStream = null;
        BufferedReader bufferedReader=null;
        try{
            inputStream = getAssets().open("quiz.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while((s= bufferedReader.readLine()) !=null){
                quizset.add(s.split("\t"));
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(inputStream !=null) inputStream.close();
                if(bufferedReader !=null)bufferedReader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
