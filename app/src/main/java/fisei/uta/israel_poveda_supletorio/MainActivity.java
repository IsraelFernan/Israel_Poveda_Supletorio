package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void listarUsuarios(String metodo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tareas-a8a1a-default-rtdb.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUsuarios apiUsuarios = retrofit.create(ApiUsuarios.class);
        Call<List<Usuarios>> call = apiUsuarios.obtenerDatos();
        call.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Codigo: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Usuarios> datos = response.body();
                if(metodo.equals("validarUsuario")){
                    validarUsuario(datos);
                }
            }
            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}