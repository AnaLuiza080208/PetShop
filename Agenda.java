import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Agenda {

    private Pessoa pessoa;
    private Animal animal;
    private Servico servico;
    private LocalDateTime dataConsulta;

    public enum Servico {
        BANHO_E_TOSA,
        VACINACAO,
        DESPARASITACAO,
        CONSULTA_VETERINARIA
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public static void verificarDisponibilidade(List<Agenda> agendamentos, LocalDateTime novaData) throws Exception {
        LocalDateTime agora = LocalDateTime.now();

        if (novaData.isBefore(agora)) {
            throw new Exception("Não é possível agendar uma consulta em uma data passada.");
        }

        for (Agenda agenda : agendamentos) {
            if (agenda.getDataConsulta().equals(novaData)) {
                throw new Exception("Já existe uma consulta agendada para essa data e horário.");
            }
        }
    }

    public long calcularDiasRestantes() {
        LocalDateTime agora = LocalDateTime.now();
        return ChronoUnit.DAYS.between(agora, dataConsulta);
    }

    public void exibirStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Dono: " + pessoa.getNome());
        System.out.println("Animal: " + animal.getNome());
        System.out.println("Serviço: " + servico);  
        System.out.println("Data da consulta: " + dataConsulta.format(formatter));
        System.out.println("Faltam "  + calcularDiasRestantes() +  " dias para a consulta");
    }
}
