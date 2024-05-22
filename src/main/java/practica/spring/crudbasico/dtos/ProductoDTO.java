package practica.spring.crudbasico.dtos;

import lombok.*;
import practica.spring.crudbasico.modelos.Categoria;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Categoria categoria;
}
