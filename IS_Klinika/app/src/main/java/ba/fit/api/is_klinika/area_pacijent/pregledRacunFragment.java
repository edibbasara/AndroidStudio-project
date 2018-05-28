package ba.fit.api.is_klinika.area_pacijent;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_pacijent.model.PacijentKartonPregledVM;
import ba.fit.api.is_klinika.helper.F;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class pregledRacunFragment extends Fragment {

    private static final String ARG_MODEL = "model";

    public static pregledRacunFragment newInstance(PacijentKartonPregledVM model)
    {
        pregledRacunFragment fragment = new pregledRacunFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_MODEL,model);
        fragment.setArguments(arg);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View RootView = inflater.inflate(R.layout.activity_pregled_racun, container, false);

        Bundle bundle = getArguments();

        final PacijentKartonPregledVM x = (PacijentKartonPregledVM)bundle.getSerializable(ARG_MODEL);

        F.findView(RootView,R.id.LVRacun, ExpandableListView.class).setAdapter(new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getGroupCount() {
                return x.RacunList.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return x.RacunList.get(i).RacunStavkaList.size();
            }

            @Override
            public Object getGroup(int i) {
                return x.RacunList.get(i);
            }

            @Override
            public Object getChild(int i, int i1) {
                return x.RacunList.get(i).RacunStavkaList.get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return i;
            }

            @Override
            public long getChildId(int i, int i1) {
                return i1;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup parent) {

                PacijentKartonPregledVM.RacunInfo y = x.RacunList.get(i);
                if(view == null)
                {
                    final LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.activity_racun,parent,false);
                }

                F.findView(view,R.id.lblDatumRac, TextView.class).setText("Datum: "+F.Date_dd_MM_YYYY(y.Datum));
                F.findView(view,R.id.lblPopustRac,TextView.class).setText("Popust"+F.Decimal_0_00(y.Popust));
                F.findView(view,R.id.lblIznosBezPDVRac,TextView.class).setText("Bez PDV-a:"+F.Decimal_0_00(y.IznosBezPDVUkupno));
                F.findView(view,R.id.lblPDVRac,TextView.class).setText("PDV:"+F.Decimal_0_00(y.PDVIznosUkupno));
                F.findView(view,R.id.lblIznosUkupnoRac,TextView.class).setText("Ukupno"+F.Decimal_0_00(y.IznosUkupno));

                return view;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup parent) {

                PacijentKartonPregledVM.RacunInfo.RacunStavkaInfo c = x.RacunList.get(i).RacunStavkaList.get(i1);

                if(view == null)
                {
                    final LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.activity_racun_stavka,parent,false);
                }

                F.findView(view,R.id.lblProizvod,TextView.class).setText("Naziv:"+c.NazivProizvod);
                F.findView(view,R.id.lblCijena,TextView.class).setText("Cijena:"+F.Decimal_0_00(c.Cijena));
                F.findView(view,R.id.lblKolicina,TextView.class).setText("Količina:"+c.Kolicina+"");
                F.findView(view,R.id.lblIznosBezPDV,TextView.class).setText("Bez PDV-a:"+F.Decimal_0_00(c.IznosBezPDV));
                F.findView(view,R.id.lblPDV,TextView.class).setText("PDV:"+F.Decimal_0_00(c.IznosPDV));
                F.findView(view,R.id.lblIznosSaPDV,TextView.class).setText("Ukupno:"+F.Decimal_0_00(c.IznosSaPDV));

                return view;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return false;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int i) {

            }

            @Override
            public void onGroupCollapsed(int i) {

            }

            @Override
            public long getCombinedChildId(long l, long l1) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long l) {
                return 0;
            }
        });

        return RootView;
    }
}
