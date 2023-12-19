/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_yuyun;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Yoenyeta
 */
public class GUI_DATASISWA extends javax.swing.JFrame {

    /**
     * Creates new form GUI_PEMBAYARANLES
     */
    public GUI_DATASISWA() {
        initComponents();
        tampil();
    }
    String nama,ttl, kelamin, alamat, notelp , bidang;
    public Connection conn;
    
    public void batal() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtTL.setText("");
        txtTelp.setText("");
        buttonGroup1.clearSelection();
        buttonGroup3.clearSelection();
    }
    
    public void koneksi() throws SQLException{
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oop_datasiswa?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_DATASISWA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_DATASISWA.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_DATASISWA.class.getName()).log(Level.SEVERE, null, es);
        }

    }
    
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("NAMA");
        tabelhead.addColumn("TTL");
        tabelhead.addColumn("JENIS KELAMIN");
        tabelhead.addColumn("ALAMAT");
        tabelhead.addColumn("TELEPON");
        tabelhead.addColumn("BIDANG");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_siswa";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),});
            }
            Table_Siswa.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void refresh() {
        new GUI_DATASISWA().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        String Nama = txtNama.getText();
        String ttl = txtTL.getText();
        String jk;
        if (BtnP.isSelected()) {
            jk = "Perempuan";
        } else {
            jk = "Laki - laki";
        }
        String Bidang;
        if (BtnMod.isSelected()) {
            Bidang = "Modern";
        } else {
            Bidang = "Tradisional";
        }
        String NoTelp = txtTelp.getText();
        String Alamat = txtAlamat.getText();
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_siswa (nama,ttl,jenis_kelamin,alamat,no_telp,bidang)"
                    + "VALUES('" + Nama + "','" + ttl + "','" + jk + "','" + Alamat + "','" + NoTelp + "','" + Bidang + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Siswa!" + "\n" + Nama);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
    
    public void update() {
        String Nama = txtNama.getText();
        String ttl = txtTL.getText();
        String jk;
        if (BtnP.isSelected()) {
            jk = "Perempuan";
        } else {
            jk = "laki - laki";
        }
        String Bidang;
        if (BtnMod.isSelected()) {
            Bidang = "Modern";
        } else {
            Bidang = "Tradisional";
        }
        String NoTelp = txtTelp.getText();
        String Alamat = txtAlamat.getText();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_siswa SET nama='" + Nama + "'," + "ttl='" + ttl + "',"
                    + "jenis_kelamin='" + jk + "'" + ",alamat='" + Alamat + "',no_telp='" + NoTelp + "',bidang='"
                    + Bidang + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Siswa Berhasil!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
//
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                 String sql = "DELETE FROM tb_siswa WHERE nama='" + txtNama.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
    
//    public void cari() {
//        try {
//            try ( Statement statement = conn.createStatement()) {
//                String sql = "SELECT * FROM tb_mahasiswa WHERE `nim`  LIKE '%" + txtSearch.getText() + "%'";
//                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
//                if (rs.next()) // .next() = scanner method
//                {
//                    txtNim.setText(rs.getString(2));
//                    txtNama.setText(rs.getString(3));
//                    String jk = rs.getString(4);
//                    if (jk.equalsIgnoreCase("L")) {
//                        radiobtnLaki.setSelected(true);
//                    } else {
//                        radiobtnPerempuan.setSelected(true);
//                    }
//                    txtProdi.setText(rs.getString(4));
//                    txtAngkatan.setText(rs.getString(5));
//                    txtAlamat.setText(rs.getString(6));
//                } else {
//                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Error." + ex);
//        }
//    }
//    
    public void itempilih() {
        txtNama.setText(nama);
        txtTL.setText(ttl);
        txtAlamat.setText(alamat);
        txtTelp.setText(notelp);
        String jk = null;
        if (jk.equalsIgnoreCase("P")) {
            BtnL.setSelected(true);
        } else {
            BtnP.setSelected(true);

        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtTL = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        btnTra = new javax.swing.JRadioButton();
        BtnMod = new javax.swing.JRadioButton();
        BtnP = new javax.swing.JRadioButton();
        BtnL = new javax.swing.JRadioButton();
        btnSimpan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Siswa = new javax.swing.JTable();
        update = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DATA SISWA SANGGAR TARI ");

        jLabel2.setText("Nama");

        jLabel3.setText("Tanggal Lahir");

        jLabel4.setText("Jenis Kelamin");

        jLabel5.setText("Alamat");

        jLabel6.setText("No. Telp");

        jLabel7.setText("Bidang Minat");

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        buttonGroup3.add(btnTra);
        btnTra.setText("Tradisional");

        buttonGroup3.add(BtnMod);
        BtnMod.setText("Modern");
        BtnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModActionPerformed(evt);
            }
        });

        buttonGroup1.add(BtnP);
        BtnP.setText("Perempuan");

        buttonGroup1.add(BtnL);
        BtnL.setText("Laki - Laki");

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        Table_Siswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "TTL", "Jenis Kelamin", "Alamat", "No.Telp", "Bidang"
            }
        ));
        Table_Siswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_SiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_Siswa);

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNama)
                            .addComponent(txtTL)
                            .addComponent(txtAlamat)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BtnP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BtnL))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTra)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnMod)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTelp))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BtnP)
                                    .addComponent(BtnL))))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(btnTra)
                            .addComponent(BtnMod))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(update)
                            .addComponent(btnDelete)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnModActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_updateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void Table_SiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_SiswaMouseClicked
        // TODO add your handling code here:
        int tabel = Table_Siswa.getSelectedRow();
        nama = Table_Siswa.getValueAt(tabel, 0).toString();
        ttl = Table_Siswa.getValueAt(tabel, 1).toString();
        alamat= Table_Siswa.getValueAt(tabel, 2).toString();
        notelp = Table_Siswa.getValueAt(tabel, 3).toString();
//        ang1 = table_data_mahasiswa.getValueAt(tabel, 4).toString();
//        alamat1 = table_data_mahasiswa.getValueAt(tabel, 5).toString();
        itempilih();
    }//GEN-LAST:event_Table_SiswaMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_DATASISWA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_DATASISWA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_DATASISWA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_DATASISWA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_DATASISWA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BtnL;
    private javax.swing.JRadioButton BtnMod;
    private javax.swing.JRadioButton BtnP;
    private javax.swing.JTable Table_Siswa;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JRadioButton btnTra;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTL;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
