package ui.produto;

import projetomercado.GerenciadorProdutos;
import projetomercado.Mercado;
import projetomercado.Produto;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class dialogConsultarProduto extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox cboxTipo;
    private JTextField txtPesquisa;

    private Border borderError;
    private Border borderDefault;


    public dialogConsultarProduto() {
        setTitle("[Topicos Avancados A] Consultar Produto");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        borderDefault = txtPesquisa.getBorder();
        borderError = BorderFactory.createLineBorder(Color.red);

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
                if (cboxTipo.getSelectedIndex() == 0) { // Codigo
                    Produto p = GerenciadorProdutos.consultaProdutoPorCodigo(Integer.parseInt(txtPesquisa.getText()));
                    if(p == null)
                        JOptionPane.showMessageDialog(null,"Nenhum produto encontrado!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        Mercado.searchTableProduto(p);
                    }
                }
                else{   // Descricao
                    ArrayList<Integer> codigos = GerenciadorProdutos.consultaPorDescricao(txtPesquisa.getText());
                    if(codigos.get(0) == -1)
                        JOptionPane.showMessageDialog(null, "Nenhum produto encontrado!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        Mercado.searchTableProduto(codigos);
                    }
                }
                dispose();
            }
            catch(RuntimeException re){
                JOptionPane.showMessageDialog(null, re, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onCancel() {
        dispose();
    }

    private boolean validateForm(){
        if(txtPesquisa.getText().isEmpty()) {
            txtPesquisa.setBorder(borderError);
            return false;
        }
        else {
            txtPesquisa.setBorder(borderDefault);
            return true;
        }
    }
}
