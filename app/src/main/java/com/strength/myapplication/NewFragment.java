package com.strength.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.strength.myapplication1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class NewFragment extends Fragment {

    public String difference;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(ExerciseStore.DATE_FORMAT);
        String dateToday = format.format(curDate);

        ExerciseStore.initialise(getActivity());
        List<String> mExercises = ExerciseStore.getInstance().getExerciseDates();
        Collections.sort(mExercises, new ExerciseStore.StringDateComparator());
        String lastDate = mExercises.get(mExercises.size() - 1);

        Date aDate = null;
        try {
            aDate = format.parse(lastDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date bDate = null;
        try {
            bDate = format.parse(dateToday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar with = Calendar.getInstance();
        with.setTime(aDate);
        Calendar to = Calendar.getInstance();
        to.setTime(bDate);
        to.set(Calendar.YEAR, with.get(Calendar.YEAR));
        int withDAY = with.get(Calendar.DAY_OF_YEAR);
        int toDAY = to.get(Calendar.DAY_OF_YEAR);

        int diffDay = toDAY - withDAY;
        difference = Integer.toString(diffDay);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.lowerLabel);
        textView.setText(difference);

        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}