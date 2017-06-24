package dzikizachod;

import java.util.List;

public abstract class StrategiaSzeryfa extends Strategia {

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
        return false;
    }
}
