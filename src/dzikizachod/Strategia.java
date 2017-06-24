package dzikizachod;

import java.util.List;

public abstract class Strategia {

    public boolean dajRuch(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (zasieg(gracz, pulaAkcji))
            return true;
        else if (leczenie(gracz, gracze, pulaAkcji))
            return true;
        else if (strza³(gracz, gracze, pulaAkcji))
            return true;
        else if (dynamit(gracz, gracze, pulaAkcji))
            return true;

        return false;

    }

    public boolean zasieg(Gracz gracz, PulaAkcji pulaAkcji) {

        if (gracz.getWylosowaneAkcje().contains(Akcja.ZASIEG_PLUS_JEDEN)) {

            gracz.getDoWykonania()
                    .add(new Ruch(Akcja.ZASIEG_PLUS_JEDEN, gracz));
            gracz.getWylosowaneAkcje().remove(Akcja.ZASIEG_PLUS_JEDEN);
            return true;

        }

        if (gracz.getWylosowaneAkcje().contains(Akcja.ZASIEG_PLUS_DWA)) {

            gracz.getDoWykonania().add(new Ruch(Akcja.ZASIEG_PLUS_DWA, gracz));
            gracz.getWylosowaneAkcje().remove(Akcja.ZASIEG_PLUS_DWA);
            return true;

        }

        return false;
    }

    public abstract boolean leczenie(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji);

    public abstract boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji);

    public abstract boolean dynamit(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji);

    public int znajdzSzeryfa(List<Gracz> graczeWZasiegu) {
        for (int i = 0; i <= graczeWZasiegu.size() - 1; i++) {
            if (graczeWZasiegu.get(i).getPozycja().equals("Szeryf")) {
                return i;
            }
        }
        return -1;
    }

    public boolean szeryfWZasiegu(List<Gracz> graczeDoPrzeszukania) {
        if ((znajdzSzeryfa(graczeDoPrzeszukania)) == -1)
            return false;
        return true;
    }
}
