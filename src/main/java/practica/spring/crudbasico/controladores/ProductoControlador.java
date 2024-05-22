package practica.spring.crudbasico.controladores;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practica.spring.crudbasico.dtos.ProductoDTO;
import practica.spring.crudbasico.servicios.ProductoServicio;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    ProductoServicio productoServicio;

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto( @RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProductoDTO = productoServicio.crearProducto(productoDTO);
        return new ResponseEntity<>(nuevoProductoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        ProductoDTO productoDTO = productoServicio.obtenerProductoPorId(id);
            return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
        ProductoDTO productoActualizadoDTO = productoServicio.actualizarProducto(id, productoDTO);
        return new ResponseEntity<>(productoActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProduto(@PathVariable Long id){
        productoServicio.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    private ResponseEntity<List<ProductoDTO>> buscarTodos() {
        List<ProductoDTO> productos = productoServicio.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
