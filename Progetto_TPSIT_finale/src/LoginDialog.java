
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginDialog extends JDialog {
    private String password;
    private boolean succeeded;
    private JPasswordField passwordField;

    public LoginDialog(Frame parent) {
        super(parent, "Autenticazione", true);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel label = new JLabel("Inserisci la tua password: ");
        passwordField = new JPasswordField(20);

        panel.add(label);
        panel.add(passwordField);

        JButton loginButton = new JButton("Conferma");
        JButton cancelButton = new JButton("Annulla");

        JPanel buttons = new JPanel();
        buttons.add(loginButton);
        buttons.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                password = new String(passwordField.getPassword());
                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "La password deve avere almeno 8 caratteri.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    succeeded = false;
                } else {
                    succeeded = true;
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(e -> {
            succeeded = false;
            dispose();
        });
    }

    public String getPassword() {
        return password;
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
