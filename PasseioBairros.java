
import java.util.*;

public class PasseioBairros {
    public static void main(String[] args) {
        Map<String, List<String>> grafo = new HashMap<>();
        grafo.put("A", Arrays.asList("B", "C"));
        grafo.put("B", Arrays.asList("A", "C", "D"));
        grafo.put("C", Arrays.asList("A", "B", "D"));
        grafo.put("D", Arrays.asList("B", "C"));

        List<String> caminho = new ArrayList<>();
        String origem = "A"; // Defina a origem

        if (resolvePasseio(grafo, caminho, origem)) {
            System.out.println("Passeio completo: " + caminho);
            imprimirMatrizAdjacencia(grafo, caminho);

            int quantidadePassos = caminho.size() - 1; // A quantidade de passos é igual ao tamanho do caminho - 1
            System.out.println("Quantidade total de passos: " + quantidadePassos);
        } else {
            System.out.println("Não foi possível encontrar um passeio completo.");
        }
    }

    public static boolean resolvePasseio(Map<String, List<String>> grafo,
                                         List<String> caminho, String atual) {
        caminho.add(atual);

        if (todosVisitados(grafo.keySet(), caminho)) {
            return true;
        }

        List<String> vizinhos = grafo.get(atual);
        for (String vizinho : vizinhos) {
            if (!caminho.contains(vizinho)) {
                if (resolvePasseio(grafo, caminho, vizinho)) {
                    return true;
                }
                caminho.remove(caminho.size() - 1);
            }
        }

        return false;
    }

    public static boolean todosVisitados(Set<String> vertices, List<String> caminho) {
        return caminho.size() == vertices.size();
    }

    public static void imprimirMatrizAdjacencia(Map<String, List<String>> grafo, List<String> caminho) {
        System.out.println("\nMatriz de Adjacência:");

        for (String origem : grafo.keySet()) {
            for (String destino : grafo.keySet()) {
                if (caminho.contains(origem) && caminho.contains(destino)) {
                    if (grafo.get(origem).contains(destino)) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
}
