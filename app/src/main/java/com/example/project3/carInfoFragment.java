package com.example.project3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView makeModel;
    private TextView price;
    private TextView date;
    private TextView summary;
    private ImageView carPic;
    // TODO: Rename and change types of parameters
    //private CarObject mParam1;
    private DetailCar mParam1;
    private String mParam2;
    //CarObject co = new CarObject();
    DetailCar co=new DetailCar();

    public carInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment carInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
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
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam1 = getArguments().getString(ARG_PARAM1);
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
            summary = root.findViewById(R.id.carSummaryBig);
            carPic = root.findViewById(R.id.carPicBig);

            //makeModel.setText(co.make + " " + co.model);
            price.setText(Double.toString(co.price));
            date.setText(co.vin_number);
            summary.setText(co.veh_description);
            //carPic.setImageResource(co.carPic);


        }

        return root;
    }
}