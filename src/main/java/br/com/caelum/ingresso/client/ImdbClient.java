package br.com.caelum.ingresso.client;

import java.net.URI;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {

	private Logger log = Logger.getLogger(this.getClass());
	
	public <T>Optional<T> request(Filme filme, Class<T> cLass){
		RestTemplate client = new RestTemplate();
		try{
			URI u =  new URI("https://imdb-fj22.herokuapp.com/imdb?title=" + filme.getNome().replace(" ", "+"));
			System.err.println(u.toURL());
			return Optional.of(client.getForObject(u, cLass));
		}catch(Exception e){
			log.error(e.getMessage(), e);
			return Optional.empty();
		}
	}
	
}
