package com.example.bki;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView boy_tv,durum_tv,kilo_tv,ideal_kilo_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean kadinmi=true;
    private  double boy=0.0;
    private  int kilo=50;
    private TextWatcher editTextOlayIsleyicisi= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                boy=Double.parseDouble(s.toString())/100.0;
            }catch (NumberFormatException e){
                boy=0.0;
            }
            guncelle();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarOlayIsleyicisi=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo=30+progress;
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private RadioGroup.OnCheckedChangeListener radioGroupOlayIsleyicisi = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId==R.id.kadin){
                kadinmi= true;
            }else if(checkedId==R.id.erkek){
                kadinmi=false;
            }
            guncelle();
        }
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText) findViewById(R.id.editText);
        boy_tv=(TextView) findViewById(R.id.boy_tv);
        ideal_kilo_tv=(TextView) findViewById(R.id.ideal_kilo_tv);
        kilo_tv=(TextView) findViewById(R.id.kilo_tv);
        durum_tv=(TextView) findViewById(R.id.durum_tv);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        seekBar=(SeekBar) findViewById(R.id.seekBar);


        editText.addTextChangedListener(editTextOlayIsleyicisi);
        seekBar.setOnSeekBarChangeListener(seekBarOlayIsleyicisi);
        radioGroup.setOnCheckedChangeListener(radioGroupOlayIsleyicisi);

        guncelle();
    }

    private void guncelle() {

        kilo_tv.setText(String.valueOf(kilo)+"kg");
        boy_tv.setText(String.valueOf(boy)+"m");

        int ideal_kiloErkek= (int) (50+2.3*(boy*100*0.4-60));
        int ideal_kiloKadin= (int) (45.5+2.3*(boy*100*0.4-60));
        double bki= kilo/(boy*boy);



        if(kadinmi){

            ideal_kilo_tv.setText(String.valueOf(ideal_kiloKadin));
            if (bki<=19.1){
                durum_tv.setBackgroundResource(R.color.zayif);
                durum_tv.setText(R.string.zayif);
            }
            else if (19.1<bki && bki <=25.8){
                durum_tv.setBackgroundResource(R.color.ideal);
                durum_tv.setText(R.string.ideal);
            }
            else if (25.8<bki && bki <=27.3){
                durum_tv.setBackgroundResource(R.color.idealden_fazla);
                durum_tv.setText(R.string.idealden_fazla);
            }
            else if (27.3<bki && bki <=32.3){
                durum_tv.setBackgroundResource(R.color.fazla);
                durum_tv.setText(R.string.fazla);
            }
            else if (32.3<bki && bki <=34.9){
                durum_tv.setBackgroundResource(R.color.obez);
                durum_tv.setText(R.string.obez);
            }


        }else{

            ideal_kilo_tv.setText(String.valueOf(ideal_kiloErkek));
                if (bki<=20.7){
                    durum_tv.setBackgroundResource(R.color.zayif);
                    durum_tv.setText(R.string.zayif);
                }
                else if (20.7<bki && bki <=26.4){
                    durum_tv.setBackgroundResource(R.color.ideal);
                    durum_tv.setText(R.string.ideal);
                }
                else if (26.4<bki && bki <=27.8){
                    durum_tv.setBackgroundResource(R.color.idealden_fazla);
                    durum_tv.setText(R.string.idealden_fazla);
                }
                else if (27.8<bki && bki <=31.1){
                    durum_tv.setBackgroundResource(R.color.fazla);
                    durum_tv.setText(R.string.fazla);
                }
                else if (31.1<bki && bki <=34.9){
                    durum_tv.setBackgroundResource(R.color.obez);
                    durum_tv.setText(R.string.obez);
                }

        }

    }
}