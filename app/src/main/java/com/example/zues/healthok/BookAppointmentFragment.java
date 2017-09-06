package com.example.zues.healthok;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zues.healthok.model.Doctor;
import com.example.zues.healthok.util.ServiceHandler;
import com.example.zues.healthok.util.ServiceURL;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookAppointmentFragment extends Fragment {
    public static ArrayList<Doctor> listresult = new ArrayList<Doctor>();
    public static int pos;
    View view;
    ListView listView;
    ArrayList<Doctor> filterlist=new ArrayList<Doctor>();
    HomeActivity homeActivity;



    public BookAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity= (HomeActivity) getActivity();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_book_appointment, container, false);


        //   searchView=view.findViewById(R.id.searchview);

        listView=  view.findViewById(R.id.doctorlist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                homeActivity.doctorForOtherFragments = listresult.get(i);
//                Intent intent=new Intent(getActivity(),Profile.class);
//                startActivity(intent);
                //  intent.putExtra("abc",pos);
                homeActivity.swapFragment(new DoctorProfileFragment(), true);
            }
        });


        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuinflater = getActivity().getMenuInflater();
        menuinflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem= menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView= (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("search for doctor ");
        //searchview
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist.clear();
                if (newText.length() > 0) {
                    listView.setVisibility(View.VISIBLE);
                    myasynctask m = (myasynctask) new myasynctask().execute(newText);
                }
                if(newText.isEmpty())
                {
                    listView.setVisibility(View.GONE);
                }


                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }
    //filtering result after getting response from server
    public  void filterDoctor(String string)
    {
        String Dname;
        filterlist.clear();
        for(int i=0;i<listresult.size();i++)
        {
            Dname=(listresult.get(i).getFirstName().toLowerCase()+listresult.get(i).getLastName().toLowerCase());
            if(Dname.contains(string.toLowerCase().trim()))
            {
                filterlist.add(listresult.get(i));
            }
        }
    }

    static class Viewholder {
        TextView firstname;
        TextView speciality;
        ImageView imageView;
    }

    class myasynctask extends AsyncTask<String,Void,String>
    {
        JsonParser jParser;
        JSONArray DoctorList;
        String textSearch;
        // ProgressDialog progressDialog;
        String url=new String();
        //  ProgressBar progressBar;


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            jParser=new JsonParser();
            DoctorList=new JSONArray();
            //  progressBar=getActivity().findViewById(R.id.progressbar);

        /*   progressDialog=new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage("please wait");
            progressDialog.getWindow().setGravity(Gravity.CENTER);
            progressDialog.show(); */

        }

        @Override
        protected String doInBackground(String... strings) {

            Doctor doctordata=new Doctor();
            String matchfound="N";
            url= ServiceURL.Base+ServiceURL.SearchDoctor+strings[0];
            ServiceHandler servicehandler =new ServiceHandler();
            String jsonStr = servicehandler.makeServiceCall(url);
            if(jsonStr!=null)
            {
                try {
                    JSONArray json=new JSONArray(jsonStr);
                    for(int i=0;i<json.length();i++)
                    {   doctordata = new Doctor();
                        JSONObject obj=json.getJSONObject(i);
                        if(obj.has("doctorImageId"))
                            doctordata.setDoctorImageid(obj.getInt("doctorImageId"));
                        else
                        {
                            doctordata.setDoctorImageid(R.drawable.doc);
                        }
                        if(obj.has("firstName"))
                        {
                            doctordata.setFirstName(obj.getString("firstName"));
                        }

                        if(obj.has("lastName"))
                            doctordata.setLastName(obj.getString("lastName"));
                        if(obj.has("doctorId"))
                            doctordata.setDoctorId(obj.getInt("doctorId"));
                        if(obj.has("speciality"))
                            doctordata.setSpeciality(obj.getString("speciality"));
                        if(obj.has("fees"))
                            doctordata.setFees(obj.getInt("fees"));

                        if(obj.has("clinicTiming"))
                        {
                            doctordata.setClinicTiming(obj.getString("clinicTiming"));
                        }
                        else
                            doctordata.setClinicTiming("4 pm");

                        if(obj.has("addressLine1"))
                            doctordata.setAddressLine1(obj.getString("addressLine1"));
                        if(obj.has("addressLine2"))
                            doctordata.setAddressLine2(obj.getString("addressLine2"));
                        else
                            doctordata.setAddressLine2("");
                        if(obj.has("addressLine3"))
                            doctordata.setAddressLine3(obj.getString("addressLine3"));
                        else
                            doctordata.setAddressLine3("");


/*
                        for(int j=0;j<listresult.size();j++)
                        {  int k= (listresult.get(j).getDoctorId());
                            int p=doctordata.getDoctorId();
                            if(k==p)
                                matchfound = "Y";
                        }
                        if(matchfound=="N")
                            listresult.add(doctordata);
*/
                        if (!listresult.contains(doctordata))
                            listresult.add(doctordata);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Exception Caught";
                }
            }

            this.textSearch = strings[0];
            return "OK";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(getActivity(), "Unable to connect to server,please try later", Toast.LENGTH_LONG).show();

                //  progressDialog.dismiss();
                //   progressBar.setVisibility(view.INVISIBLE);
            }
            else
            {
                filterDoctor(textSearch);
                listView.setAdapter(new SearchResultsAdapter(getActivity(),filterlist));
                //  progressDialog.dismiss();
                //   progressBar.setVisibility(view.INVISIBLE);
            }
        }
    }

    // Adapter class
    public class SearchResultsAdapter extends BaseAdapter
    {
        ArrayList<Doctor> doctor_detail=new ArrayList<>();
        int count;
        Context context;
        Bitmap bitmap;
        private LayoutInflater layoutInflater;


        public SearchResultsAdapter(Context context, ArrayList<Doctor> doctor_detail)
        {
            layoutInflater = LayoutInflater.from(context);
            this.doctor_detail=doctor_detail;
            count=doctor_detail.size();
            this.context=context;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return doctor_detail.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Viewholder viewholder;
            if(view==null)
            {
                view=layoutInflater.inflate(R.layout.customdoctorview,null);
                viewholder=new Viewholder();
                viewholder.firstname= view.findViewById(R.id.firstname);
                viewholder.speciality=  view.findViewById(R.id.speciality);
                viewholder.imageView=view.findViewById(R.id.dimage);
                view.setTag(viewholder);
            }
            else
            {
                viewholder = (Viewholder) view.getTag();

            }
            if (doctor_detail.size() > 0) {
                Doctor doctordata = doctor_detail.get(i);
                viewholder.firstname.setText("Dr. " + doctordata.getFirstName() + " " + doctordata.getLastName());
                viewholder.speciality.setText(doctordata.getSpeciality());

                // Loading image using Glide library
                Glide.with(context).load(doctordata.getDoctorImageid())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewholder.imageView);

            }

            return view;
        }

    }

}
