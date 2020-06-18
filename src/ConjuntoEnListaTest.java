import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConjuntoEnListaTest {

    private ConjuntoEnLista<String> universal;

    @Before
    public void crearConjunto() {
        universal = new ConjuntoEnLista<>();
        universal.agregar("A");
        universal.agregar("B");
        universal.agregar("C");
        universal.agregar("D");
        universal.agregar("E");
        universal.agregar("F");
        universal.agregar("G");
        universal.agregar("H");
        universal.agregar("I");
        universal.agregar("J");
        universal.agregar("K");
        universal.agregar("L");
        universal.mostrar();
    }

    @Test
    public void debeInsertarDatosAlConjuntoSinRepetirse() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("A");
        conjunto.agregar("A");
        conjunto.agregar("A");
        conjunto.agregar("A");
        conjunto.mostrar();
    }


    @Test
    public void debeRetornarLaCantidadElementosDelConjunto() {
        Assert.assertEquals(universal.cantidadElementos(), 12);
    }

    @Test
    public void debeRetornarVerdaderoSiElementoPerteneceAlConjunto() {
        boolean elemento1 = universal.pertenece("Z");
        boolean elemento2 = universal.pertenece("C");
        boolean elemento3 = universal.pertenece("J");

        assertFalse(elemento1);
        assertTrue(elemento2);
        assertTrue(elemento3);
    }

    @Test
    public void debeRetornarVerdaderoSiEsUnSubConjunto() {
        ConjuntoEnLista<String> subConjunto = new ConjuntoEnLista<>();
        subConjunto.agregar("A");
        subConjunto.agregar("N");
        subConjunto.agregar("F");
        subConjunto.agregar("C");
        subConjunto.agregar("T");
        subConjunto.agregar("I");

        boolean esSubConjuntoA = universal.subConjunto(subConjunto);
        assertFalse(esSubConjuntoA);

        subConjunto.borrar("N");
        subConjunto.borrar("T");

        boolean esSubConjuntoB = universal.subConjunto(subConjunto);
        assertTrue(esSubConjuntoB);
    }

    @Test
    public void debeRetornarFalsoSiElConjuntoNoEsvacio() {
        assertFalse(universal.esvacio());
    }

    @Test
    public void debeCrearUnConjuntoUnion() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("U");
        conjunto.agregar("V");
        conjunto.agregar("W");
        conjunto.agregar("X");
        conjunto.agregar("Y");
        conjunto.agregar("Z");
        ConjuntoEnLista<String> union = universal.union(conjunto);
        union.mostrar();
    }

    @Test
    public void debeCrearUnConjuntoInterseccion() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("A");
        conjunto.agregar("B");
        conjunto.agregar("C");
        conjunto.agregar("X");
        conjunto.agregar("Y");
        conjunto.agregar("Z");
        ConjuntoEnLista<String> interseccion = universal.interseccion(conjunto);
        interseccion.mostrar();
    }

    @Test
    public void debeRetornarVerdaderoSiElConjuntoEsIgualAlUniversal() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("A");
        conjunto.agregar("B");
        conjunto.agregar("C");
        conjunto.agregar("D");
        conjunto.agregar("E");
        conjunto.agregar("F");
        conjunto.agregar("G");
        conjunto.agregar("H");
        conjunto.agregar("I");
        conjunto.agregar("J");
        conjunto.agregar("K");
        conjunto.agregar("L");
        boolean esIgual = universal.igualdad(conjunto);
        assertTrue(esIgual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void debeLanzarExcepcionSiNoEsSubConjuntoDelUniversalParaElComplemento() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("A");
        conjunto.agregar("B");
        conjunto.agregar("C");
        conjunto.agregar("X");
        conjunto.agregar("Y");
        conjunto.agregar("Z");
        ConjuntoEnLista<String> complemento = universal.complemento(conjunto);
        complemento.mostrar();
    }

    @Test()
    public void debeCrearUnConjuntoComplemento() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("A");
        conjunto.agregar("B");
        conjunto.agregar("C");
        conjunto.agregar("D");
        ConjuntoEnLista<String> complemento = universal.complemento(conjunto);
        complemento.mostrar();
    }

    @Test
    public void debeAgregarUnElementoAlConjunto() {
        universal.agregar("A");
        universal.mostrar();
        Assert.assertEquals(universal.cantidadElementos(), 12);
    }

    @Test
    public void debeBorrarUnElementoDelConjunto() {
        universal.borrar("A");
        universal.borrar("F");
        universal.borrar("L");
        universal.mostrar();
        Assert.assertEquals(universal.cantidadElementos(), 9);
    }

    @Test
    public void debeRetornarLaPosicionDelDato() {
        int posicion = universal.posicion("B");
        assertEquals(posicion, 1);
    }

    @Test
    public void debeCrearConjuntoDiferencia() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("A");
        conjunto.agregar("B");
        conjunto.agregar("C");
        conjunto.agregar("L");
        conjunto.agregar("O");
        conjunto.agregar("P");
        conjunto.agregar("Q");
        conjunto.agregar("R");

        ConjuntoEnLista<String> diferencia = universal.diferencia(conjunto);
        diferencia.mostrar();
    }

    @Test
    public void debeCrearConjuntoDiferenciaSimetrica() {
        ConjuntoEnLista<String> conjunto = new ConjuntoEnLista<>();
        conjunto.agregar("M");
        conjunto.agregar("N");
        conjunto.agregar("P");
        conjunto.agregar("X");
        conjunto.agregar("Y");
        conjunto.agregar("A");
        conjunto.agregar("Z");

        ConjuntoEnLista<String> diferenciaSimetrica = universal.diferenciaSimetrica(conjunto);
        diferenciaSimetrica.mostrar();
    }

    @Test
    public void debeVaciarLosElementosDelConjunto() {
        universal.vaciar();

        int elementos = universal.cantidadElementos();
        boolean esvacio = universal.esvacio();

        assertTrue(esvacio);
        assertEquals(elementos, 0);
    }
}