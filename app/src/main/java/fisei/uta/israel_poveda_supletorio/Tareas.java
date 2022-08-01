package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Interfaces.AdaptadorPersonal;
import Interfaces.ApiPersonal;
import Modelos.Personal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tareas extends AppCompatActivity {

    private List<Personal> listaAdaptadorPersonal = new ArrayList<>();
    AdaptadorPersonal adaptadorPersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        ImageView IPeliminar = findViewById(R.id.IPeliminar);
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaR-j-nYEgA-B7qQD3z0hCM8eUwNSfH-3_Fw&usqp=CAU").into(IPeliminar);
        ImageView IPiconoLista = findViewById(R.id.IPiconoLista);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/06/10/07/18/list-2389219_960_720.png").into(IPiconoLista);

    }

    public void listarPersonal(String metodo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tareas-a8a1a-default-rtdb.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiPersonal apiPersonal = retrofit.create(ApiPersonal.class);
        Call<List<Personal>> call = apiPersonal.obtenerDatos();
        call.enqueue(new Callback<List<Personal>>() {
            @Override
            public void onResponse(Call<List<Personal>> call, Response<List<Personal>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Tareas.this,"Codigo: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(metodo == "listarTareasPersonal"){
                    ListView listaPersonal = findViewById(R.id.IPlistas);
                    List<Personal> datos = response.body();
                    for(Personal post: datos){
                        listaAdaptadorPersonal.add(new Personal(post.getIdpersonal(),post.getNombre(),post.getNota(),post.getEstado()));
                    }
                    adaptadorPersonal = new AdaptadorPersonal(Tareas.this,R.layout.item,listaAdaptadorPersonal);
                    listaPersonal.setAdapter(adaptadorPersonal);
                }
            }
            @Override
            public void onFailure(Call<List<Personal>> call, Throwable t) {
                Toast.makeText(Tareas.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}