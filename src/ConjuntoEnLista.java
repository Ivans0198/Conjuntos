public class ConjuntoEnLista<T extends Comparable<T>> {

    private Nodo<T> cabeza;

    // METODOS ACCESORES

    public ConjuntoEnLista() {
        this.cabeza = new Nodo<T>();
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    // METODOS PROPUESTOS POR EL PROFESOR

    public void mostrar() {
        Nodo<T> p = cabeza;
        while (p != null) {
            System.out.println(p.getDato());
            p = p.getLiga();
        }
    }

    private int cantidadElementos(Nodo<T> cabeza) {
        Nodo<T> p = cabeza;
        int contador = 0;
        while (p != null) {
            contador++;
            p = p.getLiga();
        }
        return contador;
    }

    public int cantidadElementos() {
        return cantidadElementos(cabeza);
    }

    public boolean pertenece(T elemento) {
        Nodo<T> p = cabeza;
        while (p != null) {
            if (p.getDato().equals(elemento)) {
                return true;
            }
            p = p.getLiga();
        }
        return false;
    }

    public boolean subConjunto(ConjuntoEnLista<T> conjuntoB) {
        Nodo<T> b = conjuntoB.getCabeza();
        Nodo<T> a;
        int aciertos = 0;
        while (b != null) {
            a = cabeza;
            while (a != null) {
                if (b.getDato().equals(a.getDato())) {
                    aciertos++;
                    break;
                }
                a = a.getLiga();
            }
            b = b.getLiga();
        }
        return aciertos == cantidadElementos(conjuntoB.getCabeza());
    }

    public boolean esvacio() {
        return cabeza == null;
    }

    public ConjuntoEnLista<T> union(ConjuntoEnLista<T> conjuntoB) {
        Nodo<T> a = cabeza;
        Nodo<T> b = conjuntoB.getCabeza();
        if (a == null) {
            return conjuntoB;
        }
        ConjuntoEnLista<T> union = new ConjuntoEnLista<>();
        while (a != null) {
            union.agregar(a.getDato());
            a = a.getLiga();
        }
        while (b != null) {
            union.agregar(b.getDato());
            b = b.getLiga();
        }
        return union;
    }

    public ConjuntoEnLista<T> interseccion(ConjuntoEnLista<T> conjuntoEnListaB) {
        Nodo<T> a = cabeza;
        Nodo<T> b;
        ConjuntoEnLista<T> interseccion = new ConjuntoEnLista<>();

        while (a != null) {
            b = conjuntoEnListaB.getCabeza();
            while (b != null) {
                if (a.getDato().equals(b.getDato())) {
                    interseccion.agregar(a.getDato());
                    break;
                }
                b = b.getLiga();
            }
            a = a.getLiga();
        }
        return interseccion;
    }

    public boolean igualdad(ConjuntoEnLista<T> conjuntoEnListaB) {
        int totalElementosA = cantidadElementos(cabeza);
        int totalElementosB = cantidadElementos(conjuntoEnListaB.getCabeza());

        if (totalElementosA != totalElementosB) {
            return false;
        }

        int totalElementosRepetidos = contarElementosRepetidos(this, conjuntoEnListaB);
        return totalElementosRepetidos == totalElementosA;
    }

    private int contarElementosRepetidos(ConjuntoEnLista<T> conjuntoEnListaA, ConjuntoEnLista<T> conjuntoEnListaB) {
        Nodo<T> a = conjuntoEnListaA.getCabeza();
        Nodo<T> b;
        int aciertos = 0;
        while (a != null) {
            b = conjuntoEnListaB.getCabeza();
            while (b != null) {
                if (a.getDato().equals(b.getDato())) {
                    aciertos++;
                    break;
                }
                b = b.getLiga();
            }
            a = a.getLiga();
        }
        return aciertos;
    }

    public ConjuntoEnLista<T> complemento(ConjuntoEnLista<T> conjuntoEnListaB) {
        if (!subConjunto(conjuntoEnListaB)) {
            throw new IllegalArgumentException("El conjunto B no es un subconjunto del universal");
        }
        return diferencia(this, conjuntoEnListaB);
    }

    public void agregar(T elemento) {
        Nodo<T> p = cabeza;
        Nodo<T> nuevo = new Nodo<>();
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            while (p.getLiga() != null) {
                if (p.getDato().equals(elemento)) {
                    return;
                }
                p = p.getLiga();
            }
            p.setLiga(nuevo);
        }
        nuevo.setDato(elemento);
    }

    public void borrar(T elemento) {
        Nodo<T> p = cabeza;
        Nodo<T> aux = p;
        while (p != null) {
            if (p.getDato().equals(elemento)) {
                aux.setLiga(p.getLiga());
                break;
            }
            aux = p;
            p = p.getLiga();
        }
    }

    public Nodo<T> nodo(T elemento) {
        Nodo<T> p = cabeza;
        while (p != null) {
            if (p.getDato().equals(elemento)) {
                return p;
            }
            p = p.getLiga();
        }
        return null;
    }

    public int posicion(T elemento) {
        Nodo<T> p = cabeza;
        int ordenNodo = 0;
        while (p != null) {
            if (p.getDato().equals(elemento)) {
                return ordenNodo;
            }
            ordenNodo++;
            p = p.getLiga();
        }
        return -1;
    }

    private ConjuntoEnLista<T> diferencia(ConjuntoEnLista<T> conjuntoEnListaA, ConjuntoEnLista<T> conjuntoEnListaB) {
        Nodo<T> a = conjuntoEnListaA.getCabeza();
        Nodo<T> b;
        ConjuntoEnLista<T> diferencia = new ConjuntoEnLista<>();

        minuendo:
        while (a != null) {
            b = conjuntoEnListaB.getCabeza();
            while (b != null) {
                if (a.getDato().equals(b.getDato())) {
                    continue minuendo;
                }
                b = b.getLiga();
            }
            diferencia.agregar(a.getDato());
            a = a.getLiga();
        }
        return diferencia;
    }

    public ConjuntoEnLista<T> diferencia(ConjuntoEnLista<T> B) {
        return diferencia(this, B);
    }

    public ConjuntoEnLista<T> diferenciaSimetrica(ConjuntoEnLista<T> conjuntoEnLista) {
        ConjuntoEnLista<T> diferenciaA = diferencia(this, conjuntoEnLista);
        ConjuntoEnLista<T> diferenciaB = diferencia(conjuntoEnLista, this);
        ConjuntoEnLista<T> diferenciaSimetrica = new ConjuntoEnLista<>();

        Nodo<T> a = diferenciaA.getCabeza();
        Nodo<T> b = diferenciaB.getCabeza();

        while (a != null) {
            diferenciaSimetrica.agregar(a.getDato());
            a = a.getLiga();
        }
        while (b != null) {
            diferenciaSimetrica.agregar(b.getDato());
            b = b.getLiga();
        }
        return diferenciaSimetrica;
    }

    public void vaciar() {
        cabeza = null;
    }
}
