package mx.itesm.secondpartial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EventViewHolder> {

    private ArrayList<Book> books;
    private OnEventListener mOnEventListener;

    public RVAdapter(ArrayList<Book> books, OnEventListener onEventListener) {
        this.books = books;
        this.mOnEventListener = onEventListener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book_detail, parent, false), mOnEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Book book = books.get(position);
        Picasso.get()
                .load(book.getImageUrl())
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(holder.bookImage);
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookEditorial.setText(book.getEditorial());
        holder.bookPrice.setText(String.valueOf(book.getPrice()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookEditorial;
        TextView bookPrice;
        OnEventListener onEventListener;

        public EventViewHolder(View inflate, OnEventListener onEventListener) {
            super(inflate);
            bookImage = inflate.findViewById(R.id.bookImageView);
            bookTitle = inflate.findViewById(R.id.titleTextView);
            bookAuthor = inflate.findViewById(R.id.authorTextView);
            bookEditorial = inflate.findViewById(R.id.editorialTextView);
            bookPrice = inflate.findViewById(R.id.priceTextView);
            inflate.setOnClickListener(this);
            this.onEventListener = onEventListener;
        }

        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }
    
    public interface OnEventListener {
        void onEventClick(int position);
    }
}
