package com.example.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class carInfoFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView mileage;
    private TextView vin;
    private TextView summary;
    private ImageView carPic;

//    private CarObject mParam1;
    private DetailCar mParam1;
    private String mParam2;

    DetailCar co = new DetailCar();

    public carInfoFragment() {
        // Required empty public constructor
    }

    public static carInfoFragment newInstance(DetailCar param1, String param2) {
        carInfoFragment fragment = new carInfoFragment();
        Bundle args = new Bundle();
        //co=param1;
        //args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
            makeModel = root.findViewById(R.id.makeModelBig);
            price = root.findViewById(R.id.priceBig);
            date = root.findViewById(R.id.lastUpdatedBig);
            mileage = root.findViewById(R.id.mileageBig);
            vin = root.findViewById(R.id.vinBig);
            summary = root.findViewById(R.id.carSummaryBig);
            carPic = root.findViewById(R.id.carPicBig);

            makeModel.setText(co.vehicle_make + " " + co.model);
            price.setText(co.getPrice());
//            date.setText(co.getCreated_at());
//            mileage.setText(co.getMileage());
//            vin.setText(co.getVin_number());
            summary.setText(co.veh_description);
            //carPic.setImageResource(co.carPic);

            Picasso.get()
                    .load("https://i.imgur.com/gwy9G6s.jpg")
//                    .resize(600,600)
                    .into(carPic);
        }
        return root;
    }

}