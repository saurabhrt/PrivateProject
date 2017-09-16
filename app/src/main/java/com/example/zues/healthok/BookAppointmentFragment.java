package com.example.zues.healthok;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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


        SearchView searchView = view.findViewById(R.id.searchview);
        searchView.setQueryHint("Search for doctor ");
        //searchview
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterlist.clear();
                if (s.length() > 0) {
                    listView.setVisibility(View.VISIBLE);
                    myasynctask m = (myasynctask) new myasynctask().execute(s);
                } else
                {
                    listView.setVisibility(View.GONE);
                }
                return true;
            }
        });

        listView = view.findViewById(R.id.doctorlist);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                homeActivity.doctorForOtherFragments = listresult.get(i);
                homeActivity.swapFragment(new DoctorProfileFragment(), true);
            }
        });
        return view;
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

            url= ServiceURL.Base+ServiceURL.SearchDoctor+strings[0];
            ServiceHandler servicehandler =new ServiceHandler();
            String jsonStr = servicehandler.makeServiceCall(url);
            if(jsonStr!=null)
            {
                try {
                    JSONArray json=new JSONArray(jsonStr);
                    listresult.clear();
                    for(int i=0;i<json.length();i++) {
                        Doctor doctor = Doctor.fromJSON(json.getJSONObject(i).toString());
                        listresult.add(doctor);
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
//                filterDoctor(textSearch);
                listView.setAdapter(new SearchResultsAdapter(getActivity(), listresult));
                //  progressDialog.dismiss();
                //   progressBar.setVisibility(view.INVISIBLE);
            }
        }
    }

    // Adapter class
    public class SearchResultsAdapter extends BaseAdapter
    {
        ArrayList<Doctor> doctors = new ArrayList<>();
        int count;
        Context context;
        Bitmap bitmap;
        private LayoutInflater layoutInflater;


        public SearchResultsAdapter(Context context, ArrayList<Doctor> doctors)
        {
            layoutInflater = LayoutInflater.from(context);
            this.doctors = doctors;
            count = doctors.size();
            this.context=context;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return doctors.get(i);
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
            if (doctors.size() > 0) {
                Doctor doctor = doctors.get(i);
                viewholder.firstname.setText("Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
                viewholder.speciality.setText(doctor.getSpeciality());

                if (doctor.getDoctorImageid() > 0) {
                    // Loading image using Glide library
                    Glide.with(context).load(ServiceURL.ImageDisplayPath + doctor.getDoctorImageid())
                            .placeholder(R.drawable.doc)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(viewholder.imageView);
                }

            }

            return view;
        }

    }

}
