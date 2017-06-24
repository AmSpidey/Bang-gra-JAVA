package dzikizachod;

public class Bandyta extends Gracz {

    public Bandyta() {
        this(new StrategiaBandytyDomyslna());
    }

    public Bandyta(Strategia strategia) {
        super();

        this.maksZycie = GeneratorZycia.wylosujmaksZycie();
        this.aktZycie = this.maksZycie;
        this.strategia = strategia;
    }

    @Override

    public String getPozycja() {
        return "Bandyta";
    }

    @Override
    public Gracz klonuj() {
        return new Bandyta(strategia);
    }
}
