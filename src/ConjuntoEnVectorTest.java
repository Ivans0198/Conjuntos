import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConjuntoEnVectorTest {

    private ConjuntoEnVector<Integer> universal;

    @Before
    public void crearConjunto() {
        universal = new ConjuntoEnVector<>(Integer.class, 10);
        universal.agregar(0);
        universal.agregar(1);
        universal.agregar(2);
        universal.agregar(3);
        universal.agregar(4);
        universal.agregar(5);
        universal.agregar(6);
        universal.agregar(7);
        universal.agregar(8);
        universal.agregar(9);
        universal.mostrar();
    }

    @Test
    public void debeInsertarDatosAlConjuntoSinRepetirse() {
        ConjuntoEnVector<String> conjunto = new ConjuntoEnVector<>(String.class, 4);
        conjunto.agregar("A");
        conjunto.agregar("A");
        conjunto.agregar("A");
        conjunto.agregar("B");
        conjunto.agregar("C");
        conjunto.agregar("D");
        conjunto.mostrar();
    }

    @Test
    public void debeRetornarLaCantidadElementosDelConjunto() {
        Assert.assertEquals(universal.cantidadElementos(), 10);
    }

    @Test
    public void debeRetornarVerdaderoSiElementoPerteneceAlConjunto() {
        boolean elemento1 = universal.pertenece(10);
        boolean elemento2 = universal.pertenece(3);
        boolean elemento3 = universal.pertenece(5);

        assertFalse(elemento1);
        assertTrue(elemento2);
        assertTrue(elemento3);
    }

    @Test
    public void debeRetornarVerdaderoSiEsUnSubConjunto() {
        ConjuntoEnVector<Integer> subConjunto = new ConjuntoEnVector<>(Integer.class, 4);
        subConjunto.agregar(1);
        subConjunto.agregar(2);
        subConjunto.agregar(3);
        subConjunto.agregar(4);

        boolean esSubConjuntoA = universal.subConjunto(subConjunto);
        assertTrue(esSubConjuntoA);

        subConjunto.agregar(20);

        boolean esSubConjuntoB = universal.subConjunto(subConjunto);
        assertFalse(esSubConjuntoB);
    }

    @Test
    public void debeRetornarFalsoSiElConjuntoNoEsvacio() {
        assertFalse(universal.esVacio());
    }

    @Test
    public void debeCrearUnConjuntoUnion() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 6);
        conjunto.agregar(11);
        conjunto.agregar(12);
        conjunto.agregar(13);
        conjunto.agregar(14);
        conjunto.agregar(15);
        conjunto.agregar(16);

        ConjuntoEnVector<Integer> union = universal.union(conjunto);
        union.mostrar();
    }

    @Test
    public void debeCrearUnConjuntoInterseccion() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 5);
        conjunto.agregar(7);
        conjunto.agregar(8);
        conjunto.agregar(9);
        conjunto.agregar(10);
        conjunto.agregar(11);
        ConjuntoEnVector<Integer> interseccion = universal.interseccion(conjunto);
        interseccion.mostrar();
    }

    @Test
    public void debeRetornarVerdaderoSiElConjuntoEsIgualAlUniversal() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 10);
        conjunto.agregar(0);
        conjunto.agregar(1);
        conjunto.agregar(2);
        conjunto.agregar(3);
        conjunto.agregar(4);
        conjunto.agregar(5);
        conjunto.agregar(6);
        conjunto.agregar(7);
        conjunto.agregar(8);
        conjunto.agregar(9);

        boolean esIgual = universal.igualdad(conjunto);
        assertTrue(esIgual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void debeLanzarExcepcionSiNoEsSubConjuntoDelUniversalParaElComplemento() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 10);
        conjunto.agregar(1);
        conjunto.agregar(2);
        conjunto.agregar(3);
        conjunto.agregar(4);
        conjunto.agregar(5);
        conjunto.agregar(15);
        conjunto.agregar(16);
        conjunto.agregar(17);
        conjunto.agregar(18);
        conjunto.agregar(19);

        ConjuntoEnVector<Integer> complemento = universal.complemento(conjunto);
        complemento.mostrar();
    }

    @Test
    public void debeCrearUnConjuntoComplemento() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 5);
        conjunto.agregar(1);
        conjunto.agregar(2);
        conjunto.agregar(3);
        conjunto.agregar(4);
        conjunto.agregar(5);

        ConjuntoEnVector<Integer> complemento = universal.complemento(conjunto);
        complemento.mostrar();
    }

    @Test
    public void debeAgregarUnElementoAlConjunto() {
        universal.agregar(5);
        universal.agregar(50);
        universal.mostrar();
        Assert.assertEquals(universal.cantidadElementos(), 11);
    }

    @Test
    public void debeBorrarUnElementoDelConjunto() {
        universal.borrar(6);
        universal.mostrar();
        Assert.assertEquals(universal.cantidadElementos(), 9);
    }

    @Test
    public void debeRetornarLaPosicionDelDato() {
        int posicion = universal.posicion(5);
        assertEquals(posicion, 5);
    }

    @Test
    public void debeCrearConjuntoDiferencia() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 7);
        conjunto.agregar(1);
        conjunto.agregar(3);
        conjunto.agregar(5);
        conjunto.agregar(7);
        conjunto.agregar(9);
        conjunto.agregar(11);
        conjunto.agregar(13);
        conjunto.agregar(15);

        ConjuntoEnVector<Integer> diferencia = universal.diferencia(conjunto);
        diferencia.mostrar();
    }

    @Test
    public void debeCrearConjuntoDiferenciaSimetrica() {
        ConjuntoEnVector<Integer> conjunto = new ConjuntoEnVector<>(Integer.class, 6);
        conjunto.agregar(2);
        conjunto.agregar(4);
        conjunto.agregar(6);
        conjunto.agregar(8);
        conjunto.agregar(10);
        conjunto.agregar(12);
        conjunto.agregar(14);

        ConjuntoEnVector<Integer> diferenciaSimetrica = universal.diferenciaSimetrica(conjunto);
        diferenciaSimetrica.mostrar();
    }

    @Test
    public void debeVaciarLosElementosDelConjunto() {
        universal.vaciar();

        int elementos = universal.cantidadElementos();
        boolean esvacio = universal.esVacio();

        assertTrue(esvacio);
        assertEquals(elementos, 0);
    }
}