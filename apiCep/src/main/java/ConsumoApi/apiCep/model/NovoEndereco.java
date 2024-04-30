package ConsumoApi.apiCep.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import jakarta.persistence.*;

@Entity
@Table(name = "enderecos")
public class NovoEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;

    @Column(name = "uf")
    private String estado;

    public NovoEndereco(Endereco endereco) {
        this.cep = endereco.cep();
        this.rua = endereco.rua();
        this.bairro = endereco.bairro();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
    }

    public NovoEndereco(){}


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
}
