package practica.spring.crudbasico.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.spring.crudbasico.dtos.CategoriaDTO;
import practica.spring.crudbasico.excepciones.CreacionFallida;
import practica.spring.crudbasico.excepciones.RecursoNoEncontrado;
import practica.spring.crudbasico.modelos.Categoria;
import practica.spring.crudbasico.repositorios.CategoriaRepositorio;
import practica.spring.crudbasico.servicios.CategoriaServicio;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getNombre() == null || categoriaDTO.getNombre().trim().isBlank()) {
            throw new CreacionFallida("El nombre de la categoría no puede estar vacío");
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria = categoriaRepositorio.save(categoria);

        CategoriaDTO nuevaCategoriaDTO = new CategoriaDTO();
        nuevaCategoriaDTO.setId(categoria.getId());
        nuevaCategoriaDTO.setNombre(categoria.getNombre());

        return nuevaCategoriaDTO;
    }
    @Override
    public CategoriaDTO obtenerCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElseThrow(()->
                new RecursoNoEncontrado("La categoría con el id: " + id + " no existe"));

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setListaDeProdcutos(categoria.getListaDeProdcutos());

        return categoriaDTO;
    }
    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        return categorias.stream()
                .map(categoria -> {
                    CategoriaDTO categoriaDTO = new CategoriaDTO();
                    categoriaDTO.setId(categoria.getId());
                    categoriaDTO.setNombre(categoria.getNombre());
                    categoriaDTO.setListaDeProdcutos(categoria.getListaDeProdcutos());
                    return categoriaDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepositorio.findById(id).orElseThrow(()->
                new RecursoNoEncontrado("La categoría con el id: " + id + " no existe"));
        if (categoriaDTO.getNombre() == null || categoriaDTO.getNombre().trim().isBlank()) {
            throw new CreacionFallida("El nombre de la categoría no puede estar vacío");
        }
        categoria.setNombre(categoriaDTO.getNombre());

        categoria = categoriaRepositorio.save(categoria);

        CategoriaDTO categoriaActualizadaDTO = new CategoriaDTO();
        categoriaActualizadaDTO.setId(categoria.getId());
        categoriaActualizadaDTO.setNombre(categoria.getNombre());

        return categoriaActualizadaDTO;
    }
    @Override
    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElseThrow(()->
                new RecursoNoEncontrado("La categoría con el id: " + id + " no existe"));
        categoriaRepositorio.deleteById(id);
    }
}
