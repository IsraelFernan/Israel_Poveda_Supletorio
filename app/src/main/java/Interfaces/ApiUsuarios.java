package Interfaces;

import java.util.List;

import Modelos.Usuarios;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiUsuarios {
    @GET("Usuarios.json")
    Call<List<Usuarios>> obtenerDatos();

    @POST("Usuarios.json")
    Call<ResponseBody> insertarDatos(@Body Usuarios personal);
}
