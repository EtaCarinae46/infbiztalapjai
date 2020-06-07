package controller.fxCtrls;

import controller.RSA;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigInteger;

public class MainCtrl {

    @FXML TextField p;
    @FXML TextField q;
    @FXML TextArea input;
    @FXML TextArea output;

    @FXML
    private void decrypt() {
        if (!isValid()) return;

        BigInteger[] keys = RSA.generateKey(new BigInteger(p.getText()), new BigInteger(q.getText()));

        if (keys == null) return;

        System.out.println("PK=(n;e)=(" + keys[0] + ";" + keys[2] + ")");
        System.out.println("SK=d=(" +keys[3] + ")");

        int length = String.valueOf(keys[0]).length();
        String iStr = input.getText();
        StringBuilder sb1 = new StringBuilder();
        for (char c : iStr.toCharArray()) {
            sb1.append(String.format("%04d", (int)c  - 32));
        }

        StringBuilder oStr = new StringBuilder();

        for (int i = 0; i < sb1.length()/length; i++) {
            String tmp = sb1.substring(i*length, i*length+length);
            BigInteger out = new BigInteger(tmp).modPow(keys[3],keys[0]);
            oStr.append((char)out.intValue());
        }

        output.setText(oStr.toString());
    }

    @FXML
    private void encrypt() {
        if (!isValid()) return;

        BigInteger[] keys = RSA.generateKey(new BigInteger(p.getText()), new BigInteger(q.getText()));

        if (keys == null) return;

        System.out.println("PK=(n;e)=(" + keys[0] + ";" + keys[2] + ")");
        System.out.println("SK=d=(" +keys[3] + ")");

        int length = String.valueOf(keys[0]).length();
        String format = "%0" + length + "d";

        String iStr = input.getText();
        StringBuilder oStr = new StringBuilder();
        for (char c : iStr.toCharArray()) {
            BigInteger out = BigInteger.valueOf(c).modPow(keys[2],keys[0]);
            oStr.append(String.format(format, out));
        }

        String[] arr = oStr.toString().split("(?<=\\G....)");
        StringBuilder sb2 = new StringBuilder();
        for (String s : arr) {
            sb2.append((char)(Integer.parseInt(s) + 32));
        }

        output.setText(sb2.toString());
    }

    private boolean isValid() {
        String p = this.p.getText();
        String q = this.q.getText();

        try {

            new BigInteger(p);
            new BigInteger(q);

        } catch (Exception e) {
            return false;
        }

        return input.getText().length() > 0;
    }
}
