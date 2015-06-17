package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import projetomercado.*;
import ui.cliente.dialogAlterarCliente;
import ui.cliente.dialogConsultarCliente;
import ui.cliente.dialogInserirCliente;
import ui.faturamento.dialogFaturamento;
import ui.produto.dialogAlterarEstoque;
import ui.produto.dialogAlterarProduto;
import ui.produto.dialogConsultarProduto;
import ui.produto.dialogInserirProduto;
import ui.venda.dialogVenda;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private JTable tblRegistroProdutos;
    private JTable tblRegistroVendas;
    private JButton btnNovaVenda;
    private JButton btnCalcularFaturamento;

    private DefaultTableModel modelProdutos;
    private DefaultTableModel modelClientes;
    private DefaultTableModel modelRegistroVendas;
    private DefaultTableModel modelRegistroProdutos;
    private SimpleDateFormat simpleDate;
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

        simpleDate = new SimpleDateFormat("dd/MM/yyyy");

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
        btnNovaVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNovaVenda();
            }
        });
        tblRegistroVendas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                onRegistroVendasClicked();
            }
        });
        btnCalcularFaturamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCalcularFaturamento();
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
        setTblClientesPreferences();

        setModelRegistroVendas();
        populateTable(GerenciadorRegistrosVenda.getRegistros());
        tblRegistroVendas = new JTable(modelRegistroVendas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        setTblRegistroVendasPreferences();

        setModelRegistroProdutos();
        tblRegistroProdutos = new JTable(modelRegistroProdutos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

    }

    // region Listeners Methods

    // region Registro de Vendas
    private void onCalcularFaturamento() {
        dialogFaturamento dFaturamento = new dialogFaturamento();
        dFaturamento.pack();
        dFaturamento.setLocationRelativeTo(null);
        dFaturamento.setVisible(true);
    }

    private void onNovaVenda() {
        dialogVenda dVenda = new dialogVenda();
        dVenda.pack();
        dVenda.setLocationRelativeTo(null);
        dVenda.setVisible(true);
    }

    private void onRegistroVendasClicked() {
        int row = tblRegistroVendas.getSelectedRow();
        if (row >= 0) {
            clearTable(modelRegistroProdutos);

            int qtd = GerenciadorRegistrosVenda.getRegistros().get(row).getQuantidadeItens();
            for (int i = 0; i < qtd; i++) {

                modelRegistroProdutos.addRow(new Object[]{
                        GerenciadorRegistrosVenda.getRegistros().get(row).getProdutos().get(i).getCodigo(),
                        GerenciadorRegistrosVenda.getRegistros().get(row).getQuantidades().get(i)
                });
            }
        }
    }
    // endregion

    // region Clientes
    private void onListarClientes() {
        System.out.println("Listar tudo: tabela Clientes");
        // remove all rows
        clearTable(modelClientes);
        // populate rows
        populateTable(GerenciadorClientes.getClientes());
    }

    private void onConsultaCliente() {
        dialogConsultarCliente dConsultaCliente = new dialogConsultarCliente();
        dConsultaCliente.pack();
        dConsultaCliente.setLocationRelativeTo(null);
        dConsultaCliente.setVisible(true);
    }

    private void onAlteraCliente() {
        if (tblClientes.getSelectedRow() >= 0) {
            dialogAlterarCliente dAlterarCliente =
                    new dialogAlterarCliente(GerenciadorClientes.consultaClientePorCpf(
                            (String) tblClientes.getValueAt(tblClientes.getSelectedRow(), 1)));
            dAlterarCliente.pack();
            dAlterarCliente.setLocationRelativeTo(null);
            dAlterarCliente.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    null, "Nenhum cliente selecionado!", "Mensagem", JOptionPane.WARNING_MESSAGE);
        }
    }

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

    // region Tables

    // region Methods
    private void populateTable(ArrayList<?> lista) {
        if (lista.size() > 0) {
            if (lista.get(0) instanceof Produto)
                for (Produto p : (ArrayList<Produto>) lista)
                    insertRow(p);
            else if (lista.get(0) instanceof Cliente)
                for (Cliente c : (ArrayList<Cliente>) lista)
                    insertRow(c);
            else if (lista.get(0) instanceof RegistroVenda)
                for (RegistroVenda v : (ArrayList<RegistroVenda>) lista)
                    insertRow(v);
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
        } else if (list.get(0) instanceof RegistroVenda) {
            clearTable(modelRegistroVendas);
            populateTable(GerenciadorRegistrosVenda.getRegistros());
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

    private void insertRow(RegistroVenda v) {
        modelRegistroVendas.addRow(new Object[]{
                v.getNumero(), v.getCliente().getCpf(), v.getData()
        });
    }

    private String defineStatus(int s) {
        if (s == 1)
            return "Ativo";
        else
            return "Inativo";
    }

    public void searchTable(Produto p) {
        clearTable(modelProdutos);
        insertRow(p);
    }

    public void searchTable(Cliente c) {
        clearTable(modelClientes);
        insertRow(c);
    }

    public void searchTable(ArrayList<?> list) {
        if (list.get(0) instanceof Produto) {
            clearTable(modelProdutos);
            for (Produto p : (ArrayList<Produto>) list)
                insertRow(p);
        } else if (list.get(0) instanceof Cliente) {
            clearTable(modelClientes);
            for (Cliente c : (ArrayList<Cliente>) list)
                insertRow(c);
        }
    }
    // endregion

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

    private void setTblClientesPreferences() {
        DefaultTableCellRenderer centered = new DefaultTableCellRenderer();
        centered.setHorizontalAlignment(JLabel.CENTER);

        ((DefaultTableCellRenderer) tblClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblClientes.getColumnModel().getColumn(5).setCellRenderer(centered);

        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(130);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(130);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(130);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(30);
    }

    private void setModelRegistroVendas() {
        modelRegistroVendas = new DefaultTableModel() {
            String[] rVendas = {"Numero Registro", "CPF", "Data"};

            @Override
            public int getColumnCount() {
                return rVendas.length;
            }

            @Override
            public String getColumnName(int index) {
                return rVendas[index];
            }
        };
    }

    private void setTblRegistroVendasPreferences() {
        TableCellRenderer dateRenderer = new DefaultTableCellRenderer() {
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                if (value instanceof Date) {
                    value = f.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
            }
        };
        tblRegistroVendas.getColumnModel().getColumn(2).setCellRenderer(dateRenderer);
    }

    private void setModelRegistroProdutos() {
        modelRegistroProdutos = new DefaultTableModel() {
            String[] rProdutos = {"Codigo", "Quantidade"};

            @Override
            public int getColumnCount() {
                return rProdutos.length;
            }

            @Override
            public String getColumnName(int index) {
                return rProdutos[index];
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
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setMaximumSize(new Dimension(800, 600));
        contentPane.setMinimumSize(new Dimension(800, 600));
        contentPane.setName("");
        contentPane.setPreferredSize(new Dimension(800, 600));
        tabbedPane = new JTabbedPane();
        contentPane.add(tabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane.addTab("Produtos", panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tblProdutos.setAutoResizeMode(4);
        scrollPane1.setViewportView(tblProdutos);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnAlteraEstoque = new JButton();
        btnAlteraEstoque.setText("Alterar Estoque");
        panel3.add(btnAlteraEstoque, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnListarProdutos = new JButton();
        btnListarProdutos.setText("Listar Tudo");
        panel3.add(btnListarProdutos, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnAlteraProduto = new JButton();
        btnAlteraProduto.setText("Altera Produto");
        panel3.add(btnAlteraProduto, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnInserirProduto = new JButton();
        btnInserirProduto.setText("Inserir");
        panel3.add(btnInserirProduto, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsultarProduto = new JButton();
        btnConsultarProduto.setText("Consultar");
        panel3.add(btnConsultarProduto, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane.addTab("Clientes", panel4);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel5.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setViewportView(tblClientes);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnInserirCliente = new JButton();
        btnInserirCliente.setText("Inserir");
        panel6.add(btnInserirCliente, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel6.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnAlterarCliente = new JButton();
        btnAlterarCliente.setText("Alterar");
        panel6.add(btnAlterarCliente, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnConsultarCliente = new JButton();
        btnConsultarCliente.setText("Consultar");
        panel6.add(btnConsultarCliente, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnListarClientes = new JButton();
        btnListarClientes.setText("Listar Tudo");
        panel6.add(btnListarClientes, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane.addTab("Registro de Vendas", panel7);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel8.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane3.setBorder(BorderFactory.createTitledBorder("Vendas"));
        scrollPane3.setViewportView(tblRegistroVendas);
        final JScrollPane scrollPane4 = new JScrollPane();
        panel8.add(scrollPane4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane4.setBorder(BorderFactory.createTitledBorder("Produtos"));
        scrollPane4.setViewportView(tblRegistroProdutos);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel7.add(panel9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel9.add(spacer3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnNovaVenda = new JButton();
        btnNovaVenda.setText("Nova Venda");
        panel9.add(btnNovaVenda, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnCalcularFaturamento = new JButton();
        btnCalcularFaturamento.setText("Calcular Faturamento");
        panel9.add(btnCalcularFaturamento, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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


