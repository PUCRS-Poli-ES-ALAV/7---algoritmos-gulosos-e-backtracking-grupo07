/*Suponha que tenhamos disponíveis moedas com certos valores (por exemplo, de 100, 25, 10, 5 e 1). 
O problema do troco consiste criar um algoritmo que para conseguir obter um determinado valor com o menor número de moedas 
possível. Por exemplo, para “dar um troco” de R$2,89, a melhor solução, isto é, o menor número de moedas possível para obter 
o valor consiste em 10 moedas: 2 de valor 100, 3 de valor 25, 1 de valor 10 e 4 de valor 1.

Objetivo: contrua um algorítmo que recebe a lista das moedas disponíveis e um valor, 
e retorna uma lista com a menor quantidade de moedas para este troco;
Defina uma assinatura adequada para este método;
Utiliza uma abordagem gulosa (se puder);
Contabilize e exiba o número de iterações para cada caso de teste;
O exercício pode ser feito em grupos de um, dois ou três elementos.*/

public class Troco {
    public static void main (String args[]) {
        int[] moedas = {100, 25, 10, 5, 1};
        int valor = 289;
        calcularTroco(moedas, valor);
    }

    public static void calcularTroco(int[] moedas, int valor) {
        int[] resultado = new int[moedas.length];
        int iteracoes = 0;

        for (int i = 0; i < moedas.length; i++) {
            while (valor >= moedas[i]) {
                valor -= moedas[i];
                resultado[i]++;
                iteracoes++;
            }
        }

        System.out.println("Moedas utilizadas:");
        for (int i = 0; i < moedas.length; i++) {
            if (resultado[i] > 0) {
                System.out.println(resultado[i] + " moeda(s) de valor " + moedas[i]);
            }
        }
        System.out.println("Número de iterações: " + iteracoes);
        
    }
}
