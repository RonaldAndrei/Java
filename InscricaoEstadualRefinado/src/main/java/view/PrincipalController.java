package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.UF;

public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFldInscricaoEst;

    @FXML
    private ComboBox cmbUf;

    private double fator = 0;
    private String ufs;

    private List<UF> uF = new ArrayList<>();

    private boolean verifica_Pr(String insc) {
        if (insc.equals("0000000000") || insc.equals("1111111111")
                || insc.equals("2222222222") || insc.equals("3333333333")
                || insc.equals("4444444444") || insc.equals("5555555555")
                || insc.equals("6666666666") || insc.equals("7777777777")
                || insc.equals("8888888888") || insc.equals("9999999999")
                || insc.length() != 10) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, peso = 3;
            int num = 0;
            try {
                for (i = 0; i < 8; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    if (peso != 2) {
                        peso--;
                    } else {
                        peso = 7;
                    }
                }
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }
                if (dig != insc.charAt(8)) {
                    return false;
                } else {
                    peso = 4;
                    soma = 0;
                    for (i = 0; i < 9; i++) {
                        num = (int) (insc.charAt(i) - 48);
                        soma += (num * peso);
                        if (peso != 2) {
                            peso--;
                        } else {
                            peso = 7;
                        }
                    }
                    r = 11 - (soma % 11);//resto
                    if (r >= 10) {
                        dig = '0';
                    } else {
                        dig = (char) (r + 48);
                    }
                    return (dig == insc.charAt(9));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica_Sc_Ma(String insc) {
        if (insc.equals("000000000") || insc.equals("111111111")
                || insc.equals("222222222") || insc.equals("333333333")
                || insc.equals("444444444") || insc.equals("555555555")
                || insc.equals("666666666") || insc.equals("777777777")
                || insc.equals("888888888") || insc.equals("999999999")
                || insc.length() != 9) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, peso = 9;
            int num = 0;
            try {
                for (i = 0; i < 8; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    peso--;
                }
                int resto = soma % 11;
                r = 11 - (resto);
                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else if (resto == 0 || resto == 1) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != insc.charAt(8)) {
                    return false;
                } else {
                    return (dig == insc.charAt(8));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica_Rs(String insc) {
        if (insc.equals("0000000000") || insc.equals("1111111111")
                || insc.equals("2222222222") || insc.equals("3333333333")
                || insc.equals("4444444444") || insc.equals("5555555555")
                || insc.equals("6666666666") || insc.equals("7777777777")
                || insc.equals("8888888888") || insc.equals("9999999999")
                || insc.length() != 10) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, peso = 2;
            int num = 0;
            try {
                for (i = 0; i < 9; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    if (peso != 2) {
                        peso--;
                    } else {
                        peso = 9;
                    }
                }
                int resto = soma % 11;
                r = 11 - (resto);
                if (resto == 0 || resto == 1) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != insc.charAt(9)) {
                    return false;
                } else {
                    return (dig == insc.charAt(9));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica_Ce_Pb_Pi(String insc) {
        if (insc.equals("000000000") || insc.equals("111111111")
                || insc.equals("222222222") || insc.equals("333333333")
                || insc.equals("444444444") || insc.equals("555555555")
                || insc.equals("666666666") || insc.equals("777777777")
                || insc.equals("888888888") || insc.equals("999999999")
                || insc.length() != 9) {
            return false;
        } else {

            char dig;
            int soma = 0, i, r, peso = 9;
            int num = 0;
            try {

                for (i = 0; i < 8; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    peso--;
                }

                r = 11 - (soma % 11);
                if (r >= 10) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != insc.charAt(8)) {
                    return false;
                } else {
                    return (dig == insc.charAt(8));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica_Mt(String insc) {
        if (insc.equals("00000000000") || insc.equals("11111111111")
                || insc.equals("22222222222") || insc.equals("33333333333")
                || insc.equals("44444444444") || insc.equals("55555555555")
                || insc.equals("66666666666") || insc.equals("77777777777")
                || insc.equals("88888888888") || insc.equals("99999999999")
                || insc.length() != 11) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, peso = 3;
            int num = 0;
            try {
                for (i = 0; i < 10; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    if (peso != 2) {
                        peso--;
                    } else {
                        peso = 9;
                    }
                }

                int resto = soma % 11;
                r = 11 - (soma % 11);
                if (resto == 0 || resto == 1) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }
                if (dig != insc.charAt(10)) {
                    return false;
                } else {
                    return (dig == insc.charAt(10));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica_Pa(String insc) {
        if (insc.equals("000000000") || insc.equals("111111111")
                || insc.equals("222222222") || insc.equals("333333333")
                || insc.equals("444444444") || insc.equals("555555555")
                || insc.equals("666666666") || insc.equals("777777777")
                || insc.equals("888888888") || insc.equals("999999999")
                || insc.length() != 9) {
            return false;
        } else {
            if (!insc.substring(0, 2).equals("15")) {
                System.out.println("ENTRO:" + insc.substring(0, 2));
                return false;
            }
            char dig;
            int soma = 0, i, r, peso = 9;
            int num = 0;
            try {
                for (i = 0; i < 8; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    peso--;
                }
                int resto = soma % 11;
                r = 11 - (resto);
                r = 11 - (soma % 11);
                if (resto == 0 || resto == 1) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != insc.charAt(8)) {
                    return false;
                } else {
                    return (dig == insc.charAt(8));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica_Rj(String insc) {
        if (insc.equals("00000000") || insc.equals("11111111")
                || insc.equals("22222222") || insc.equals("33333333")
                || insc.equals("44444444") || insc.equals("55555555")
                || insc.equals("66666666") || insc.equals("77777777")
                || insc.equals("88888888") || insc.equals("99999999")
                || insc.length() != 8) {
            return false;
        } else {
            char dig;
            int soma = 0, i, r, peso = 2;
            int num = 0;
            try {
                for (i = 0; i < 7; i++) {
                    num = (int) (insc.charAt(i) - 48);
                    soma += (num * peso);
                    if (peso != 2) {
                        peso--;
                    } else {
                        peso = 7;
                    }
                }
                int resto = soma % 11;
                r = 11 - (resto);
                r = 11 - (soma % 11);
                if (resto == 0 || resto == 1) {
                    dig = '0';
                } else {
                    dig = (char) (r + 48);
                }

                if (dig != insc.charAt(7)) {
                    return false;
                } else {
                    return (dig == insc.charAt(7));
                }
            } catch (InputMismatchException erro) {
                return false;
            }
        }
    }

    private boolean verifica(String uf, String insc) {
        uf = uf.toLowerCase();
        insc = insc.toLowerCase();
        //System.out.println(fator);

        if (insc.equals("isento")) {
            System.out.println("Tudo bem!");
            return true;
        } else if (uf.equals("pr")) {
            return verifica_Pr(insc);
        } else if (uf.equals("sc") || uf.equals("ma")) {
            return verifica_Sc_Ma(insc);
        } else if (uf.equals("rs")) {
            return verifica_Rs(insc);
        } else if (uf.equals("ce") || uf.equals("pb") || uf.equals("pi")) {
            return verifica_Ce_Pb_Pi(insc);
        } else if (uf.equals("mt")) {
            return verifica_Mt(insc);
        } else if (uf.equals("pa")) {
            return verifica_Pa(insc);
        } else if (uf.equals("rj")) {
            return verifica_Rj(insc);
        }

        if (txtFldInscricaoEst.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        uF.add(new UF("CE"));
        uF.add(new UF("MA"));
        uF.add(new UF("MT"));
        uF.add(new UF("PA"));
        uF.add(new UF("PB"));
        uF.add(new UF("PI"));
        uF.add(new UF("PR"));
        uF.add(new UF("RJ"));
        uF.add(new UF("RS"));
        uF.add(new UF("SC"));

        cmbUf.setItems(FXCollections.observableList(uF));

        txtFldInscricaoEst.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) {
                txtFldInscricaoEst.getStyleClass().remove("error");

                if (oldValue) {

                    if (!verifica(ufs, txtFldInscricaoEst.getText())) {
                        txtFldInscricaoEst.getStyleClass().add("error");
                    }
                }
            }
        });

        cmbUf.valueProperty().addListener(new ChangeListener<UF>() {

            @Override
            public void changed(ObservableValue<? extends UF> observable, UF oldValue, UF newValue) {
                ufs = newValue.getDescricaoUf();
                txtFldInscricaoEst.getStyleClass().remove("error");
                if (!txtFldInscricaoEst.getText().isEmpty()) {
                    if (!verifica(ufs, txtFldInscricaoEst.getText())) {
                        txtFldInscricaoEst.getStyleClass().add("error");
                    }
                }

            }
        });
    }
}
