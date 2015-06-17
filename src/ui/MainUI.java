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
import java.awt.*;
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
        $$$setupUI$$$();
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
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setTblProdutosPreferences();

        setModelClientes();
        populateTable(GerenciadorClientes.getClientes());
        tblClientes = new JTable(modelClientes) {
            @Override
            public boolean isCellEditable(int row, int column) {
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
        if (tblProdutos.getSelectedRow() >= 0
                && (tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0) instanceof Integer)) {
            dialogAlterarEstoque dAlterarEstoque =
                    new dialogAlterarEstoque((Integer) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0));
            dAlterarEstoque.pack();
            dAlterarEstoque.setLocationRelativeTo(null);
            dAlterarEstoque.setVisible(true);
        } else {
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
        if (tblProdutos.getSelectedRow() >= 0
                && (tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0) instanceof Integer)) {
            dialogAlterarProduto dAlterarProduto =
                    new dialogAlterarProduto(GerenciadorProdutos.consultaProdutoPorCodigo(
                            (Integer) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 0)));
            dAlterarProduto.pack();
            dAlterarProduto.setLocationRelativeTo(null);
            dAlterarProduto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    null, "Nenhum produto selecionado!", "Mensagem", JOptionPane.WARNING_MESSAGE);
        }
    }
    // endregion

    private void onCancel() {
        dispose();
    }
    // endregion

    // region Table Methods

    private void populateTable(ArrayList<?> lista) {
        if (lista.size() > 0) {
            if (lista.get(0) instanceof Produto)
                for (Produto p : (ArrayList<Produto>) lista)
                    insertRow(p);
            else if (lista.get(0) instanceof Cliente)
                for (Cliente c : (ArrayList<Cliente>) lista)
                    insertRow(c);
        }
    }

    private void clearTable(DefaultTableModel tblModel) {
        // remove all rows
        for (int i = tblModel.getRowCount() - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    public void refreshTable(ArrayList<?> list) {
        if (list.get(0) instanceof Produto) {
            clearTable(modelProdutos);
            populateTable(GerenciadorProdutos.getProdutos());
        } else if (list.get(0) instanceof Cliente) {
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

    private String defineStatus(int s) {
        if (s == 1)
            return "Ativo";
        else
            return "Inativo";
    }

    public void searchProduto(Produto p) {
        clearTable(modelProdutos);
        insertRow(p);
    }

    public void searchProduto(ArrayList<Produto> ps) {
        clearTable(modelProdutos);
        for (Produto p : ps) {
            insertRow(p);
        }
    }

    // region Define Table Models and Preferences
    private void setModelProdutos() {
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

    private void setTblProdutosPreferences() {
        DefaultTableCellRenderer centered = new DefaultTableCellRenderer();
        centered.setHorizontalAlignment(JLabel.CENTER);

        ((DefaultTableCellRenderer) tblProdutos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblProdutos.getColumnModel().getColumn(0).setCellRenderer(centered);
        tblProdutos.getColumnModel().getColumn(5).setCellRenderer(centered);
        tblProdutos.getColumnModel().getColumn(6).setCellRenderer(centered);

        tblProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblProdutos.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblProdutos.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblProdutos.getColumnModel().getColumn(6).setPreferredWidth(40);

    }

    private void setModelClientes() {
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setMaximumSize(new Dimension(800, 600));
        contentPane.setMinimumSize(new Dimension(800, 600));
        contentPane.setName("");
        contentPane.setPreferredSize(new Dimension(800, 600));
        tabbedPane = new JTabbedPane();
        contentPane.add(tabbedPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane.addTab("Produtos", panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tblProdutos.setAutoResizeMode(4);
        scrollPane1.setViewportView(tblProdutos);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnAlteraEstoque = new JButton();
        btnAlteraEstoque.setText("Alterar Estoque");
        panel3.add(btnAlteraEstoque, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnListarProdutos = new JButton();
        btnListarProdutos.setText("Listar Tudo");
        panel3.add(btnListarProdutos, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel3.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnAlteraProduto = new JButton();
        btnAlteraProduto.setText("Altera Produto");
        panel3.add(btnAlteraProduto, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnInserirProduto = new JButton();
        btnInserirProduto.setText("Inserir");
        panel3.add(btnInserirProduto, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsultarProduto = new JButton();
        btnConsultarProduto.setText("Consultar");
        panel3.add(btnConsultarProduto, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane.addTab("Clientes", panel4);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel5.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setViewportView(tblClientes);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnInserirCliente = new JButton();
        btnInserirCliente.setText("Inserir");
        panel6.add(btnInserirCliente, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel6.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnAlterarCliente = new JButton();
        btnAlterarCliente.setText("Alterar");
        panel6.add(btnAlterarCliente, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsultarCliente = new JButton();
        btnConsultarCliente.setText("Consultar");
        panel6.add(btnConsultarCliente, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnListarClientes = new JButton();
        btnListarClientes.setText("Listar Tudo");
        panel6.add(btnListarClientes, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
    //endregion

    //endregion
}
