package ba.fit.api.is_klinika.area_pacijent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_pacijent.model.PacijentKartonPregledVM;
import ba.fit.api.is_klinika.helper.F;

public class pacijentPregledFragment extends Fragment {

    private static final String ARG_MODEL = "model";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View RootView = inflater.inflate(R.layout.activity_pacijent_pregled, container, false);

        Bundle bundle = getArguments();
        final PacijentKartonPregledVM x =(PacijentKartonPregledVM)bundle.getSerializable(ARG_MODEL);

        F.findView(RootView,R.id.lblOpis,TextView.class).setText("Opis bolesti: "+x.Opis);
        F.findView(RootView,R.id.lblHistorija,TextView.class).setText("Historija bolesti: "+x.HistorijaBolesti);
        F.findView(RootView,R.id.lblDijagnozaOpis,TextView.class).setText(x.DijagnozaOpis);

        return RootView;
    }

    public static pacijentPregledFragment newInstance(PacijentKartonPregledVM model){

        pacijentPregledFragment fragment = new pacijentPregledFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_MODEL,model);
        fragment.setArguments(arg);

        return fragment;
    }
}
