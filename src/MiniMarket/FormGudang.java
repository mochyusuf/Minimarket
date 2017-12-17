/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MiniMarket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author lenovo G400S
 */
public class FormGudang extends javax.swing.JFrame {

    public Connection con=null;
    public Statement stt;
    DefaultTableModel tabModel;
     ResultSet rsbarang=null;
     ResultSet rsid=null;
     String angka;
    double selisih,angka1,angka2;
    private Profile Profile;
    
    public FormGudang() {
        super ("Aplikasi Penjualan (beta version)");
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minimarket?zeroDateTimeBehavior=convertToNull","root","");
            stt = con.createStatement();
            Object[] judul_kolom = {"ID Barang","Quantity","Nama Barang","Harga Beli","Harga Jual","Selisih"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            TabelB.setModel(tabModel);
            } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Gagal terhubung ke Database CEK ULANG DATABASE!");
            }
        tampilData();
        
         ActionListener aksi = new ActionListener() {

       // @Override
        public void actionPerformed(ActionEvent e) {
     //       throw new UnsupportedOperationException("Not supported yet.");
            DateFormat tgl = new SimpleDateFormat("yyyy-dd-MM/HH:mm:ss");
            Date hasil = new Date();
            String tgl_skr = tgl.format(hasil).toString();           
            jTextFieldTgl.setText(tgl_skr);
            tampilNama();
            
            
        }
        
    };              
        new Timer(1000, aksi).start();
        
    }
    
       void tampilNama(){
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            rsbarang=stm.executeQuery("select * from user where id_user like '%"+ID_User.getText()+"%'");//mengambil data barang
            while (rsbarang.next()){
                   Nama_User.setText(rsbarang.getString("level"));
                }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void tampilData(){
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from barang");//mengambil data barang
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("kode_barang"),
                               rsbarang.getString("quantity"),
                               rsbarang.getString("nama_barang"),
                               rsbarang.getString("harga_beli"),
                               rsbarang.getString("harga_jual"),
                               rsbarang.getString("selisih"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    void tblKeForm(){
        JID_Barang.setText(tabModel.getValueAt(TabelB.getSelectedRow(),0)+"");
        JQuantity.setText(tabModel.getValueAt(TabelB.getSelectedRow(),1)+"");
        JNama_Barang.setText(tabModel.getValueAt(TabelB.getSelectedRow(),2)+"");
        JHarga_Beli.setText(tabModel.getValueAt(TabelB.getSelectedRow(),3)+"");
        JHarga_Jual.setText(tabModel.getValueAt(TabelB.getSelectedRow(),4)+"");
        JSelisih.setText(tabModel.getValueAt(TabelB.getSelectedRow(),5)+"");
    }
    
    void kosongkanForm(){
        JID_Barang.setText("");
        JQuantity.setText("");
        JNama_Barang.setText("");
        JHarga_Beli.setText("");
        JHarga_Jual.setText("");
        JSelisih.setText("");
    }
    void Selisih(){
        angka1 = Integer.parseInt(JHarga_Beli.getText());
        angka2 = Integer.parseInt(JHarga_Jual.getText());
                selisih = angka2 - angka1;
                angka = Integer.toString((int) selisih);
                JSelisih.setText(angka);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panelGambar1 = new MiniMarket.panelGambar();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JID_Barang = new javax.swing.JTextField();
        JQuantity = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Edit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelB = new javax.swing.JTable();
        JNama_Barang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        JHarga_Beli = new javax.swing.JTextField();
        Tambah = new javax.swing.JButton();
        Hapus = new javax.swing.JButton();
        Cari = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JHarga_Jual = new javax.swing.JTextField();
        JSelisih = new javax.swing.JTextField();
        jTextFieldCari = new javax.swing.JTextField();
        jTextFieldTgl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ID_User = new javax.swing.JLabel();
        Nama_User = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Menubar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setText("Profile Staf");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jButton3.setText("Log Out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, -1));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setText("Kelola Barang");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        jLabel6.setText("ID Barang");

        jLabel16.setText("Tabel Data Barang");

        jLabel8.setText("Quantity");

        jLabel18.setText("Tanggal & Waktu");

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        TabelB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Quantity (Gudang)", "Nama Barang", "Harga Beli", "Harga Jual", "Selisih"
            }
        ));
        TabelB.setOpaque(false);
        TabelB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelBMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelB);

        jLabel11.setText("Harga Beli");

        Tambah.setText("Tambah");
        Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahActionPerformed(evt);
            }
        });

        Hapus.setText("Hapus");
        Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusActionPerformed(evt);
            }
        });

        Cari.setText("Cari");
        Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariActionPerformed(evt);
            }
        });

        jLabel7.setText("Harga Jual");

        jLabel9.setText("Selisih");

        JSelisih.setEditable(false);

        jTextFieldTgl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldTgl.setText("tanggal");

        jLabel12.setText("Nama Barang");

        jLabel13.setText("Nama Barang");

        jLabel4.setText("ID Staf        :");

        jLabel3.setText("Nama Staf   :");

        ID_User.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ID_User.setText("your ID");

        Nama_User.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Nama_User.setText("your Name");

        javax.swing.GroupLayout panelGambar1Layout = new javax.swing.GroupLayout(panelGambar1);
        panelGambar1.setLayout(panelGambar1Layout);
        panelGambar1Layout.setHorizontalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGambar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ID_User)
                            .addComponent(Nama_User))
                        .addGap(81, 81, 81)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jTextFieldTgl)))
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel16)
                        .addGap(181, 181, 181)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cari))
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelGambar1Layout.createSequentialGroup()
                                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelGambar1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(51, 51, 51))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelGambar1Layout.createSequentialGroup()
                                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JID_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JNama_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JHarga_Beli, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JHarga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JSelisih, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
                            .addGroup(panelGambar1Layout.createSequentialGroup()
                                .addComponent(Tambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Edit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Hapus)
                                .addGap(18, 18, 18)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelGambar1Layout.setVerticalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGambar1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ID_User)
                            .addComponent(jLabel4)))
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nama_User)
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGambar1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTgl)))
                .addGap(12, 12, 12)
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Cari)))
                .addGap(6, 6, 6)
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JID_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(10, 10, 10)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JNama_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(10, 10, 10)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JHarga_Beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(10, 10, 10)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JHarga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JSelisih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(20, 20, 20)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Edit)
                            .addComponent(Hapus)
                            .addComponent(Tambah)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        getContentPane().add(panelGambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 860, 440));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon_stock2.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Anda Login Sebagai Staf Gudang");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 80));

        file.setText("File");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/pintuu.png"))); // NOI18N
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        file.add(jMenuItem1);

        Menubar.add(file);

        jMenu3.setText("Help");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        Menubar.add(jMenu3);

        jMenu4.setText("About");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        Menubar.add(jMenu4);

        setJMenuBar(Menubar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
       if (JID_Barang.getText().equals("") || JQuantity.getText().equals("") 
            || JNama_Barang.getText().equals("") || JHarga_Beli.getText().equals("") 
            || JHarga_Jual.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Form Tidak Boleh Kosong");
        }else{
        Selisih();
        try{
    Statement stm=con.createStatement();
    stm.executeUpdate("update barang set kode_barang='"+JID_Barang.getText()+"',"+
                "quantity='"+JQuantity.getText()+"',"+
                "nama_barang='"+JNama_Barang.getText()+"',"+
                "harga_beli='"+JHarga_Beli.getText()+"',"+
                "harga_jual='"+JHarga_Jual.getText()+"',"+
                "selisih='"+JSelisih.getText()+"'"
                + "where kode_barang like '%"+JID_Barang.getText()+"%'");
    tampilData();
    kosongkanForm();
}catch(Exception e){
    e.printStackTrace();
}}
    }//GEN-LAST:event_EditActionPerformed

    private void CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariActionPerformed
        try {
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from barang where nama_barang like '%"+jTextFieldCari.getText()+"%'");
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("kode_barang"),
                               rsbarang.getString("quantity"),
                               rsbarang.getString("nama_barang"),
                               rsbarang.getString("harga_beli"),
                               rsbarang.getString("harga_jual"),
                               rsbarang.getString("selisih"),
                };
                tabModel.addRow(data);
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
    }//GEN-LAST:event_CariActionPerformed

    private void TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahActionPerformed
       if (JID_Barang.getText().equals("") || JQuantity.getText().equals("") 
            || JNama_Barang.getText().equals("") || JHarga_Beli.getText().equals("") 
            || JHarga_Jual.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Form Tidak Boleh Kosong");
        }else{
        Selisih();
        try{
    Statement stm=con.createStatement();
    stm.executeUpdate("insert into barang set kode_barang='"+JID_Barang.getText()+"',"+
                      "quantity='"+JQuantity.getText()+"',"+
                      "nama_barang='"+JNama_Barang.getText()+"',"+
                      "harga_beli='"+JHarga_Beli.getText()+"',"+
                      "harga_jual='"+JHarga_Jual.getText()+"',"+
                      "selisih='"+JSelisih.getText()+"'");
    
    kosongkanForm();
      
    //set Focus Kode Barang
    JID_Barang.requestFocus();
    
    tampilData();
}catch(Exception e){
    e.printStackTrace();
}
       }
    }//GEN-LAST:event_TambahActionPerformed

    private void HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusActionPerformed
        Selisih();
        try{
    Statement stm=con.createStatement();
    stm.executeUpdate("delete from barang where kode_barang='"+JID_Barang.getText()+"'");
    tampilData();
    kosongkanForm();
}catch(Exception e){
    e.printStackTrace();
}
    }//GEN-LAST:event_HapusActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TabelBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelBMouseClicked
     tblKeForm();
    }//GEN-LAST:event_TabelBMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Profile = new Profile();
        new Profile().setVisible(true);
        Profile.ID_User.setText(Login.user.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new Tentang().setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        new Help().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormGudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cari;
    private javax.swing.JButton Edit;
    private javax.swing.JButton Hapus;
    public static javax.swing.JLabel ID_User;
    private javax.swing.JTextField JHarga_Beli;
    private javax.swing.JTextField JHarga_Jual;
    private javax.swing.JTextField JID_Barang;
    private javax.swing.JTextField JNama_Barang;
    private javax.swing.JTextField JQuantity;
    private javax.swing.JTextField JSelisih;
    private javax.swing.JMenuBar Menubar;
    private javax.swing.JLabel Nama_User;
    private javax.swing.JTable TabelB;
    private javax.swing.JButton Tambah;
    private javax.swing.JMenu file;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldCari;
    private javax.swing.JLabel jTextFieldTgl;
    private MiniMarket.panelGambar panelGambar1;
    // End of variables declaration//GEN-END:variables
}
