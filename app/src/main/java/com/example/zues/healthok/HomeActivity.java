package com.example.zues.healthok;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.zues.healthok.model.Doctor;
import com.example.zues.healthok.model.Order;

import static com.example.zues.healthok.BookAppointmentFragment.pos;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager sessionManager;
    String fullName;
    Order orderForOtherFragments;
    Doctor doctordata=BookAppointmentFragment.listresult.get(pos);
    int doctorForOtherFragments=doctordata.getDoctorId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Adding HomeFragment as default fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new HomeFragment()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);
        sessionManager = new SessionManager(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionManager.checkLogin();
        fullName = sessionManager.getUser().getFirstName() + " " +
                sessionManager.getUser().getLastName();
        TextView userNameTextView = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.userNameTextView);
        userNameTextView.setText(fullName);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!(getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof HomeFragment)) {
            selectNavigationItem(R.id.nav_menu_home);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_menu_home: {
                swapFragment(new HomeFragment());
                break;
            }
            case R.id.nav_menu_my_profile: {
                swapFragment(new MyProfileFragment());
                break;
            }
            case R.id.nav_menu_book_appointment: {
                swapFragment(new BookAppointmentFragment());
                break;
            }
            case R.id.nav_menu_my_doctors: {
                swapFragment(new MyDoctorsFragment());
                break;
            }
            case R.id.nav_menu_my_family: {
                swapFragment(new MyFamilyFragment());
                break;
            }
            case R.id.nav_menu_my_orders: {
                swapFragment(new MyOrdersFragment());
                break;
            }
            case R.id.nav_menu_doctor_visit_history: {
                swapFragment(new DoctorVisitHistoryFragment());
                break;
            }
            case R.id.nav_menu_reports: {
                swapFragment(new ReportsFragment());
                break;
            }
            case R.id.nav_menu_order_medicine: {
                swapFragment(new OrderMedicineFragment());
                break;
            }
            case R.id.nav_menu_order_test: {
                swapFragment(new OrderTestFragment());
                break;
            }
            case R.id.nav_menu_order_ambulance: {
                swapFragment(new OrderAmbulanceFragment());
                break;
            }
            case R.id.nav_menu_home_nursing: {
                swapFragment(new HomeNursingFragment());
                break;
            }
            case R.id.nav_menu_logout: {
                swapFragment(new HomeFragment());
                sessionManager.logoutUser();
                break;
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectNavigationItem(int id) {
        MenuItem menuItem = ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(id);
        menuItem.setChecked(true);
        onNavigationItemSelected(menuItem);
    }

    public void swapFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void swapFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

}
