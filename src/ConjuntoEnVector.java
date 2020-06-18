import java.lang.reflect.Array;

public class ConjuntoEnVector<T> {

    private int n;
    private T[] vec;
    private final Class<T> clazz;

    public ConjuntoEnVector(Class<T> clazz, int n) {
        this.clazz = clazz;
        this.n = n;
        this.vec = (T[]) Array.newInstance(clazz, n);
    }

    // METODOS ACCESORES

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public T[] getVec() {
        return vec;
    }

    public void setVec(T[] vec) {
        this.vec = vec;
    }

    // METODOS PROPUESTOS POR EL PROFESOR

    public void mostrar() {
        System.out.println();
        for (T t : vec) {
            System.out.print(t + "\t");
        }
    }

    public T getDato(int pos) {
        return vec[pos];
    }

    public void setDato(T dato) {
        agregar(dato);
    }

    public int cantidadElementos() {
        return vec.length;
    }

    public boolean pertenece(T elemento) {
        for (T t : vec) {
            if (t.equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public boolean subConjunto(ConjuntoEnVector<T> subconjunto) {
        T[] vecSubconjunto = subconjunto.getVec();
        int aciertos = contarElementosRepetidos(vec, vecSubconjunto);
        return aciertos == vecSubconjunto.length;
    }

    public boolean esVacio() {
        return vec.length == 0;
    }

    public ConjuntoEnVector<T> union(ConjuntoEnVector<T> conjuntoB) {
        T[] vectorA = vec;
        T[] vectorB = conjuntoB.getVec();

        if (vectorB.length == 0) {
            return this;
        }

        int tamanoVecA = vectorA.length;
        int tamanoVecB = vectorB.length;
        int tamanoVecUnion;
        int aciertos = contarElementosRepetidos(vectorA, vectorB);

        tamanoVecUnion = tamanoVecA - aciertos + tamanoVecB;
        ConjuntoEnVector<T> union = new ConjuntoEnVector<>(clazz, tamanoVecUnion);

        for (T itemA : vectorA) {
            union.agregar(itemA);
        }
        for (T itemB : vectorB) {
            union.agregar(itemB);
        }
        return union;
    }

    private int contarElementosRepetidos(T[] vectorA, T[] vectorB) {
        int aciertos = 0;
        for (T itemB : vectorB) {
            for (T itemA : vectorA) {
                if (itemB.equals(itemA)) {
                    aciertos++;
                }
            }
        }
        return aciertos;
    }

    public ConjuntoEnVector<T> interseccion(ConjuntoEnVector<T> conjuntoEnVectorB) {
        T[] vectorA = vec;
        T[] vectorB = conjuntoEnVectorB.getVec();
        int aciertos = contarElementosRepetidos(vectorA, vectorB);
        ConjuntoEnVector<T> interseccion = new ConjuntoEnVector<>(clazz, aciertos);

        for (T itemB : vectorB) {
            for (T itemA : vectorA) {
                if (itemB.equals(itemA)) {
                    interseccion.agregar(itemB);
                }
            }
        }
        return interseccion;
    }

    public boolean igualdad(ConjuntoEnVector<T> conjuntoEnVectorB) {
        T[] vectorA = vec;
        T[] vectorB = conjuntoEnVectorB.getVec();
        if (vectorA.length != vectorB.length) {
            return false;
        }
        int aciertos = contarElementosRepetidos(vectorA, vectorB);
        return aciertos == vectorA.length;
    }

    public ConjuntoEnVector<T> complemento(ConjuntoEnVector<T> conjuntoEnVectorB) {
        if (!subConjunto(conjuntoEnVectorB)) {
            throw new IllegalArgumentException("El vector B no es un subconjunto del universal");
        }
        return diferencia(this, conjuntoEnVectorB);
    }

    //TODO probar cuando no hayan espacios disponibles
    public void agregar(T elemento) {
        boolean yaExiste = false;
        for (int i = 0; i < vec.length; i++) {
            if (elemento.equals(vec[i])) {
                yaExiste = true;
                break;
            }
            if (vec[i] == null) {
                vec[i] = elemento;
                return;
            }
        }
        if (!yaExiste) {
            ConjuntoEnVector<T> nuevoConjuntoVector = new ConjuntoEnVector<>(clazz, 1);
            nuevoConjuntoVector.setDato(elemento);
            ConjuntoEnVector<T> union = union(nuevoConjuntoVector);
            vec = union.getVec();
        }
    }

    public void borrar(T elemento) {
        if (vec.length > 0) {
            boolean existeDatoAEliminar = false;
            for (T item : vec) {
                if (item.equals(elemento)) {
                    existeDatoAEliminar = true;
                    break;
                }
            }
            if (existeDatoAEliminar) {
                ConjuntoEnVector<T> conjuntoEnVector = new ConjuntoEnVector<>(this.clazz, 1);
                conjuntoEnVector.setDato(elemento);
                ConjuntoEnVector<T> diferencia = diferencia(this, conjuntoEnVector);
                vec = diferencia.getVec();
            }
        }
    }

    public int posicion(T elemento) {
        for (int i = 0; i < vec.length; i++) {
            if (vec[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    private ConjuntoEnVector<T> diferencia(ConjuntoEnVector<T> conjuntoEnVectorA, ConjuntoEnVector<T> conjuntoEnVectorB) {
        T[] vectorA = conjuntoEnVectorA.getVec();
        T[] vectorB = conjuntoEnVectorB.getVec();
        int aciertos = contarElementosRepetidos(vectorA, vectorB);
        int tamano = vectorA.length - aciertos;
        ConjuntoEnVector<T> diferencia = new ConjuntoEnVector<>(clazz, tamano);

        minuendo:
        for (T itemA : vectorA) {
            for (T itemB : vectorB) {
                if (itemB.equals(itemA)) {
                    continue minuendo;
                }
            }
            diferencia.agregar(itemA);
        }
        return diferencia;
    }

    public ConjuntoEnVector<T> diferencia(ConjuntoEnVector<T> conjuntoEnVectorB) {
        return diferencia(this, conjuntoEnVectorB);
    }

    public ConjuntoEnVector<T> diferenciaSimetrica(ConjuntoEnVector<T> conjuntoEnVectorB) {
        ConjuntoEnVector<T> diferenciaA = diferencia(this, conjuntoEnVectorB);
        ConjuntoEnVector<T> diferenciaB = diferencia(conjuntoEnVectorB, this);

        int lengthA = diferenciaA.getVec().length;
        int lengthB = diferenciaB.getVec().length;
        int tamanoDiferenciaSimetrica = lengthA + lengthB;

        ConjuntoEnVector<T> diferencia = new ConjuntoEnVector<>(clazz, tamanoDiferenciaSimetrica);
        for (T itemA : diferenciaA.getVec()) {
            diferencia.agregar(itemA);
        }
        for (T itemB : diferenciaB.getVec()) {
            diferencia.agregar(itemB);
        }
        return diferencia;
    }

    public void vaciar() {
        vec = (T[]) Array.newInstance(clazz, 0);
    }
}
