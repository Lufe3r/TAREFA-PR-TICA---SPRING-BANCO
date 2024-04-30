package ConsumoApi.apiCep;

import ConsumoApi.apiCep.model.Endereco;
import ConsumoApi.apiCep.model.NovoEndereco;
import ConsumoApi.apiCep.repository.NovoEnderecoRepository;
import ConsumoApi.apiCep.service.ConsomeApi;
import ConsumoApi.apiCep.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ApiCepApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiCepApplication.class, args);
	}

	@Autowired
	private NovoEnderecoRepository repositorio;

	@Override
	public void run(String... args) throws Exception {
		ConsomeApi consomeApi = new ConsomeApi();
		ConverteDados convert = new ConverteDados();
		Scanner ler = new Scanner(System.in);
		int op = 0;

		System.out.println("\n\nSeja bem-vindo a ViaCep\n");

		do {

			System.out.println("Qual ação você deseja realizar?\n"
					+ "1 - Mostrar todos os endereços\n"
					+ "2 - Buscar e Registrar um novo CEP\n"
					+ "3 - Sair da aplicação\n");
			op = ler.nextInt();

			switch (op) {
				case 1:
					System.out.println("\n");

					List<NovoEndereco> enderecos = repositorio.findAll();
					enderecos.forEach(System.out::println);

					System.out.println("\n");
					break;
				case 2:
					System.out.print("\nDigite o CEP que deseja buscar (sem pontos e traços): ");
					String cep = ler.next();

					String json = consomeApi.retornaJson("https://viacep.com.br/ws/" + cep + "/json/");

					Endereco endereco = convert.enviaDados(json, Endereco.class);
					NovoEndereco end = new NovoEndereco(endereco);

					try {
						repositorio.save(end);
					} catch (Exception e) {
						break;
					}
					System.out.println("\n" + repositorio.findById(end.getId()) + "\n");
					break;
				case 3:
					System.out.println("Obrigado por utilizar nossa API!\n");
					break;
				default:
					System.out.println("Digite uma opção válida.\n");
					break;
			}
		} while (op < 3);
	}
}
