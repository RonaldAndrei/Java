package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.UF;

public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFldCpf, txtFldCnpj, txtFldIE;

    @FXML
    private ComboBox cmbUF;

    private String est;

    private List<UF> uf = new ArrayList<>();

    @FXML
    private void btnLimparClick(ActionEvent event) {
        txtFldCpf.setText("");
        txtFldCnpj.setText("");
        txtFldIE.setText("");
        txtFldCpf.getStyleClass().remove("error");
        txtFldCnpj.getStyleClass().remove("error");
        txtFldIE.getStyleClass().remove("error");
        Platform.runLater(() -> txtFldIE.requestFocus());
    }

    //Validacao CPF
    private boolean isCpf(String cpf) {

        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999") || cpf.length() != 11) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, num, peso = 10;
            try {
                for (i = 0; i < 9; i++) {
                    num = (int) (cpf.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }
                if (dig != cpf.charAt(9)) {
                    return false;
                } else {
                    peso = 11;
                    soma = 0;

                    for (i = 0; i < 10; i++) {
                        num = (int) (cpf.charAt(i) - 48);
                        soma = soma + (num * peso);
                        peso--;
                    }
                    r = 11 - (soma % 11);
                    if (r >= 10) {
                        dig = '0';
                    } else {
                        dig = (char) (r + 48);
                    }
                    if (dig != cpf.charAt(10)) {
                        return false;
                    } else {
                        return true;
                    }
                }

            } catch (InputMismatchException erro) {
                return false;
            }

        }

    }

    //Validacao CNPJ
    private boolean isCnpj(String cnpj) {

        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || cnpj.length() != 14) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, num, peso = 5;

            try {
                for (i = 0; i < 12; i++) {
                    num = (int) (cnpj.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                    if (peso == 1) {
                        peso = 9;
                    }
                }
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != cnpj.charAt(12)) {
                    return false;
                } else {
                    peso = 6;
                    soma = 0;

                    for (i = 0; i < 13; i++) {
                        num = (int) (cnpj.charAt(i) - 48);
                        soma = soma + (num * peso);
                        peso--;
                        if (peso == 1) {
                            peso = 9;
                        }
                    }
                    r = 11 - (soma % 11);
                    if (r >= 10) {
                        dig = '0';
                    } else {
                        dig = (char) (r + 48);
                    }
                    if (dig != cnpj.charAt(13)) {
                        return false;
                    } else {
                        return true;
                    }
                }

            } catch (InputMismatchException erro) {
                return false;
            }

        }

    }

    // Validacao Acre 0100482300112
    private boolean verificaAC(String IE) {
        if (IE.equals("0000000000000") || IE.equals("1111111111111")
                || IE.equals("2222222222222") || IE.equals("3333333333333")
                || IE.equals("4444444444444") || IE.equals("5555555555555")
                || IE.equals("6666666666666") || IE.equals("7777777777777")
                || IE.equals("8888888888888") || IE.equals("9999999999999") || IE.length() != 13) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 4;

            try {
                if (!IE.substring(0, 2).equals("01")) {
                    return false;
                }
                for (i = 0; i < 11; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                    if (peso == 1) {
                        peso = 9;
                    }
                }
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(11)) {
                    return false;
                } else {
                    peso = 5;
                    soma = 0;

                    for (i = 0; i < 12; i++) {
                        num = (int) (IE.charAt(i) - 48);
                        soma = soma + (num * peso);
                        peso--;
                        if (peso == 1) {
                            peso = 9;
                        }
                    }
                    r = 11 - (soma % 11);
                    if (r >= 10) {
                        dig = '0';
                    } else {
                        dig = (char) (r + 48);
                    }
                    if (dig != IE.charAt(12)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Amazonas 053219015
    private boolean verificaAM(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                if (soma < 11) {
                    r = 11 - soma;
                } else {
                    r = soma % 11;
                }
                if (r <= 1) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Ceara 967267650
    private boolean verificaCE(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = soma % 11;
                if (r <= 1) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Distrito Federal 0730000100109
    private boolean verificaDF(String IE) {
        if (IE.equals("0000000000000") || IE.equals("1111111111111")
                || IE.equals("2222222222222") || IE.equals("3333333333333")
                || IE.equals("4444444444444") || IE.equals("5555555555555")
                || IE.equals("6666666666666") || IE.equals("7777777777777")
                || IE.equals("8888888888888") || IE.equals("9999999999999") || IE.length() != 13) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 4;

            try {
                if (!IE.substring(0, 2).equals("07")) {
                    return false;
                }
                for (i = 0; i < 11; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                    if (peso == 1) {
                        peso = 9;
                    }
                }
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(11)) {
                    return false;
                } else {
                    peso = 5;
                    soma = 0;

                    for (i = 0; i < 12; i++) {
                        num = (int) (IE.charAt(i) - 48);
                        soma = soma + (num * peso);
                        peso--;
                        if (peso == 1) {
                            peso = 9;
                        }
                    }
                    r = 11 - (soma % 11);
                    if (r >= 10) {
                        dig = '0';
                    } else {
                        dig = (char) (r + 48);
                    }
                    if (dig != IE.charAt(12)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Espirito Santo 240931548
    private boolean verificaES(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = soma % 11;
                if (r < 2) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Maranhão 122029720
    private boolean verificaMA(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {
                if (!IE.substring(0, 2).equals("12")) {
                    return false;
                }

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = soma % 11;
                if (r < 2) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Mato Grosso 52582921298
    private boolean verificaMT(String IE) {
        if (IE.equals("00000000000") || IE.equals("11111111111")
                || IE.equals("22222222222") || IE.equals("33333333333")
                || IE.equals("44444444444") || IE.equals("55555555555")
                || IE.equals("66666666666") || IE.equals("77777777777")
                || IE.equals("88888888888") || IE.equals("99999999999") || IE.length() != 11) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 3;

            try {

                for (i = 0; i < 10; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                    if (peso == 1) {
                        peso = 9;
                    }
                }
                r = soma % 11;
                if (r < 2) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(10)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Mato Grosso do Sul 282965220
    private boolean verificaMS(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {
                if (!IE.substring(0, 2).equals("28")) {
                    return false;
                }

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = soma % 11;
                if (r <= 1) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Para 150425732
    private boolean verificaPA(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {
                if (!IE.substring(0, 2).equals("15")) {
                    return false;
                }

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = soma % 11;
                if (r <= 1) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    // Validacao Paraiba 126637202
    private boolean verificaPB(String IE) {
        if (IE.equals("000000000") || IE.equals("111111111")
                || IE.equals("222222222") || IE.equals("333333333")
                || IE.equals("444444444") || IE.equals("555555555")
                || IE.equals("666666666") || IE.equals("777777777")
                || IE.equals("888888888") || IE.equals("999999999") || IE.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, num, peso = 9;

            try {

                for (i = 0; i < 8; i++) {
                    num = (int) (IE.charAt(i) - 48);
                    soma = soma + (num * peso);
                    peso--;
                }
                r = soma % 11;
                if (r <= 1) {
                    dig = '0';
                } else {
                    r = 11 - r;
                    dig = (char) (r + 48);
                }

                if (dig != IE.charAt(8)) {
                    return false;
                } else {
                    return true;
                }
            } catch (InputMismatchException erro) {
                return false;
            }

        }
    }

    private boolean verifica(String UF, String IE) {
        UF = UF.toUpperCase();
        IE = IE.toUpperCase();

        if (IE.equals("ISENTO")) {
            return true;

        } else if (UF.equals("AC")) {
            return verificaAC(IE);

        } else if (UF.equals("AM")) {
            return verificaAM(IE);

        } else if (UF.equals("CE")) {
            return verificaCE(IE);

        } else if (UF.equals("DF")) {
            return verificaDF(IE);

        } else if (UF.equals("ES")) {
            return verificaES(IE);

        } else if (UF.equals("MA")) {
            return verificaMA(IE);

        } else if (UF.equals("MT")) {
            return verificaMT(IE);

        } else if (UF.equals("MS")) {
            return verificaMS(IE);

        } else if (UF.equals("PA")) {
            return verificaPA(IE);

        } else if (UF.equals("PB")) {
            return verificaPB(IE);

        } else if (txtFldIE.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        //System.out.println(verifica("AC","0100482300113"));

        // listener IE
        uf.add(new UF("AC"));
        uf.add(new UF("AM"));
        uf.add(new UF("CE"));
        uf.add(new UF("DF"));
        uf.add(new UF("ES"));
        uf.add(new UF("MA"));
        uf.add(new UF("MT"));
        uf.add(new UF("MS"));
        uf.add(new UF("PA"));
        uf.add(new UF("PB"));

        cmbUF.setItems(FXCollections.observableList(uf));

        txtFldIE.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) {
                txtFldIE.getStyleClass().remove("error");

                if (oldValue) {
                    if (!verifica(est, txtFldIE.getText())) {
                        txtFldIE.getStyleClass().add("error");
                    }
                }
            }
        });

        cmbUF.valueProperty().addListener(new ChangeListener<UF>() {

            @Override
            public void changed(ObservableValue<? extends UF> observable, UF oldValue, UF newValue) {
                est = newValue.getDescricaoUf();
                txtFldIE.getStyleClass().remove("error");
                if (!txtFldIE.getText().isEmpty()) {
                    if (!verifica(est, txtFldIE.getText())) {
                        txtFldIE.getStyleClass().add("error");
                    }
                }
            }
        });

        //listener CPF
        txtFldCpf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) {
                txtFldCpf.getStyleClass().remove("error");

                if (oldValue) {
                    if (!txtFldCpf.getText().isEmpty()) {
                        if (!isCpf(txtFldCpf.getText())) {
                            txtFldCpf.getStyleClass().add("error");
                        }
                    }
                }
            }

        });
        //listener CNPJ
        txtFldCnpj.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) {
                txtFldCnpj.getStyleClass().remove("error");

                if (oldValue) {
                    if (!txtFldCnpj.getText().isEmpty()) {
                        if (!isCnpj(txtFldCnpj.getText())) {
                            txtFldCnpj.getStyleClass().add("error");
                        }
                    }
                }
            }
        });

    }
}
