package dzikizachod;

public class PomocnikSzeryfa extends Gracz {

    public PomocnikSzeryfa() {
        this(new StrategiaPomocnikaSzeryfaDomyslna());

    }

    public PomocnikSzeryfa(Strategia strategia) {
        super();

        this.maksZycie = GeneratorZycia.wylosujmaksZycie();
        this.aktZycie = this.maksZycie;
        this.strategia = strategia;

    }

    public String getPozycja() {
        return "PomocnikSzeryfa";
    }

    @Override
    public Gracz klonuj() {
        return new PomocnikSzeryfa(strategia);
    }

}
