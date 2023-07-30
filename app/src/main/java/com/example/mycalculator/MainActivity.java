package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBracketO,ButtonBracketC;
    MaterialButton buttonDivide,buttonMultiply,buttonAddition,buttonSub,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);
        assignId(buttonC,R.id.button_c);
        assignId(buttonBracketO,R.id.button_bracketO);
        assignId(ButtonBracketC,R.id.button_bracketC);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonAddition,R.id.button_Addition);
        assignId(buttonSub,R.id.button_sub);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);
    }
    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        MaterialButton button =(MaterialButton) v;
        String buttonText=button.getText().toString();
//        solutionTv.setText(buttonText);
        String dataToCal =solutionTv.getText().toString();
        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            solutionTv.setText("");
            return;

        }
         else{
            dataToCal =dataToCal+buttonText;
        }
        solutionTv.setText(dataToCal);
        String finalResult = getResult(dataToCal);
        if(!finalResult.equals("Er")){
            resultTv.setText(finalResult);
        }
    }
   String getResult(String data){
try{
    Context context = Context.enter();
    context.setOptimizationLevel(-1);
    Scriptable scriptable = context.initSafeStandardObjects();
    String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
    if(finalResult.endsWith(".0")) {
        finalResult = finalResult.replace(".0","");
    }
    return finalResult;
}catch (Exception e){
    return "Er";
}
    }
}