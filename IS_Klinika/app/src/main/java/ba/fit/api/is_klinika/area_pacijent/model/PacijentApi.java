package ba.fit.api.is_klinika.area_pacijent.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import ba.fit.api.is_klinika.helper.HttpManager;
import ba.fit.api.is_klinika.helper.MyRunnable;
import ba.fit.api.is_klinika.helper.url.MojRazultat;

public class PacijentApi {
    public static void pregled(final Context context, final int pregledID, final MyRunnable<PacijentKartonPregledVM> onSuccess){
        new AsyncTask<Void,Void,MojRazultat<PacijentKartonPregledVM>>(){

            private ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(context,"Pristup podacima","podaci se učitavaju!!!");
                dialog.show();
            }

            @Override
            protected MojRazultat<PacijentKartonPregledVM> doInBackground(Void... voids) {
                return HttpManager.get("PacijentKarton/Pregled", PacijentKartonPregledVM.class,
                        new BasicNameValuePair("PregledID",pregledID+""));
            }

            @Override
            protected void onPostExecute(MojRazultat<PacijentKartonPregledVM> result) {
                if(result.IsError)
                {
                    Toast.makeText(context,"Pacijent nema rezervacija",Toast.LENGTH_LONG).show();
                }
                else{
                    dialog.dismiss();
                    onSuccess.run(result.value);
                }
            }
        }.execute();
    }
}
