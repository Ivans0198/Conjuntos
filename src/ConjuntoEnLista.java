public class ConjuntoEnLista<T extends Comparable<T>> {

    private Nodo<T> cabeza;

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    void mostrar() {
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

    //TODO: validar en el insertar que el conjunto no tenga elementos repetidos
    public boolean subConjunto(ConjuntoEnLista<T> B) {
        Nodo<T> p = B.cabeza;
        Nodo<T> q = this.cabeza;
        int aciertos = 0;
        while (p != null) {
            while (q != null) {
                if (p.getDato().equals(q.getDato())) {
                    aciertos++;
                }
                q = q.getLiga();
            }
            p = p.getLiga();
        }
        return aciertos == cantidadElementos(B.cabeza);
    }

    public boolean esvacio() {
        return cabeza == null;
    }

    public ConjuntoEnLista<T> union(ConjuntoEnLista<T> B) {
        Nodo<T> p = cabeza;
        Nodo<T> q = p;
        if (p == null) {
            p = B.cabeza;
        } else {
            while (q.getLiga() != null) {
                q = q.getLiga();
            }
            q.setLiga(B.cabeza);
        }
        ConjuntoEnLista<T> union = new ConjuntoEnLista<>();
        union.setCabeza(p);
        return union;
    }

    public ConjuntoEnLista<T> interseccion(ConjuntoEnLista<T> B) {
        Nodo<T> p = this.cabeza;
        Nodo<T> q = B.cabeza;
        Nodo<T> nuevaCabeza = null;
        Nodo<T> aux = nuevaCabeza;
        while (p != null) {
            while (q != null) {
                if (p.getDato().equals(q.getDato())) {
                    Nodo<T> nuevo = new Nodo<>();
                    nuevo.setDato(p.getDato());
                    if (aux == null) {
                        nuevaCabeza = nuevo;
                        aux = nuevaCabeza;
                    } else {
                        aux.setLiga(nuevo);
                        aux = nuevo;
                    }
                }
                q = q.getLiga();
            }
            p = p.getLiga();
        }
        ConjuntoEnLista<T> interseccion = new ConjuntoEnLista<>();
        interseccion.setCabeza(nuevaCabeza);
        return interseccion;
    }

    public boolean igualdad(ConjuntoEnLista<T> B) {
        int lista1 = cantidadElementos(cabeza);
        int lista2 = cantidadElementos(B.getCabeza());
        if (lista1 != lista2) {
            return false;
        }
        Nodo<T> p1 = cabeza;
        Nodo<T> p2 = B.getCabeza();

        while (p1 != null) {
            boolean existe = false;
            while (p2 != null) {
                if (p1.getDato().equals(p2.getDato())) {
                    existe = true;
                    break;
                }
                p2 = p2.getLiga();
            }
            if (!existe) {
                return false;
            }
            p1 = p1.getLiga();
        }
        return false;
    }

/*
    public ConjuntoEnLista<T> complemento(ConjuntoEnLista<T> A)  {
            Nodo<T> universal = cabeza;

            List<T> complementos = new ArrayList<>();
            conjuntoUniversal:
            for (T universal : vec) {
                for (T conjunto : A.vec) {
                    if (universal.equals(conjunto)) {
                        continue conjuntoUniversal;
                    }
                }
                complementos.add(universal);
            }
    }
*/

    public void agregar(T elemento) {
        Nodo<T> p = cabeza;
        Nodo<T> nuevo = new Nodo<>();
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            while (p.getLiga() != null) {
                if (p.getDato().equals(elemento)) {
                    System.out.println("El dato ya se encuentra registrado");
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

    private ConjuntoEnLista<T> diferencia(ConjuntoEnLista<T> A, ConjuntoEnLista<T> B) {
        Nodo<T> p1 = A.getCabeza();
        Nodo<T> p2 = B.getCabeza();
        Nodo<T> nuevaCabeza = null;
        Nodo<T> aux = nuevaCabeza;

        principal:
        while (p1 != null) {
            while (p2 != null) {
                if (p1.getDato().equals(p2.getDato())) {
                    continue principal;
                }
                p2 = p2.getLiga();
            }
            Nodo<T> nuevo = new Nodo<>();
            nuevo.setDato(p1.getDato());
            if (aux == null) {
                nuevaCabeza = nuevo;
                aux = nuevaCabeza;
            } else {
                aux.setLiga(nuevo);
                aux = nuevo;
            }

            p1 = p1.getLiga();
        }
        ConjuntoEnLista<T> diferencia = new ConjuntoEnLista<>();
        diferencia.setCabeza(nuevaCabeza);
        return diferencia;
    }

    private ConjuntoEnLista<T> diferencia(ConjuntoEnLista<T> B) {
        return diferencia(this, B);
    }

    public ConjuntoEnLista<T> diferenciaSimetrica(ConjuntoEnLista<T> B) {
        ConjuntoEnLista<T> diferencia1 = diferencia(this, B);
        ConjuntoEnLista<T> diferencia2 = diferencia(B, this);
        Nodo<T> nuevaCabeza = diferencia1.cabeza;

        Nodo<T> p = diferencia1.cabeza;
        while (p.getLiga() != null) {
            p = p.getLiga();
        }
        p.setLiga(diferencia2.cabeza);
        ConjuntoEnLista<T> diferenciaSimetrica = new ConjuntoEnLista<>();
        diferenciaSimetrica.setCabeza(nuevaCabeza);
        return diferenciaSimetrica;
    }

    public void vaciar() {
        cabeza = null;
    }
}
