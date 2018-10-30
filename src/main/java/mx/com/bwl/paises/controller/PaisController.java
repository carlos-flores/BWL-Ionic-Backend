package mx.com.bwl.paises.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.bwl.paises.exception.ResourceNotFoundException;
import mx.com.bwl.paises.model.Pais;
import mx.com.bwl.paises.repository.PaisesRepository;

@RestController
@RequestMapping("/api")
public class PaisController {
	@Autowired
	PaisesRepository paisREPO;

	@GetMapping("/paises")
	public List<Pais> getAllPaises() {
	    return paisREPO.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:8100")
	@GetMapping("/paises/idioma/{idioma}")
	public List<Pais> getAllPaisesByIdioma(@PathVariable(value = "idioma") String idioma) {
	    return paisREPO.findAllByIdioma(idioma);
	}
	
	@PostMapping("/paises")
	public Pais createPais(@Valid @RequestBody Pais pais) {
	    return paisREPO.save(pais);
	}
	
	@GetMapping("/paises/{id}")
	public Pais getPaiById(@PathVariable(value = "id") Integer paisId) {
	    return paisREPO.findById(paisId).orElseThrow(() -> new ResourceNotFoundException("Pais", "id", paisId));
	}
	
	@PutMapping("/paises/{id}")
	public Pais updateNote(@PathVariable(value = "id") Integer paisId,  @Valid @RequestBody Pais paisDetails) {

	    Pais pais = paisREPO.findById(paisId).orElseThrow(() -> new ResourceNotFoundException("Pais", "id", paisId));

	    pais.setPais(paisDetails.getPais());
	    pais.setIdioma(paisDetails.getIdioma());

	    Pais updatedPais = paisREPO.save(pais);
	    return updatedPais;
	}

	@DeleteMapping("/paises/{id}")
	public ResponseEntity<?> deletePais(@PathVariable(value = "id") Integer paisId) {
	    Pais pais = paisREPO.findById(paisId).orElseThrow(() -> new ResourceNotFoundException("Pais", "id", paisId));

	    paisREPO.delete(pais);

	    return ResponseEntity.ok().build();
	}

}

