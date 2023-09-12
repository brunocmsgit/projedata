import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Data
@SuperBuilder
public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public BigDecimal getSalario() {
        return salario;
    }

    public void aumentarSalario(BigDecimal percentualAumento) {
        salario = salario.add(salario.multiply(percentualAumento));
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return super.toString() + ", Salário: R$" + decimalFormat.format(salario) + ", Função: " + funcao;
    }

}