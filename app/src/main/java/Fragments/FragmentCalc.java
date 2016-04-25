package Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import admin.example.com.pipes_v2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCalc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCalc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCalc extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayAdapter<String> adapter;
    String[] data = {"Круглые трубы", "Прямоугольные трубы"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentCalc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCalc.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCalc newInstance(String param1, String param2) {
        FragmentCalc fragment = new FragmentCalc();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_calc, container, false);

        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> apt =  ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.type_pipes,R.layout.spinner_item);
        apt.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(apt);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,View itemSelected, int selectedItemPosition, long selectedId) {
                TextView txt_pipes = (TextView) getActivity().findViewById(R.id.txt_pipes);
                String[] choose = getResources().getStringArray(R.array.type_pipes);
                txt_pipes.setText(choose[selectedItemPosition]);

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final EditText edit_D = (EditText) v.findViewById(R.id.edit_D);
        final EditText edit_S = (EditText) v.findViewById(R.id.edit_S);
        final EditText edit_L = (EditText) v.findViewById(R.id.edit_L);
        edit_D.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        edit_S.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        edit_L.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        final TextView txt_M = (TextView) v.findViewById(R.id.txt_M);
        final TextView txt_D = (TextView) v.findViewById(R.id.txt_D);
        final TextView txt_S = (TextView) v.findViewById(R.id.txt_S);
        final TextView txt_L = (TextView) v.findViewById(R.id.txt_L);
        edit_D.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_D.setText(edit_D.getText().toString());
                if(edit_S.getText().length() != 0 && edit_L.getText().length() != 0){
                    Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                    txt_M.setText(" M = " + String.format( Locale.US, "%.2f", res_pM));
                }
                return true;
            }
        });
        edit_S.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_S.setText(edit_S.getText().toString());
                if(edit_D.getText().length() != 0 && edit_L.getText().length() != 0){
                    Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                    txt_M.setText(" M = " + String.format( Locale.US, "%.2f", res_pM));
                }
                return true;
            }
        });
        edit_L.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                txt_L.setText(edit_L.getText().toString());
                if(edit_S.getText().length() != 0 && edit_D.getText().length() != 0){
                    Double res_pM = ((Double.parseDouble(edit_D.getText().toString()) - Double.parseDouble(edit_S.getText().toString()))*Double.parseDouble(edit_S.getText().toString()))/40.55;
                    txt_M.setText(" M = " + String.format( Locale.US, "%.2f", res_pM));
                }
                return true;
            }
        });

               //return inflater.inflate(R.layout.fragment_fragment_calc, container, false);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
