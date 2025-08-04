package com.alura.pixesoft.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner opcion = new Scanner(System.in);
        Scanner valorAConvertir = new Scanner(System.in);
        Scanner continuar = new Scanner(System.in);
        String textoMoneda = "";
        var op = 0;
        var val = 0.0;
        while(true){
            System.out.println("************************************");
            System.out.println("Bienvenido al Conversor de Monedas");
            System.out.println("1) Dólar ==> Peso Argentino.");
            System.out.println("2) Peso Argentino ==> Dólar.");
            System.out.println("3) Dólar ==> Real Brazileño.");
            System.out.println("4) Real Brazileño ==> Dólar.");
            System.out.println("5) Dólar ==> Peso Colombiano.");
            System.out.println("6) Peso Colombiano ==> Dólar.");
            System.out.println("0) Salir.");
            System.out.println("************************************");
            System.out.println("Elija la conversión que desea realizar: ");
            try{
                op = opcion.nextInt();
            }catch (Exception e){
                System.out.println("NO ES UN TIPO DE DATO VÁLIDO");
                break;
            }

            System.out.println("************************************");
            if(op == 1 || op == 2 || op == 3 || op == 4 || op == 5 || op == 6){
                System.out.println("Agregue valor que desea convertir: ");
                try{
                    val = valorAConvertir.nextDouble();
                }catch (Exception e){
                    System.out.println("NO ES UN TIPO DE DATO VÁLIDO");
                    break;
                }

                System.out.println("************************************");
                Conversor convertir = new Conversor();
                String valor = String.valueOf(convertir.buscarMoneda(op, val));
                if(op == 1){
                    textoMoneda = "(Peso Argentino)";
                } else if (op == 2 || op == 4 || op == 6) {
                    textoMoneda = "(Dolar)";
                } else if (op == 3) {
                    textoMoneda = "(Real brasileño)";
                }else if (op == 5) {
                    textoMoneda = "(Peso Colombiano)";
                }else {
                    break;
                }
                System.out.println("Resultado:" +valor+textoMoneda);
                System.out.println("¿Desea Continuar? SI= 1 NO= 0");
                var cont = continuar.nextInt();
                if (cont == 0){
                    break;
                }
            } else if (op == 0) {
                break;
            }else{
                System.out.println("LA OPCIÓN NO ES VÁLIDA");
            }
        }
        System.out.println("Gracias por usar nuestro servicio, ¡hasta pronto! :)");
    }
}
