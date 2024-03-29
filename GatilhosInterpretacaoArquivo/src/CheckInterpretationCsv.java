import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckInterpretationCsv{

    public static void fileInterpretation(Exception ExceptionCollumNotFound, Exception ExceptionEmptyLine, Exception ExceptionDuplicateLine, Exception ExceptionInterpretation ){


        ArrayList<String> cidade = new ArrayList<>();
        Map<String, Integer> temperatura = new HashMap<>();
        Map<String, Integer> umidade = new HashMap<>();
        Map<String, Integer> mapaCidade = new HashMap<>();
        List<String> colunasEsperadas = Arrays.asList("cidade", "temperatura", "umidade");

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

        //linha vaiz
        for (int i = 0; i < cidade.size(); i++) {
            if (cidade.get(i).isEmpty()) {
                try {
                    throw new ExceptionEmptyLine(i + 1);
                } catch (ExceptionEmptyLine e) {
                    System.out.println("Linha vazia na linha: " + e.getLinha());
                }
            }
        }

        
        //Linha duplicada
        for (int i = 0; i < cidade.size(); i++) {
            String linha = cidade.get(i);
            if (mapaCidade.containsKey(linha)) {
                try {
                    throw new ExceptionDuplicateLine(i + 1, mapaCidade.get(linha) + 1);
                } catch (ExceptionDuplicateLine e) {
                    System.out.println("Informação duplicada na linha: " + e.getLinha() + " (primeira ocorrência na linha: " + e.getLinhaPrimeiroIncidente() + ")");
                }
            } else {
                mapaCidade.put(linha, i);
            }
        }

        
        //Verificador de coluna
        for (int i = 0; i < colunasEsperadas.size(); i++) {
            List<String> colunasAusentes = new ArrayList<>();
            for (String colunaEsperada : colunasEsperadas) {
                if (!mapaCidade.containsKey(colunaEsperada)) {
                    colunasAusentes.add(colunaEsperada);
                }
            }
            if (!colunasAusentes.isEmpty()) {
                try {
                    throw new ExceptionColumnNotFound(i + 1, colunasAusentes);
                } catch (ExceptionColumnNotFound e) {
                    System.out.println("Colunas ausentes na linha " + e.getLinha() + ": " + e.getColunasNaoEncontradas());
                }
            }
        }


        

    }
}