package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Tareas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        ImageView IPeliminar = findViewById(R.id.IPeliminar);
        Picasso.get().load()
        ImageView IPiconoLista = findViewById(R.id.IPiconoLista);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/06/10/07/18/list-2389219_960_720.png").into(IPiconoLista);
    }
}