package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategiaBandytySprytna extends StrategiaBandyty {

    @Override
    public boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.STRZEL))
            return false;
        List<Gracz> zasieg = gracz.zbierzZasieg(gracze);

        if (szeryfWZasiegu(zasieg)) {

            gracz.getDoWykonania().add(
                    new Ruch(Akcja.STRZEL, gracze.get(znajdzSzeryfa(gracze))));
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);

            return true;

        }

        List<Gracz> bandyci = bandyciWZasiegu(gracz, gracze);

        if (!bandyci.isEmpty()) {
            Collections.shuffle(bandyci);

            for (int i = 1; i <= bandyci.get(0).getAktZycie(); i++) {
                gracz.getDoWykonania()
                        .add(new Ruch(Akcja.STRZEL, bandyci.get(0)));
                gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            }
            return domyslneDzialanie(gracz, gracze, pulaAkcji, true);

        }

        return domyslneDzialanie(gracz, gracze, pulaAkcji, false);

    }

    private List<Gracz> bandyciWZasiegu(Gracz gracz, List<Gracz> gracze) {
        List<Gracz> doZwrocenia = new ArrayList<Gracz>();

        doZwrocenia = gracz.zbierzZasieg(gracze);

        for (int i = 0; i <= doZwrocenia.size() - 1; i++) {
            if (!doZwrocenia.get(i).getPozycja().equals("Bandyta")
                    || doZwrocenia.get(i)
                            .getAktZycie() > liczbaStrzalow(gracz)) {
                doZwrocenia.remove(i);
                --i;
            }
        }

        return doZwrocenia;
    }

    private int liczbaStrzalow(Gracz gracz) {

        int j = 0;

        for (int i = 0; i <= gracz.getWylosowaneAkcje().size() - 1; i++) {
            if (gracz.getWylosowaneAkcje().get(i).equals(Akcja.STRZEL)) {
                j++;
            }
        }

        return j;
    }

    private List<Gracz> przeciwnicyWZasiegu(Gracz gracz, List<Gracz> gracze) {
        List<Gracz> doZwrocenia = new ArrayList<Gracz>();

        doZwrocenia = gracz.zbierzZasieg(gracze);

        for (int i = 0; i <= doZwrocenia.size() - 1; i++) {
            if (doZwrocenia.get(i).getPozycja().equals("Bandyta")) {
                doZwrocenia.remove(i);
                --i;
            }
        }

        return doZwrocenia;
    }

    public boolean domyslneDzialanie(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji, boolean juzDzialano) {

        if (!gracz.getWylosowaneAkcje().contains(Akcja.STRZEL))
            return juzDzialano;
        List<Gracz> wZasiegu = przeciwnicyWZasiegu(gracz, gracze);

        if (!wZasiegu.isEmpty()) {
            Collections.shuffle(wZasiegu);

            gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, wZasiegu.get(0)));
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            return true;
        }
        return false;
    }

}
