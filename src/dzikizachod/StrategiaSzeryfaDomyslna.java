package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    public boolean strza³(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        if (gracz.getWylosowaneAkcje().contains(Akcja.STRZEL)) {

            if (gracz.getAtakuj¹cy().isEmpty()) {
                return atakujLosow¹(gracz, gracze, pulaAkcji);
            }

            else {
                return atakujAtakuj¹cych(gracz, gracze, pulaAkcji);
            }
        }

        return false;
    }

    private boolean atakujLosow¹(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {
        List<Gracz> wZasiêgu = gracz.zbierzZasieg(gracze);

        if (wZasiêgu.isEmpty())
            return false;

        else {
            Collections.shuffle(wZasiêgu);

            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            gracz.getDoWykonania().add(new Ruch(Akcja.STRZEL, wZasiêgu.get(0)));
            return true;

        }

    }

    private boolean atakujAtakuj¹cych(Gracz gracz, List<Gracz> gracze,
            PulaAkcji pulaAkcji) {

        List<Gracz> wZasiêgu = gracz.zbierzZasieg(gracze);
        List<Gracz> atakuj¹cyWZasiêgu = new ArrayList<Gracz>();

        for (int i = 0; i <= wZasiêgu.size() - 1; i++) {
            if (gracz.getAtakuj¹cy().contains(wZasiêgu.get(i))) {
                atakuj¹cyWZasiêgu.add(wZasiêgu.get(i));
            }
        }

        if (atakuj¹cyWZasiêgu.isEmpty())
            return false;
        else {
            Collections.shuffle(atakuj¹cyWZasiêgu);
            gracz.getWylosowaneAkcje().remove(Akcja.STRZEL);
            gracz.getDoWykonania()
                    .add(new Ruch(Akcja.STRZEL, atakuj¹cyWZasiêgu.get(0)));
            return true;

        }
    }
}
