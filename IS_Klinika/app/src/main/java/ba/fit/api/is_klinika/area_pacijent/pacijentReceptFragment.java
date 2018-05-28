package ba.fit.api.is_klinika.area_pacijent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_pacijent.model.PacijentKartonPregledVM;
import ba.fit.api.is_klinika.helper.F;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class pacijentReceptFragment extends Fragment{

    private static final String ARG_MODEL ="model";

    public static pacijentReceptFragment newInstance(PacijentKartonPregledVM model){
        pacijentReceptFragment fragment=new pacijentReceptFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_MODEL,model);
        fragment.setArguments(arg);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View RootView = inflater.inflate(R.layout.activity_pacijent_recept, container, false);

        Bundle bundle = getArguments();


        final PacijentKartonPregledVM x = (PacijentKartonPregledVM)bundle.getSerializable(ARG_MODEL);

        F.findView(RootView,R.id.LVRecept, ListView.class).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return x.ReceptList.size();
            }

            @Override
            public Object getItem(int i) {
                return x.ReceptList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {
                if(view == null)
                {
                    final LayoutInflater inflater =(LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.activity_recept_stavka,parent,false);
                }
                PacijentKartonPregledVM.ReceptInfo y = x.ReceptList.get(i);

                F.findView(view,R.id.lblNazivLijeka, TextView.class).setText("Naziv Lijeka: "+y.Naziv);
                F.findView(view,R.id.lblVrstaLijeka,TextView.class).setText("Vrsta Lijeka: "+y.Vrsta);
                F.findView(view,R.id.lblUpotreba,TextView.class).setText("Upotreba lijeka: "+y.Upotreba);

                return view;
            }
        });
        return RootView;
    }
}
