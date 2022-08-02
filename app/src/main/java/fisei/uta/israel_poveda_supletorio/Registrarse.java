package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Interfaces.ApiNegocios;
import Interfaces.ApiUsuarios;
import Modelos.Negocios;
import Modelos.Usuarios;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        Button registrarse = findViewById(R.id.btnregistrarRe);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView clave = findViewById(R.id.textClaveRe);
                boolean res = validarClave(String.valueOf(clave.getText()));
                if(res == false){
                    Toast.makeText(Registrarse.this, "La clave no cumple con los requerimientos.", Toast.LENGTH_SHORT).show();
                }
                listarUsuarios("validarCorreo");
            }
        });
    }
    public boolean validarClave(String dato) {
        return dato.matches("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{6,10}$");
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
                    Toast.makeText(Registrarse.this,"Codigo: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Usuarios> datos = response.body();
                if(metodo.equals("validarCorreo")){
                    validarCorreo(datos);
                }
            }
            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                Toast.makeText(Registrarse.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void agregarUsuarios(Usuarios usuarios){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.israel.somee.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiUsuarios apiUsuarios = retrofit.create(ApiUsuarios.class);
        Call<ResponseBody> call = apiUsuarios.insertarDatos(usuarios);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Registrarse.this,"Codigo: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.code() == 200){
                    Toast.makeText(Registrarse.this, "Agregado exitosamente.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Registrarse.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Registrarse.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validarCorreo(List<Usuarios> usuarios){
        TextView correo = findViewById(R.id.textCorreoRe);
        for(Usuarios post: usuarios){
            if(post.getCorreo().equals(String.valueOf(correo.getText()))){
                Toast.makeText(this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
