package subnautica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import subnautica.model.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByOrderByNomeAsc();
    java.util.Optional<Categoria> findByCodigo(String codigo);
}
