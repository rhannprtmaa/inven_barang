package tampilan;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import jdk.nashorn.internal.runtime.logging.Loggable;
import koneksi.koneksi;


public class transaksi_barangmasuk extends javax.swing.JDialog {


    public final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode1;
    
    public void Tanggal(){
        Date tanggal = new Date();
        tglBarangMasuk.setDate(tanggal);
    }
    
    public void auto_idbm(){
        try {
            Connection conn = new koneksi().connect();
            java.sql.Statement stat = conn.createStatement();
            Date tanggal_update = new Date();
            String tampilan_tgl = "yyMM";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan_tgl);
            String tanggal = String.valueOf(fm.format(tglBarangMasuk.getDate()));
            String sql = "select max(right(id_bm,4)) as no from tb_bm where id_bm like '%"+"BM" +tanggal.toString()+"%'";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                if(rs.first()==false){
                    txtIDBarangMasuk.setText("BM "+tanggal.toString()+"0001");
                }else{
                    rs.last();
                    int auto_id= rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int no_id = no.length();
                    //jumlah nomor 0
                    for(int j = 0; j<4 - no_id; j++){
                        no = "0"+no;
                    }
                    txtIDBarangMasuk.setText("BM"+tanggal.toString()+no);
                }
                 
            }
            rs.close();
            stat.close();
           
            
        } catch (SQLException error) {
             JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilknnan"+error);
        }
    }
    
    protected void reset(){
        Tanggal();
        auto_idbm();
        txtKodeSupplier.setText(null);
        txtNamaSupplier.setText(null);
        txtKeteranganBarangMasuk.setText(null);
        tglBarangMasuk.requestFocus();
    }
    public void noTable(){
        int Baris = tabmode.getRowCount();
        for(int a = 0; a<Baris; a++){
            String nomor = String.valueOf(a+1);
            tabmode.setValueAt(nomor+".", a, 0);
        }
    }
     public void noTableKategori(){
        int Baris = tabmode1.getRowCount();
        for(int a = 0; a<Baris; a++){
            String nomor = String.valueOf(a+1);
            tabmode1.setValueAt(nomor+".", a, 0);
        }
    }
    public void lebarKolom(){
        TableColumn kolom;
        tblBarangMasuk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        kolom = tblBarangMasuk.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(40);
//        ID
        kolom = tblBarangMasuk.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(50);
//        Kode Barang
        kolom = tblBarangMasuk.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(100);
//        Nama Barang
        kolom = tblBarangMasuk.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(100);
//        jumlah Barang
        kolom = tblBarangMasuk.getColumnModel().getColumn(4);
        kolom.setPreferredWidth(100);
//        Kode Kategori
        kolom = tblBarangMasuk.getColumnModel().getColumn(5);
        kolom.setPreferredWidth(70);
    }
     public void lebarKolom1(){
        TableColumn kolom;
        tableSupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        kolom = tableSupplier.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(40);
//        ID
        kolom = tableSupplier.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(50);
//        Kode Barang
        kolom = tableSupplier.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(100);
//        Nama Barang
        kolom = tableSupplier.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(100);
        
    }
    
    
    
    public void dataTable(){
        Object[] Baris = {"No","Tanggal","ID BM","Kode Supplier","Nama Supplier","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        tblBarangMasuk.setModel(tabmode);
        String sql = "select * FROM tb_bm JOIN tb_supplier ON tb_supplier.kode_supplier = tb_bm.kode_supplier ORDER BY tb_bm.id_bm DESC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String tanggal = hasil.getString("tanggal");
                String id_bm = hasil.getString("id_bm");
                String kode_supplier = hasil.getString("kode_supplier");
                String nama_supplier = hasil.getString("nama_supplier");
                String keterangan = hasil.getString("keterangan");
                String [] data = {"",tanggal,id_bm,kode_supplier,nama_supplier,keterangan};
                tabmode.addRow(data);
                noTable();
                lebarKolom();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public void dataTable1(){
        Object[] Baris = {"No","ID","Kode Supplier","Nama Supplier"};
        tabmode1 = new DefaultTableModel(null,Baris);
        tableSupplier.setModel(tabmode1);
        String sql = "select * from tb_supplier order by id_supplier DESC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String id_supplier = hasil.getString("id_supplier");
                String kode_supplier = hasil.getString("kode_supplier");
                String nama_supplier = hasil.getString("nama_supplier");
                String [] data = {"",id_supplier,kode_supplier,nama_supplier};
                tabmode1.addRow(data);
                noTableKategori();
                lebarKolom1();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public void pencarian(String sql){
        Object[] Baris = {"No","Tanggal","ID BM","Kode Supplier","Nama Supplier","Keterangan"};
        tabmode = new DefaultTableModel(null,Baris);
        tblBarangMasuk.setModel(tabmode);
        int baris = tblBarangMasuk.getRowCount();
        for (int i = 0; i < baris; i++) {
            tabmode.removeRow(i);
        }
//        String sql = "select * from tb_user order by id_user asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String tanggal = hasil.getString("tanggal");
                String id_bm = hasil.getString("id_bm");
                String kode_supplier = hasil.getString("kode_supplier");
                String nama_supplier = hasil.getString("nama_supplier");
                String keterangan = hasil.getString("keterangan");
                String [] data = {"",tanggal,id_bm,kode_supplier,nama_supplier,keterangan};
                tabmode.addRow(data);
                noTable();
                lebarKolom();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public void pencarian1(String sql){
        Object[] Baris = {"No","ID","Kode Supplier","Nama Supplier"};
        tabmode1 = new DefaultTableModel(null,Baris);
        tableSupplier.setModel(tabmode1);
        int baris = tableSupplier.getRowCount();
        for (int i = 0; i < baris; i++) {
            tabmode1.removeRow(i);
        }
//        String sql = "select * from tb_user order by id_user asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String id_supplier = hasil.getString("id_supplier");
                String kode_supplier = hasil.getString("kode_supplier");
                String nama_supplier = hasil.getString("nama_supplier");
                String [] data = {"",id_supplier,kode_supplier,nama_supplier};
                tabmode1.addRow(data);
                noTableKategori();
                lebarKolom1();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public transaksi_barangmasuk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Tanggal();
        auto_idbm();
        dataTable();
        lebarKolom();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listSupplier = new javax.swing.JFrame();
        txtCariSupplier = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSupplier = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNamaSupplier = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtKodeSupplier = new javax.swing.JTextField();
        btnKodeSupplier = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtKeteranganBarangMasuk = new javax.swing.JTextArea();
        tglBarangMasuk = new com.toedter.calendar.JDateChooser();
        txtIDBarangMasuk = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarangMasuk = new javax.swing.JTable();
        txtPencarian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        listSupplier.setTitle("Daftar Supplier");
        listSupplier.setModalExclusionType(null);
        listSupplier.setSize(new java.awt.Dimension(400, 400));

        txtCariSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariSupplierActionPerformed(evt);
            }
        });
        txtCariSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariSupplierKeyTyped(evt);
            }
        });

        tableSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSupplier.setRowHeight(30);
        tableSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSupplierMouseClicked(evt);
            }
        });
        tableSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableSupplierKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableSupplier);

        javax.swing.GroupLayout listSupplierLayout = new javax.swing.GroupLayout(listSupplier.getContentPane());
        listSupplier.getContentPane().setLayout(listSupplierLayout);
        listSupplierLayout.setHorizontalGroup(
            listSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addComponent(txtCariSupplier))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        listSupplierLayout.setVerticalGroup(
            listSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCariSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Barang");
        setModalityType(null);

        jPanel1.setBackground(new java.awt.Color(71, 93, 145));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tanggal");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ID Barang Masuk");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama Supplier");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Kode Supplier");

        txtNamaSupplier.setEditable(false);
        txtNamaSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaSupplierActionPerformed(evt);
            }
        });
        txtNamaSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaSupplierKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Transaksi Barang Masuk");

        btnSimpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSimpan.setText("Tambah");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtKodeSupplier.setEditable(false);
        txtKodeSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeSupplierActionPerformed(evt);
            }
        });
        txtKodeSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodeSupplierKeyPressed(evt);
            }
        });

        btnKodeSupplier.setText("...");
        btnKodeSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKodeSupplierActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Keterangan");

        txtKeteranganBarangMasuk.setColumns(20);
        txtKeteranganBarangMasuk.setLineWrap(true);
        txtKeteranganBarangMasuk.setRows(5);
        txtKeteranganBarangMasuk.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtKeteranganBarangMasuk);

        tglBarangMasuk.setDateFormatString("dd-MM-yyyy");

        txtIDBarangMasuk.setEditable(false);
        txtIDBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDBarangMasukActionPerformed(evt);
            }
        });
        txtIDBarangMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDBarangMasukKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(tglBarangMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtIDBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(32, 32, 32))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNamaSupplier)
                            .addComponent(jScrollPane3))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKodeSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNamaSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(71, 93, 145));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        tblBarangMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBarangMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblBarangMasuk.setRowHeight(30);
        tblBarangMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarangMasuk);

        txtPencarian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPencarian.setText("Pencarian");
        txtPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPencarianKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Cari Barang Masuk :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        String sql = "update tb_bm set id_bm=?, tanggal=?, kode_supplier=?, keterangan=? where id_bm='"+txtIDBarangMasuk.getText()+"'";
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(tglBarangMasuk.getDate()));
            
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIDBarangMasuk.getText());
            stat.setString(2, tanggal);
            stat.setString(3, txtKodeSupplier.getText());
            stat.setString(4, txtKeteranganBarangMasuk.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di Ubah");
            dataTable();
            reset();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di Ubah"+e);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblBarangMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMasukMouseClicked
        int baris = tblBarangMasuk.getSelectedRow();
        String a = tabmode.getValueAt(baris, 0).toString();
        String b = tabmode.getValueAt(baris, 1).toString();
        String c = tabmode.getValueAt(baris, 2).toString();
        String d = tabmode.getValueAt(baris, 3).toString();
        String e = tabmode.getValueAt(baris, 4).toString();
        String f = tabmode.getValueAt(baris, 5).toString();
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = null;
        try {
            dateValue= date.parse((String) tblBarangMasuk.getValueAt(baris, 1));
            
        } catch (ParseException ex) {
            Logger.getLogger(transaksi_barangmasuk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tglBarangMasuk.setDate(dateValue);
        txtIDBarangMasuk.setText(c);
        txtKodeSupplier.setText(d);
        txtNamaSupplier.setText(e);
        txtKeteranganBarangMasuk.setText(f);
        
    }//GEN-LAST:event_tblBarangMasukMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (txtIDBarangMasuk.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Maaf ID Barang Masuk tidak boleh kosong!");
        } else if (txtNamaSupplier.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Nama Supplier tidak boleh kosong!");
        } else if (txtKodeSupplier.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Kode Supplier tidak boleh kosong!");
        } else {
            String sql = "insert into tb_bm (id_bm,tanggal,kode_supplier,keterangan) values (?,?,?,?)";
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(tglBarangMasuk.getDate()));
            
            try {
                PreparedStatement stat = conn.prepareCall(sql);
                stat.setString(1, txtIDBarangMasuk.getText());
                stat.setString(2, tanggal);
                stat.setString(3, txtKodeSupplier.getText());
                stat.setString(4, txtKeteranganBarangMasuk.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil di simpan");
                dataTable();
                reset();
                txtIDBarangMasuk.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di simpan "+e);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNamaSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaSupplierKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnKodeSupplier.requestFocus();
        }
    }//GEN-LAST:event_txtNamaSupplierKeyPressed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
            int pilihan =  JOptionPane.showConfirmDialog(null, "Apakah yakin ingin menghapus data","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
            if(pilihan == 0){
                String sql = "delete from tb_bm where id_bm ='"+txtIDBarangMasuk.getText()+"'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil di hapus");
                    dataTable();
                    reset();
                    tglBarangMasuk.requestFocus();
                } catch (SQLException e) {
                     JOptionPane.showMessageDialog(null, "data gagal di hapus"+e);
                }
            }
            
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtPencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyTyped
       String pencariansql = "select * from tb_bm JOIN tb_supplier ON tb_supplier.kode_supplier = tb_bm.kode_supplier " 
               + "where tb_bm.kode_supplier like '%"+txtPencarian.getText()+"%' or "
               + "tb_supplier.nama_supplier like '%"+txtPencarian.getText()+"%' or "
               + "tb_bm.tanggal like '%"+txtPencarian.getText()+"%' or "
               + "tb_bm.keterangan like '%"+txtPencarian.getText()+"%' or "
               + "tb_bm.id_bm like '%"+txtPencarian.getText()+"%' "
               ;
       pencarian(pencariansql);
       lebarKolom();
    }//GEN-LAST:event_txtPencarianKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtCariSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariSupplierActionPerformed
         String pencariansql = "select * from tb_supplier " 
               + "where kode_supplier like '%"+txtCariSupplier.getText()+"%' or "
               + "nama_supplier like '%"+txtCariSupplier.getText()+"%' "
               ;
       pencarian1(pencariansql);
       lebarKolom1();
    }//GEN-LAST:event_txtCariSupplierActionPerformed

    private void btnKodeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKodeSupplierActionPerformed
           dataTable1();
           lebarKolom1();
           listSupplier.setVisible(true);
           listSupplier.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnKodeSupplierActionPerformed

    private void tableSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableSupplierKeyPressed
        
    }//GEN-LAST:event_tableSupplierKeyPressed

    private void tableSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSupplierMouseClicked
        int baris = tableSupplier.getSelectedRow();
        String no = tabmode1.getValueAt(baris, 0).toString();
        String id = tabmode1.getValueAt(baris, 1).toString();
        String kode_kategori = tabmode1.getValueAt(baris, 2).toString();
        String nama_kategori = tabmode1.getValueAt(baris, 3).toString();
        
        txtKodeSupplier.setText(kode_kategori);
        txtNamaSupplier.setText(nama_kategori);
        listSupplier.setVisible(false);
    }//GEN-LAST:event_tableSupplierMouseClicked

    private void txtCariSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariSupplierKeyTyped
        String pencariansql = "select * from tb_kategori " 
               + "where kode_kategori like '%"+txtCariSupplier.getText()+"%' or "
               + "nama_kategori like '%"+txtCariSupplier.getText()+"%' "
               ;
       pencarian1(pencariansql);
       lebarKolom1();
    }//GEN-LAST:event_txtCariSupplierKeyTyped

    private void txtKodeSupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeSupplierKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeSupplierKeyPressed

    private void txtKodeSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeSupplierActionPerformed

    private void txtNamaSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaSupplierActionPerformed

    private void txtIDBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDBarangMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDBarangMasukActionPerformed

    private void txtIDBarangMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDBarangMasukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDBarangMasukKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                transaksi_barangmasuk dialog = new transaksi_barangmasuk(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKodeSupplier;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JFrame listSupplier;
    private javax.swing.JTable tableSupplier;
    private javax.swing.JTable tblBarangMasuk;
    private com.toedter.calendar.JDateChooser tglBarangMasuk;
    private javax.swing.JTextField txtCariSupplier;
    private javax.swing.JTextField txtIDBarangMasuk;
    private javax.swing.JTextArea txtKeteranganBarangMasuk;
    private javax.swing.JTextField txtKodeSupplier;
    private javax.swing.JTextField txtNamaSupplier;
    private javax.swing.JTextField txtPencarian;
    // End of variables declaration//GEN-END:variables
}
