package src.EstructuraArbre;

public interface Acb<E extends Comparable<E>> {
    void inserir(E element) throws ArbreException; // Insereix un element a l’arbre. Si l’element ja existeix, llança
                                                   // una excepció ArbreException.

    void esborrar(E element) throws ArbreException; // Esborra un element de l’arbre. Llança una excepció si l’arbre és
                                                    // buit o si l’element no es troba a l’arbre.

    boolean membre(E element); // true si l’element està a l’arbre, fals en cas contrari

    E arrel() throws ArbreException; // Si no és buit, retorna el contingut de l’arrel, en cas contrari llança una
                                     // excepció ArbreException

    Acb<E> fillEsquerre() throws CloneNotSupportedException; // retorna una còpia del subarbre esquerre en cas que
                                                             // l’arbre estigui buit o si no té fill esquerre retorna un
                                                             // arbre buit.

    Acb<E> fillDret() throws CloneNotSupportedException; // retorna una còpia del subarbre dret. en cas que l’arbre
                                                         // estigui buit o si no té fill dret retorna un arbre buit.

    boolean arbreBuit(); // Retorna true si l’arbre és buit.

    void buidar(); // Neteja l’arbre deixant-lo buit.
}