package practica.spring.crudbasico.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practica.spring.crudbasico.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private List<Producto> ListaDeProdcutos = new ArrayList<>();
}
