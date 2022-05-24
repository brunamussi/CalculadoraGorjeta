package com.example.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText textConta;
    private SeekBar seekBar;
    private TextView textGorjeta;
    private TextView textTotal;
    private TextView textPercentual;
    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textConta = findViewById(R.id.conta);
        seekBar = findViewById(R.id.seekBar);
        textGorjeta = findViewById(R.id.textGorjeta);
        textTotal = findViewById(R.id.textTotal);
        textPercentual = findViewById(R.id.textPercentual);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                textPercentual.setText(Math.round(porcentagem) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                calculoGorjeta();
            }

        });
        }

    public void calculoGorjeta() {

        String valorRecuperado = textConta.getText().toString();

        if (valorRecuperado == null || valorRecuperado.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor!",
                    Toast.LENGTH_SHORT).show();
        } else {
            double valorDigitado = Double.parseDouble(valorRecuperado);

            double gorjeta = valorDigitado * (porcentagem / 100);
            //new DecimalFormat(".##").format(gorjeta);//

            double total = gorjeta + valorDigitado;
            //new DecimalFormat(".##").format(total);//

            textGorjeta.setText("R$" + gorjeta);
            textTotal.setText("R$" + total);
        }
    }

    public void restartButtom(View view){
        seekBar.setProgress(0);
        textConta.setText(null);
        textGorjeta.setText("R$ 0.00");
        textTotal.setText("R$ 0.00");
    }
}