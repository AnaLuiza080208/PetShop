import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Pessoa dono1 = new Pessoa("Ana Luiza", "987654321");
        Animal animal1 = new Animal("Debbie", "Cachorro", "vira-lata" , "10");

        Pessoa dono2 = new Pessoa("Júlia", "123456789");
        Animal animal2 = new Animal("Sofia", "Gato", "vira-lata", "2");

        List<Agenda> agendamentos = new ArrayList<>();

        String data1Str = "30-08-2025 10:00";
        String data2Str = "30-08-2025 10:00"; 
        String data3Str = "01-08-2025 14:00";

        tentarAgendarConsulta(dono1, animal1, data1Str, Agenda.Servico.VACINACAO, agendamentos, formatter);
        tentarAgendarConsulta(dono2, animal2, data2Str, Agenda.Servico.BANHO_E_TOSA, agendamentos, formatter);
        tentarAgendarConsulta(dono2, animal2, data3Str, Agenda.Servico.DESPARASITACAO, agendamentos, formatter);

        System.out.println("\nConsultas agendadas:");
        for (Agenda agenda : agendamentos) {
            agenda.exibirStatus();
            System.out.println("---------------------");
        }
    }

    public static void tentarAgendarConsulta(Pessoa pessoa, Animal animal, String dataStr, Agenda.Servico servico,
                                              List<Agenda> agendamentos, DateTimeFormatter formatter) {
        try {
            LocalDateTime data = LocalDateTime.parse(dataStr, formatter);
            Agenda.verificarDisponibilidade(agendamentos, data);

            Agenda agenda = new Agenda();
            agenda.setPessoa(pessoa);
            agenda.setAnimal(animal);
            agenda.setServico(servico);
            agenda.setDataConsulta(data);

            agendamentos.add(agenda);
            System.out.println("Consulta agendada com sucesso para " + pessoa.getNome() + " e o animal " + animal.getNome());
        } catch (Exception e) {
            System.out.println("Consulta para " + pessoa.getNome() + " e o animal " + animal.getNome() +
                    " não pode ser agendada: " + e.getMessage());
        }
    }
}
