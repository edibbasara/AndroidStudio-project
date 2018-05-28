package ba.fit.api.is_klinika.area_pacijent.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PacijentKartonPregledVM implements Serializable {
    public int Id ;
    public boolean Valid ;
    public String Opis ;
    public String HistorijaBolesti ;
    public String DijagnozaOpis ;
    public int DijagnozaId ;


    public class ReceptInfo implements Serializable
    {
        public int Id ;
        public boolean Valid ;
        public String Naziv ;
        public String Vrsta ;
        public String Upotreba ;
        public int DijagnozaId ;
    }

    public class OsiguranjeInfo implements Serializable
    {
        public int Id ;
        public boolean Valid ;
        public String NazivPoslodavca ;
        public String Adresa ;
        public String OpstinaNaziv ;
        public String LicniBrOsig ;
        public String RegBr ;
        public String Zajednica ;
        public String RadnoMjesto ;
        public Date OsiguranOd ;
        public Date OsiguranDo ;
        public int PacijentId ;
    }

    public class RacunInfo implements Serializable
    {
        public int Id ;
        public boolean Valid ;
        public float PDVIznosUkupno ;
        public float IznosUkupno ;
        public float IznosBezPDVUkupno ;
        public float Popust ;
        public Date Datum ;
        public int PregledId ;

        public class RacunStavkaInfo implements Serializable
        {
            public int Id ;
            public boolean Valid ;
            public int Kolicina ;
            public int RacunId ;
            public int ProizvodId ;
            public String NazivProizvod ;
            public float Cijena ;
            public int PDVStopeId ;
            public float PDVstopa ;
            public float IznosBezPDV ;
            public float IznosSaPDV ;
            public float IznosPDV ;
        }

        public List<RacunStavkaInfo> RacunStavkaList ;
    }

    public List<RacunInfo> RacunList ;
    public List<OsiguranjeInfo> OsiguranjeList ;
    public List<ReceptInfo> ReceptList ;
}
