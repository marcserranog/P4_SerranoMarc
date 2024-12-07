package src;

import java.util.Queue;
import java.util.Scanner;

import src.Alumnes.Alumnes_SEC;
import src.Alumnes.Assignatura;
import src.EstructuraArbre.AcbEnll;

public class Beca {

    private static Scanner scanner = new Scanner(System.in);

    private AcbEnll<Alumnes_SEC> arbreACB;
    private Queue<Alumnes_SEC> llistaDescendent;

    public Beca() {
        arbreACB = new AcbEnll<>();

        try {
            arbreACB.inserir(exempleRosa());
            arbreACB.inserir(exempleEnric());
            arbreACB.inserir(exempleRandom("Anna"));
            arbreACB.inserir(exempleRandom("Jordi"));
            arbreACB.inserir(exempleRandom("Pere"));

            llistaDescendent = arbreACB.getDescendentList();
        } catch (Exception e) {
            System.err.println("Error inicialitzant exemples: " + e.getMessage());
        }
    }

    private Alumnes_SEC exempleRosa() {
        Alumnes_SEC rosa = new Alumnes_SEC("Rosa");
        rosa.addAssignatura(new Assignatura("Matemàtiques", 6, 9.5, false));
        rosa.addAssignatura(new Assignatura("Física", 4, 8.0, true));
        return rosa;
    }

    private Alumnes_SEC exempleEnric() {
        Alumnes_SEC enric = new Alumnes_SEC("Enric");
        enric.addAssignatura(new Assignatura("Química", 5, 7.5, false));
        enric.addAssignatura(new Assignatura("Biologia", 3, 8.0, false));
        return enric;
    }

    private Alumnes_SEC exempleRandom(String nom) {
        Alumnes_SEC alumne = new Alumnes_SEC(nom);
        alumne.addAssignatura(new Assignatura("Assignatura1", 5, Math.random() * 10, false));
        alumne.addAssignatura(new Assignatura("Assignatura2", 3, Math.random() * 10, true));
        alumne.addAssignatura(new Assignatura("Assignatura3", 4, Math.random() * 10, false));
        alumne.addAssignatura(new Assignatura("Assignatura4", 6, Math.random() * 10, false));
        return alumne;
    }

    private boolean finalRecorregut() {
        return llistaDescendent.isEmpty();
    }

    private Alumnes_SEC segRecorregut() {
        return llistaDescendent.poll();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        llistaDescendent = arbreACB.getDescendentList();
        while (!finalRecorregut()) {
            result.append(segRecorregut()).append("\n");
        }
        return result.toString();
    }

    public void esborraAlumnesSenseMatricula() {
        try {
            Queue<Alumnes_SEC> alumnes = arbreACB.getAscendentList();
            for (Alumnes_SEC alumne : alumnes) {
                if (!alumne.hiHa(10)) { // Comprova si l'alumne té alguna matrícula d'honor (nota = 10)
                    arbreACB.esborrar(alumne);
                }
            }
        } catch (Exception e) {
            System.err.println("Error esborrant alumnes: " + e.getMessage());
        }
    }

    public void afegirAlumne() {
        try {
            System.out.print("Nom de l'alumne: ");
            String nom = scanner.nextLine();
            Alumnes_SEC alumne = new Alumnes_SEC(nom);

            boolean continuar = true;
            while (continuar) {
                System.out.print("Nom de l'assignatura: ");
                String assignaturaNom = scanner.nextLine();

                System.out.print("Crèdits: ");
                int credits = scanner.nextInt();

                System.out.print("Nota: ");
                double nota = scanner.nextDouble();

                System.out.print("Té matrícula d'honor? (true/false): ");
                boolean honor = scanner.nextBoolean();

                alumne.addAssignatura(new Assignatura(assignaturaNom, credits, nota, honor));

                System.out.print("Vols afegir més assignatures? (true/false): ");
                continuar = scanner.nextBoolean();
                scanner.nextLine(); // Neteja el buffer
            }

            arbreACB.inserir(alumne);
            llistaDescendent = arbreACB.getDescendentList();
        } catch (Exception e) {
            System.err.println("Error afegint alumne: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Beca beca = new Beca();
        boolean sortir = false;

        while (!sortir) {
            System.out.println("\nMenú:");
            System.out.println("1. Afegir un nou alumne");
            System.out.println("2. Esborrar un alumne");
            System.out.println("3. Mostrar tots els alumnes en ordre descendent");
            System.out.println("4. Esborrar alumnes sense matrícula d'honor");
            System.out.println("5. Sortir");
            System.out.print("Selecciona una opció: ");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Neteja el buffer

            switch (opcio) {
                case 1:
                    beca.afegirAlumne();
                    break;
                case 2:
                    System.out.print("Nom de l'alumne a esborrar: ");
                    String nom = scanner.nextLine();
                    try {
                        Queue<Alumnes_SEC> alumnes = beca.arbreACB.getAscendentList();
                        for (Alumnes_SEC alumne : alumnes) {
                            if (alumne.toString().contains(nom)) {
                                beca.arbreACB.esborrar(alumne);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error esborrant alumne: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println(beca);
                    break;
                case 4:
                    beca.esborraAlumnesSenseMatricula();
                    break;
                case 5:
                    sortir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna a intentar-ho.");
            }
        }
        scanner.close();
    }
}
