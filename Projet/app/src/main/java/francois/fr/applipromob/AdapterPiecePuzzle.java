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

import francois.fr.applipromob.gameview.GameViewPuzzle;
import francois.fr.applipromob.objetsJeux.PiecePuzzle;

public class AdapterPiecePuzzle extends RecyclerView.Adapter<AdapterPiecePuzzle.ViewHolder> {

    private Context context;

    public AdapterPiecePuzzle(Context c) {
        this.context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_piece_puzzle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final int indexPiece = GameViewPuzzle.puzzleChoisi.getLp().get(position).getPosAleatoire();
        holder.imageView.setImageDrawable(GameViewPuzzle.puzzleChoisi.getLp().get(GameViewPuzzle.puzzleChoisi.getLp().get(position).getPosAleatoire()).getImg());
        holder.imageView.setVisibility(GameViewPuzzle.puzzleChoisi.getLp().get(GameViewPuzzle.puzzleChoisi.getLp().get(position).getPosAleatoire()).isVisible() ? View.VISIBLE : View.INVISIBLE);

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setId(indexPiece);
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                System.out.println(v.getId());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return GameViewPuzzle.puzzleChoisi.getLp().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_piece);
        }
    }
}
