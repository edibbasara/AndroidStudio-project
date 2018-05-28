package ba.fit.api.is_klinika.area_pacijent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model.AutentifikacijaProvjeraVM;
import ba.fit.api.is_klinika.helper.F;
import ba.fit.api.is_klinika.helper.Sesija;

public class pregledRezervacija extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_rezervacija);

        final AutentifikacijaProvjeraVM korisnik = Sesija.getLogiraniKorisnik();
        final ListView ListRezervacija = (ListView)findViewById(R.id.LVRezPregled);

        ListRezervacija.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return korisnik.RezervacijaList.size();
            }

            @Override
            public Object getItem(int i) {
                return korisnik.RezervacijaList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup parent) {
                AutentifikacijaProvjeraVM.RezervacijeInfo x = korisnik.RezervacijaList.get(i);

                if(view == null){
                    final LayoutInflater inflater =(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(R.layout.activity_pregled_stavka,parent,false);
                }

                F.findView(view,R.id.lblPacijent,TextView.class).setText("Pacijent: "+x.PacijentNaziv);
                F.findView(view,R.id.lblZaposlenik,TextView.class).setText("Zaposlenik: "+x.ZaposlenikNaziv);
                F.findView(view,R.id.lblDatum,TextView.class).setText("Datum: "+F.Date_dd_MM_YYYY(x.DatumPregleda));
                F.findView(view,R.id.lblTermin,TextView.class).setText("Termin: "+x.Termin);
                F.findView(view,R.id.cbxZavrsen, CheckBox.class).setChecked(x.Zavrsen);

                return view;
            }
        });

    ListRezervacija.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AutentifikacijaProvjeraVM.RezervacijeInfo x = korisnik.RezervacijaList.get(i);
            Intent intent = new Intent(pregledRezervacija.this,pacijentKarton.class);
            intent.putExtra("model",x.PregledId);
            startActivity(intent);
        }
    });
    }
}
