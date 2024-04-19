package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        operas.put("+", true);
        operas.put("-", true);
        operas.put("x", true);
        operas.put("AC", false);
        operas.put("/", true);
    }

    String tt="";
    String tt2 = "";
    String oper = "";
    Boolean operator = false;

    Map<String, Boolean> operas = new HashMap<String, Boolean>();



    // Метод, вызываемый при нажатии на кнопку
    public void onButtonClick(View view) {

        // Преобразуем переданный View в Button
        TextView screen = findViewById(R.id.editText);
        Button button = (Button) view;
        // Получаем текст кнопки
        String buttonText = button.getText().toString();
        if (buttonText.equals("="))
        {
            if(tt=="")
            {
                tt="0";
            }
            if (tt2=="0") tt2="0";
            Double result=Double.valueOf(tt);
            Double result2=Double.valueOf(tt2);
            Double res= 0.0;
            if (oper=="+")
                res=result+result2;
            else if(oper=="-")
                res=result-result2;
            if (oper=="x")
                res=result*result2;
            else if(oper=="/")
                res=result/result2;
            screen.setText(String.valueOf(res));
            tt="";
            tt2="";
            oper="";
            operator= false;
        }
        else
        {
            if (operas.containsKey(buttonText)&&operas.get(buttonText)==false)
            {
                tt="";
                tt2="";
                oper="";
                operator=false;
                screen.setText("");
            }
            if (operas.containsKey(buttonText) && operator==false)
            {
                switch (buttonText) {
                    case "+":
                        oper = "+";
                        operator=true;
                        break;
                    case "-":
                        oper = "-";
                        operator = true;
                        break;
                    case "x":
                        oper = "x";
                        operator = true;
                        break;
                    case "/":
                        oper = "/";
                        operator = true;
                        break;
                    case "AC":
                        tt="";
                        tt2="";
                        oper="";
                        break;
                }
                screen.setText("0");
            }
            else if(!operator)
            {
                tt+=buttonText;
                screen.setText(tt);
            }
            else
            {
                tt2+=buttonText;
                screen.setText(tt2);
            }
        }



    }
}