package dzikizachod;

import java.util.ArrayList;
import java.util.List;

public class Tura {

    private int numerTury;
    private final int maksTury;

    Tura(int numerTury) {
        this.numerTury = numerTury;
        this.maksTury = 42;
    }

    public Wynik RozegrajEtap(List<Gracz> gracze, List<Gracz> wszyscy,
            PulaAkcji pulaAkcji) {

        Komunikator komunikatorTury = new Komunikator();

        int j = znajdzSzeryfa(gracze);
        int pozycjaSzeryfa = j;

        Wynik pom = Wynik.BRAK_WYNIKU;

        komunikatorTury.wypiszNaglowek(numerTury);

        do {

            wszyscy.get(j).rozegrajTure(this, gracze, wszyscy, pulaAkcji);
            if (!sprawdzenieWyniku(wszyscy).equals(Wynik.BRAK_WYNIKU)) {
                return sprawdzenieWyniku(wszyscy);
            }
            j = (j + 1) % (wszyscy.size());

        } while (j != pozycjaSzeryfa);

        if (pom != Wynik.BRAK_WYNIKU) {
            return pom;
        }

        if (numerTury == maksTury) {
            return Wynik.REMIS;
        }

        return pom;

    }

    public Wynik sprawdzenieWyniku(List<Gracz> gracze) {

        if (gracze.get(znajdzSzeryfa(gracze)).martwy()) {
            return Wynik.WYGRANA_BANDYTOW;
        }
        List<Gracz> Bandyci = znajdzBandytow(gracze);

        for (int i = 0; i <= Bandyci.size() - 1; i++) {
            if (!Bandyci.get(i).martwy()) {
                return Wynik.BRAK_WYNIKU;
            }
        }
        return Wynik.WYGRANA_SZERYFA;
    }

    private int znajdzSzeryfa(List<Gracz> gracze) {
        for (int i = 0; i <= gracze.size() - 1; i++) {
            if (gracze.get(i).getPozycja().equals("Szeryf")) {
                return i;
            }
        }
        return -1;

    }

    private List<Gracz> znajdzBandytow(List<Gracz> gracze) {
        List<Gracz> Bandyci = new ArrayList<Gracz>();
        for (int i = 0; i <= gracze.size() - 1; i++) {
            if (gracze.get(i).getPozycja().equals("Bandyta"))
                Bandyci.add(gracze.get(i));
        }

        return Bandyci;
    }

}
