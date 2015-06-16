package ui;

import projetomercado.Cliente;
import projetomercado.GerenciadorClientes;
import projetomercado.GerenciadorProdutos;
import projetomercado.Produto;
import ui.cliente.dialogInserirCliente;
import ui.produto.dialogAlterarEstoque;
import ui.produto.dialogAlterarProduto;
import ui.produto.dialogConsultarProduto;
import ui.produto.dialogInserirProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class MainUI extends JDialog {

    // region Variables
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JTable tblProdutos;
    private JButton btnInserirProduto;
    private JButton btnAlteraEstoque;
    private JButton btnConsultarProduto;
    private JButton btnListarProdutos;
    private JButton btnAlteraProduto;
    private JTable tblClientes;
    private JButton btnInserirCliente;
    private JButton btnAlterarCliente;
    private JButton btnConsultarCliente;
    private JButton btnListarClientes;

    private DefaultTableModel modelProdutos;
    private DefaultTableModel modelClientes;
    // endregion

    public MainUI() {
        // region Start Declaration / set-up
        setTitle("[Topicos Avancados A] Gerenciador de Mercado");
        setContentPane(contentPane);
        setModal(true);
        setResizable(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        //endregion

        // region ActionListeners
        btnListarProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onListarProdutos();
            }
        });
        btnInserirProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onInserirProduto();
            }
        });
        btnConsultarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConsultarProduto();
            }
        });
        btnAlteraEstoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAlteraEstoque();

            }
        });
        btnAlteraProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAlteraProduto();
            }
        });
        btnInserirCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onInsereCliente();
            }
        });
        btnAlterarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAlteraCliente();
            }
        });
        btnConsultarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConsultaCliente();
            }
        });
        btnListarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onListarClientes();
            }
        });
        //endregion
    }

    private void createUIComponents() {
        setModelProdutos();
        populateTable(GerenciadorProdutos.getProdutos());
        tblProdutos = new JTable(modelProdutos) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        setTblProdutosPreferences();

        setModelClientes();
        populateTable(GerenciadorClientes.getClientes());
        tblClientes = new JTable(modelClientes) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }

    // region Listeners Methods

    // region Clientes
    private void onListarClientes() {
        System.out.println("Listar tudo: tabela Clientes");
        // remove all rows
        clearTable(modelClientes);
        // populate rows
        populateTable(GerenciadorClientes.getClientes());
    }

    // TODO: Consulta Cliente - interface e codigo
    private void onConsultaCliente() {
    }

    // TODO: Altera Cliente - interface e codigo
    private void onAlteraCliente() {
    }

    // TODO: Insere Cliente
    private void onInsereCliente() {
        dialogInserirCliente dInserirCliente = new dialogInserirCliente();
        dInserirCliente.pack();
        dInserirCliente.setLocationRelativeTo(null);
        dInserirCliente.setVisible(true);
    }
    // endregion

    // region Produtos
    private void onAlteraEstoque() {
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

    private void onConsultarProduto() {
        dialogConsultarProduto dConsultaProduto = new dialogConsultarProduto();
        dConsultaProduto.pack();
        dConsultaProduto.setLocationRelativeTo(null);
        dConsultaProduto.setVisible(true);
    }

    private void onInserirProduto() {
        dialogInserirProduto dInserirProduto = new dialogInserirProduto();
        dInserirProduto.pack();
        dInserirProduto.setLocationRelativeTo(null);
        dInserirProduto.setVisible(true);
    }

    private void onListarProdutos() {
        System.out.println("Listar tudo: tabela Produtos");
        // remove all rows
        clearTable(modelProdutos);
        // populate rows
        populateTable(GerenciadorProdutos.getProdutos());
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
    // endregion

    private void onCancel() { dispose(); }
    // endregion

    // region Table Methods

    private void populateTable(ArrayList<?> lista) {
        if(lista.size() > 0)
        {
            if(lista.get(0) instanceof Produto)
                for (Produto p : (ArrayList<Produto>)lista)
                    insertRow(p);
            else if(lista.get(0) instanceof Cliente)
                for (Cliente c : (ArrayList<Cliente>)lista)
                    insertRow(c);
        }
    }

    private void clearTable(DefaultTableModel tblModel) {
        // remove all rows
        for (int i = tblModel.getRowCount() - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    public void refreshTable(ArrayList<?> list){
        if(list.get(0) instanceof Produto)
        {
            clearTable(modelProdutos);
            populateTable(GerenciadorProdutos.getProdutos());
        }
        else if (list.get(0) instanceof Cliente)
        {
            clearTable(modelClientes);
            populateTable(GerenciadorClientes.getClientes());
        }
    }

    private void insertRow(Produto p) {
        String status = defineStatus(p.getStatus());
        modelProdutos.addRow(new Object[]{
                p.getCodigo(), p.getDescricao(), String.format("%.2f", p.getPrecoCompra()),
                String.format("%.2f", p.getPrecoVenda()), p.getEstoque(), p.getUnidade(), status
        });
    }

    private void insertRow(Cliente c) {
        String status = defineStatus(c.getStatus());
        modelClientes.addRow(new Object[]{
                c.getNome(), c.getCpf(), c.getEndereco(), c.getTelefone(), c.getEmail(), status
        });
    }

    private String defineStatus(int s){
        if(s == 1)
            return "Ativo";
        else
            return "Inativo";
    }

    public void searchProduto(Produto p){
        clearTable(modelProdutos);
        insertRow(p);
    }

    public void searchProduto(ArrayList<Produto> ps){
        clearTable(modelProdutos);
        for(Produto p: ps){
            insertRow(p);
        }
    }

    // region Define Table Models and Preferences
    private void setModelProdutos(){
        modelProdutos = new DefaultTableModel() {
            String[] produto = {"Codigo", "Descricao", "Preco Compra", "Preco Venda", "Estoque", "Unidade", "Status"};

            @Override
            public int getColumnCount() {
                return produto.length;
            }

            @Override
            public String getColumnName(int index) {
                return produto[index];
            }
        };
    }
    private void setTblProdutosPreferences(){
        DefaultTableCellRenderer centered = new DefaultTableCellRenderer();
        centered.setHorizontalAlignment(JLabel.CENTER);

        ((DefaultTableCellRenderer)tblProdutos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblProdutos.getColumnModel().getColumn(0).setCellRenderer(centered);
        tblProdutos.getColumnModel().getColumn(5).setCellRenderer(centered);
        tblProdutos.getColumnModel().getColumn(6).setCellRenderer(centered);

        tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblProdutos.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblProdutos.getColumnModel().getColumn(6).setPreferredWidth(40);

    }
    private void setModelClientes(){
        modelClientes = new DefaultTableModel() {
            String[] cliente = {"Nome", "CPF", "Endereco", "Telefone", "E-mail", "Status"};

            @Override
            public int getColumnCount() {
                return cliente.length;
            }

            @Override
            public String getColumnName(int index) {
                return cliente[index];
            }
        };
    }
    //endregion

    //endregion
}
