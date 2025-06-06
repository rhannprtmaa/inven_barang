package tampilan;

import auth.login;
import auth.data_user;
import javax.swing.JOptionPane;


public class menu_utama extends javax.swing.JFrame {

    String name = login.getU_name();
    String level = login.getU_level();
    
    
    public void hakAksesAdmin(){
        
    }
    public void hakAksesMember(){
        btnDataUser.setEnabled(false);
    }
    
    
    public menu_utama() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        labelName.setText(name);
        labelLevel.setText(level);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelLevel = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnDataUser = new javax.swing.JMenuItem();
        btnDataKategori = new javax.swing.JMenuItem();
        btnDataBarang = new javax.swing.JMenuItem();
        btnDataSupplier = new javax.swing.JMenuItem();
        btnDataCustomer = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        btnDataBarangMasuk = new javax.swing.JMenuItem();
        btnDataBarangKeluar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnLpnStockBarang = new javax.swing.JMenuItem();
        btnLpnBarangMasuk = new javax.swing.JMenuItem();
        btnLpnBarangKeluar = new javax.swing.JMenuItem();
        btnLpnDataSupplier = new javax.swing.JMenuItem();
        btnLpnDataCustomer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Utama Distribusi Barang");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Selamat Datang");

        labelName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(", Anda Login Sebagai");

        labelLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelLevel.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLevel)
                .addContainerGap(1048, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(743, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelName)
                    .addComponent(jLabel3)
                    .addComponent(labelLevel))
                .addGap(34, 34, 34))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu3.setText("File");

        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Master Data");

        btnDataUser.setText("Data User");
        btnDataUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataUserActionPerformed(evt);
            }
        });
        jMenu4.add(btnDataUser);

        btnDataKategori.setText("Data Kategori");
        btnDataKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataKategoriActionPerformed(evt);
            }
        });
        jMenu4.add(btnDataKategori);

        btnDataBarang.setText("Data Barang");
        btnDataBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataBarangActionPerformed(evt);
            }
        });
        jMenu4.add(btnDataBarang);

        btnDataSupplier.setText("Data Supplier");
        btnDataSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataSupplierActionPerformed(evt);
            }
        });
        jMenu4.add(btnDataSupplier);

        btnDataCustomer.setText("Data Customer");
        btnDataCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataCustomerActionPerformed(evt);
            }
        });
        jMenu4.add(btnDataCustomer);

        jMenuBar2.add(jMenu4);

        jMenu1.setText("Transaksi");

        btnDataBarangMasuk.setText("Barang Masuk");
        btnDataBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataBarangMasukActionPerformed(evt);
            }
        });
        jMenu1.add(btnDataBarangMasuk);

        btnDataBarangKeluar.setText("Barang Keluar");
        jMenu1.add(btnDataBarangKeluar);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Laporan");

        btnLpnStockBarang.setText("Laporan Stok Barang");
        jMenu2.add(btnLpnStockBarang);

        btnLpnBarangMasuk.setText("Laporan Barang Masuk");
        jMenu2.add(btnLpnBarangMasuk);

        btnLpnBarangKeluar.setText("Laporan Barang Keluar");
        jMenu2.add(btnLpnBarangKeluar);

        btnLpnDataSupplier.setText("Laporan Data Supplier");
        jMenu2.add(btnLpnDataSupplier);

        btnLpnDataCustomer.setText("Laporan Data Customer");
        jMenu2.add(btnLpnDataCustomer);

        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       String ObjButton[] = {"YES","NO"};
       int pilihan = JOptionPane.showOptionDialog(null, "Apakah anda yakin ingin keluar", "Message", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,ObjButton,ObjButton[1]);
       if(pilihan == 0){
           login lg = new login();
           lg.setLocationRelativeTo(null);
           lg.setVisible(true);
           dispose();
       }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnDataUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataUserActionPerformed
           data_user data_user = new data_user(this, rootPaneCheckingEnabled);
           data_user.setLocationRelativeTo(this);
           data_user.setVisible(true);
    }//GEN-LAST:event_btnDataUserActionPerformed

    private void btnDataKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataKategoriActionPerformed
           data_kategori data_kategori = new data_kategori(this, rootPaneCheckingEnabled);
           data_kategori.setLocationRelativeTo(this);
           data_kategori.setVisible(true);
    }//GEN-LAST:event_btnDataKategoriActionPerformed

    private void btnDataBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataBarangActionPerformed
        data_barang data_barang = new data_barang(this, rootPaneCheckingEnabled);
        data_barang.setLocationRelativeTo(this);
        data_barang.setVisible(true);
    }//GEN-LAST:event_btnDataBarangActionPerformed

    private void btnDataSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataSupplierActionPerformed
        data_supplier data_supplier = new data_supplier(this, rootPaneCheckingEnabled);
        data_supplier.setLocationRelativeTo(this);
        data_supplier.setVisible(true);
    }//GEN-LAST:event_btnDataSupplierActionPerformed

    private void btnDataCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataCustomerActionPerformed
        data_customer data_customer = new data_customer(this, rootPaneCheckingEnabled);
        data_customer.setLocationRelativeTo(this);
        data_customer.setVisible(true);
    }//GEN-LAST:event_btnDataCustomerActionPerformed

    private void btnDataBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataBarangMasukActionPerformed
        // TODO add your handling code here:
        transaksi_barangmasuk transaksi_barangmasuk = new transaksi_barangmasuk(this, rootPaneCheckingEnabled);
        transaksi_barangmasuk.setLocationRelativeTo(this);
        transaksi_barangmasuk.setVisible(true);
    }//GEN-LAST:event_btnDataBarangMasukActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new menu_utama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnDataBarang;
    private javax.swing.JMenuItem btnDataBarangKeluar;
    private javax.swing.JMenuItem btnDataBarangMasuk;
    private javax.swing.JMenuItem btnDataCustomer;
    private javax.swing.JMenuItem btnDataKategori;
    private javax.swing.JMenuItem btnDataSupplier;
    private javax.swing.JMenuItem btnDataUser;
    private javax.swing.JMenuItem btnLpnBarangKeluar;
    private javax.swing.JMenuItem btnLpnBarangMasuk;
    private javax.swing.JMenuItem btnLpnDataCustomer;
    private javax.swing.JMenuItem btnLpnDataSupplier;
    private javax.swing.JMenuItem btnLpnStockBarang;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelLevel;
    private javax.swing.JLabel labelName;
    // End of variables declaration//GEN-END:variables
}
