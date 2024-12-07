package src.Alumnes;

public class Assignatura {
    public static final double APROVAT = 5.0;
    public static final double NOTABLE = 7.0;
    public static final double EXCELLENT = 9.0;

    private final String nom;
    private final int credits;
    private double nota;
    private final boolean mHonor;

    public Assignatura(String nom, int credits, double nota, boolean mHonor) {
        if (credits < 0 || nota < 0) {
            throw new IllegalArgumentException("Els crèdits i la nota no poden ser negatius.");
        }
        this.nom = nom;
        this.credits = credits;
        this.nota = nota;

        if (nota >= EXCELLENT && mHonor) {
            this.mHonor = true;
        } else {
            this.mHonor = false;
        }
    }

    public Assignatura(String nom) {
        this.nom = nom;
        this.credits = 0;
        this.nota = 0.0;
        this.mHonor = false;
    }

    public void setNota(double nota) {
        if (nota < 0) {
            throw new IllegalArgumentException("La nota no pot ser negativa.");
        }
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public int getCredits() {
        return credits;
    }

    public int getPunts() {
        if (nota >= EXCELLENT) {
            return mHonor ? 4 : 3;
        } else if (nota >= NOTABLE) {
            return 2;
        } else if (nota >= APROVAT) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Assignatura{" +
                "nom='" + nom + '\'' +
                ", credits=" + credits +
                ", nota=" + nota +
                ", mHonor=" + mHonor +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Assignatura that = (Assignatura) obj;
        return nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
