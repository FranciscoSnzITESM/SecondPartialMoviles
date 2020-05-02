package mx.itesm.secondpartial;

import android.content.DialogInterface;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements RVAdapter.OnEventListener{

    ArrayList<Book> books;
    private RecyclerView recycler;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private RVAdapter adapter;
    static String category;

    private String bookImage;
    private String bookTitle;
    private String bookAuthor;
    private String bookEditorial;
    private String bookDescription;
    private String bookPrice;

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
        //category = "scifi";
        String url = "http://androidstorepddm.000webhostapp.com/services/getbooks.php?category="+category;


        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray jsonArray = response;
                        try {
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONArray(i).getJSONObject(0);
                                bookImage = object.getString("url_picture");
                                bookTitle = object.getString("title");
                                bookAuthor = object.getString("author");
                                bookEditorial = object.getString("editorial");
                                bookDescription = object.getString("description");
                                bookPrice = object.getString("price");
                                books.add(new Book(bookImage, bookTitle, bookAuthor, bookEditorial, bookDescription, bookPrice));
                            }
                            adapter = new RVAdapter(books, eventListener);
                            recycler.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
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

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });


        AlertDialog dialog = builder.create();

        dialog.show();
    }
}
