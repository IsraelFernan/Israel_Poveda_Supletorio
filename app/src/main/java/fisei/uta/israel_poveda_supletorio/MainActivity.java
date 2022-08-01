package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import Interfaces.ApiUsuarios;
import Modelos.Usuarios;
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
        Button ingresar = findViewById(R.id.IPbotonIngresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarUsuarios("validarUsuario");
            }
        });
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
    public void validarUsuario(List<Usuarios> lista){
        String res = "";
        TextView correo = findViewById(R.id.IPingresarCorreo);
        TextView clave = findViewById(R.id.IPingresarClave);
        for(Usuarios post: lista){
            Log.d("usuarioIngresa",String.valueOf(correo.getText()));
            if(String.valueOf(correo.getText()).equals(post.getCorreo()) && String.valueOf(clave.getText()).equals(post.getContrasena())){
                res += "true";
                break;
            }
        }
        if(res.equals("true")){
            Intent i = new Intent(MainActivity.this, Tareas.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Por favor, las credenciales están mal ingresadas.", Toast.LENGTH_SHORT).show();
        }
    }
}