package adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nao.R;
import com.example.nao.ver;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entidades.Productos;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ProductoViewHolder>{

    ArrayList<Productos> listaProductos;

    ArrayList<Productos> listaOriginal;

    public ListaProductosAdapter(ArrayList<Productos> listaProductos){
        this.listaProductos = listaProductos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaProductos);
    }
    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, null, false);
        return new ProductoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.viewNombre.setText(listaProductos.get(position).getNombre());
        holder.viewCodigo.setText(listaProductos.get(position).getCodigo());
        holder.viewCantidad.setText(listaProductos.get(position).getCantidad());
    }

    public void  filtrado(String txtbuscar){
        int longitud = txtbuscar.length();
        if (longitud == 0){
            listaProductos.clear();
            listaProductos.addAll(listaOriginal);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Productos>collecion = listaProductos.stream().
                        filter(i -> i.getNombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collecion);
            }else {
                for (Productos c : listaOriginal){
                    if(c.getNombre().toLowerCase().contains(txtbuscar.toLowerCase())){
                        listaProductos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewCodigo, viewCantidad;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCodigo = itemView.findViewById(R.id.viewCodigo);
            viewCantidad = itemView.findViewById(R.id.viewCantidad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ver.class);
                    intent.putExtra("ID", listaProductos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
