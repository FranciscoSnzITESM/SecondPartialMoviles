package mx.itesm.secondpartial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new Home());
        transaction.commit();
        configureNavigationDrawer();
        configureToolbar();
    }

    private void configureNavigationDrawer() {
        drawerLayout =  findViewById(R.id.drawer_layout);
        NavigationView navView =  findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment f = null;
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.home:
                        f = new Home();
                        break;
                    case R.id.ScienceFiction:
                        f = new CategoryFragment("Science Fiction");
                        break;
                    case R.id.Sports:
                        f = new CategoryFragment("Sports");
                        break;
                    case R.id.EnglishLiterature:
                        f = new CategoryFragment("English Literature");
                        break;
                    case R.id.ArtAndCulture:
                        f = new CategoryFragment("Art & Culture");
                        break;
                    case R.id.Comics:
                        f = new CategoryFragment("Comics");
                        break;
                    case R.id.SelfHelp:
                        f = new CategoryFragment("Self-help");
                        break;

                }
                if (f != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, f);
                    transaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }
    private void configureToolbar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        Drawable drawable= getResources().getDrawable(R.drawable.hamburger_icon);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newDrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 40, 40, true));
        actionbar.setHomeAsUpIndicator(newDrawable);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            // Android home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
                // manage other entries if you have it ...
        }
        return true;
    }
}
