package ba.fit.api.is_klinika.area_pacijent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import ba.fit.api.is_klinika.R;
import ba.fit.api.is_klinika.area_autentifikacija.LoginActivity.model.AutentifikacijaApi;
import ba.fit.api.is_klinika.area_pacijent.model.PacijentApi;
import ba.fit.api.is_klinika.area_pacijent.model.PacijentKartonPregledVM;
import ba.fit.api.is_klinika.helper.F;
import ba.fit.api.is_klinika.helper.MyRunnable;

public class pacijentKarton extends AppCompatActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacijent_karton);

        final int PregledID = (int)getIntent().getSerializableExtra("model");

        pager = (ViewPager)findViewById(R.id.Pager);

        PacijentApi.pregled(this, PregledID, new MyRunnable<PacijentKartonPregledVM>() {
            @Override
            public void run(PacijentKartonPregledVM result) {
                if(result.DijagnozaOpis == null)
                    Toast.makeText(pacijentKarton.this,"Pregled još nije obavljen",Toast.LENGTH_LONG).show();
                else
                {
                    pripremi_ViewPager(result);
                }
            }

            private void pripremi_ViewPager(final PacijentKartonPregledVM model) {

                final FragmentManager fm = getSupportFragmentManager();
                pager.setAdapter(new FragmentPagerAdapter(fm) {
                    @Override
                    public Fragment getItem(int position) {
                        Fragment fragment = null;
                        if(position == 0)
                            fragment = pacijentPregledFragment.newInstance(model);
                        if(position == 1)
                            fragment = pacijentReceptFragment.newInstance(model);
                        if(position == 2)
                            fragment = pregledRacunFragment.newInstance(model);
                        if(position == 3)
                            fragment = PacijentOsigFragment.newInstance(model);
                        return fragment;
                    }

                    @Override
                    public int getCount() {
                        return 4;
                    }
                });

                final ActionBar actionBar =  getSupportActionBar();
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

                ActionBar.TabListener tabListener=new ActionBar.TabListener() {
                    @Override
                    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                        int pozicija = tab.getPosition();
                        pager.setCurrentItem(pozicija);
                    }

                    @Override
                    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

                    }

                    @Override
                    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

                    }
                };
                ActionBar.Tab tab1 = actionBar.newTab().setText("Pregled").setTabListener(tabListener);
                actionBar.addTab(tab1);
                ActionBar.Tab tab2 = actionBar.newTab().setText("Terapija").setTabListener(tabListener);
                actionBar.addTab(tab2);
                ActionBar.Tab tab3 = actionBar.newTab().setText("Račun").setTabListener(tabListener);
                actionBar.addTab(tab3);
                ActionBar.Tab tab4 = actionBar.newTab().setText("Osiguranje").setTabListener(tabListener);
                actionBar.addTab(tab4);

                pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        actionBar.setSelectedNavigationItem(position);
                    }
                });
            }
        });

    }
}
