package examen;

import java.util.List;

import org.comviva.jdbc.h2.data.ComvivaJdbc;
import org.comviva.jdbc.h2.domain.Comviva;
import org.comviva.service.CountNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseFile {

	private static Logger logger = LoggerFactory.getLogger(ParseFile.class);
			
	public static void main(String[] args) {
		CountNumber numbers = new CountNumber();
		Comviva conviva = new Comviva();
		long startTime = System.nanoTime();
		long millis_startTime = System.currentTimeMillis();
		conviva=numbers.countNumber(conviva);
		
		long endTime =  (System.nanoTime() - startTime);		
		
		conviva.setProcessData(endTime);
		long millis_endTime = System.currentTimeMillis();
		logger.info("Time en microsegundos (nano) es: {}",endTime/1e7);
		logger.info("Time en Milesegundos: {}",(millis_endTime - millis_startTime));
		logger.info("Se guarda la información de microsegundos por mayor precision");
		ComvivaJdbc comvivaJdbc = new ComvivaJdbc();
		comvivaJdbc.create();
		comvivaJdbc.insert(conviva);
		List<Comviva> comvivas = comvivaJdbc.select();
		
		for (Comviva comviva : comvivas) {
			logger.info("La tabla contiene: {} {}", comviva,"ms");
		}
		
		
	}
}
