/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MiniMarket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 *
 * @author lenovo G400S
 */
public class FormKasir extends javax.swing.JFrame {
    public Connection con;
    public Statement stt;
    DefaultTableModel tabModel;
    ResultSet rskasir=null;
    String angka;
    double jumlah,angka1,angka2,stok,Quantity;
    private Profile Profile;
    
    
    
    public FormKasir() {
        super ("Aplikasi Penjualan (beta version)");
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minimarket?zeroDateTimeBehavior=convertToNull","root","");
            stt = con.createStatement();
            Object[] judul_kolom = {"kode Barang","Nama Barang","Harga Barang","Quantity","Total"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            jTableRincian.setModel(tabModel);
            } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Gagal terhubung ke Database CEK ULANG DATABASE!");
            }
        
        ActionListener aksi = new ActionListener() {

       // @Override
        public void actionPerformed(ActionEvent e) {
                 DateFormat tgl = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
            Date hasil = new Date();
            String tgl_skr = tgl.format(hasil).toString();           
            jTextFieldTglFktr.setText(tgl_skr);
            tampilNama();
            
        }
        
    };              
        new Timer(1000, aksi).start();
        
        TampilFaktur();
    }
    
       void tampilNama(){
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            rskasir=stm.executeQuery("select * from user where id_user like '%"+ID_User.getText()+"%'");
            while(rskasir.next()){
             jTextFieldPnrmFktr.setText(rskasir.getString("nama_user"));
            }
            //mengambil data
            
                
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void tampilrincian(){
        try{
            Statement stmr;
            stmr=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rskasir=stmr.executeQuery("select trans_jual.no_faktur,trans_jual.qty_jual,trans_jual.kode_barang,barang.nama_barang,barang.harga_jual,(barang.harga_jual*trans_jual.qty_jual)as jumlah from trans_jual inner join barang on barang.kode_barang=trans_jual.kode_barang where no_faktur='"+jTextFieldNoFktr.getText()+"'");
            while(rskasir.next()){
                Object[] data={rskasir.getString("kode_barang"),
                               rskasir.getString("nama_barang"),
                               rskasir.getString("harga_jual"),
                               rskasir.getString("qty_jual"),
                               rskasir.getString("jumlah")
                };
                tabModel.addRow(data);
            }
            
        
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
        
    void Bayar(){
        angka1 = Integer.parseInt(JPembayaran.getText());
        angka2 = Integer.parseInt(jTextFieldTtlFktr.getText());
                jumlah = angka1 - angka2;
                angka = Integer.toString((int) jumlah);
                
    } 
    
    void tblKeForm(){
        JID_Barang2.setText(tabModel.getValueAt(jTableRincian.getSelectedRow(),0)+"");
        JNama_Barang2.setText(tabModel.getValueAt(jTableRincian.getSelectedRow(),1)+"");
        jTextFieldQty.setText(tabModel.getValueAt(jTableRincian.getSelectedRow(),2)+"");
        JHarga_Barang2.setText(tabModel.getValueAt(jTableRincian.getSelectedRow(),3)+"");
        
    }
    
    void kosongkanForm(){
        JID_Barang2.setText("");
        JNama_Barang2.setText("");
        jTextFieldQty.setText("");
        JHarga_Barang2.setText("");
        
    }
    
    void updatebarang(){
        try{
        Statement stm=con.createStatement();
        stm.executeUpdate("UPDATE barang set quantity = quantity - '"+jTextFieldQty.getText()+"' where nama_barang ='"+JNama_Barang2.getText()+"'");        
        }catch(Exception e){         
            e.printStackTrace();
        }
    }
    
    void TampilFaktur(){
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            rskasir=stm.executeQuery("select * from faktur");//mengambil data barang
            while(rskasir.next()){
                   FakturTerakhir.setText(rskasir.getString("no_faktur"));
        }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void KosongTransaksi(){
        jTextFieldNoFktr.setText("");
        JID_Barang2.setText("");
        JNama_Barang2.setText("");
        JHarga_Barang2.setText("");
        JStok.setText("");
        jTextFieldQty.setText("");
        jTextFieldTtlFktr.setText("");
        JPembayaran.setText("");
        JKembalian.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        panelGambar1 = new MiniMarket.panelGambar();
        idbarang = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        JID_Barang2 = new javax.swing.JTextField();
        jTextFieldQty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRincian = new javax.swing.JTable();
        Cari = new javax.swing.JButton();
        JTransaksi_Baru = new javax.swing.JButton();
        totalbayar = new javax.swing.JLabel();
        pembayaran = new javax.swing.JLabel();
        jTextFieldTtlFktr = new javax.swing.JTextField();
        JPembayaran = new javax.swing.JTextField();
        Bayar = new javax.swing.JButton();
        kembalian = new javax.swing.JLabel();
        JKembalian = new javax.swing.JTextField();
        tabeltrans = new javax.swing.JLabel();
        buatfaktur = new javax.swing.JLabel();
        jTextFieldNoFktr = new javax.swing.JTextField();
        tanggal = new javax.swing.JLabel();
        Beli = new javax.swing.JButton();
        Hapus = new javax.swing.JButton();
        namabarang = new javax.swing.JLabel();
        harga = new javax.swing.JLabel();
        JNama_Barang2 = new javax.swing.JTextField();
        JHarga_Barang2 = new javax.swing.JTextField();
        JBuat = new javax.swing.JButton();
        jTextFieldTglFktr = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JStok = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        FakturTerakhir = new javax.swing.JLabel();
        ID_User = new javax.swing.JLabel();
        jTextFieldPnrmFktr = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        andalogin = new javax.swing.JLabel();
        Menubar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jLabel2.setText("jLabel2");

        jLabel9.setText("jLabel9");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 102));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setText("Profile Kasir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jButton9.setText("Log Out");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        panelGambar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idbarang.setText("Kode barang");
        panelGambar1.add(idbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        quantity.setText("Quantity");
        panelGambar1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        JID_Barang2.setEditable(false);
        panelGambar1.add(JID_Barang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 222, -1));

        jTextFieldQty.setEditable(false);
        panelGambar1.add(jTextFieldQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 140, -1));

        jTableRincian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga Barang", "Quantity", "Jumlah"
            }
        ));
        jScrollPane2.setViewportView(jTableRincian);

        panelGambar1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 570, 230));

        Cari.setText("Cari");
        Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariActionPerformed(evt);
            }
        });
        panelGambar1.add(Cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        JTransaksi_Baru.setText("Transaksi Baru");
        JTransaksi_Baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTransaksi_BaruActionPerformed(evt);
            }
        });
        panelGambar1.add(JTransaksi_Baru, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, -1, -1));

        totalbayar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalbayar.setText("Total Bayar Rp.");
        panelGambar1.add(totalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, -1, -1));

        pembayaran.setText("Pembayaran Rp");
        panelGambar1.add(pembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, -1, -1));

        jTextFieldTtlFktr.setEditable(false);
        jTextFieldTtlFktr.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldTtlFktr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        panelGambar1.add(jTextFieldTtlFktr, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 160, -1));

        JPembayaran.setEditable(false);
        JPembayaran.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        panelGambar1.add(JPembayaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 160, -1));

        Bayar.setText("Bayar");
        Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarActionPerformed(evt);
            }
        });
        panelGambar1.add(Bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, -1, -1));

        kembalian.setText("Kembalian Rp");
        panelGambar1.add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, -1, -1));

        JKembalian.setEditable(false);
        JKembalian.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        panelGambar1.add(JKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 460, 160, -1));

        tabeltrans.setText("Tabel Transaksi");
        panelGambar1.add(tabeltrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        buatfaktur.setText("Buat Faktur");
        panelGambar1.add(buatfaktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        jTextFieldNoFktr.setColumns(4);
        panelGambar1.add(jTextFieldNoFktr, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 80, -1));

        tanggal.setText("Tanggal & waktu");
        panelGambar1.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        Beli.setText("Beli");
        Beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BeliActionPerformed(evt);
            }
        });
        panelGambar1.add(Beli, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, -1));

        Hapus.setText("Hapus");
        Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusActionPerformed(evt);
            }
        });
        panelGambar1.add(Hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

        namabarang.setText("Nama barang");
        panelGambar1.add(namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        harga.setText("Harga");
        panelGambar1.add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        JNama_Barang2.setEditable(false);
        JNama_Barang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JNama_Barang2ActionPerformed(evt);
            }
        });
        panelGambar1.add(JNama_Barang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 139, -1));

        JHarga_Barang2.setEditable(false);
        panelGambar1.add(JHarga_Barang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 140, -1));

        JBuat.setText("Buat");
        JBuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBuatActionPerformed(evt);
            }
        });
        panelGambar1.add(JBuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        jTextFieldTglFktr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldTglFktr.setText("tanggal");
        panelGambar1.add(jTextFieldTglFktr, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jLabel3.setText("Stok gudang");
        panelGambar1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        JStok.setEditable(false);
        panelGambar1.add(JStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 140, -1));

        jLabel4.setText("ID Staf         :");
        panelGambar1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel5.setText("Nama Staf    :");
        panelGambar1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel6.setText("Faktur Terakhir");
        panelGambar1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        FakturTerakhir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FakturTerakhir.setText("No faktur");
        panelGambar1.add(FakturTerakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, -1, -1));

        ID_User.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ID_User.setText("your ID");
        panelGambar1.add(ID_User, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jTextFieldPnrmFktr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldPnrmFktr.setText("your Name");
        panelGambar1.add(jTextFieldPnrmFktr, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        getContentPane().add(panelGambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 850, 530));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setText("Transaksi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon_transaksi2.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        andalogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        andalogin.setText("Anda Login Sebagai Kasir");
        jPanel1.add(andalogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 80));

        file.setText("File");
        file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileActionPerformed(evt);
            }
        });

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

    private void BeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BeliActionPerformed
        
        JPembayaran.setEditable(true);
        stok = Integer.parseInt(JStok.getText());
        Quantity = Integer.parseInt(jTextFieldQty.getText());
        if (stok == 0){
                JOptionPane.showMessageDialog(null,"Maaf Stok Gudang Habis");                
            }
        else if(stok < Quantity){ 
                JOptionPane.showMessageDialog(null,"Maaf Stok Gudang Kurang");
        }else { 
         updatebarang(); 
        try{
        Statement stm=con.createStatement();
        stm.executeUpdate("insert into trans_jual set no_faktur='"+jTextFieldNoFktr.getText()+"',"+
                           "kode_barang='"+JID_Barang2.getText()+"',"+
                           "qty_jual='"+jTextFieldQty.getText()+"'");
        
        tampilrincian();
        
        //hapus view rincian
        try{
            Statement stmr;
            stmr=con.createStatement();
            stmr.executeUpdate("drop table rincian");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        //buat view rincian nested query
        try{
            Statement stmr;
            stmr=con.createStatement();
            stmr.executeUpdate("create table rincian as select trans_jual.no_faktur,trans_jual.qty_jual,trans_jual.kode_barang,barang.nama_barang,barang.harga_jual,(barang.harga_jual*trans_jual.qty_jual)as jumlah from trans_jual inner join barang on barang.kode_barang=trans_jual.kode_barang where no_faktur='"+jTextFieldNoFktr.getText()+"'");
        
        }catch(Exception e){
            e.printStackTrace();
                    
        }
        
        //nested query
        try{
            Statement stmr;
            stmr=con.createStatement();
            rskasir=stmr.executeQuery("select sum(jumlah) as total from rincian");
            while (rskasir.next()){
                jTextFieldTtlFktr.setText(rskasir.getString("total"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //Update Total_jual ke tabel faktur
        try{
            Statement stmr;
            stmr=con.createStatement();
            stmr.executeUpdate("update faktur set total_jual='"+jTextFieldTtlFktr.getText()+"' where no_faktur='"+jTextFieldNoFktr.getText()+"'");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }catch(Exception e){
        e.printStackTrace();
    }       
            }  
         
        
    }                                              

    private void jComboBoxKdBrgItemStateChanged(java.awt.event.ItemEvent evt) {                                                
    
    }//GEN-LAST:event_BeliActionPerformed

    private void HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusActionPerformed
        try{
    stt=con.createStatement();
    stt.executeUpdate("delete from trans_jual where kode_barang='"+tabModel.getValueAt(jTableRincian.getSelectedRow(),0)+"'");
    tampilrincian();
}catch(Exception e){
    e.printStackTrace();
}
        try{
            Statement stmr;
            stmr=con.createStatement();
            stmr.executeUpdate("drop table rincian");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        //buat view rincian nested query
        try{
            Statement stmr;
            stmr=con.createStatement();
            stmr.executeUpdate("create table rincian as select trans_jual.no_faktur,trans_jual.qty_jual,trans_jual.kode_barang,barang.nama_barang,barang.harga_jual,(barang.harga_jual*trans_jual.qty_jual)as jumlah from trans_jual inner join barang on barang.kode_barang=trans_jual.kode_barang where no_faktur='"+jTextFieldNoFktr.getText()+"'");
        
        }catch(Exception e){
            e.printStackTrace();
                    
        }
        
        //nested query
        try{
            Statement stmr;
            stmr=con.createStatement();
            rskasir=stmr.executeQuery("select sum(jumlah) as total from rincian");
            while (rskasir.next()){
                jTextFieldTtlFktr.setText(rskasir.getString("total"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        try{ //update harus bayar
            Statement stmr;
            stmr=con.createStatement();
            stmr.executeUpdate("update faktur set total_jual='"+jTextFieldTtlFktr.getText()+"' where no_faktur='"+jTextFieldNoFktr.getText()+"'");
        }catch(Exception e){
            e.printStackTrace();
        }
        
     
    }//GEN-LAST:event_HapusActionPerformed

    private void CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariActionPerformed
    jTextFieldQty.setEditable(true);
        try {   
            Statement stm; //buat stm
            stm=con.createStatement();
            rskasir=stm.executeQuery("select * from barang where kode_barang like '%"+JID_Barang2.getText()+"%'");
            while(rskasir.next()){
             JNama_Barang2.setText(rskasir.getString("nama_barang"));
             JHarga_Barang2.setText(rskasir.getString("harga_jual"));
             JStok.setText(rskasir.getString("quantity"));
            jTextFieldQty.requestFocus();
               
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_CariActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new Tentang().setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Profile = new Profile();
        new Profile().setVisible(true);
        Profile.ID_User.setText(Login.user.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void JBuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBuatActionPerformed
        JID_Barang2.setEditable(true);
        try{
        Statement stm=con.createStatement();
        stm.executeUpdate("insert into faktur set no_faktur='"+jTextFieldNoFktr.getText()+"',"+
                           "tgl_jual='"+jTextFieldTglFktr.getText()+"',"+
                           "total_Jual='"+jTextFieldTtlFktr.getText()+"',"+                           
                           "Penerima='"+jTextFieldPnrmFktr.getText()+"'");
        JID_Barang2.requestFocus();
        
    }catch(Exception e){
        e.printStackTrace();
    }
    }//GEN-LAST:event_JBuatActionPerformed

    private void BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarActionPerformed
        Bayar();
         try {
            if (jumlah < 0){
                JOptionPane.showMessageDialog(null,"maaf uang anda kurang");                
            }
            else { JKembalian.setText(angka);
            }
            }
                    catch (Exception e){
            
        }
         
        
    }//GEN-LAST:event_BayarActionPerformed

    private void JNama_Barang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JNama_Barang2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JNama_Barang2ActionPerformed

    private void JTransaksi_BaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTransaksi_BaruActionPerformed
        Object[] judul_kolom = {"kode Barang","Nama Barang","Harga Barang","Quantity","Total"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            jTableRincian.setModel(tabModel);
            
            TampilFaktur();
            KosongTransaksi();
            JID_Barang2.setEditable(false);
            jTextFieldQty.setEditable(false);
            JPembayaran.setEditable(false);
            jTextFieldNoFktr.requestFocus();
                        
    }//GEN-LAST:event_JTransaksi_BaruActionPerformed

    private void fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileActionPerformed
        System.exit(0);
    }//GEN-LAST:event_fileActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        new Help().setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bayar;
    private javax.swing.JButton Beli;
    private javax.swing.JButton Cari;
    private javax.swing.JLabel FakturTerakhir;
    private javax.swing.JButton Hapus;
    public static javax.swing.JLabel ID_User;
    private javax.swing.JButton JBuat;
    private javax.swing.JTextField JHarga_Barang2;
    private javax.swing.JTextField JID_Barang2;
    private javax.swing.JTextField JKembalian;
    private javax.swing.JTextField JNama_Barang2;
    private javax.swing.JTextField JPembayaran;
    private javax.swing.JTextField JStok;
    private javax.swing.JButton JTransaksi_Baru;
    private javax.swing.JMenuBar Menubar;
    private javax.swing.JLabel andalogin;
    private javax.swing.JLabel buatfaktur;
    private javax.swing.JMenu file;
    private javax.swing.JLabel harga;
    private javax.swing.JLabel idbarang;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableRincian;
    private javax.swing.JTextField jTextFieldNoFktr;
    private javax.swing.JLabel jTextFieldPnrmFktr;
    private javax.swing.JTextField jTextFieldQty;
    private javax.swing.JLabel jTextFieldTglFktr;
    private javax.swing.JTextField jTextFieldTtlFktr;
    private javax.swing.JLabel kembalian;
    private javax.swing.JLabel namabarang;
    private MiniMarket.panelGambar panelGambar1;
    private javax.swing.JLabel pembayaran;
    private javax.swing.JLabel quantity;
    private javax.swing.JLabel tabeltrans;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel totalbayar;
    // End of variables declaration//GEN-END:variables

    private JTextField setText(String nama_barang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
