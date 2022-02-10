package clases;

public class Metodos {
    public static void main(String[] args) {
        int[] array = new int[43];
        int posicion1=0, posicion2=0, d=0;
        String sub= "", x[], t[], f, h;


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
            f=subrutina[0];
            x=f.split(" ");
            h=x[1];
            t=h.split(",");
            if(t[0].contains("r")){

            }


        switch (x[0]) {
            case "MOV":
                MOV(array, posicion1, posicion2);
                break;
            case "MOV2":
                MOV2(array, posicion1, d);
                break;
        }

      }
    }

    public void DEC(int arrayBin[], int posicion) {//Disminuir
        double valor = Math.pow(2, 32) - 1;
        if (arrayBin[posicion] == 0) {
            arrayBin[posicion] = (int) valor;
        } else {
            arrayBin[posicion] -= 1;
        }
    }

    public void INC(int arrayBin[], int posicion) { //incrementar

        if (arrayBin[posicion] == Math.pow(2, 32) - 1) {
            arrayBin[posicion] = 0;
        } else {
            arrayBin[posicion] += 1;
        }
    }

    public void INV(int arrayBin[], int posicion) {
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

    public void ADD(int arrayBin[], int rxx, int ryy) {
        arrayBin[rxx] = (int) ((rxx + ryy) % Math.pow(2, 32));
    }

    public void jmp() {

    }

    public void jz() {

    }

    public void NOP() {
        System.out.println("Yo no hago nada :P");
    }
}


