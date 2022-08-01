package Interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import Modelos.Negocios;
import Modelos.Personal;
import fisei.uta.israel_poveda_supletorio.R;

public class AdaptadorNegocios extends ArrayAdapter<Negocios> {
    private List<Negocios> lista;
    private Context contexto;
    private int resourceLayout;
    public AdaptadorNegocios(@NonNull Context context, int resource, List<Negocios> objets) {
        super(context, resource,objets);
        this.lista = objets;
        this.contexto = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null) {
            view = LayoutInflater.from(contexto).inflate(resourceLayout, null);
        }
        Negocios negocios = lista.get(position);
        TextView nombre = view.findViewById(R.id.IPtextNombrePersonal);
        nombre.setText(negocios.getNombre());
        TextView nota = view.findViewById(R.id.IPtextNotaPersonal);
        nota.setText(negocios.getNota());
        return view;
    }
}
