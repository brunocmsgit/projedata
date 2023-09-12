import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        inserirFuncionarios(funcionarios);

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        /** 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
            • informação de data deve ser exibido no formato dd/mm/aaaa;
            • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.*/
        funcionarios.forEach(System.out::println);

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(new BigDecimal("0.10")));

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários por Função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        LocalDate dataAtual = LocalDate.now();
        Funcionario funcionarioMaiorIdade = funcionarios.stream()
                .max(Comparator.comparing(f ->
                        Period.between(f.getDataNascimento(), dataAtual).getYears()))
                .orElse(null);

        if (funcionarioMaiorIdade != null) {
            int idade = Period.between(funcionarioMaiorIdade.getDataNascimento(), dataAtual).getYears();
            System.out.println("\nFuncionário com maior idade: Nome: " +
                    funcionarioMaiorIdade.getNome() + ", Idade: " + idade + " anos");
        }

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.forEach(System.out::println);

        // 3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal dos Salários dos Funcionários: R$" +
                new DecimalFormat("#,##0.00").format(totalSalarios));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212);
        System.out.println("\nSalários Mínimos por Funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioEmSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_DOWN);
            System.out.println("Nome: " + funcionario.getNome() + ", Salários Mínimos: " +
                    new DecimalFormat("#,##0.00").format(salarioEmSalariosMinimos));
        });
    }

    private static void inserirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.add(Funcionario.builder().nome("Maria").dataNascimento(LocalDate.of(2000, 10, 18)).salario(BigDecimal.valueOf(2009.44)).funcao("Operador").build());
        funcionarios.add(Funcionario.builder().nome("João").dataNascimento(LocalDate.of(1990, 5, 12)).salario(BigDecimal.valueOf(2284.38)).funcao("Operador").build());
        funcionarios.add(Funcionario.builder().nome("Caio").dataNascimento(LocalDate.of(1961, 5, 2)).salario(BigDecimal.valueOf(9836.14)).funcao("Coordenador").build());
        funcionarios.add(Funcionario.builder().nome("Miguel").dataNascimento(LocalDate.of(1988, 10, 14)).salario(BigDecimal.valueOf(19119.88)).funcao("Diretor").build());
        funcionarios.add(Funcionario.builder().nome("Alice").dataNascimento(LocalDate.of(1995, 1, 5)).salario(BigDecimal.valueOf(2234.68)).funcao("Recepcionista").build());
        funcionarios.add(Funcionario.builder().nome("Heitor").dataNascimento(LocalDate.of(1999, 11, 19)).salario(BigDecimal.valueOf(1582.72)).funcao("Operador").build());
        funcionarios.add(Funcionario.builder().nome("Arthur").dataNascimento(LocalDate.of(1993, 3, 31)).salario(BigDecimal.valueOf(4071.84)).funcao("Contador").build());
        funcionarios.add(Funcionario.builder().nome("Laura").dataNascimento(LocalDate.of(1994, 7, 8)).salario(BigDecimal.valueOf(3017.45)).funcao("Gerente").build());
        funcionarios.add(Funcionario.builder().nome("Heloísa").dataNascimento(LocalDate.of(2003, 5, 24)).salario(BigDecimal.valueOf(1606.85)).funcao("Eletricista").build());
        funcionarios.add(Funcionario.builder().nome("Helena").dataNascimento(LocalDate.of(1996, 9, 2)).salario(BigDecimal.valueOf(2799.93)).funcao("Gerente").build());
    }
}