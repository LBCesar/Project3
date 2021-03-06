package com.example.project3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class carInfoFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView summary;
    private TextView mileage;
    private ImageView carPic;
    private TextView textView;

    public static ArrayList<MoreDetails> moreDetailsCarList=new ArrayList<>();

    private DetailCar mParam1;
    private String mParam2;
    DetailCar co=new DetailCar();

    public carInfoFragment() {
        // Required empty public constructor
    }

    public static carInfoFragment newInstance(DetailCar param1) {
        carInfoFragment fragment = new carInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (DetailCar) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            co = mParam1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_car_info, container, false);

        if (getArguments() != null) {
            makeModel = root.findViewById(R.id.makeModelBIG);
            price = root.findViewById(R.id.priceBIG);
            date = root.findViewById(R.id.lastUpdatedBIG);
            summary = root.findViewById(R.id.carSummaryBIG);
            mileage = root.findViewById(R.id.mileageBIG);
            carPic = root.findViewById(R.id.carPicBIG);

            makeModel.setText( co.vehicle_make + " _ " + co.model);
            price.setText(co.getPrice());
            date.setText(co.vin_number);
            mileage.setText(" " + co.mileage + " miles");
            summary.setText(co.veh_description);

            //Tested link: "https://i.imgur.com/gwy9G6s.jpg"
            if(URLUtil.isValidUrl(co.image_url)) {
                Picasso.get()
                        .load(co.image_url)
//                    .resize(600,600)
                        .error(R.drawable.coming_soon)
                        .into(carPic);
            }

            textView = root.findViewById(R.id.mddBIG);
            textView.setVisibility(TextView.INVISIBLE);

            Button btn_moredetails = root.findViewById(R.id.btn_moredetailsBIG);
            btn_moredetails.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if (textView.getVisibility() == View.VISIBLE){
                        textView.setText(" ");
                        textView.setVisibility(TextView.INVISIBLE);
                    }
                    else{
                        textView.setVisibility(TextView.VISIBLE);
                        textView.setText(moreDetailsCarList.get(0).toString());
                    }
                }
            });
        }
        return root;
    }

}  // end class
