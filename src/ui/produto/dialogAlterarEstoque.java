package ui.produto;

import projetomercado.GerenciadorProdutos;
import projetomercado.Mercado;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class dialogAlterarEstoque extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtValor;
    private JLabel lblCodigo;

    private Border borderError;
    private Border borderDefault;
    private int codigo;

    public dialogAlterarEstoque(int codigo) {
        setTitle("Alterar Estoque");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        lblCodigo.setText("Produto selecionado: " + String.valueOf(codigo));
        borderDefault = txtValor.getBorder();
        borderError = BorderFactory.createLineBorder(Color.red);
        this.codigo = codigo;

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
            try{
                double estoque = Double.parseDouble(txtValor.getText());
                GerenciadorProdutos.alteraEstoque(codigo, estoque);
                Mercado.updateTableProduto();
                dispose();
            }
            catch (RuntimeException re)
            {
                JOptionPane.showMessageDialog(null, re, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onCancel() {
        dispose();
    }

    private boolean validateForm(){
        if(txtValor.getText().isEmpty()) {
            txtValor.setBorder(borderError);
            return false;
        }
        else {
            txtValor.setBorder(borderDefault);
            return true;
        }
    }
}
