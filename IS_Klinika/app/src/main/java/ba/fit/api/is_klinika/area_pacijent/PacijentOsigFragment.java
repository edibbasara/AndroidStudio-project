package ba.fit.api.is_klinika.area_pacijent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_pacijent.model.PacijentKartonPregledVM;
import ba.fit.api.is_klinika.helper.F;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PacijentOsigFragment extends Fragment{
    private static final String ARG_MODEL = "model";

    public static PacijentOsigFragment newInstance(PacijentKartonPregledVM model){
        PacijentOsigFragment fragment = new PacijentOsigFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_MODEL,model);
        fragment.setArguments(arg);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View RootView = inflater.inflate(R.layout.activity_pacijent_osiguranje,container,false);

        Bundle bundle = getArguments();
        final PacijentKartonPregledVM x =(PacijentKartonPregledVM) bundle.getSerializable(ARG_MODEL);



        F.findView(RootView,R.id.LVOsig, ListView.class).setAdapter(new BaseAdapter() {
            @Override

            public int getCount() {
                return x.OsiguranjeList.size();
            }

            @Override
            public Object getItem(int i) {
                return x.OsiguranjeList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {

                PacijentKartonPregledVM.OsiguranjeInfo y = x.OsiguranjeList.get(i);

                if (view == null) {
                    final LayoutInflater inflater =(LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.activity_osiguranje, parent, false);
                }

                F.findView(view, R.id.lblPoslodavac, TextView.class).setText("Poslodavac:" + y.NazivPoslodavca);
                F.findView(view, R.id.lblOpstina, TextView.class).setText("Opština:" + y.OpstinaNaziv);
                F.findView(view, R.id.lblAdresa, TextView.class).setText("Adresa:" + y.Adresa);
                F.findView(view, R.id.lblRadMjesto, TextView.class).setText("Rad.mjesto:" + y.RadnoMjesto);
                F.findView(view, R.id.lblRegBr, TextView.class).setText("Reg.broj:" + y.RegBr);
                F.findView(view, R.id.lblZajednica, TextView.class).setText("Zajednica:" + y.Zajednica);
                F.findView(view, R.id.lblDatumOd, TextView.class).setText("Datum od:" + F.Date_dd_MM_YYYY(y.OsiguranOd));
                F.findView(view, R.id.lblDatumDo, TextView.class).setText("Datum do:" + F.Date_dd_MM_YYYY(y.OsiguranDo));
                F.findView(view, R.id.cbxValid, CheckBox.class).setChecked(y.Valid);

                return view;
            }
        });

        return RootView;
    }
}
