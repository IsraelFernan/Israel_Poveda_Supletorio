package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tipotarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipotarea);
        Button btnTipoPersonal = findViewById(R.id.btnTipoPersonal);
        Button btnTipoNegocios = findViewById(R.id.btnTipoNegocios);
        btnTipoNegocios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Tipotarea.this,Agregartarea.class);
                i.putExtra("tipo","Negocios");
                startActivity(i);
            }
        });
        btnTipoPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Tipotarea.this,Agregartarea.class);
                i.putExtra("tipo","Personal");
                startActivity(i);
            }
        });
    }
}