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
                Fragment fragment = null;
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.home:
                        fragment = new Home();
                        break;
                    case R.id.ScienceFiction:
                        fragment = new CategoryFragment("scifi");
                        break;
                    case R.id.Sports:
                        fragment = new CategoryFragment("sports");
                        break;
                    case R.id.EnglishLiterature:
                        fragment = new CategoryFragment("english");
                        break;
                    case R.id.ArtAndCulture:
                        fragment = new CategoryFragment("art");
                        break;
                    case R.id.Comics:
                        fragment = new CategoryFragment("comics");
                        break;
                    case R.id.SelfHelp:
                        fragment = new CategoryFragment("self");
                        break;
                    case R.id.FindUs:
                        fragment = new MyMapFragment();
                        break;
                    case R.id.Email:
                        //fragment = new EmailFragment();
                        break;

                }
                if (fragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, fragment);
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
        Drawable newDrawable = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap, 40, 40, true));
        actionbar.setHomeAsUpIndicator(newDrawable);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            // Android home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }
}
