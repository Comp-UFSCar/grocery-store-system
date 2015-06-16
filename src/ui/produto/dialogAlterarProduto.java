package ui.produto;

import projetomercado.GerenciadorProdutos;
import projetomercado.Mercado;
import projetomercado.Produto;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class dialogAlterarProduto extends JDialog {
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
    private Produto p;

    public dialogAlterarProduto(Produto p) {
        setTitle("[Topicos Avancados A] Alterar Produto");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        borderDefault = txtCodigo.getBorder();
        borderError = BorderFactory.createLineBorder(Color.red);

        txtCodigo.setText(String.valueOf(p.getCodigo()));
        txtDescricao.setText(p.getDescricao());
        txtPrecoCompra.setText(String.valueOf(p.getPrecoCompra()));
        txtPrecoVenda.setText(String.valueOf(p.getPrecoVenda()));
        txtUnidade.setText(p.getUnidade());
        txtEstoque.setText(String.valueOf(p.getEstoque()));
        if(p.getStatus() == Produto.INATIVO)
            cboxStatus.setSelectedIndex(Produto.INATIVO);
        else
            cboxStatus.setSelectedIndex(Produto.ATIVO);
        this.p = p;

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
        if(validateForm()) {
            try {
                p.alteraProduto(Integer.parseInt(txtCodigo.getText()),
                        txtDescricao.getText(),
                        Double.parseDouble(txtPrecoCompra.getText()),
                        Double.parseDouble(txtPrecoVenda.getText()),
                        txtUnidade.getText(),
                        Double.parseDouble(txtEstoque.getText()),
                        cboxStatus.getSelectedIndex());
                System.out.println("Produto Alterado!");
                Mercado.updateTableProduto();
                dispose();
            }
            catch (RuntimeException re) {
                System.err.println(re);
                JOptionPane.showMessageDialog(null, re, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onCancel() { dispose(); }

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
