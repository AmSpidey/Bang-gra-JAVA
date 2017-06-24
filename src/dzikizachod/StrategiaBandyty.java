package dzikizachod;

import java.util.List;

public abstract class StrategiaBandyty extends Strategia {

    public boolean leczenie(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        if (gracz.getWylosowaneAkcje().contains(Akcja.ULECZ) && gracz.ranny()) {
            gracz.getDoWykonania().add(new Ruch(Akcja.ULECZ, gracz));
            gracz.getWylosowaneAkcje().remove(Akcja.ULECZ);
            return true;

        }
        return false;
    }

    public boolean dynamit(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        if (gracz.getWylosowaneAkcje().contains(Akcja.DYNAMIT)) {

            if (odlegloscOdSzeryfa(gracz, gracze) < 3) {
                gracz.getDoWykonania().add(new Ruch(Akcja.DYNAMIT,
                        gracze.get(gracz.numerNastepnego(gracze))));
                gracz.getWylosowaneAkcje().remove(Akcja.DYNAMIT);

                return true;
            }

        }

        return false;
    }

    private int odlegloscOdSzeryfa(Gracz gracz, List<Gracz> gracze) {

        int i = znajdzSzeryfa(gracze);

        if (i > gracz.numerGracza(gracze)) {
            return gracz.numerGracza(gracze) - i - 1;
        }

        else {
            return gracze.size() - 1 - gracz.numerGracza(gracze) + i;
        }

    }

}
