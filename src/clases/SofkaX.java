package clases;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SofkaX {

    //creación de los 43 registros inicializados en 0.
    private static final Map<String, Integer> registros = IntStream.iterate(0, n -> n + 1).limit(43)
            .boxed() // devuelve un Stream que consta de los elementos de este flujo.
            .collect(Collectors.toMap(i -> "R" + i, i -> 0));

    private static int contador = 0;

    public static void main(String[] args) {


    }


}
