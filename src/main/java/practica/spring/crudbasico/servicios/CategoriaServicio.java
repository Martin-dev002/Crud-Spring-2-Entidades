package practica.spring.crudbasico.servicios;

import practica.spring.crudbasico.dtos.CategoriaDTO;

import java.util.List;

public interface CategoriaServicio {
    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO obtenerCategoriaPorId(Long id);
    List<CategoriaDTO> obtenerTodasLasCategorias();
    CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO);
    void eliminarCategoria(Long id);
}
