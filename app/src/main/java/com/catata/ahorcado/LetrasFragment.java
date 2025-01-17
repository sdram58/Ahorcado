package com.catata.ahorcado;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LetrasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LetrasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public LetrasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LetrasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LetrasFragment newInstance(String param1) {
        LetrasFragment fragment = new LetrasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_letras, container, false);
        TextView tvLetras = (TextView) v.findViewById(R.id.tv_letras);
        if(mParam1.length()>0){
            String letras = mParam1.replace("*"," - ");
            tvLetras.setText(letras.substring(0, letras.length()-3));
        }

        return v;
    }
}