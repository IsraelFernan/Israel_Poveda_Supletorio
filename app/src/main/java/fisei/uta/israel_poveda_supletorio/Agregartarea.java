package fisei.uta.israel_poveda_supletorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.AsyncHttpStack;

import Interfaces.ApiNegocios;
import Interfaces.ApiPersonal;
import Modelos.Negocios;
import Modelos.Personal;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Agregartarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregartarea);
        String tipo = getIntent().getStringExtra("tipo");
        if(tipo.equals("Personal")){

        }
    }
    public void agregarTareaPersonal(Personal personal){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.israel.somee.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiPersonal apiPersonal = retrofit.create(ApiPersonal.class);
        Call<ResponseBody> call = apiPersonal.insertarDatos(personal);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Agregartarea.this,"Codigo: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.code() == 200){
                    Toast.makeText(Agregartarea.this, "Agregado exitosamente.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Agregartarea.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Agregartarea.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void agregarTareaNegocio(Negocios negocios){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.israel.somee.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiNegocios apiNegocios = retrofit.create(ApiNegocios.class);
        Call<ResponseBody> call = apiNegocios.insertarDatos(negocios);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Agregartarea.this,"Codigo: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.code() == 200){
                    Toast.makeText(Agregartarea.this, "Agregado exitosamente.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Agregartarea.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Agregartarea.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*public boolean elementosVacios(){

        return
    }*/
}