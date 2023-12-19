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


public class GUI_Coach extends javax.swing.JFrame {
    /**
     * Creates new form GUI_Coach
     */
    public GUI_Coach() {
        initComponents();
        tampil();
    }

    String nama,specialist, waktu, pendapatan, potongan;
    public Connection conn;
    
    public void batal() {
        txtname.setText("");
        txtspec.setText("");
        txtlmLat.setText("");
        txtPotongan.setText("");        
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
        tabelhead.addColumn("SPECIALIST");
        tabelhead.addColumn("WAKTU");
        tabelhead.addColumn("PENDAPATAN");
        tabelhead.addColumn("POTONGAN");
        
        try {
            koneksi();
            String sql = "SELECT * FROM tb_coach";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6),});
            }
            tableCoach.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
    
    public void refresh() {
        new GUI_Coach().setVisible(true);
        this.setVisible(false);
    }
    
    public void insert() {
        String Nama = txtname.getText();
        String Specialist = txtspec.getText();
        int Waktu = Integer.parseInt(txtlmLat.getText());
        int Pendapatan = Waktu * 30000;
        int Potongan = Integer.parseInt(txtPotongan.getText());
        try {
            koneksi();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO tb_coach (nama,specialist,waktu,pendapatan,potongan)"
                    + "VALUES('" + Nama + "','" + Specialist + "','" + Waktu + "','" + Pendapatan + "','" + Potongan + "')");
            statement.close();
            JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Coach!" + "\n" + Nama);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
        }
        refresh();
    }
    
    public void update() {
        String Nama = txtname.getText();
        String Specialist = txtspec.getText();
        int Waktu = Integer.parseInt(txtlmLat.getText());
        int Pendapatan = Waktu * 30000;
        int Potongan = Integer.parseInt(txtPotongan.getText());
        
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_coach SET nama='" + Nama + "'," + "specialist='" + Specialist + "',"
                    + "waktu='" + Waktu + "'" + ",pendapatan='" + Pendapatan + "',potongan='" + Potongan + "' where nama= '"+ nama+"'");
                     
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Coach Berhasil!");
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
                 String sql = "DELETE FROM tb_coach WHERE nama='" + txtname.getText() + "'";
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
    public void itempilih() {
        txtname.setText(nama);
        txtspec.setText(specialist);
        txtlmLat.setText(waktu);
        txtPotongan.setText(potongan);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        txtspec = new javax.swing.JTextField();
        txtlmLat = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCoach = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPotongan = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nama");

        jLabel2.setText("Specialist");

        jLabel4.setText("Lama Latihan");

        txtspec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtspecActionPerformed(evt);
            }
        });

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        tableCoach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama", "Specialist", "Waktu", "Pendapatan", "Potongan"
            }
        ));
        tableCoach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCoachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCoach);

        jLabel7.setText("COACH");

        jLabel8.setText("Potongan Sanggar ");

        txtPotongan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPotonganActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel7)
                .addContainerGap(207, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtlmLat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(txtspec, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(175, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnsimpan)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtspec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtlmLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnUpdate)
                    .addComponent(btnsimpan))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtspecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtspecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtspecActionPerformed

    private void txtPotonganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPotonganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPotonganActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        insert();

//        try {
//            JOptionPane.showMessageDialog(null, "Data Anda Ditambahkan Ke Table");
//            DefaultTableModel dataModel = (DefaultTableModel) tableCoach.getModel();
//            List list = new ArrayList<>();
//            tableCoach.setAutoCreateColumnsFromModel(true);
//            coach.Nama = txtname.getText();
//            coach.Specialist = txtspec.getText();
//            coach.jamLatihan = Double.parseDouble(txtlmLat.getText().split(" ")[0]);
//            coach.pendapatan = Double.parseDouble(txtpendapatan.getText());
//            list.add(coach.hasilNama());
//            list.add(coach.hasilSpecialist());
//            list.add(String.valueOf(coach.lamaLatihan()));
//            list.add(String.valueOf(coach.getPendapatanCoach()));
//            list.add(String.valueOf(coach.menghitungPembayaran()));
//            dataModel.addRow(list.toArray());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Pastikan input lama latihan dan pendapatan berupa angka.");
//        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void tableCoachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCoachMouseClicked
        // TODO add your handling code here:
        int tabel = tableCoach.getSelectedRow();
        nama = tableCoach.getValueAt(tabel, 0).toString();
        specialist = tableCoach.getValueAt(tabel, 1).toString();
        waktu= tableCoach.getValueAt(tabel, 2).toString();
        potongan = tableCoach.getValueAt(tabel, 4).toString();
//        ang1 = table_data_mahasiswa.getValueAt(tabel, 4).toString();
//        alamat1 = table_data_mahasiswa.getValueAt(tabel, 5).toString();
        itempilih();
    }//GEN-LAST:event_tableCoachMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_Coach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Coach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Coach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Coach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Coach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCoach;
    private javax.swing.JTextField txtPotongan;
    private javax.swing.JTextField txtlmLat;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtspec;
    // End of variables declaration//GEN-END:variables
}
