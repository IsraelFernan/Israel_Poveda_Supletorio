package Interfaces;

import java.util.List;
import Modelos.Personal;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPersonal {
    @GET("Personal.json")
    Call<List<Personal>> obtenerDatos();

    @POST("Personal.json")
    Call<ResponseBody> insertarDatos(@Body Personal personal);
}
