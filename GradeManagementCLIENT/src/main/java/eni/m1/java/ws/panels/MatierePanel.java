/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.panels;

import eni.m1.java.ws.entities.Matiere;
import eni.m1.java.ws.entities.Utilisateur;
import eni.m1.java.ws.service.MatiereService;
import eni.m1.java.ws.service.RegistreService;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shirleyodon
 */
public class MatierePanel extends javax.swing.JPanel {

    /**
     * Creates new form MatierePanel
     */
    
    private DefaultTableModel model;
    private MatiereService service;
    private RegistreService registreService;
    private Utilisateur profile;
    
    public MatierePanel(Utilisateur user) {
        initComponents();
        initTableGrid();
        initModel();
        initService();
        initUser(user);
    }
    
    private void initTableGrid(){
        matiereTable.setGridColor(Color.lightGray);
        matiereTable.setShowGrid(true);
    }
    
    private void initModel(){
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        model.addColumn("Code Matière");
        model.addColumn("Libellé");
        model.addColumn("Coefficient");
    }
    
    private void fillModel(Matiere mat) {
    	model.setRowCount(0);
    	model.addRow(
    		new Object[]{
                    mat.getCodeMatiere(),
                    mat.getLibelle(),
                    mat.getCoef()
                });
    	matiereTable.setModel(model);
    }
    
    private void fillModel(Matiere[] list) {
    	model.setRowCount(0);
    	for(Matiere mat: list){
            model.addRow(
                new Object[]{
                    mat.getCodeMatiere(),
                    mat.getLibelle(),
                    mat.getCoef()
                });
        }
    	matiereTable.setModel(model);
    }
    
    private void addRow(Matiere mat){
        model.addRow(
    		new Object[]{
                    mat.getCodeMatiere(),
                    mat.getLibelle(),
                    mat.getCoef()
                });
    	matiereTable.setModel(model);
    }
    
    private void initService(){
        registreService=new RegistreService();
        service=new MatiereService();
        fillModel(service.getAll());
    }
    
    private void initUser(Utilisateur user){
        profile=user;
        if(profile.getStatut().equals("Visitor")){
            ajouterButton.setEnabled(false);
            modifierButton.setEnabled(false);
            supprimerButton.setEnabled(false);
        }
    }
    
    private void eraseField(){
        codeMatiereField.setText("");
        libelleField.setText("");
        coefficientFormattedField.setText("");
        rechercheComboBox.setSelectedIndex(0);
        rechercheField.setText("");
    }
    
    private boolean estVide(JTextField field){
        return field.getText().equals("");
    }
    
    private boolean estVide(JFormattedTextField formattedField){
        return formattedField.getText().equals("");
    }
    
    public void setProfile(Utilisateur profile){
        this.profile = profile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyPanel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        codeMatiereLabel = new javax.swing.JLabel();
        codeMatiereField = new javax.swing.JTextField();
        libelleLabel = new javax.swing.JLabel();
        libelleField = new javax.swing.JTextField();
        coefficientLabel = new javax.swing.JLabel();
        coefficientFormattedField = new javax.swing.JFormattedTextField();
        listePanel = new javax.swing.JPanel();
        listScrollPane = new javax.swing.JScrollPane();
        matiereTable = new javax.swing.JTable();
        operationPanel = new javax.swing.JPanel();
        ajouterButton = new javax.swing.JButton();
        supprimerButton = new javax.swing.JButton();
        modifierButton = new javax.swing.JButton();
        actualiserButton = new javax.swing.JButton();
        recherchePanel = new javax.swing.JPanel();
        rechercheParLabel = new javax.swing.JLabel();
        rechercheComboBox = new javax.swing.JComboBox<>();
        rechercheField = new javax.swing.JTextField();
        rechercheButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(44, 62, 80));
        setMaximumSize(new java.awt.Dimension(712, 437));
        setMinimumSize(new java.awt.Dimension(710, 435));

        bodyPanel.setBackground(new java.awt.Color(44, 62, 80));

        infoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INFO SUR LA MATIERE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 20), java.awt.Color.lightGray)); // NOI18N
        infoPanel.setOpaque(false);

        codeMatiereLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        codeMatiereLabel.setForeground(new java.awt.Color(236, 240, 241));
        codeMatiereLabel.setText("Code Matière");

        codeMatiereField.setEditable(false);
        codeMatiereField.setBackground(new java.awt.Color(108, 122, 137));
        codeMatiereField.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        codeMatiereField.setForeground(new java.awt.Color(228, 241, 254));
        codeMatiereField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        libelleLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        libelleLabel.setForeground(new java.awt.Color(236, 240, 241));
        libelleLabel.setText("Libellé");

        libelleField.setBackground(new java.awt.Color(108, 122, 137));
        libelleField.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        libelleField.setForeground(new java.awt.Color(228, 241, 254));
        libelleField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        coefficientLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        coefficientLabel.setForeground(new java.awt.Color(236, 240, 241));
        coefficientLabel.setText("Coefficient");

        coefficientFormattedField.setBackground(new java.awt.Color(108, 122, 137));
        coefficientFormattedField.setForeground(new java.awt.Color(228, 241, 254));
        try {
            coefficientFormattedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        coefficientFormattedField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        coefficientFormattedField.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        coefficientFormattedField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coefficientFormattedFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(libelleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeMatiereLabel)
                    .addComponent(coefficientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codeMatiereField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(libelleField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coefficientFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeMatiereLabel)
                    .addComponent(codeMatiereField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(libelleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(libelleLabel))
                .addGap(16, 16, 16)
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coefficientLabel)
                    .addComponent(coefficientFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTE DES MATIERES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 20), java.awt.Color.lightGray)); // NOI18N
        listePanel.setOpaque(false);
        listePanel.setPreferredSize(new java.awt.Dimension(478, 173));

        matiereTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code Matière", "Libellé", "Coefficient"
            }
        ));
        matiereTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        matiereTable.setRowHeight(20);
        matiereTable.setShowGrid(true);
        matiereTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                matiereTableMouseClicked(evt);
            }
        });
        listScrollPane.setViewportView(matiereTable);

        javax.swing.GroupLayout listePanelLayout = new javax.swing.GroupLayout(listePanel);
        listePanel.setLayout(listePanelLayout);
        listePanelLayout.setHorizontalGroup(
            listePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        listePanelLayout.setVerticalGroup(
            listePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );

        operationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OPERATIONS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 20), java.awt.Color.lightGray)); // NOI18N
        operationPanel.setOpaque(false);

        ajouterButton.setBackground(new java.awt.Color(108, 122, 137));
        ajouterButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ajouterButton.setForeground(new java.awt.Color(228, 241, 254));
        ajouterButton.setText("Ajouter");
        ajouterButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ajouterButton.setMinimumSize(new java.awt.Dimension(79, 25));
        ajouterButton.setPreferredSize(new java.awt.Dimension(80, 25));
        ajouterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterButtonActionPerformed(evt);
            }
        });

        supprimerButton.setBackground(new java.awt.Color(108, 122, 137));
        supprimerButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        supprimerButton.setForeground(new java.awt.Color(228, 241, 254));
        supprimerButton.setText("Supprimer");
        supprimerButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        supprimerButton.setPreferredSize(new java.awt.Dimension(80, 25));
        supprimerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerButtonActionPerformed(evt);
            }
        });

        modifierButton.setBackground(new java.awt.Color(108, 122, 137));
        modifierButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        modifierButton.setForeground(new java.awt.Color(228, 241, 254));
        modifierButton.setText("Modifier");
        modifierButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        modifierButton.setPreferredSize(new java.awt.Dimension(80, 25));
        modifierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierButtonActionPerformed(evt);
            }
        });

        actualiserButton.setBackground(new java.awt.Color(108, 122, 137));
        actualiserButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        actualiserButton.setForeground(new java.awt.Color(228, 241, 254));
        actualiserButton.setText("Actualiser");
        actualiserButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        actualiserButton.setPreferredSize(new java.awt.Dimension(80, 25));
        actualiserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualiserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout operationPanelLayout = new javax.swing.GroupLayout(operationPanel);
        operationPanel.setLayout(operationPanelLayout);
        operationPanelLayout.setHorizontalGroup(
            operationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(operationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(operationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(operationPanelLayout.createSequentialGroup()
                        .addComponent(ajouterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supprimerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(operationPanelLayout.createSequentialGroup()
                        .addComponent(modifierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actualiserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        operationPanelLayout.setVerticalGroup(
            operationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, operationPanelLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(operationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajouterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supprimerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(operationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualiserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        recherchePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECHERCHE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Trebuchet MS", 1, 20), java.awt.Color.lightGray)); // NOI18N
        recherchePanel.setOpaque(false);

        rechercheParLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rechercheParLabel.setForeground(new java.awt.Color(236, 240, 241));
        rechercheParLabel.setText("Par");

        rechercheComboBox.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rechercheComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "code matière", "mot-clé", "coefficient" }));
        rechercheComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        rechercheField.setBackground(new java.awt.Color(108, 122, 137));
        rechercheField.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        rechercheField.setForeground(new java.awt.Color(228, 241, 254));
        rechercheField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        rechercheButton.setBackground(new java.awt.Color(108, 122, 137));
        rechercheButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rechercheButton.setForeground(new java.awt.Color(228, 241, 254));
        rechercheButton.setText("Rechercher");
        rechercheButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rechercheButton.setPreferredSize(new java.awt.Dimension(80, 25));
        rechercheButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout recherchePanelLayout = new javax.swing.GroupLayout(recherchePanel);
        recherchePanel.setLayout(recherchePanelLayout);
        recherchePanelLayout.setHorizontalGroup(
            recherchePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recherchePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(recherchePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rechercheButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(recherchePanelLayout.createSequentialGroup()
                        .addComponent(rechercheParLabel)
                        .addGap(18, 18, 18)
                        .addComponent(rechercheComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rechercheField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        recherchePanelLayout.setVerticalGroup(
            recherchePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recherchePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(recherchePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rechercheParLabel)
                    .addComponent(rechercheComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rechercheField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rechercheButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(listePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recherchePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(operationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addComponent(listePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(recherchePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void coefficientFormattedFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coefficientFormattedFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coefficientFormattedFieldActionPerformed

    private void move(int i){
        try{
            codeMatiereField.setText(model.getValueAt(i, 0).toString());
            libelleField.setText(model.getValueAt(i, 1).toString());
            coefficientFormattedField.setText(model.getValueAt(i, 2).toString()); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Erreur de deplacement", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void ajouterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterButtonActionPerformed
        // TODO add your handling code here:
        Matiere mat;
        int coef;
        
        if(estVide(libelleField) || estVide(coefficientFormattedField)){
            JOptionPane.showMessageDialog(null, "Les champs 'Libellé' ou 'Coefficient' ne peuvent être laissés vides", "Champs vide(s)", JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                coef=Integer.valueOf(coefficientFormattedField.getText());
                coefficientFormattedField.setValue(null);
                mat=new Matiere(libelleField.getText(), coef);

                String codeMatiere=service.post(mat);
                
                if(codeMatiere==null){
                    JOptionPane.showMessageDialog(null, "Echec de l'ajout de la matière", "Erreur d'ajout", JOptionPane.ERROR_MESSAGE);
                }else{
                    mat.setCodeMatiere(codeMatiere);
                    String message="La matière :\n"
                                        +"\tCode matière = '"+mat.getCodeMatiere()+"'\n"
                                        +"\tLibellé = '"+mat.getLibelle()+"'\n"
                                        +"\tCoefficient = '"+mat.getCoef()+"'\n"
                                    +"a été ajoutée avec succès";
                    JOptionPane.showMessageDialog(null, message, "Succès de l'ajout", JOptionPane.INFORMATION_MESSAGE);
                    registreService.saveToLog(profile.getLogin(), "AJOUT", codeMatiere);
                    eraseField();
                    addRow(mat);
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Le champ 'Coefficient' doit être un chiffre : "+e.getLocalizedMessage(), "Valeur incorrecte", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ajouterButtonActionPerformed

    private void supprimerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerButtonActionPerformed
        // TODO add your handling code here:
        Integer deleted;
        if(!estVide(codeMatiereField)){
            if(JOptionPane.showConfirmDialog(null, "Voullez-vous vraiment supprimer la matière dont le Code Matière= '"+
                codeMatiereField.getText()+"' ?", "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                
                deleted=service.delete(codeMatiereField.getText());

                if(deleted==1){
                    JOptionPane.showMessageDialog(null, "La matière dont le Code Matière= '"+codeMatiereField.getText()+"' a été supprimé(e) avec succès", "Succès de suppression", JOptionPane.INFORMATION_MESSAGE);
                    registreService.saveToLog(profile.getLogin(), "SUPPRESSION", codeMatiereField.getText());
                    eraseField();
                    fillModel(service.getAll());
                }else{
                    JOptionPane.showMessageDialog(null, "Echec de la suppression de la matière dont le Code Matiere= '"+codeMatiereField.getText()+"'", "Erreur de suppression", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_supprimerButtonActionPerformed

    private void modifierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierButtonActionPerformed
        // TODO add your handling code here:
        Matiere mat;
        Integer updated;
        String codeMatiere;
        int coef;
        
        if(!estVide(codeMatiereField)){
            if(estVide(libelleField) || estVide(coefficientFormattedField)){
                JOptionPane.showMessageDialog(null, "Les champs 'Libellé' ou 'Coefficient' ne peuvent être laissés vides", "Champs vide(s)", JOptionPane.WARNING_MESSAGE);
            }else{
                codeMatiere=codeMatiereField.getText();
                try{
                    coef=Integer.valueOf(coefficientFormattedField.getText());
                    coefficientFormattedField.setValue(null);

                    if(JOptionPane.showConfirmDialog(null, "Voullez-vous enregistrer les modifications apportées à la matière dont le Code Matière= '"+codeMatiere+"' ?", "Confirmation de mise à jour", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)== JOptionPane.YES_OPTION){
                        mat=new Matiere(libelleField.getText(), coef);

                        updated=service.put(codeMatiere, mat);
                        
                        if(updated==1){
                            JOptionPane.showMessageDialog(null, "Les modifications ont été enregistrées avec succès", "Succès de mise à jour", JOptionPane.INFORMATION_MESSAGE);
                            registreService.saveToLog(profile.getLogin(), "MODIFICATION", codeMatiere);
                            eraseField();
                            fillModel(service.getAll());
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Echec de l'enregistrement des modifications", "Erreur de mise à jour", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Le champ 'Coefficient' doit être un chiffre : "+e.getLocalizedMessage(), "Valeur incorrecte", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_modifierButtonActionPerformed

    private void actualiserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualiserButtonActionPerformed
        // TODO add your handling code here:
        eraseField();
        fillModel(service.getAll());
    }//GEN-LAST:event_actualiserButtonActionPerformed

    private void rechercheButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheButtonActionPerformed
        // TODO add your handling code here
        String searchType, lookingFor;
        Matiere mat, listMatiere[];
        
        if(estVide(rechercheField)){
            JOptionPane.showMessageDialog(null, "Le champs de recherche est vide", "Champs vide", JOptionPane.WARNING_MESSAGE);
        }else{
            searchType=rechercheComboBox.getSelectedItem().toString();
            lookingFor=rechercheField.getText();
            
            if(searchType.equals("code matière")){
                mat=service.get(lookingFor);
                
                if(mat.getCodeMatiere()==null) {
                    JOptionPane.showMessageDialog(null, "Aucune matière dont "+searchType+"= ' "+lookingFor+" ' n'a été trouvée", "Aucun resultat", JOptionPane.WARNING_MESSAGE);
        	}else{
                    fillModel(mat);
                    eraseField();
        	}
            }else{
                if(searchType.equals("coefficient"))
                    listMatiere=service.getAll(lookingFor);
                else
                    listMatiere=service.getKW(lookingFor);
        	
                if(listMatiere.length==0){
                    JOptionPane.showMessageDialog(null, "Aucune matière dont "+searchType+"= ' "+lookingFor+" ' n'a été trouvée", "Aucun resultat", JOptionPane.WARNING_MESSAGE);
        	}else{
                    fillModel(listMatiere);
                    eraseField();
        	}
            }
            registreService.saveToLog(profile.getLogin(), "RECHERCHE", "Matière("+searchType+" = '"+lookingFor+"')");
        }
    }//GEN-LAST:event_rechercheButtonActionPerformed

    private void matiereTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_matiereTableMouseClicked
        // TODO add your handling code here:
        try{
            move(matiereTable.getSelectedRow());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Erreur de deplacement", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_matiereTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualiserButton;
    private javax.swing.JButton ajouterButton;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JTextField codeMatiereField;
    private javax.swing.JLabel codeMatiereLabel;
    private javax.swing.JFormattedTextField coefficientFormattedField;
    private javax.swing.JLabel coefficientLabel;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JTextField libelleField;
    private javax.swing.JLabel libelleLabel;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JPanel listePanel;
    private javax.swing.JTable matiereTable;
    private javax.swing.JButton modifierButton;
    private javax.swing.JPanel operationPanel;
    private javax.swing.JButton rechercheButton;
    private javax.swing.JComboBox<String> rechercheComboBox;
    private javax.swing.JTextField rechercheField;
    private javax.swing.JPanel recherchePanel;
    private javax.swing.JLabel rechercheParLabel;
    private javax.swing.JButton supprimerButton;
    // End of variables declaration//GEN-END:variables
}
