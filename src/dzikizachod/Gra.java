package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gra {

    Gra() {
    }

    void rozgrywka(List<Gracz> gracze, PulaAkcji pulaAkcji) {

        gracze = glebokaKopiaGraczy(gracze);

        pulaAkcji = glebokaKopiaPuli(pulaAkcji);

        Komunikator rozgrywka = new Komunikator();

        losujKolejnosc(gracze);

        rozgrywka.wypiszPoczatek(gracze);

        Wynik wynik = Wynik.BRAK_WYNIKU;

        List<Gracz> listaZywych = stworzListeZywych(gracze);

        pulaAkcji.tasowanie();

        for (int i = 1; true; i++) {
            Tura aktualnaTura = new Tura(i);

            wynik = aktualnaTura.RozegrajEtap(listaZywych, gracze, pulaAkcji);

            if (!(wynik.equals(Wynik.BRAK_WYNIKU))) {
                break;

            }
        }

        rozgrywka.wypiszKoniec(gracze, wynik);
    }

    public void losujKolejnosc(List<?> gracze) {

        Collections.shuffle(gracze);

    }

    public List<Gracz> stworzListeZywych(List<Gracz> gracze) {

        List<Gracz> doZwrocenia = new ArrayList<Gracz>();
        for (int i = 0; i <= gracze.size() - 1; i++) {
            doZwrocenia.add(gracze.get(i));
        }
        return doZwrocenia;
    }

    public PulaAkcji glebokaKopiaPuli(PulaAkcji pulaAkcji) {

        PulaAkcji gleboka = new PulaAkcji();

        for (int i = 0; i <= pulaAkcji.getListaAkcji().size() - 1; i++) {
            gleboka.dodaj(pulaAkcji.getListaAkcji().get(i), 1);
        }

        return gleboka;
    }

    public List<Gracz> glebokaKopiaGraczy(List<Gracz> gracze) {

        List<Gracz> gleboka = new ArrayList<Gracz>();

        for (int i = 0; i <= gracze.size() - 1; i++) {

            gleboka.add(gracze.get(i).klonuj());

        }
        return gleboka;

    }

}
