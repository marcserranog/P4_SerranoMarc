package src.Alumnes;

import java.util.Objects;

public class Alumnes_SEC implements Comparable<Alumnes_SEC> {

    private class Node {
        Assignatura info;
        Node next;

        Node(Assignatura info) {
            this.info = info;
            this.next = null;
        }

        Node(String nom) {
            this.info = new Assignatura(nom);
            this.next = null;
        }
    }

    private Node cap;

    public Alumnes_SEC(String nom) {
        this.cap = new Node(nom);
    }

    public void addAssignatura(Assignatura nova) {
        Node actual = cap.next;
        Node anterior = cap;

        boolean trobat = false;

        while (actual != null) {
            if (actual.info.equals(nova)) {
                actual.info = nova;
                trobat = true;
                break;
            }
            anterior = actual;
            actual = actual.next;
        }

        if (!trobat) {
            anterior.next = new Node(nova);
        }

        recalculaNotaMitjana();
    }

    public boolean hiHa(int punts) {
        Node actual = cap.next;
        while (actual != null) {
            if (actual.info.getPunts() == punts) {
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    private void recalculaNotaMitjana() {
        Node actual = cap.next;
        double sumaPonderada = 0.0;
        int totalCredits = 0;

        while (actual != null) {
            sumaPonderada += actual.info.getPunts() * actual.info.getCredits();
            totalCredits += actual.info.getCredits();
            actual = actual.next;
        }

        double notaMitjana = totalCredits > 0 ? sumaPonderada / totalCredits : 0.0;
        cap.info.setNota(notaMitjana);
    }

    @Override
    public int compareTo(Alumnes_SEC other) {
        return Double.compare(this.cap.info.getNota(), other.cap.info.getNota());
    }

    @Override
    public String toString() {
        return cap.info.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Alumnes_SEC other = (Alumnes_SEC) obj;
        return Objects.equals(this.cap.info, other.cap.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cap.info);
    }
}
