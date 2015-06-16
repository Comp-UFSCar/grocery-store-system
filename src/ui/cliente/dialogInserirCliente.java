package ui.cliente;

import projetomercado.GerenciadorClientes;
import projetomercado.Mercado;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class dialogInserirCliente extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNome;
    private JComboBox cboxStatusCliente;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private Border borderError;
    private Border borderDefault;

    public dialogInserirCliente() {
        setTitle("[Topicos Avancados A] Inserir Cliente");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        cboxStatusCliente.setSelectedIndex(1);
        borderError = BorderFactory.createLineBorder(Color.red);
        borderDefault = txtNome.getBorder();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if(validateForm())
        {
            try {
                GerenciadorClientes.insereCliente(
                        txtNome.getText(),
                        txtCpf.getText(),
                        txtEndereco.getText(),
                        txtTelefone.getText(),
                        txtEmail.getText(),
                        cboxStatusCliente.getSelectedIndex()
                );
                System.out.println("Cliente inserido!");
                Mercado.refreshTable(GerenciadorClientes.getClientes());
                dispose();
            }
            catch (RuntimeException re)
            {
                JOptionPane.showMessageDialog(null, re, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onCancel() { dispose(); }

    private boolean validateForm(){
        int error = 0;
        if(txtNome.getText().isEmpty()){
            txtNome.setBorder(borderError);
            error++;
        }
        else
            txtNome.setBorder(borderDefault);

        if(txtCpf.getText().isEmpty()){
            txtCpf.setBorder(borderError);
            error++;
        }
        else
            txtCpf.setBorder(borderDefault);

        if(txtEndereco.getText().isEmpty()){
            txtEndereco.setBorder(borderError);
            error++;
        }
        else
            txtEndereco.setBorder(borderDefault);

        if(txtTelefone.getText().isEmpty()){
            txtTelefone.setBorder(borderError);
            error++;
        }
        else
            txtTelefone.setBorder(borderDefault);

        if(txtEmail.getText().isEmpty()){
            txtEmail.setBorder(borderError);
            error++;
        }
        else
            txtEmail.setBorder(borderDefault);

        if(error > 0)
            return false;
        return true;
    }
}
