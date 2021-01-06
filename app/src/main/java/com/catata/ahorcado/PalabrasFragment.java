package com.catata.ahorcado;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PalabrasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PalabrasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    IMensaje iMensaje;

    public PalabrasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PalabrasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PalabrasFragment newInstance(String param1, String param2) {
        PalabrasFragment fragment = new PalabrasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        iMensaje = (IMensaje)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_palabras, container, false);
        TextView palabra = (TextView) v.findViewById(R.id.tv_palabra_final);
        String s = "";
        String sPalabra = mParam1.toUpperCase();
        String[] letrasDichas = mParam2.split("");
        if(mParam1.compareTo("")!=0){
            //iMensaje.onFinishWord(true);
            for(int i = 0; i< sPalabra.length();i++){
                if(mParam2.indexOf(sPalabra.charAt(i))>=0){
                    s += " " + sPalabra.charAt(i);
                }else{
                    s += " _";
                }

            }

            if(s.replace(" ","").compareTo(sPalabra)==0){
                iMensaje.onFinishWord(true);
            }

            palabra.setText(s);

        }
        return v;
    }
}