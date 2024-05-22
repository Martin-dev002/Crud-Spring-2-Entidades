package practica.spring.crudbasico.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import practica.spring.crudbasico.modelos.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {

}
