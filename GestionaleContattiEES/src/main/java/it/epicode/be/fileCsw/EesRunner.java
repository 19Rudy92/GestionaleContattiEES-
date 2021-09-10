package it.epicode.be.fileCsw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;
import it.epicode.be.repository.ComuneRepository;
import it.epicode.be.repository.ProvinciaRepository;

@Component
public class EesRunner implements CommandLineRunner {

	@Autowired
	private ComuneRepository comuneRepo;

	@Autowired
	private ProvinciaRepository provinciaRepo;

	public void caricaProvincia() throws IOException {

		final String PATH_FILE_CSV = ("C:\\Users\\Martella Rudy\\git\\ProgettoFinale\\GestionaleContattiEES\\FileCsv\\province-italiane.csv");

		FileReader reader = new FileReader(new File(PATH_FILE_CSV));

		CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();

		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();

		List<String[]> dati = csvReader.readAll();

		for (String[] d : dati) {
			Provincia provincia = new Provincia();
			provincia.setSigla(d[0]);
			provincia.setNomeProvincia(d[1]);
			provincia.setRegione(d[2]);
			provinciaRepo.save(provincia);

		}
	}

	public void caricaComune() throws IOException {

		 final String PATH_FILE_CSV = ("C:\\Users\\Martella Rudy\\git\\ProgettoFinale\\GestionaleContattiEES\\FileCsv\\comuni-italiani.csv");

		FileReader reader = new FileReader(new File(PATH_FILE_CSV));

		CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();

		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();

		List<String[]> dati = csvReader.readAll();

		for (String[] d : dati) {
			Comune comune = new Comune();
			comune.setCodiceComune(d[1]);
			comune.setNomeComune(d[2]);
			Provincia p = provinciaRepo.findByNomeProvincia(d[3]);
			comune.setProvincia(p);
			comuneRepo.save(comune);

		}

	}

	@Override
	public void run(String... args) throws Exception {
		//caricaProvincia();

		// caricaComune();
	}

}
