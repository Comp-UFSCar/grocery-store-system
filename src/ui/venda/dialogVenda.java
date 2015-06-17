package ui.venda;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import projetomercado.Cliente;
import projetomercado.Produto;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class dialogVenda extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tblVenda;
    private JTextField txtProdCod;
    private JTextField txtProdQtd;
    private JTextField txtNumRegistro;
    private JTextField txtData;
    private JTextField txtCpf;
    private JButton btnInserirProd;
    private JButton btnAlterarProd;
    private JButton btnRemoverProd;
    private JScrollPane panelTblVenda;

    private DefaultTableModel modelVenda;
    private Border borderError;
    private Border borderDefault;
    private Border borderTblDefault;

    private Map<Integer, Double> produtos;

    public dialogVenda() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        borderDefault = txtNumRegistro.getBorder();
        borderTblDefault = panelTblVenda.getBorder();
        borderError = BorderFactory.createLineBorder(Color.red);
        produtos = new HashMap<Integer, Double>();

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
        btnInserirProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onInserirProd();
            }
        });
        btnAlterarProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAlterarProd();
            }
        });
        btnRemoverProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemoverProd();
            }
        });
        tblVenda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                onTableClicked();
            }
        });
    }

    private int cod;
    private double qtd;
    private void onTableClicked() {
        txtProdCod.setText(String.valueOf(tblVenda.getValueAt(tblVenda.getSelectedRow(), 0)));
        txtProdQtd.setText(String.valueOf(tblVenda.getValueAt(tblVenda.getSelectedRow(), 1)));
        btnAlterarProd.setEnabled(true);
        btnRemoverProd.setEnabled(true);
        cod = Integer.parseInt(txtProdCod.getText());
        qtd = Double.parseDouble(txtProdQtd.getText());
    }

    private void onRemoverProd() {
        btnAlterarProd.setEnabled(false);
        btnRemoverProd.setEnabled(false);
        produtos.remove(Integer.parseInt(txtProdCod.getText()));
        refreshTable();
        clearProdFields();
    }

    private void onAlterarProd() {
        btnAlterarProd.setEnabled(false);
        btnRemoverProd.setEnabled(false);
        produtos.remove(cod);
        produtos.put(Integer.parseInt(txtProdCod.getText()), Double.parseDouble(txtProdQtd.getText()));
        refreshTable();
        clearProdFields();
    }

    private void onInserirProd() {
        if (validateProdutoForm()) {
            produtos.put(Integer.parseInt(txtProdCod.getText()), Double.parseDouble(txtProdQtd.getText()));
            refreshTable();
            clearProdFields();
        }
    }

    private void clearProdFields() {
        txtProdCod.setText("");
        txtProdQtd.setText("");
    }

    private void onOK() {
        if (validateForm()) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(txtNumRegistro.getText() + " , ");
                sb.append(txtCpf.getText() + " , ");
                sb.append(txtData.getText());
                for (Map.Entry<Integer, Double> entry : produtos.entrySet()) {
                    sb.append(" , " + entry.getKey() + " , ");
                    sb.append(entry.getValue());
                }
                System.out.println(sb);
                dispose();
            } catch (RuntimeException re) {
                JOptionPane.showMessageDialog(null, re, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        setModelVenda();
        tblVenda = new JTable(modelVenda) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setTblVendaPreferences();
    }

    private void setModelVenda() {
        modelVenda = new DefaultTableModel() {
            String[] produto = {"Codigo", "Quantidade"};

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

    private void setTblVendaPreferences() {
        DefaultTableCellRenderer centered = new DefaultTableCellRenderer();
        centered.setHorizontalAlignment(JLabel.CENTER);

        ((DefaultTableCellRenderer) tblVenda.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblVenda.getColumnModel().getColumn(0).setCellRenderer(centered);
        tblVenda.getColumnModel().getColumn(1).setCellRenderer(centered);
    }

    private boolean validateForm() {
        int error = 0;
        if (txtNumRegistro.getText().isEmpty()) {
            txtNumRegistro.setBorder(borderError);
            error++;
        } else
            txtNumRegistro.setBorder(borderDefault);

        if (txtData.getText().isEmpty()) {
            txtData.setBorder(borderError);
            error++;
        } else
            txtData.setBorder(borderDefault);

        if (txtCpf.getText().isEmpty()) {
            txtCpf.setBorder(borderError);
            error++;
        } else
            txtCpf.setBorder(borderDefault);

        if (tblVenda.getRowCount() == 0) {
            panelTblVenda.setBorder(borderError);
            error++;
            JOptionPane.showMessageDialog(null, "Nenhum produto adicionado!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else
            panelTblVenda.setBorder(borderTblDefault);

        if (error > 0)
            return false;
        return true;
    }

    private boolean validateProdutoForm() {
        int error = 0;
        if (txtProdCod.getText().isEmpty()) {
            txtProdCod.setBorder(borderError);
            error++;
        } else
            txtProdCod.setBorder(borderDefault);

        if (txtProdQtd.getText().isEmpty()) {
            txtProdQtd.setBorder(borderError);
            error++;
        } else
            txtProdQtd.setBorder(borderDefault);

        if (error > 0)
            return false;
        return true;
    }

    // region Table
    private void insertRow(int cod, double qtd) {
        modelVenda.addRow(new Object[]{cod, qtd});
    }

    private void populateTable() {
        if (!produtos.isEmpty()) {
            for (Map.Entry<Integer, Double> entry : produtos.entrySet()) {
                insertRow(entry.getKey(), entry.getValue());
            }
        }
    }

    private void clearTable(DefaultTableModel tblModel) {
        // remove all rows
        for (int i = tblModel.getRowCount() - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    public void refreshTable() {
        clearTable(modelVenda);
        populateTable();
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
        contentPane.setLayout(new GridLayoutManager(5, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setMinimumSize(new Dimension(500, 600));
        contentPane.setPreferredSize(new Dimension(550, 600));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancelar");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder("Lista de Produtos"));
        panelTblVenda = new JScrollPane();
        panel3.add(panelTblVenda, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelTblVenda.setViewportView(tblVenda);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(2, 5, 2, 5), -1, -1));
        contentPane.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder("Cliente"));
        final JLabel label1 = new JLabel();
        label1.setText("CPF");
        panel4.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtCpf = new JTextField();
        panel4.add(txtCpf, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 7, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 2, new Insets(2, 5, 2, 5), -1, -1));
        contentPane.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder("Venda"));
        final JLabel label2 = new JLabel();
        label2.setText("Numero Registro");
        panel5.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtNumRegistro = new JTextField();
        panel5.add(txtNumRegistro, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Data");
        panel5.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtData = new JTextField();
        panel5.add(txtData, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 4, new Insets(2, 5, 2, 5), -1, -1));
        contentPane.add(panel6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder("Produto"));
        final JLabel label4 = new JLabel();
        label4.setText("Codigo");
        panel6.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtProdCod = new JTextField();
        panel6.add(txtProdCod, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Quantidade");
        panel6.add(label5, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtProdQtd = new JTextField();
        panel6.add(txtProdQtd, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panel7, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnInserirProd = new JButton();
        btnInserirProd.setText("Inserir");
        panel7.add(btnInserirProd, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel7.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnRemoverProd = new JButton();
        btnRemoverProd.setEnabled(false);
        btnRemoverProd.setText("Remover");
        panel7.add(btnRemoverProd, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnAlterarProd = new JButton();
        btnAlterarProd.setEnabled(false);
        btnAlterarProd.setText("Alterar");
        panel7.add(btnAlterarProd, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    // endregion

}
