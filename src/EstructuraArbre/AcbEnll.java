package src.EstructuraArbre;

import java.util.LinkedList;
import java.util.Queue;

public class AcbEnll<E extends Comparable<E>> implements Acb<E> {

    private class NodeA implements Cloneable {
        E element;
        NodeA esquerre;
        NodeA dret;

        NodeA(E element) {
            this.element = element;
            this.esquerre = null;
            this.dret = null;
        }

        @Override
        protected NodeA clone() throws CloneNotSupportedException {
            NodeA clon = new NodeA(this.element);
            if (this.esquerre != null) clon.esquerre = this.esquerre.clone();
            if (this.dret != null) clon.dret = this.dret.clone();
            return clon;
        }
    }

    private NodeA arrel;

    public AcbEnll() {
        this.arrel = null;
    }

    public AcbEnll(NodeA arrel) {
        this.arrel = arrel;
    }

    @Override
    public void inserir(E element) throws ArbreException {
        if (arrel == null) {
            arrel = new NodeA(element);
        } else {
            inserirRecursive(arrel, element);
        }
    }

    private void inserirRecursive(NodeA node, E element) throws ArbreException {
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            throw new ArbreException("L'element ja existeix a l'arbre");
        } else if (cmp < 0) {
            if (node.esquerre == null) {
                node.esquerre = new NodeA(element);
            } else {
                inserirRecursive(node.esquerre, element);
            }
        } else {
            if (node.dret == null) {
                node.dret = new NodeA(element);
            } else {
                inserirRecursive(node.dret, element);
            }
        }
    }

    @Override
    public void esborrar(E element) throws ArbreException {
        if (arrel == null) {
            throw new ArbreException("L'arbre és buit");
        }
        arrel = esborrarRecursive(arrel, element);
    }

    private NodeA esborrarRecursive(NodeA node, E element) throws ArbreException {
        if (node == null) {
            throw new ArbreException("L'element no es troba a l'arbre");
        }

        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.esquerre = esborrarRecursive(node.esquerre, element);
        } else if (cmp > 0) {
            node.dret = esborrarRecursive(node.dret, element);
        } else {
            if (node.esquerre == null) return node.dret;
            if (node.dret == null) return node.esquerre;
            NodeA minNode = esborrarMinim(node.dret);
            node.element = minNode.element;
            node.dret = esborrarRecursive(node.dret, minNode.element);
        }
        return node;
    }

    private NodeA esborrarMinim(NodeA node) {
        while (node.esquerre != null) {
            node = node.esquerre;
        }
        return node;
    }

    @Override
    public boolean membre(E element) {
        return membreRecursive(arrel, element);
    }

    private boolean membreRecursive(NodeA node, E element) {
        if (node == null) return false;

        int cmp = element.compareTo(node.element);
        if (cmp == 0) return true;
        return cmp < 0 ? membreRecursive(node.esquerre, element) : membreRecursive(node.dret, element);
    }

    @Override
    public E arrel() throws ArbreException {
        if (arrel == null) {
            throw new ArbreException("L'arbre és buit");
        }
        return arrel.element;
    }

    @Override
    public Acb<E> fillEsquerre() throws CloneNotSupportedException {
        if (arrel == null || arrel.esquerre == null) return new AcbEnll<>();
        return new AcbEnll<>(arrel.esquerre.clone());
    }

    @Override
    public Acb<E> fillDret() throws CloneNotSupportedException {
        if (arrel == null || arrel.dret == null) return new AcbEnll<>();
        return new AcbEnll<>(arrel.dret.clone());
    }

    @Override
    public boolean arbreBuit() {
        return arrel == null;
    }

    @Override
    public void buidar() {
        arrel = null;
    }

    public Queue<E> getAscendentList() {
        Queue<E> result = new LinkedList<>();
        omplirInOrdre(arrel, result);
        return result;
    }

    private void omplirInOrdre(NodeA node, Queue<E> result) {
        if (node != null) {
            omplirInOrdre(node.esquerre, result);
            result.add(node.element);
            omplirInOrdre(node.dret, result);
        }
    }

    public Queue<E> getDescendentList() {
        Queue<E> result = new LinkedList<>();
        omplirReverseInOrdre(arrel, result);
        return result;
    }

    private void omplirReverseInOrdre(NodeA node, Queue<E> result) {
        if (node != null) {
            omplirReverseInOrdre(node.dret, result);
            result.add(node.element);
            omplirReverseInOrdre(node.esquerre, result);
        }
    }

    public int cardinalitat() {
        return cardinalitatRecursive(arrel);
    }

    private int cardinalitatRecursive(NodeA node) {
        if (node == null) return 0;
        return 1 + cardinalitatRecursive(node.esquerre) + cardinalitatRecursive(node.dret);
    }
}
