package dzikizachod;

import java.util.List;

public class Komunikator {

    public void wypiszPoczatek(List<Gracz> gracze) {

        System.out.println("**START\n  Gracze:");

        for (int i = 0; i <= gracze.size() - 1; i++) {

            int j = i + 1;

            System.out.println("    " + j + ": " + gracze.get(i).getPozycja()
                    + " (liczba ¿yæ: " + gracze.get(i).getMaksZycie() + ")");
        }

    }

    public void wypiszKoniec(List<Gracz> gracze, Wynik wynik) {

        System.out.println("");

        if (wynik.equals(Wynik.WYGRANA_BANDYTOW)) {
            System.out.printf("**KONIEC\n  WYGRANA STRONA: " + "bandyci");
        } else if (wynik.equals(Wynik.WYGRANA_SZERYFA)) {
            System.out.printf(
                    "**KONIEC\n  WYGRANA STRONA: " + "szeryf i pomocnicy");
        }

        else if (wynik.equals(Wynik.REMIS)) {
            System.out.printf("**KONIEC\n  REMIS - OSI¥GNIÊTO LIMIT TUR");
        }
    }

    public void wypiszNaglowek(int numerTury) {

        if (numerTury == 1)
            System.out.println("");
        System.out.println("** TURA " + numerTury + "\n");

    }

    public void wypiszStan(List<Gracz> gracze) {
        System.out.println("Gracze: ");
        for (int i = 0; i <= gracze.size() - 1; i++) {
            int j = i + 1;
            System.out.println("  " + j + ": " + gracze.get(i).toString());
        }

    }

    public void wypiszGraczaZywego(List<Gracz> wszyscy, Gracz gracz) {

        int j = gracz.numerGracza(wszyscy) + 1;

        System.out.println("GRACZ " + j + " (" + gracz.getPozycja() + "):\n"
                + wypiszAkcje(gracz));

    }

    public void wypiszGraczaMartwego(List<Gracz> wszyscy, Gracz gracz) {
        int j = gracz.numerGracza(wszyscy) + 1;
        System.out.println(
                "GRACZ " + j + " (" + gracz.getPozycja() + "):\n  MARTWY");
    }

    public String wypiszAkcje(Gracz gracz) {
        String ca³oœæ = ("  Akcje: [");

        for (int i = 0; i <= gracz.getWylosowaneAkcje().size() - 2; i++) {
            ca³oœæ = ca³oœæ + ((gracz.getWylosowaneAkcje().get(i) + ", "));
        }
        ca³oœæ = ca³oœæ + gracz.getWylosowaneAkcje()
                .get(gracz.getWylosowaneAkcje().size() - 1) + "]";
        return ca³oœæ;

    }

    public void wypiszStanPoRuchu(List<Gracz> wszyscy) {
        System.out.println("");
        wypiszStan(wszyscy);
        System.out.println("");
    }

    public void martwy() {
        System.out.println("MARTWY");
    }

    public void wypiszDynamit(Gracz gracz, boolean oddynamitowany,
            List<Gracz> wszyscy) {
        if (oddynamitowany) {
            System.out.println("Dynamit: PRZECHODZI DALEJ");
        }

        if (!oddynamitowany) {
            System.out.println("Dynamit: WYBUCH£");
        }

    }

}
