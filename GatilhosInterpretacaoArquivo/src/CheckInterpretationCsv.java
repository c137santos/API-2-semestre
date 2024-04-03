import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckInterpretationCsv {

    public static void main(String[] args) {

        ArrayList<String> cidade = new ArrayList<>();
        Map<String, Integer> temperatura = new HashMap<>();
        Map<String, Integer> umidade = new HashMap<>();
        Map<String, Integer> mapaCidade = new HashMap<>();
        List<String> colunasEsperadas = Arrays.asList("cidade", "cidade", "umidade");

        cidade.add("São Paulo");
        cidade.add("São José dos Campos");
        cidade.add("Campos do Jordão");
        cidade.add("tres");
        cidade.add("");
        cidade.add("Cotia");
        cidade.add("São Paulo");
        cidade.add("São Paulo");

        temperatura.put("São Paulo", 25);
        temperatura.put("São José dos Campos", 23);
        temperatura.put("Campos do Jordão", 18);
        temperatura.put("Cotia", 24);

        umidade.put("São Paulo", 70);
        umidade.put("São José dos Campos", 65);
        umidade.put("Campos do Jordão", 80);
        umidade.put("Cotia", 75);

        // Verificação de linha vazia
        boolean linhaVaziaEncontrada = false;
        for (int i = 0; i < cidade.size(); i++) {
            if (cidade.get(i).isEmpty()) {
                linhaVaziaEncontrada = true;
                System.out.println("Linha vazia na linha: " + (i + 1));
            }
        }

        // Verificação de linha duplicada
        boolean linhaDuplicadaEncontrada = false;
        for (int i = 0; i < cidade.size(); i++) {
            String linha = cidade.get(i);
            if (mapaCidade.containsKey(linha)) {
                linhaDuplicadaEncontrada = true;
                System.out.println("Informação duplicada na linha: " + (i + 1) + " (primeira ocorrência na linha: " + (mapaCidade.get(linha) + 1) + ")");
            } else {
                mapaCidade.put(linha, i);
            }
        }

        // Verificação de coluna ausente
        boolean colunaAusenteEncontrada = false;
        for (int i = 0; i < colunasEsperadas.size(); i++) {
            if (!mapaCidade.containsKey(colunasEsperadas.get(i))) {
                colunaAusenteEncontrada = true;
                System.out.println("Coluna ausente: " + colunasEsperadas.get(i));
            }
        }

        // Impressão dos dados
        if (!linhaVaziaEncontrada && !linhaDuplicadaEncontrada && !colunaAusenteEncontrada) {
            for (int i = 0; i < cidade.size(); i++) {
                System.out.println("Cidade: " + cidade.get(i) + ", Temperatura: " + temperatura.get(cidade.get(i)) + "°C, Umidade: " + umidade.get(cidade.get(i)) + "%");
            }
        }
    }
}
