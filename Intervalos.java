import java.util.*;

public class Intervalos {
    static class Interval {
        int s, f, idx;

        Interval(int s, int f, int idx) {
            this.s = s;
            this.f = f;
            this.idx = idx;
        }

        public String toString() {
            return String.format("%d:(%d,%d)", idx, s, f);
        }
    }

    public static void main(String[] args) {
        // Dados de exemplo retirados do README
        int[] s = { 4, 6, 13, 4, 2, 6, 7, 9, 1, 3, 9 };
        int[] f = { 8, 7, 14, 5, 4, 9, 10, 11, 6, 13, 12 };

        if (s.length != f.length) {
            System.err.println("Os arrays de início e término devem ter o mesmo tamanho");
            return;
        }

        int n = s.length;
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++)
            intervals.add(new Interval(s[i], f[i], i + 1));

        // Ordena por tempo de término (crescente). Em caso de empate, por início
        // (quebra-de-empate estável).
        intervals.sort(Comparator.comparingInt((Interval it) -> it.f)
                .thenComparingInt(it -> it.s));

        // Seleção gulosa: seleciona intervalos cujo início é maior que o término
        // do último selecionado (estritamente)
        List<Interval> selected = new ArrayList<>();
        int iterations = 0; // count of condition checks (sk > lastFinish)
        int lastFinish = Integer.MIN_VALUE; // f0 = -infinito (representado por MIN_VALUE)

        for (Interval it : intervals) {
            iterations++; // one check for this candidate
            if (it.s > lastFinish) {
                selected.add(it);
                lastFinish = it.f;
            }
        }

        // Imprime resultados
        System.out.println("Intervalos de entrada (índiceOriginal:(s,f)) em ordem arbitrária:");
        for (int i = 0; i < n; i++)
            System.out.printf("%d:(%d,%d) ", i + 1, s[i], f[i]);
        System.out.println("\n");

        System.out.println("Intervalos selecionados (em ordem crescente de término):");
        for (Interval it : selected)
            System.out.print(it + " ");
        System.out.println();

        // Vetor característico X (1 se selecionado, 0 caso contrário) na ordem do
        // índice original
        int[] X = new int[n];
        for (Interval it : selected)
            X[it.idx - 1] = 1;
        System.out.print("X (vetor característico) ");
        for (int i = 0; i < n; i++)
            System.out.print(X[i] + (i + 1 < n ? " " : ""));
        System.out.println();

        System.out.println("Quantidade selecionada: " + selected.size());
        System.out.println("Iterações (checagens de condição): " + iterations);
    }
}
