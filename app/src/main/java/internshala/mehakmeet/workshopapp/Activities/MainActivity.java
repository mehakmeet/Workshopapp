package internshala.mehakmeet.workshopapp.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import internshala.mehakmeet.workshopapp.login.Login;
import internshala.mehakmeet.workshopapp.Fragments.AvailableWorkshop;
import internshala.mehakmeet.workshopapp.Fragments.Dashboard;
import internshala.mehakmeet.workshopapp.R;
import internshala.mehakmeet.workshopapp.SqliteDB.WorkshopDbHelper;
import internshala.mehakmeet.workshopapp.model.DashModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static int previous_state=0;
    WorkshopDbHelper workshopDbHelper;
    SQLiteDatabase sqLiteDatabase;
    public static NavigationView navigationView;

    String[] workshop_name_List={"W.A.R - Workshop on Augumented Reality", "Android Study Jam", "Hello World!", "Fastrack", "Swiftshop", "Rhythm in Algorithm"};
    String[] workshop_desc_List={"A workshop on Augumented reality which will make you understand how an AR app is developed.",
            "Study session on Android from beginner level to advanced with one on one doubt clearing sessions.",
            "A workshop conducted for people stepping into the coding world and need proper guidance.",
            "Workshop for competitive coders - casual or serious, looking for tips and tricks to write quick algorithms.",
            "Workshop on swift programming language for iOS app development, at the end of the workshop participants will be able to make whatsapp,fb and twitter clones for iOS. ",
            "Data structures and Algorithm workshop for every level of coder. The main aim of this workshop is to guide student on efficient coding by understanding time complexities in their codes."};
    String[] workshop_dates_List={"12-05-18","18-05-18","23-05-18","26-05-18","28-05-18","3-06-18"};
    String[] workshop_time_List={"9:00 AM - 6:00PM","10:00AM - 6:00PM","9:30AM - 6:00PM","9:00AM - 6:00PM","10:00AM - 6:00PM","9:00AM - 6:00PM"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         addWorkshop();//Adding Workshops to the SQLite DB..



        if(PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).contains("My_SAVED_LIST"))
        {
            Dashboard.dashList=new ArrayList<>();
            Dashboard.dashList=new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).getString("My_SAVED_LIST", ""), new TypeToken<ArrayList<DashModel>>() {
            }.getType());

        }
        else{
            Dashboard.dashList=new ArrayList<>();

        }//Setting up home fragment on top of mainactivity
        Dashboard fm=new Dashboard();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container,fm).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).contains("login_status"))
        {
            Login.HAS_LOGGED_IN=PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).getInt("login_status", 0);
            if(Login.HAS_LOGGED_IN==1)
            {
                navigationView.getMenu().getItem(2).setTitle("Log Out");
            }
        }
    }

    private void addWorkshop() {

        Log.e("LENGTH", String.valueOf(workshop_name_List.length));
        for(int i=0;i<workshop_name_List.length;i++) {
            workshopDbHelper = new WorkshopDbHelper(this);

            sqLiteDatabase = workshopDbHelper.getWritableDatabase();

            workshopDbHelper.addInfo(workshop_name_List[i],workshop_desc_List[i],workshop_dates_List[i],workshop_time_List[i],sqLiteDatabase);

            Log.e("WORDDD",workshop_name_List[i]);

        }

        workshopDbHelper.close();//Close the DB helper after inserting the data
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(count!=0){
            getSupportFragmentManager().popBackStackImmediate();
        }
        else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.workshop_item) {
            if(previous_state!=id){
                AvailableWorkshop aw=new AvailableWorkshop();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_container,aw).addToBackStack(null).commit();
                previous_state=0;
            }
            // Handle the camera action
        } else if (id == R.id.dashboard_item) {
            if(previous_state!=id){
                Dashboard aw=new Dashboard();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main_container,aw).addToBackStack(null).commit();
                previous_state=1;
            }
        } else if (id == R.id.login_item) {
            if(previous_state!=id){

                if(Login.HAS_LOGGED_IN==1){
                    Toast.makeText(getApplicationContext(),"Successfully Logged out!",Toast.LENGTH_SHORT).show();
                    Login.HAS_LOGGED_IN=0;
                    navigationView.getMenu().getItem(2).setTitle("Log In");
                }
                else
                {

                    Intent i=new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    previous_state=2;
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
