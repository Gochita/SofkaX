package clases;

public class Metodos {
    public static void main(String[] args) {
        int[] array = new int[43];
        int posicion1 = 0, posicion2 = 0, d = 0, cont=0;
        String sub = "", x[], t[], c[], f, h;


        String subrutina[] = {
                "MOV 5,R00",
                "MOV 10,R01",
                "JZ 7",
                "ADD R02,R01",
                "DEC R00",
                "ADD R02,R01",
                "DEC R00",
                "JMP 3",
                "MOV R02 ,R42",
        };

        for (int i = 0; i < subrutina.length; i++) {

            int q;
            f = subrutina[0];
            x = f.split(" ");
            h = x[1];
            t = h.split(",");
            if (t[0].contains("R")) {
                f = t[0];
                c = f.split("R");
                f = c[1];
                posicion1 = Integer.valueOf(f);

            } else {
                f = t[0];
                d = Integer.valueOf(f);
            }
            if (t[1] != null) {
                f = t[1];
                c = f.split("R");
                f = c[1];
                posicion2 = Integer.valueOf(f);
            }


            switch (x[0]) {
                case "MOV":
                    MOV(array, posicion1, posicion2);
                    cont++;
                    break;
                case "MOV2":
                    MOV2(array, posicion1, d);
                    cont++;
                    break;

                case "ADD":
                    ADD(array, posicion1, posicion2);
                    cont++;
                    break;

                case "DEC":
                    DEC(array,posicion1);
                    cont++;
                    break;
                case "INC":
                    INC(array,posicion1);
                    cont++;
                    break;
                case "INV":
                    INV(array,posicion1);
                    cont++;
                    break;
                case "JMP":
                    q=JMP(cont,d);
                    cont++;
                    if(q!=0){
                        i=q;
                    }
                    break;
                case "JZ":
                    q=JZ(array, cont, d);
                    cont++;
                    if(q!=0){
                        i=q;
                    }
                    break;
                default:
                    System.out.println("Algo pasó D:");
                    break;

            }
           if(cont == 1024){
               System.out.println("Ya no se puede más :P");
                break;
           }
        }
        System.out.println("El valor del registro 43 es: "+array[42]);
    }

    public static void DEC(int arrayBin[], int posicion) {
        double valor = Math.pow(2, 32) - 1;
        if (arrayBin[posicion] == 0) {
            arrayBin[posicion] = (int) valor;
        } else {
            arrayBin[posicion] -= 1;
        }
    }

    public static void INC(int arrayBin[], int posicion) {

        if (arrayBin[posicion] == Math.pow(2, 32) - 1) {
            arrayBin[posicion] = 0;
        } else {
            arrayBin[posicion] += 1;
        }
    }

    public static void INV(int arrayBin[], int posicion) {
        int b = Integer.reverse(arrayBin[posicion]);
        arrayBin[posicion] = b;
    }

    public static void MOV(int R[], int posicion1, int posicion2) {
        R[posicion2] = R[posicion1];
        System.out.println(R[posicion2]);
    }

    public static void MOV2(int R[], int d, int posicion2) {
        R[posicion2] = d;
        System.out.println(R[posicion2]);
    }

    public static void ADD(int arrayBin[], int rxx, int ryy) {
        arrayBin[rxx] = (int) ((rxx + ryy) % Math.pow(2, 32));
    }

    public static int JMP(int x, int d) {
        if (d < x) {
            return d;
        } else {
            System.out.println("la instruccion deseada no es correcta");
        }
        return 0;
    }


        public static int JZ ( int arraybin[], int x, int d){
            if (d < x && arraybin[0] == 0) {
                return d;
            } else {
                System.out.println("la instruccion deseada no es correcta o el registro 00 no es igual a 0");
            }
            return 0;
        }

        public static void NOP () {
            System.out.println("Yo no hago nada :P");
        }


    }
