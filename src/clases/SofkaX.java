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
        String[] subrutina = {
                "MOV 5,R0",
                "MOV 10,R1",
                "JZ 7",
                "ADD R2,R1",
                "DEC R0",
                "JMP 3",
                "MOV R2,R42"
        };


    }

    // subrutina MOV -> Rxx,Ryy → copia el valor del registro Rxx al registro Ryy;
    private static void mov (String datos){
        var valores = datos.split(",");

        try {
            registros.put(valores[1], Integer.parseInt(valores[0]));
        } catch (NumberFormatException exception) {
            registros.put(valores[1], registros.get(valores[0]));
        }
    }
    // subrutina ADD -> copia la constante numérica d (especificada como un número decimal) al registro Rxx;
    private static void add (String datos){
        var valores = datos.split(",");
        var rxx = registros.get(valores[0]);
        var ryy = registros.get(valores[1]);

        // operación
        registros.put(valores[0], rxx + ryy);
    }

    // subrutina DEC -> disminuye el valor de Rxx en 1. Si el valor del registro es 0, al disminuirlo se genera un desbordamiento y su resultado sería 232–1
    private static void dec (String datos) {
        var registro0 =  registros.get(datos);
        var calculo = (int) Math.pow(2, 32) - 1;

        if (registro0 == 0) {
            registros.put(datos, calculo);
        } else {
            registros.put(datos, registros.get(datos) - 1);
        }
    }



}
