package subnautica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import subnautica.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByNomeAsc();
    List<Item> findByCategoria_CodigoOrderByNomeAsc(String codigo);
    boolean existsByNomeIgnoreCaseAndCategoria_Id(String nome, Long categoriaId);
    boolean existsByNomeIgnoreCaseAndCategoria_IdAndIdNot(String nome, Long categoriaId, Long id);
}
