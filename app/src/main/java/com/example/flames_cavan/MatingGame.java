package com.example.flames_cavan;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MatingGame extends AppCompatActivity {
    //Dexter Joshua Cavan - Mobile Programming


    EditText UrName,PartnerName;
    Button mate_btn,close_btn,retry;
    TextView result;
    ImageView image;

    String n1,n2;
    String complete_char;
    int complete = 0;
    String commonChars = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_game);

        UrName = findViewById(R.id.UrName);
        PartnerName = findViewById(R.id.PartnerName);
        result = findViewById(R.id.result);

        mate_btn = findViewById(R.id.mate_btn);
        close_btn = findViewById(R.id.close_btn);
        retry = findViewById(R.id.retry);

        image = findViewById(R.id.image);


        mate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(UrName.getText().toString().isEmpty() || PartnerName.getText().toString().isEmpty() )
                    {
                        Toast.makeText(getApplicationContext(),"Please input field!", Toast.LENGTH_SHORT).show();
                    }
                else if(UrName.getText().toString().equals(PartnerName.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Inputs are the same! Try again",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        commonChar();
                        if(complete != 0 )
                        {
                            flamesProccess();
                            inputD();

                        }else{
                            Toast.makeText(getApplicationContext(),"Inputs have common letters! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception error){
                        Toast.makeText(getApplicationContext(),"Oops naay mali! Try again", Toast.LENGTH_SHORT).show();
                    }
                    }


                }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrName.getText().clear();
                PartnerName.getText().clear();
                recreate();
            }
        });
    }

    public void commonChar(){
        n1 = UrName.getText().toString().toLowerCase().replaceAll("\\s+", "");
        n2 = PartnerName.getText().toString().toLowerCase().replaceAll("\\s+", "");

        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                if (n1.charAt(i) == n2.charAt(j)) {
                    commonChars += n1.charAt(i);
                }
            }
        }

        for(int i = 0; i < commonChars.length(); i ++) {
            String charToRemove = commonChars.charAt(i)+"";
            n1 = n1.replace(charToRemove, "");
            n2 = n2.replace(charToRemove, "");
        }




                complete_char = n1+n2;
        complete = complete_char.length();
//      Toast.makeText(this,"Val is "+complete_char+" "+complete, Toast.LENGTH_SHORT).show();

    }
    public void flamesProccess(){

        String base = "FLAMES";
        char relationIs = 0;
        int temp = 0;
        if (complete > 0) {
            temp = complete % base.length();
        }
        if (temp == 0 && complete >= 6)  {
            relationIs = 'S';
        } else {
            int count = temp - 1;
            if (count >= 0) {
                relationIs = base.charAt(count);
            }
        }
        String res = "The relationship between "+UrName.getText().toString().toUpperCase()+" and "+PartnerName.getText().toString().toUpperCase()+" is ";
        switch (relationIs) {
            case 'F':
                result.setText(res);
                image.setImageResource(R.drawable.friends);
                break;
            case 'L':
                result.setText(res);
                image.setImageResource(R.drawable.lover);
                break;
            case 'A':
                result.setText(res);
                image.setImageResource(R.drawable.affection);
                break;
            case 'M':
                result.setText(res);
                image.setImageResource(R.drawable.marriage);
                break;
            case 'E':
                result.setText(res);
                image.setImageResource(R.drawable.enemy);
                break;
            case 'S':
                result.setText(res);
                image.setImageResource(R.drawable.sister);
                break;

        }
//        Toast.makeText(this,"Val is "+relationIs, Toast.LENGTH_SHORT).show();
    }
    public void inputD(){
        UrName.setFocusable(false);
        UrName.setEnabled(false);
        PartnerName.setFocusable(false);
        PartnerName.setEnabled(false);
        PartnerName.onEditorAction(EditorInfo.IME_ACTION_DONE);
        UrName.onEditorAction(EditorInfo.IME_ACTION_DONE);
    }


}