package ui;

import projetomercado.GerenciadorProdutos;
import projetomercado.Produto;
import ui.produto.dialogAlterarEstoque;
import ui.produto.dialogAlterarProduto;
import ui.produto.dialogConsultarProduto;
import ui.produto.dialogInserirProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class MainUI extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JTable tblProdutos;
    private JButton btnInserirProduto;
    private JButton btnAlteraEstoque;
    private JButton btnConsultar;
    private JButton btnExibirTudo;
    private JButton btnAlteraProduto;

    private DefaultTableModel modelProdutos;

    public MainUI() {
        setTitle("[Topicos Avancados A] Gerenciador de Mercado");
        setContentPane(contentPane);
        setModal(true);
        setResizable(false);

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

        btnExibirTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Listar tudo: tabela Produtos");
                // remove all rows
                removeProdutos();
                // populate rows
                populateProdutos();
            }
        });
        btnInserirProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogInserirProduto dInserirProduto = new dialogInserirProduto();
                dInserirProduto.pack();
                dInserirProduto.setLocationRelativeTo(null);
                dInserirProduto.setVisible(true);
            }
        });
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogConsultarProduto dConsultaProduto = new dialogConsultarProduto();
                dConsultaProduto.pack();
                dConsultaProduto.setLocationRelativeTo(null);
                dConsultaProduto.setVisible(true);
            }
        });
        btnAlteraEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblProdutos.getSelectedRow() >= 0
                        && (tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0) instanceof Integer))
                {
                    dialogAlterarEstoque dAlterarEstoque =
                            new dialogAlterarEstoque((Integer)tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0));
                    dAlterarEstoque.pack();
                    dAlterarEstoque.setLocationRelativeTo(null);
                    dAlterarEstoque.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(
                            null, "Nenhum produto selecionado!", "Mensagem", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        btnAlteraProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAlteraProduto();
            }
        });
    }

    private void onAlteraProduto() {
        if(tblProdutos.getSelectedRow() >= 0
                && (tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0) instanceof Integer))
        {
            dialogAlterarProduto dAlterarProduto =
                    new dialogAlterarProduto(GerenciadorProdutos.consultaProdutoPorCodigo(
                            (Integer)tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0)));
            dAlterarProduto.pack();
            dAlterarProduto.setLocationRelativeTo(null);
            dAlterarProduto.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(
                    null, "Nenhum produto selecionado!", "Mensagem", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        modelProdutos = new DefaultTableModel() {
            String[] produto = {"Codigo", "Descricao", "Preco Compra", "Preco Venda", "Unidade", "Estoque", "Status"};

            @Override
            public int getColumnCount() {
                return produto.length;
            }

            @Override
            public String getColumnName(int index) {
                return produto[index];
            }
        };

        populateProdutos();
        tblProdutos = new JTable(modelProdutos);

    }

    private void populateProdutos() {
        for (Produto p : GerenciadorProdutos.getProdutos()) {
            insertProduto(p);
        }
    }

    private void removeProdutos() {
        // remove all rows
        for (int i = tblProdutos.getRowCount() - 1; i >= 0; i--) {
            modelProdutos.removeRow(i);
        }
    }

    public void relistProdutos(){
        removeProdutos();
        populateProdutos();
    }

    private void insertProduto(Produto p) {
        String status = defineStatus(p.getStatus());
        modelProdutos.addRow(new Object[]{
                p.getCodigo(), p.getDescricao(), String.format("%.2f", p.getPrecoCompra()),
                String.format("%.2f", p.getPrecoVenda()), p.getUnidade(), p.getEstoque(), status
        });
    }

    private String defineStatus(int s){
        if(s == 1)
            return "Ativo";
        else
            return "Inativo";
    }

    public void searchProduto(Produto p){
        removeProdutos();
        insertProduto(p);
    }


    public void searchProduto(ArrayList<Produto> ps){
        removeProdutos();
        for(Produto p: ps){
            insertProduto(p);
        }
    }
}
