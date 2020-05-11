package org.comviva.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

import org.comviva.interfaces.ParseFile;
import org.comviva.jdbc.h2.domain.Comviva;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountNumber implements ParseFile {
	
	private final Logger logger = LoggerFactory.getLogger(CountNumber.class);


	public Comviva countNumber(Comviva comviva) {
		double countNUmber =0;
		String desplegar ="";
		
		try {
			File fileReader = new File ("..\\examen\\src\\main\\resources\\ejemplo.txt");
			FileReader fileOpen = new FileReader(fileReader);
			
			StreamTokenizer st = new StreamTokenizer(fileOpen);
			
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				if (st.ttype == StreamTokenizer.TT_NUMBER) {
					
					
					desplegar = desplegar.concat((String.valueOf(st.nval)).concat(" + "));
					countNUmber = countNUmber + st.nval;
				}
			}
			desplegar = desplegar.substring(0, desplegar.length() -2);
			desplegar = desplegar.concat("=");
			logger.info("Los numeros hallados son: {} {}",desplegar,countNUmber);
			comviva.setFileName(fileReader.getCanonicalFile().toString());
			comviva.setFileValue(countNUmber);
			comviva.setProcessData(0);
		} catch (IOException e) {
			logger.error("Se ha producido un error: CountNumber::countNumber {}",CountNumber.class,e);
			
		}
		return comviva;
	}

}

 