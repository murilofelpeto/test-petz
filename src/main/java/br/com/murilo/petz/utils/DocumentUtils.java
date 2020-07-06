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
