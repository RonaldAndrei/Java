package view;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField txtFldCpf, txtFldCnpj;

    @FXML
    private void btnLimparClick(ActionEvent event) {
        txtFldCpf.setText("");
        txtFldCnpj.setText("");
        txtFldCpf.getStyleClass().remove("error");
        txtFldCnpj.getStyleClass().remove("error");
        Platform.runLater(() -> txtFldCpf.requestFocus());
    }

    //Validacao CPF
    private boolean isCpf(String cpf) {
        System.out.println(cpf);

        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999") || cpf.length() != 11) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, num = 0, peso = 10;
            try {
                for (i = 0; i < 9; i++) {
                    num = (int) (cpf.charAt(i) - 48);
                    soma = soma + (num * peso);
//                    System.out.println(peso);
//                    System.out.println(num);
//                    System.out.println(soma);
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

    //ValidaÃ§Ã£o CPNJ
    private boolean isCnpj(String cnpj) {
        //System.out.println(cnpj);

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
                //System.out.println(soma); //

                r = 11 - (soma % 11);
                //System.out.println(r); //                
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
                    //System.out.println(soma); //

                    r = 11 - (soma % 11);
                    //System.out.println(r); //
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println(isCnpj("01838723036237"));
        //listener CPF
        txtFldCpf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) {
                txtFldCpf.getStyleClass().remove("error");

                if (oldValue) {
                    if (!isCpf(txtFldCpf.getText())) {
                        txtFldCpf.getStyleClass().add("error");
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
                    if (!isCnpj(txtFldCnpj.getText())) {
                        txtFldCnpj.getStyleClass().add("error");
                    }
                }
            }
        });
    }
}
