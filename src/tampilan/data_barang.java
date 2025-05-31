package tampilan;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksi.koneksi;


public class data_barang extends javax.swing.JDialog {


    public final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode1;
    
    protected void reset(){
        txtKodeBarang.setText(null);
        txtNamaBarang.setText(null);
        txtJumlahBarang.setText(null);
        txtID.setText(null);
        txtKodeKategori.setText(null);
        txtKodeBarang.requestFocus();
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
        tblBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        kolom = tblBarang.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(40);
//        ID
        kolom = tblBarang.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(50);
//        Kode Barang
        kolom = tblBarang.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(100);
//        Nama Barang
        kolom = tblBarang.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(100);
//        jumlah Barang
        kolom = tblBarang.getColumnModel().getColumn(4);
        kolom.setPreferredWidth(100);
//        Kode Kategori
        kolom = tblBarang.getColumnModel().getColumn(5);
        kolom.setPreferredWidth(70);
    }
     public void lebarKolom1(){
        TableColumn kolom;
        tableKategori.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        kolom = tableKategori.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(40);
//        ID
        kolom = tableKategori.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(50);
//        Kode Barang
        kolom = tableKategori.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(100);
//        Nama Barang
        kolom = tableKategori.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(100);
        
    }
    
    
    public void dataTable(){
        Object[] Baris = {"No","ID","Kode Barang","Nama Barang","Kode Kategori","Jumlah Barang"};
        tabmode = new DefaultTableModel(null,Baris);
        tblBarang.setModel(tabmode);
        String sql = "select * from tb_barang order by id_barang asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String id_barang = hasil.getString("id_barang");
                String kode_barang = hasil.getString("kode_barang");
                String nama_barang = hasil.getString("nama_barang");
                String kode_kategori = hasil.getString("kode_kategori");
                String jumlah_barang = hasil.getString("jumlah_barang");
                String [] data = {"",id_barang,kode_barang,nama_barang,kode_kategori,jumlah_barang};
                tabmode.addRow(data);
                noTable();
                lebarKolom();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public void dataTable1(){
        Object[] Baris = {"No","ID","Kode Kategori","Nama Kategori"};
        tabmode1 = new DefaultTableModel(null,Baris);
        tableKategori.setModel(tabmode1);
        String sql = "select * from tb_kategori order by id_kategori asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String id_kategori = hasil.getString("id_kategori");
                String kode_kategori = hasil.getString("kode_kategori");
                String nama_kategori = hasil.getString("nama_kategori");
                String [] data = {"",id_kategori,kode_kategori,nama_kategori};
                tabmode1.addRow(data);
                noTableKategori();
                lebarKolom1();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public void pencarian(String sql){
        Object[] Baris = {"No","ID","Kode Barang","Nama Barang","Jumlah Barang","Kode Kategori"};
        tabmode = new DefaultTableModel(null,Baris);
        tblBarang.setModel(tabmode);
        int baris = tblBarang.getRowCount();
        for (int i = 0; i < baris; i++) {
            tabmode.removeRow(i);
        }
//        String sql = "select * from tb_user order by id_user asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String id_barang = hasil.getString("id_barang");
                String kode_barang = hasil.getString("kode_barang");
                String nama_barang = hasil.getString("nama_barang");
                String kode_kategori = hasil.getString("kode_kategori");
                String jumlah_barang = hasil.getString("jumlah_barang");
                String [] data = {"",id_barang,kode_barang,nama_barang,kode_kategori,jumlah_barang};
                tabmode.addRow(data);
                noTable();
                lebarKolom();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public void pencarian1(String sql){
        Object[] Baris = {"No","ID","Kode Kategori","Nama Kategori"};
        tabmode1 = new DefaultTableModel(null,Baris);
        tableKategori.setModel(tabmode1);
        int baris = tableKategori.getRowCount();
        for (int i = 0; i < baris; i++) {
            tabmode1.removeRow(i);
        }
//        String sql = "select * from tb_user order by id_user asc";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String id_kategori = hasil.getString("id_kategori");
                String kode_kategori = hasil.getString("kode_kategori");
                String nama_kategori = hasil.getString("nama_kategori");
                String [] data = {"",id_kategori,kode_kategori,nama_kategori};
                tabmode1.addRow(data);
                noTableKategori();
                lebarKolom1();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa di tampilkan"+e);
        }
    }
    
    
    public data_barang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dataTable();
        lebarKolom();
        txtID.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameDaftarKategori = new javax.swing.JFrame();
        txtCariKategori = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKategori = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtJumlahBarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtKodeKategori = new javax.swing.JTextField();
        btnCariKategori = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        txtPencarian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        frameDaftarKategori.setTitle("Daftar Kategori");
        frameDaftarKategori.setModalExclusionType(null);
        frameDaftarKategori.setSize(new java.awt.Dimension(400, 400));

        txtCariKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariKategoriActionPerformed(evt);
            }
        });
        txtCariKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKategoriKeyTyped(evt);
            }
        });

        tableKategori.setModel(new javax.swing.table.DefaultTableModel(
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
        tableKategori.setRowHeight(30);
        tableKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKategoriMouseClicked(evt);
            }
        });
        tableKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKategoriKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableKategori);

        javax.swing.GroupLayout frameDaftarKategoriLayout = new javax.swing.GroupLayout(frameDaftarKategori.getContentPane());
        frameDaftarKategori.getContentPane().setLayout(frameDaftarKategoriLayout);
        frameDaftarKategoriLayout.setHorizontalGroup(
            frameDaftarKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameDaftarKategoriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameDaftarKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addComponent(txtCariKategori))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frameDaftarKategoriLayout.setVerticalGroup(
            frameDaftarKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameDaftarKategoriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Data Barang");
        setBackground(new java.awt.Color(204, 0, 204));
        setModalityType(null);

        jPanel1.setBackground(new java.awt.Color(71, 93, 145));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Kode Barang");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Jumlah Barang");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Kode Kategori");

        txtKodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodeBarangKeyPressed(evt);
            }
        });

        txtNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNamaBarangKeyPressed(evt);
            }
        });

        txtJumlahBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahBarangKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Buat Barang Baru");

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(102, 102, 102));
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

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

        txtKodeKategori.setEditable(false);
        txtKodeKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKodeKategoriKeyPressed(evt);
            }
        });

        btnCariKategori.setText("...");
        btnCariKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKategoriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtKodeKategori, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtJumlahBarang, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 55, Short.MAX_VALUE)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtKodeKategori))
                        .addComponent(btnCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(208, 208, 208))
        );

        jPanel2.setBackground(new java.awt.Color(71, 93, 145));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblBarang.setRowHeight(30);
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        txtPencarian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPencarian.setText("Pencarian");
        txtPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPencarianKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Cari Barang :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        String sql = "update tb_barang set kode_barang=?, nama_barang=?, kode_kategori=?, jumlah_barang=? where id_barang='"+txtID.getText()+"'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtKodeBarang.getText());
            stat.setString(2, txtNamaBarang.getText());
            stat.setString(3, txtKodeKategori.getText());
            stat.setString(4, txtJumlahBarang.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di Ubah");
            dataTable();
            reset();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di Ubah"+e);
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        int baris = tblBarang.getSelectedRow();
        String no = tabmode.getValueAt(baris, 0).toString();
        String id = tabmode.getValueAt(baris, 1).toString();
        String kode_barang = tabmode.getValueAt(baris, 2).toString();
        String nama_barang = tabmode.getValueAt(baris, 3).toString();
        String jumlah_barang = tabmode.getValueAt(baris, 5).toString();
        String kode_kategori = tabmode.getValueAt(baris, 4).toString();
        
        txtID.setText(id);
        txtKodeBarang.setText(kode_barang);
        txtNamaBarang.setText(nama_barang);
        txtJumlahBarang.setText(jumlah_barang);
        txtKodeKategori.setText(kode_kategori);
        
    }//GEN-LAST:event_tblBarangMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
       reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if(txtKodeBarang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Maaf Kode Barang tidak boleh kosong!");
        } else if (txtNamaBarang.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Maaf Nama Barang tidak boleh kosong!");
        } else if (txtJumlahBarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Jumlah tidak boleh kosong!");
        } else if (txtKodeKategori.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Kode Kategori tidak boleh kosong!");
        } else {
            String sql = "insert into tb_barang (kode_barang,nama_barang,kode_kategori,jumlah_barang) values (?,?,?,?)";
            try {
                PreparedStatement stat = conn.prepareCall(sql);
                stat.setString(1, txtKodeBarang.getText());
                stat.setString(2, txtNamaBarang.getText());
                
                stat.setString(3, txtKodeKategori.getText());
                stat.setString(4, txtJumlahBarang.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasil di simpan");
                
                dataTable();
                reset();
                txtKodeBarang.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di simpan "+e);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtKodeBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeBarangKeyPressed
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                txtNamaBarang.requestFocus();
            }
    }//GEN-LAST:event_txtKodeBarangKeyPressed

    private void txtNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtJumlahBarang.requestFocus();
        }
    }//GEN-LAST:event_txtNamaBarangKeyPressed

    private void txtJumlahBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCariKategori.requestFocus();
        }
    }//GEN-LAST:event_txtJumlahBarangKeyPressed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
            int pilihan =  JOptionPane.showConfirmDialog(null, "Apakah yakin ingin menghapus data","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
            if(pilihan == 0){
                String sql = "delete from tb_barang where id_barang ='"+txtID.getText()+"'";
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "data berhasil di hapus");
                    dataTable();
                    reset();
                    txtKodeBarang.requestFocus();
                } catch (SQLException e) {
                     JOptionPane.showMessageDialog(null, "data gagal di hapus"+e);
                }
            }
            
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtPencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyTyped
       String pencariansql = "select * from tb_barang " 
               + "where nama_barang like '%"+txtPencarian.getText()+"%' or "
               + "kode_barang like '%"+txtPencarian.getText()+"%' or "
               + "jumlah_barang like '%"+txtPencarian.getText()+"%' or "
               + "kode_kategori like '%"+txtPencarian.getText()+"%' "
               ;
       pencarian(pencariansql);
       lebarKolom();
    }//GEN-LAST:event_txtPencarianKeyTyped

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtKodeKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeKategoriKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeKategoriKeyPressed

    private void txtCariKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariKategoriActionPerformed
         String pencariansql = "select * from tb_kategori " 
               + "where kode_kategori like '%"+txtCariKategori.getText()+"%' or "
               + "nama_kategori like '%"+txtCariKategori.getText()+"%' "
               ;
       pencarian1(pencariansql);
       lebarKolom1();
    }//GEN-LAST:event_txtCariKategoriActionPerformed

    private void btnCariKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKategoriActionPerformed
           dataTable1();
           lebarKolom1();
           frameDaftarKategori.setVisible(true);
           frameDaftarKategori.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnCariKategoriActionPerformed

    private void tableKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKategoriKeyPressed
        
    }//GEN-LAST:event_tableKategoriKeyPressed

    private void tableKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKategoriMouseClicked
       int baris = tableKategori.getSelectedRow();
        String no = tabmode1.getValueAt(baris, 0).toString();
        String id = tabmode1.getValueAt(baris, 1).toString();
        String kode_kategori = tabmode1.getValueAt(baris, 2).toString();
        String nama_kategori = tabmode1.getValueAt(baris, 3).toString();
        
        txtKodeKategori.setText(nama_kategori);
        frameDaftarKategori.setVisible(false);
    }//GEN-LAST:event_tableKategoriMouseClicked

    private void txtCariKategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKategoriKeyTyped
        String pencariansql = "select * from tb_kategori " 
               + "where kode_kategori like '%"+txtCariKategori.getText()+"%' or "
               + "nama_kategori like '%"+txtCariKategori.getText()+"%' "
               ;
       pencarian1(pencariansql);
       lebarKolom1();
    }//GEN-LAST:event_txtCariKategoriKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                data_barang dialog = new data_barang(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCariKategori;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JFrame frameDaftarKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableKategori;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtCariKategori;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtJumlahBarang;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtKodeKategori;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtPencarian;
    // End of variables declaration//GEN-END:variables
}
