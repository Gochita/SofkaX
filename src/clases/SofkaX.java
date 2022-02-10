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
        ejecutarCase(subrutina,0);


    }
    private static void ejecutarCase(String[] subrutinas, int index) {
        for (int i = index; i < subrutinas.length; i++) {
            //subrutina >= 1024
            if (contador >= 1024) {
                break;
            } else {

                var option = subrutinas[i].split(" ");
                System.out.println(option[0]);

                switch (option[0]) {
                    case "MOV" -> {
                        SofkaX.mov(option[1]);
                        contador++;
                        break;
                    }

                    case "ADD" -> {
                        SofkaX.add(option[1]);
                        contador++;
                        break;
                    }

                    case "DEC" -> {
                        SofkaX.dec(option[1]);
                        contador++;
                        break;
                    }

                    case "INC" -> {
                        SofkaX.inc(option[1]);
                        contador++;
                        break;
                    }

                    case "INV" -> {
                        SofkaX.inv(option[1]);
                        contador++;
                        break;
                    }

                    case "JMP" -> {
                        SofkaX.jmp(subrutinas, option[1]);
                        break;
                    }

                    case "JZ" -> {
                        SofkaX.jz(subrutinas, option[1]);
                        break;

                    }

                    case "NOP" -> {
                        contador++;
                        break;
                    }
                }

            }
        }
    }

    // subrutina MOV -> Rxx,Ryy → copia el valor del registro Rxx al registro Ryy;
    private static void mov(String datos) {
        var valores = datos.split(",");

        try {
            registros.put(valores[1], Integer.parseInt(valores[0]));
        } catch (NumberFormatException exception) {
            registros.put(valores[1], registros.get(valores[0]));
        }
    }

    // subrutina ADD -> copia la constante numérica d (especificada como un número decimal) al registro Rxx;
    private static void add(String datos) {
        var valores = datos.split(",");
        var rxx = registros.get(valores[0]);
        var ryy = registros.get(valores[1]);

        // operación
        registros.put(valores[0], rxx + ryy);
    }

    // subrutina DEC -> disminuye el valor de Rxx en 1. Si el valor del registro es 0, al disminuirlo se genera un desbordamiento y su resultado sería 232–1
    private static void dec(String datos) {
        var registro0 = registros.get(datos);
        var calculo = (int) Math.pow(2, 32) - 1;

        if (registro0 == 0) {
            registros.put(datos, calculo);
        } else {
            registros.put(datos, registros.get(datos) - 1);
        }
    }

    // subrutina INC ->  aumenta el valor de Rxx en 1. Si el valor del registro es 232–1, al aumentarlo se genera un desbordamiento obteniendo por resultado 0
    private static void inc(String datos) {
        var registro0 = registros.get(datos);
        var calculo = (int) Math.pow(2, 32) - 1;

        if (registro0 == calculo) {
            registros.put(datos, 0);
        } else {
            registros.put(datos, registros.get(datos) + 1);
        }
    }


    // subrutina INV ->  ejecuta una inversión bit a bit del registro Rxx (convierte 1 en 0 y 0 en 1)
    private static void inv(String datos) {
        var invertido = registros.get(datos);
        registros.put(datos, Integer.reverse(invertido));
    }
    // subrutina JMP -> salta incondicionalmente a la instrucción número d (basado en 1). Se garantiza que d es un número de instrucción válido
    private static void jmp(String[] subrutina, String dato) {
        contador++;
        ejecutarCase(subrutina, Integer.parseInt(dato) - 1);
    }
    // subrutina JZ ->  salta a la instrucción d (basado en 1) sólo si el registro R00 contiene 0
    private static void jz(String[] subrutina, String dato){
        var registro0 =  registros.get("R0");

        if (registro0 == 0) {
            contador++;
            ejecutarCase(subrutina, Integer.parseInt(dato) - 1);
        }
    }
    // subrutina NOP -> no hace nada
    private static void nop() {
        System.out.println("No hago nada :P");
    }

}
