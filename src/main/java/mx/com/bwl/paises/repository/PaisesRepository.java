package mx.com.bwl.paises.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.bwl.paises.model.Pais;

@Repository
public interface PaisesRepository extends JpaRepository<Pais,Integer>{
	public List<Pais> findAllByIdioma(String idioma);
}
