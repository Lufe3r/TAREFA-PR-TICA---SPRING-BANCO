package ConsumoApi.apiCep.repository;

import ConsumoApi.apiCep.model.NovoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovoEnderecoRepository extends JpaRepository<NovoEndereco, Long> {
}
