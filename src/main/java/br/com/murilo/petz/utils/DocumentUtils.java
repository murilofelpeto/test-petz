package br.com.murilo.petz.utils;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import org.apache.commons.lang3.StringUtils;

public class DocumentUtils {

    public static String removerPontuacao(String documento) {
        String tipoDocumento = descobrirTipoDocumento(documento);
        Formatter formatador = null;
        switch (tipoDocumento) {
            case "CPF":
                formatador = new CPFFormatter();
                return formatador.unformat(documento);
            case "CNPJ":
                formatador = new CNPJFormatter();
                return formatador.unformat(documento);
            default:
                return null;
        }
    }

    public static String colocarPontuacao(String documento) {
        String tipoDocumento = descobrirTipoDocumento(documento);
        Formatter formatador = null;
        switch (tipoDocumento) {
            case "CPF":
                formatador = new CPFFormatter();
                return formatador.format(documento);
            case "CNPJ":
                formatador = new CNPJFormatter();
                return formatador.format(documento);
            default:
                return null;
        }
    }

    public static boolean validarCPF(String documento) {
        if (documento.length() != 11) {
            return false;
        }

        String numDig = documento.substring(0, 9);
        return calcularDigito(numDig).equals(documento.substring(9, 11));
    }

    private static String calcularDigito(String documento) {
        Integer primeiroDigito, segundoDigito;
        int soma = 0;
        int peso = 10;

        for(int i = 0; i < documento.length(); i++) {
            soma += Integer.parseInt(documento.substring(i, i + 1)) * peso--;
        }

        if(soma % 11 == 0 || soma % 11 == 1) {
            primeiroDigito = 0;
        }else {
            primeiroDigito = 11 - (soma % 11);
        }

        soma = 0;
        peso = 11;

        for(int i = 0; i < documento.length(); i++) {
            soma += Integer.parseInt(documento.substring(i, i + 1)) * peso--;
        }
        soma += primeiroDigito.intValue() * 2;

        if(soma % 11 == 0 || soma % 11 == 1) {
            segundoDigito = 0;
        }else {
            segundoDigito = 11 - (soma % 11);
        }

        return primeiroDigito.toString() + segundoDigito.toString();
    }

    private static String descobrirTipoDocumento(String documento) {
        Integer documentSize = documento.length();
        boolean temPontuacao = StringUtils.contains(documento, "-");

        if (documentSize == 11 || (temPontuacao && documentSize == 14)) {
            return "CPF";
        } else if (documentSize == 18 || documentSize == 14) {
            return "CNPJ";
        }
        return null;
    }
}
