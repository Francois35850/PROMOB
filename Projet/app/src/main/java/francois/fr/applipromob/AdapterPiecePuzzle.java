package francois.fr.applipromob;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import francois.fr.applipromob.objetsJeux.PiecePuzzle;

public class AdapterPiecePuzzle extends RecyclerView.Adapter<AdapterPiecePuzzle.ViewHolder> {

    private List<PiecePuzzle> pieces = new ArrayList<>();
    private Context context;
    ImageView p1;

    public AdapterPiecePuzzle(Context c, ArrayList<PiecePuzzle> pieces) {
        this.context = c;
        this.pieces = pieces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_piece_puzzle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(pieces.get(position).getImg());
        holder.imageView.setId(position);


        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println(v.getId());
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pieces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_piece);
        }
    }
}
