package practica.spring.crudbasico.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.spring.crudbasico.dtos.ProductoDTO;
import practica.spring.crudbasico.excepciones.CreacionFallida;
import practica.spring.crudbasico.excepciones.RecursoNoEncontrado;
import practica.spring.crudbasico.modelos.Categoria;
import practica.spring.crudbasico.modelos.Producto;
import practica.spring.crudbasico.repositorios.CategoriaRepositorio;
import practica.spring.crudbasico.repositorios.ProductoRepositorio;
import practica.spring.crudbasico.servicios.ProductoServicio;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServicoImpl implements ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        if (productoDTO.getNombre() == null || productoDTO.getPrecio() == null || productoDTO.getNombre().trim().isBlank()
                || productoDTO.getPrecio().compareTo(BigDecimal.ZERO) < 0 || productoDTO.getCategoria()==null) {
            throw new CreacionFallida("Algun campo es incorrecto, o vacio");
        }
        Categoria categoria = categoriaRepositorio.findById(productoDTO.getCategoria().getId())
                .orElseThrow(() -> new RecursoNoEncontrado("La categoría con el id: " + productoDTO.getCategoria().getId() + " no existe"));

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);

        producto = productoRepositorio.save(producto);

        ProductoDTO nuevoProductoDTO = new ProductoDTO();
        nuevoProductoDTO.setId(producto.getId());
        nuevoProductoDTO.setNombre(producto.getNombre());
        nuevoProductoDTO.setPrecio(producto.getPrecio());
        nuevoProductoDTO.setCategoria(producto.getCategoria());

        return nuevoProductoDTO;
    }
    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepositorio.findById(id).orElseThrow(()->
                new RecursoNoEncontrado("El producto con el id: " + id + " No existe"));

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setCategoria(producto.getCategoria());
        return productoDTO;
    }
    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepositorio.findAll();
        return productos.stream()
                .map(producto -> {
                    ProductoDTO productoDTO = new ProductoDTO();
                    productoDTO.setId(producto.getId());
                    productoDTO.setNombre(producto.getNombre());
                    productoDTO.setPrecio(producto.getPrecio());
                    productoDTO.setCategoria(producto.getCategoria());
                    return productoDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepositorio.findById(id).orElseThrow(()->
                new RecursoNoEncontrado("El producto con el id: " + id + " No existe"));
        if (productoDTO.getNombre() == null || productoDTO.getPrecio() == null || productoDTO.getNombre().trim().isBlank()
                || productoDTO.getPrecio().compareTo(BigDecimal.ZERO) < 0 || productoDTO.getCategoria() == null) {
            throw new CreacionFallida("Algun campo es incorrecto, o vacio");
        }
        Categoria categoria = categoriaRepositorio.findById(productoDTO.getCategoria().getId())
                .orElseThrow(() -> new RecursoNoEncontrado("La categoría con el id: " + productoDTO.getCategoria().getId() + " no existe"));

        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(categoria);
        producto = productoRepositorio.save(producto);

        ProductoDTO productoActualizadoDTO = new ProductoDTO();
        productoActualizadoDTO.setId(producto.getId());
        productoActualizadoDTO.setNombre(producto.getNombre());
        productoActualizadoDTO.setPrecio(producto.getPrecio());
        productoActualizadoDTO.setCategoria(producto.getCategoria());

        return productoActualizadoDTO;
    }
    @Override
    public void eliminarProducto(Long id) {
        Producto producto = productoRepositorio.findById(id).orElseThrow(()->
                new RecursoNoEncontrado("El producto con el id: " + id + " No existe"));
        productoRepositorio.deleteById(id);
    }
}
