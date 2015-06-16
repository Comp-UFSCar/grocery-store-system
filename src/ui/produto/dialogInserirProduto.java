package ui.produto;

import projetomercado.GerenciadorProdutos;
import projetomercado.Mercado;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class dialogInserirProduto extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtCodigo;
    private JTextField txtDescricao;
    private JTextField txtPrecoCompra;
    private JTextField txtPrecoVenda;
    private JTextField txtUnidade;
    private JTextField txtEstoque;
    private JComboBox cboxStatus;
    private Border borderError;
    private Border borderDefault;

    public dialogInserirProduto() {
        setTitle("[Topicos Avancados A] Inserir Produto");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        borderDefault = txtCodigo.getBorder();

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
        // verify all input
        if(validateForm())
            try {
                GerenciadorProdutos.insereProduto(
                        Integer.parseInt(txtCodigo.getText()),
                        txtDescricao.getText(),
                        Double.parseDouble(txtPrecoCompra.getText()),
                        Double.parseDouble(txtPrecoVenda.getText()),
                        txtUnidade.getText(),
                        Double.parseDouble(txtEstoque.getText()),
                        cboxStatus.getSelectedIndex()
                );
                System.out.println("Produto Inserido!");
                Mercado.updateTableProduto();
                dispose();
            }
            catch (RuntimeException re) {
                System.err.println(re);
                JOptionPane.showMessageDialog(null, re, "Erro", JOptionPane.ERROR_MESSAGE);
            }


    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        cboxStatus = new JComboBox(new String[] {"Inativo", "Ativo"});
        cboxStatus.setSelectedIndex(1);

        borderError = BorderFactory.createLineBorder(Color.red);
    }

    private boolean validateForm(){
        int error = 0;
        if(txtCodigo.getText().isEmpty()){
            txtCodigo.setBorder(borderError);
            error++;
        }
        else
            txtCodigo.setBorder(borderDefault);

        if(txtDescricao.getText().isEmpty()){
            txtDescricao.setBorder(borderError);
            error++;
        }
        else
            txtDescricao.setBorder(borderDefault);

        if(txtPrecoCompra.getText().isEmpty()){
            txtPrecoCompra.setBorder(borderError);
            error++;
        }
        else
            txtPrecoCompra.setBorder(borderDefault);

        if(txtPrecoVenda.getText().isEmpty()){
            txtPrecoVenda.setBorder(borderError);
            error++;
        }
        else
            txtPrecoVenda.setBorder(borderDefault);

        if(txtUnidade.getText().isEmpty()){
            txtUnidade.setBorder(borderError);
            error++;
        }
        else
            txtUnidade.setBorder(borderDefault);

        if(txtEstoque.getText().isEmpty()){
            txtEstoque.setBorder(borderError);
            error++;
        }
        else
            txtEstoque.setBorder(borderDefault);

        if(error > 0)
            return false;
        return true;
    }
}
