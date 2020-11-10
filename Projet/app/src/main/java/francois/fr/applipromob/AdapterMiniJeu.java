package francois.fr.applipromob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Duration;
import java.util.List;

public class AdapterMiniJeu extends RecyclerView.Adapter<AdapterMiniJeu.ViewHolder> {
    LayoutInflater inflater;

    public AdapterMiniJeu(Context ctx) {
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(MainActivity.miniJeux.get(position).nom);
        holder.gridIcon.setImageResource(MainActivity.miniJeux.get(position).image);
    }

    @Override
    public int getItemCount() {
        return MainActivity.miniJeux.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView1);
            gridIcon = itemView.findViewById(R.id.imageView1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Clicked : " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}