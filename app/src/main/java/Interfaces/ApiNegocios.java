package Interfaces;

import java.util.List;

import Modelos.Negocios;
import Modelos.Personal;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiNegocios {
    @GET("Negocios.json")
    Call<List<Negocios>> obtenerDatos();

    @POST("Negocios.json")
    Call<ResponseBody> insertarDatos(@Body Negocios negocios);
}
