package activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import adapter.PakaianAdapter;
import id.sch.smktelkom_mlg.project.xiirpl207172737.indonesia.R;
import id.sch.smktelkom_mlg.project.xiirpl207172737.indonesia.detailpakaianActivity;
import model.Pakaian;

/**
 * Created by User on 13/11/2016.
 */
public class PakaianFragment extends Fragment implements PakaianAdapter.IPakaianAdapter {
    public static final String PAKAIAN = "pakaian";
    ArrayList<Pakaian> mList = new ArrayList<>();
    PakaianAdapter mAdapter;
    View view;
    Context context;

    public PakaianFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_pakaian_fragment, container, false);
        context = getContext();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.pakaian);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PakaianAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);

        fillData();


        // Inflate the layout for this fragment
        return rootView;
    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.kota);
        String[] arDeskripsi = resources.getStringArray(R.array.trailer_des1);
        String[] arDetail = resources.getStringArray(R.array.deskripsi1);
        TypedArray a = resources.obtainTypedArray(R.array.gambar1);
        String[] arFoto = new String[a.length()];
        for (int i = 0; i < arFoto.length; i++) {
            int id = a.getResourceId(i, 0);
            arFoto[i] = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + resources.getResourcePackageName(id) + '/'
                    + resources.getResourceTypeName(id) + '/'
                    + resources.getResourceEntryName(id);
        }
        a.recycle();


        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new Pakaian(arJudul[i], arDeskripsi[i], arDetail[i], arFoto[i]));
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(getActivity(), detailpakaianActivity.class);
        intent.putExtra(PAKAIAN, mList.get(pos));
        startActivity(intent);
    }
}
