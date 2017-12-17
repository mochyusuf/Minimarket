/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MiniMarket;

import java.sql.DriverManager;
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
public class FormManajer extends javax.swing.JFrame {

    public Connection con=null;
    public Statement stt;
    DefaultTableModel tabModel;
    ResultSet rsbarang=null;
    private Profile Profile;
    
    public FormManajer() {
        super ("Aplikasi Penjualan (beta version)");
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minimarket?zeroDateTimeBehavior=convertToNull","root","");
            stt = con.createStatement();
            } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Gagal terhubung ke Database CEK ULANG DATABASE!");
            }
        tampilDataB();
        tampilDataF();
        tampilDataT();
        tampilDataU();
        tampilNama();
        
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
            while(rsbarang.next()){
                   Nama_User.setText(rsbarang.getString("nama_user"));
            }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void tampilDataB(){
        Object[] judul_kolom = {"ID Barang","Nama Barang","Quantity","Harga Beli","Harga Jual","Selisih"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            TabelB.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from barang");//mengambil data barang
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("kode_barang"),                               
                               rsbarang.getString("nama_barang"),                               
                               rsbarang.getString("quantity"),
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
    
    void tampilDataF(){
        Object[] judul_kolom = {"NO FAKTUR","TANGGAL JUAL","TOTAL JUAL","KASIR"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            TabelF.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from faktur");//mengambil data barang
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("no_faktur"),                               
                               rsbarang.getString("tgl_jual"),                               
                               rsbarang.getString("total_jual"),
                               rsbarang.getString("penerima"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void tampilDataT(){
        Object[] judul_kolom = {"NO FAKTUR","Quantity Jual","KODE BARANG"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            TabelT.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from trans_jual");//mengambil data barang
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("no_faktur"),                               
                               rsbarang.getString("qty_jual"),                               
                               rsbarang.getString("kode_barang"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void tampilDataU(){
        Object[] judul_kolom = {"ID USER","NAMA USER","TEMPAT LAHIR","TANGGAL LAHIR","ALAMAT","TELEPON","LEVEL"};
            tabModel=new DefaultTableModel(null,judul_kolom);
            TabelU.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from user");//mengambil data barang
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("id_user"),                               
                               rsbarang.getString("nama_user"),                               
                               rsbarang.getString("tempat_lahir"),
                               rsbarang.getString("tanggal_lahir"),                               
                               rsbarang.getString("alamat"),                               
                               rsbarang.getString("telepon"),
                               rsbarang.getString("level"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
                    
        }
    }
    
    void kosongkanFormB(){
        ID_Barang1.setText("");
    }
    
    void kosongkanFormF(){
        No_Faktur2.setText("");
    }
    
    void kosongkanFormT(){
        No_Faktur3.setText("");
    }
    
    void kosongkanFormU(){
        ID_User4.setText("");
        Password4.setText("");
        Nama_User4.setText("");
        Tempat4.setText("");
        Tanggal_Lahir4.setText("");
        Alamat4.setText("");
        Telepon4.setText("");
        Level_User4.setSelectedItem(1);
    }
    
    void kosongkanFormUC(){
        ID_User5.setText("");
    }
    
    void tblKeForm(){
        ID_User4.setText(tabModel.getValueAt(TabelU.getSelectedRow(),0)+"");
        Nama_User4.setText(tabModel.getValueAt(TabelU.getSelectedRow(),1)+"");
        Tempat4.setText(tabModel.getValueAt(TabelU.getSelectedRow(),2)+"");
        Tanggal_Lahir4.setText(tabModel.getValueAt(TabelU.getSelectedRow(),3)+"");
        Alamat4.setText(tabModel.getValueAt(TabelU.getSelectedRow(),4)+"");
        Telepon4.setText(tabModel.getValueAt(TabelU.getSelectedRow(),5)+"");
        Level_User4.setSelectedItem(tabModel.getValueAt(TabelU.getSelectedRow(),6)+"");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGambar1 = new MiniMarket.panelGambar();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Data_Transaksi = new javax.swing.JPanel();
        CariT = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelT = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        No_Faktur3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Data_Barang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ID_Barang1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CariB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelB = new javax.swing.JTable();
        Data_Faktur = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        No_Faktur2 = new javax.swing.JTextField();
        CariF = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelF = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        Data_User = new javax.swing.JPanel();
        TambahU = new javax.swing.JButton();
        HapusU = new javax.swing.JButton();
        EDITU = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        CariU = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelU = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ID_User4 = new javax.swing.JTextField();
        Nama_User4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Tempat4 = new javax.swing.JTextField();
        Alamat4 = new javax.swing.JTextField();
        Telepon4 = new javax.swing.JTextField();
        Level_User4 = new javax.swing.JComboBox();
        Tanggal_Lahir4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Password4 = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        ID_User5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldTgl = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ID_User = new javax.swing.JLabel();
        Nama_User = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Menubar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGambar1.setPreferredSize(new java.awt.Dimension(860, 700));

        jTabbedPane1.setAlignmentX(0.2F);
        jTabbedPane1.setAlignmentY(0.2F);

        Data_Transaksi.setBackground(java.awt.Color.lightGray);

        CariT.setText("Cari");
        CariT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariTActionPerformed(evt);
            }
        });

        TabelT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "no_faktur", "qty_jual", "kode_barang"
            }
        ));
        jScrollPane3.setViewportView(TabelT);

        jLabel15.setText("No Faktur");

        No_Faktur3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                No_Faktur3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText("Transaksi");

        javax.swing.GroupLayout Data_TransaksiLayout = new javax.swing.GroupLayout(Data_Transaksi);
        Data_Transaksi.setLayout(Data_TransaksiLayout);
        Data_TransaksiLayout.setHorizontalGroup(
            Data_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_TransaksiLayout.createSequentialGroup()
                .addGroup(Data_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_TransaksiLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Data_TransaksiLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(Data_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Data_TransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(No_Faktur3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CariT, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Data_TransaksiLayout.setVerticalGroup(
            Data_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_TransaksiLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Data_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Data_TransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(Data_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(No_Faktur3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CariT)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Transaksi", Data_Transaksi);

        Data_Barang.setBackground(java.awt.Color.lightGray);

        jLabel6.setText("ID Barang");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Data Barang");

        CariB.setText("Cari");
        CariB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariBActionPerformed(evt);
            }
        });

        TabelB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga Barang", "Quantity  (Gudang)"
            }
        ));
        jScrollPane1.setViewportView(TabelB);

        javax.swing.GroupLayout Data_BarangLayout = new javax.swing.GroupLayout(Data_Barang);
        Data_Barang.setLayout(Data_BarangLayout);
        Data_BarangLayout.setHorizontalGroup(
            Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_BarangLayout.createSequentialGroup()
                .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CariB)
                            .addGroup(Data_BarangLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(ID_Barang1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        Data_BarangLayout.setVerticalGroup(
            Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_BarangLayout.createSequentialGroup()
                .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Data_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ID_Barang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CariB))
                    .addGroup(Data_BarangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Barang", Data_Barang);

        Data_Faktur.setBackground(java.awt.Color.lightGray);

        jLabel7.setText("No Faktur");

        No_Faktur2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                No_Faktur2ActionPerformed(evt);
            }
        });

        CariF.setText("Cari");
        CariF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariFActionPerformed(evt);
            }
        });

        TabelF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "no_faktur", "tgl_jual", "total_jual", "kasir"
            }
        ));
        jScrollPane2.setViewportView(TabelF);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Faktur");

        javax.swing.GroupLayout Data_FakturLayout = new javax.swing.GroupLayout(Data_Faktur);
        Data_Faktur.setLayout(Data_FakturLayout);
        Data_FakturLayout.setHorizontalGroup(
            Data_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_FakturLayout.createSequentialGroup()
                .addGroup(Data_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_FakturLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(17, 17, 17)
                        .addComponent(No_Faktur2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Data_FakturLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel11))
                    .addComponent(CariF, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
        Data_FakturLayout.setVerticalGroup(
            Data_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_FakturLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Data_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Data_FakturLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(35, 35, 35)
                        .addGroup(Data_FakturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(No_Faktur2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CariF)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Faktur", Data_Faktur);

        Data_User.setBackground(java.awt.Color.lightGray);
        Data_User.setPreferredSize(new java.awt.Dimension(945, 400));

        TambahU.setText("Tambah");
        TambahU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahUActionPerformed(evt);
            }
        });

        HapusU.setText("Hapus");
        HapusU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusUActionPerformed(evt);
            }
        });

        EDITU.setText("Edit");
        EDITU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EDITUActionPerformed(evt);
            }
        });

        jLabel17.setText("Level User");

        CariU.setText("Cari");
        CariU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariUActionPerformed(evt);
            }
        });

        TabelU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID User", "Nama User", "Level User"
            }
        ));
        TabelU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelUMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TabelU);

        jLabel20.setText("ID User");

        jLabel21.setText("Nama User");

        jLabel2.setText("Tempat Lahir");

        jLabel22.setText("Tanggal Lahir");

        jLabel23.setText("Alamat");

        jLabel24.setText("Telepon");

        Level_User4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manajer", "Staf Gudang", "Kasir" }));
        Level_User4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Level_User4ActionPerformed(evt);
            }
        });

        Tanggal_Lahir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tanggal_Lahir4ActionPerformed(evt);
            }
        });

        jLabel8.setText("Password");

        jLabel25.setText("ID User");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Kelola User");

        jLabel14.setText("(yyyy/mm/dd)");

        javax.swing.GroupLayout Data_UserLayout = new javax.swing.GroupLayout(Data_User);
        Data_User.setLayout(Data_UserLayout);
        Data_UserLayout.setHorizontalGroup(
            Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_UserLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel9)
                .addGap(477, 477, 477)
                .addComponent(jLabel25)
                .addGap(4, 4, 4)
                .addComponent(ID_User5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(CariU))
            .addGroup(Data_UserLayout.createSequentialGroup()
                .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_UserLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Data_UserLayout.createSequentialGroup()
                                .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Data_UserLayout.createSequentialGroup()
                                            .addComponent(jLabel21)
                                            .addGap(32, 32, 32)
                                            .addComponent(Nama_User4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Data_UserLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(22, 22, 22)
                                            .addComponent(Tempat4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Data_UserLayout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(20, 20, 20)
                                            .addComponent(Tanggal_Lahir4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(Alamat4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Telepon4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(Data_UserLayout.createSequentialGroup()
                                .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Data_UserLayout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ID_User4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Data_UserLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Password4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Data_UserLayout.createSequentialGroup()
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Data_UserLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Data_UserLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(Level_User4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Data_UserLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TambahU)
                        .addGap(6, 6, 6)
                        .addComponent(HapusU)
                        .addGap(6, 6, 6)
                        .addComponent(EDITU)
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Data_UserLayout.setVerticalGroup(
            Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Data_UserLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(Data_UserLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel25))
                    .addGroup(Data_UserLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(ID_User5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Data_UserLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(CariU)))
                .addGap(11, 11, 11)
                .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Data_UserLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ID_User4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Password4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(7, 7, 7)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Data_UserLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel21))
                            .addComponent(Nama_User4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Data_UserLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2))
                            .addComponent(Tempat4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Data_UserLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel22))
                            .addComponent(Tanggal_Lahir4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Alamat4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Telepon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Level_User4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Data_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TambahU)
                            .addComponent(HapusU)
                            .addComponent(EDITU)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Data User", Data_User);

        jTextFieldTgl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextFieldTgl.setText("tanggal");

        jLabel19.setText("Tanggal & waktu");

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
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGambar1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Nama_User)
                                .addGap(514, 514, 514))
                            .addGroup(panelGambar1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ID_User)
                                .addGap(191, 191, 191)))
                        .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jTextFieldTgl)))
                    .addGroup(panelGambar1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelGambar1Layout.setVerticalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGambar1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID_User)
                    .addComponent(jLabel4)
                    .addComponent(jLabel19))
                .addGap(8, 8, 8)
                .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nama_User)
                        .addComponent(jLabel3))
                    .addComponent(jTextFieldTgl))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        getContentPane().add(panelGambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1030, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setText("Log Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        jButton6.setText("Profile Manajer");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel12.setText("Manajemen");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon_manager2.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Anda Login Sebagai Manajer");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 80));

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

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new Tentang().setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void TabelUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelUMouseClicked
        tblKeForm();
    }//GEN-LAST:event_TabelUMouseClicked

    private void CariUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariUActionPerformed
        Object[] judul_kolom = {"ID USER","NAMA USER","TEMPAT LAHIR","TANGGAL LAHIR","ALAMAT","TELEPON","LEVEL"};
        tabModel=new DefaultTableModel(null,judul_kolom);
        TabelU.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from user where id_user='"+ID_User5.getText()+"'");
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("id_user"),
                    rsbarang.getString("nama_user"),
                    rsbarang.getString("tempat_lahir"),
                    rsbarang.getString("tanggal_lahir"),
                    rsbarang.getString("alamat"),
                    rsbarang.getString("telepon"),
                    rsbarang.getString("level"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        kosongkanFormUC();
    }//GEN-LAST:event_CariUActionPerformed

    private void EDITUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EDITUActionPerformed
         if (ID_User4.getText().equals("")  
            || Nama_User4.getText().equals("") || Tempat4.getText().equals("") 
            || Tanggal_Lahir4.getText().equals("") || Alamat4.getText().equals("") 
            || Telepon4.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Form Tidak Boleh Kosong");
        }else{
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            stm.executeUpdate("update user set id_user='"+ID_User4.getText()+"',"+
                "password='"+String.valueOf(Password4.getPassword())+"',"+
                "nama_user='"+Nama_User4.getText()+"',"+
                "tempat_lahir='"+Tempat4.getText()+"',"+
                "tanggal_lahir='"+Tanggal_Lahir4.getText()+"',"+
                "alamat='"+Alamat4.getText()+"',"+
                "telepon='"+Telepon4.getText()+"',"+
                "level='"+Level_User4.getSelectedItem()+"'"
                + "where id_user like '%"+ID_User4.getText()+"%'");
        }catch(Exception e){
            e.printStackTrace();

        }
        tampilDataU();
        kosongkanFormU();
         }
    }//GEN-LAST:event_EDITUActionPerformed

    private void HapusUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusUActionPerformed
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            stm.executeUpdate("delete from user where id_user='"+ID_User4.getText()+"'");
        }catch(Exception e){
            e.printStackTrace();

        }
        tampilDataU();
        kosongkanFormU();
    }//GEN-LAST:event_HapusUActionPerformed

    private void TambahUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahUActionPerformed
        if (ID_User4.getText().equals("") 
            || Nama_User4.getText().equals("") || Tempat4.getText().equals("") 
            || Tanggal_Lahir4.getText().equals("") || Alamat4.getText().equals("") 
            || Telepon4.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Form Tidak Boleh Kosong");
        }else{
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            stm.executeUpdate("insert into user set id_user='"+ID_User4.getText()+"',"+
                "password='"+String.valueOf(Password4.getPassword())+"',"+
                "nama_user='"+Nama_User4.getText()+"',"+
                "tempat_lahir='"+Tempat4.getText()+"',"+
                "tanggal_lahir='"+Tanggal_Lahir4.getText()+"',"+
                "alamat='"+Alamat4.getText()+"',"+
                "telepon='"+Telepon4.getText()+"',"+
                "level='"+Level_User4.getSelectedItem()+"'");
        }catch(Exception e){
            e.printStackTrace();
        }
        tampilDataU();
        kosongkanFormU();
        }
    }//GEN-LAST:event_TambahUActionPerformed

    private void No_Faktur3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_No_Faktur3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_No_Faktur3ActionPerformed

    private void CariTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariTActionPerformed
        Object[] judul_kolom = {"NO FAKTUR","Quantity Jual","KODE BARANG"};
        tabModel=new DefaultTableModel(null,judul_kolom);
        TabelT.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from trans_jual where no_faktur like '%"+No_Faktur3.getText()+"%'");
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("no_faktur"),
                    rsbarang.getString("qty_jual"),
                    rsbarang.getString("kode_barang"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        kosongkanFormT();
    }//GEN-LAST:event_CariTActionPerformed

    private void CariFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariFActionPerformed
        Object[] judul_kolom = {"NO FAKTUR","Tanggal Jual","Total Jual","KASIR"};
        tabModel=new DefaultTableModel(null,judul_kolom);
        TabelF.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from faktur where no_faktur like '%"+No_Faktur2.getText()+"%'");
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("no_faktur"),
                    rsbarang.getString("tgl_jual"),
                    rsbarang.getString("total_jual"),
                    rsbarang.getString("penerima"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        kosongkanFormF();
    }//GEN-LAST:event_CariFActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Profile = new Profile();
        new Profile().setVisible(true);
        Profile.ID_User.setText(Login.user.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CariBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariBActionPerformed
        Object[] judul_kolom = {"ID Barang","Nama Barang","Quantity","Harga Beli","Harga Jual","Selisih"};
        tabModel=new DefaultTableModel(null,judul_kolom);
        TabelB.setModel(tabModel);
        try{
            Statement stm; //buat stm
            stm=con.createStatement();
            tabModel.getDataVector().removeAllElements();
            rsbarang=stm.executeQuery("select * from barang where kode_barang like '%"+ID_Barang1.getText()+"%'");
            while(rsbarang.next()){
                Object[] data={rsbarang.getString("kode_barang"),
                    rsbarang.getString("nama_barang"),
                    rsbarang.getString("quantity"),
                    rsbarang.getString("harga_beli"),
                    rsbarang.getString("harga_jual"),
                    rsbarang.getString("selisih"),
                };
                tabModel.addRow(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        kosongkanFormB();
    }//GEN-LAST:event_CariBActionPerformed

    private void Level_User4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Level_User4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Level_User4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        new Help().setVisible(true);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void Tanggal_Lahir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tanggal_Lahir4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal_Lahir4ActionPerformed

    private void No_Faktur2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_No_Faktur2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_No_Faktur2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormManajer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManajer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManajer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManajer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormManajer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Alamat4;
    private javax.swing.JButton CariB;
    private javax.swing.JButton CariF;
    private javax.swing.JButton CariT;
    private javax.swing.JButton CariU;
    private javax.swing.JPanel Data_Barang;
    private javax.swing.JPanel Data_Faktur;
    private javax.swing.JPanel Data_Transaksi;
    private javax.swing.JPanel Data_User;
    private javax.swing.JButton EDITU;
    private javax.swing.JButton HapusU;
    private javax.swing.JTextField ID_Barang1;
    public static javax.swing.JLabel ID_User;
    private javax.swing.JTextField ID_User4;
    private javax.swing.JTextField ID_User5;
    private javax.swing.JComboBox Level_User4;
    private javax.swing.JMenuBar Menubar;
    private javax.swing.JLabel Nama_User;
    private javax.swing.JTextField Nama_User4;
    private javax.swing.JTextField No_Faktur2;
    private javax.swing.JTextField No_Faktur3;
    private javax.swing.JPasswordField Password4;
    private javax.swing.JTable TabelB;
    private javax.swing.JTable TabelF;
    private javax.swing.JTable TabelT;
    private javax.swing.JTable TabelU;
    private javax.swing.JButton TambahU;
    private javax.swing.JTextField Tanggal_Lahir4;
    private javax.swing.JTextField Telepon4;
    private javax.swing.JTextField Tempat4;
    private javax.swing.JMenu file;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jTextFieldTgl;
    private MiniMarket.panelGambar panelGambar1;
    // End of variables declaration//GEN-END:variables
}
