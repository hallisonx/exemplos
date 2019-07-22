package estudo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class FormatandoDatas {

	public static void main(String[] args) {
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime entrada;
		LocalDateTime intervalo;
		LocalDateTime retorno;
		LocalDateTime saida;
		LocalTime totalHorasExtras = LocalTime.of(0, 0);
	
		try {
			//Lendo arquivo
			CSVReader csvReader = lerArquivoCSV();

	        List<String[]> registros = csvReader.readAll(); // lê os registros do arquivo
	        
	        for (String[] registro : registros) {
	            imprimirLinhaArquivoCSV(registro); // mostra cada linha do arquivo
	            
	            entrada = LocalDateTime.parse(   registro[0] + " " + registro[1], formatacao ); // converte para LocalDateTime (Ex: 01/06/2019 08:00)
	            intervalo = LocalDateTime.parse( registro[0] + " " + registro[2], formatacao );
	            retorno = LocalDateTime.parse(   registro[0] + " " + registro[3], formatacao );
	            saida = LocalDateTime.parse(     registro[0] + " " + registro[5], formatacao );
	            
	            if(intervalo.getHour() >= 0 && intervalo.getHour() < entrada.getHour()) {
	            	intervalo = intervalo.plusDays(1);
	            	retorno = retorno.plusDays(1);
	            	saida = saida.plusDays(1);
	            }else if(retorno.getHour() >= 0 && retorno.getHour() < entrada.getHour()) {
	            	retorno = retorno.plusDays(1);
	            	saida = saida.plusDays(1);
	            }else if(saida.getHour() >= 0 && saida.getHour() < entrada.getHour()) {
	            	saida = saida.plusDays(1);
	            }
	            
	            //imprimirHorariosComData(entrada, intervalo, retorno, saida);
	            
	            LocalTime tempoTotal = LocalTime.of((int)entrada.until(saida, ChronoUnit.HOURS), (int)entrada.until(saida, ChronoUnit.MINUTES) % 60);
	            Duration duracaoIntervalo = Duration.between(intervalo, retorno);
	            long totalMinutosIntervalo = duracaoIntervalo.toMinutes();
	            LocalTime tempoReal = tempoTotal.minusMinutes(totalMinutosIntervalo);
	            LocalTime horasExtras = LocalTime.of(0, 0);
	            
	            if(tempoReal.getHour() < 8) { // se tempo menor que 8:00 (ex: 7:59)
	            	horasExtras = LocalTime.of(0, 0);
	            }else {
	            	horasExtras = tempoReal.minusHours(8);
	            	totalHorasExtras = calcularHorasExtras(totalHorasExtras, horasExtras);
	            }
	            
	            System.out.println("Tempo total nominal:         " + tempoTotal);
	            System.out.println("Tempo total menos intervalo: " + tempoReal);
	            System.out.println("Horas extras:                " + horasExtras);
	            System.out.println("-------------------------------------------------------------------------------------------");
	    	}
	        
	        
	        System.out.println("=======================================");
	        System.out.println("Total Horas Extras: " + totalHorasExtras);
	        System.out.println("=======================================");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	    
	    
	}

	public static LocalTime calcularHorasExtras(LocalTime totalHorasExtras, LocalTime horasExtras) {
		totalHorasExtras = totalHorasExtras.plusHours(horasExtras.getHour());
		totalHorasExtras = totalHorasExtras.plusMinutes(horasExtras.getMinute());
		return totalHorasExtras;
	}

	public static CSVReader lerArquivoCSV() throws IOException {
		Path caminho = Paths.get(System.getProperty("user.home"), "Documents/Processing/ponto/registros.csv");
		
		Reader reader = Files.newBufferedReader(caminho);
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
		return csvReader;
	}

	public static void imprimirLinhaArquivoCSV(String[] registro) {
		System.out.println("Data : " + registro[0] +
		                " - Entrada : " + registro[1] +
		                " - Intervalo : " + registro[2] +
		                " - Retorno : " + registro[3] +
		                " - Saida : " + registro[5]);
	}

	public static void imprimirHorariosComData(LocalDateTime entrada, LocalDateTime intervalo, LocalDateTime retorno, LocalDateTime saida) {
		System.out.println(entrada);
		System.out.println(intervalo);
		System.out.println(retorno);
		System.out.println(saida);
	}

}
