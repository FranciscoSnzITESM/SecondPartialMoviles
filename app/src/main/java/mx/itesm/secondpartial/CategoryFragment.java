package mx.itesm.secondpartial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements RVAdapter.OnEventListener{

    ArrayList<Book> books;
    private RecyclerView recycler;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private RVAdapter adapter;
    static String category;

    public CategoryFragment(String category) {
        this.category = category;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_category, container, false);

        recycler = view.findViewById(R.id.recyclerBooks);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);

        books = new ArrayList<>();

        final CategoryFragment eventListener = this;

        // Volley
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category="+category;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println("Error" + error);
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(request);


        books.add(new Book("http://androidstorepddm.000webhostapp.com/images/covey.jpg", "Some Title", "Some Author", "Cool Editorial", "This is the descripion of the book", 78.2f));
        books.add(new Book("http://androidstorepddm.000webhostapp.com/images/deco.jpg", "Some Title 2", "Some Author 2", "Cool Editorial", "This is the descripion of the second book", 17.9f));


        adapter = new RVAdapter(books, this);
        recycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onEventClick(int position) {
        String description = books.get(position).getDescription();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(description).setTitle("Description");

        AlertDialog dialog = builder.create();

        dialog.show();
    }
}
