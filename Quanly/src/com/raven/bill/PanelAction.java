/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAction extends javax.swing.JPanel {

    public PanelAction() {
        initComponents();
    }

  public void initEvent(TableActionEvent event, int row){
      cmdEdit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             event.onEdit(row);
          }
      });
        cmDelete.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             event.onDelete(row);
          }
      });
        cmdView.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             event.onView(row);
          }
      });
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdView = new com.raven.bill.ActionButton();
        cmdEdit = new com.raven.bill.ActionButton();
        cmDelete = new com.raven.bill.ActionButton();

        setOpaque(false);

        cmdView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/bill/view.png"))); // NOI18N
        cmdView.setMaximumSize(new java.awt.Dimension(12, 12));
        cmdView.setMinimumSize(new java.awt.Dimension(12, 12));

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/bill/edit.png"))); // NOI18N
        cmdEdit.setMaximumSize(new java.awt.Dimension(12, 12));
        cmdEdit.setMinimumSize(new java.awt.Dimension(12, 12));

        cmDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/bill/delete.png"))); // NOI18N
        cmDelete.setMaximumSize(new java.awt.Dimension(12, 12));
        cmDelete.setMinimumSize(new java.awt.Dimension(12, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.bill.ActionButton cmDelete;
    private com.raven.bill.ActionButton cmdEdit;
    private com.raven.bill.ActionButton cmdView;
    // End of variables declaration//GEN-END:variables
}
