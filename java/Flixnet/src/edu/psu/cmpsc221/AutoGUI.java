
/*
 * @name  -> Dan Tracy
 * @ID    -> 927347146
 * @Group -> Me
 */

package edu.psu.cmpsc221;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Dan
 */
public class AutoGUI extends javax.swing.JFrame {
    private FlixNetProcessor processor = null;

    public AutoGUI() {
        String url = "jdbc:derby://localhost/FlixNetDB";
        processor = new FlixNetProcessor(url, "admin","password");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSearchCust = new javax.swing.JTabbedPane();
        pnlHome = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jumpAddMovie = new javax.swing.JButton();
        jumpListMovie = new javax.swing.JButton();
        jumpAddCust = new javax.swing.JButton();
        jumpRentMovie = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnJumpToSearchCust = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlAddMovie = new javax.swing.JPanel();
        txtTitle = new javax.swing.JTextField();
        txtGenre = new javax.swing.JTextField();
        txtRelease = new javax.swing.JTextField();
        txtMID = new javax.swing.JTextField();
        btnClearAddMovie = new javax.swing.JButton();
        btnAddMovieBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addMovieErrorLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        movieTextArea = new javax.swing.JTextArea();
        btnAddMovieDisplay = new javax.swing.JButton();
        addMovieComboBox = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        pnlAddCust = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCustFirstName = new javax.swing.JTextField();
        txtCustLastName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCustAddr = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCustCity = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCustZip = new javax.swing.JTextField();
        txtCustPhone0 = new javax.swing.JTextField();
        txtCustPhone1 = new javax.swing.JTextField();
        txtCustPhone2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnClearCustInfo = new javax.swing.JButton();
        btnSubmitNewCust = new javax.swing.JButton();
        custInfoComboBox = new javax.swing.JComboBox();
        btnDisplayCust = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAddCustTextArea = new javax.swing.JTextArea();
        txtCustAddr2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lblAddCustError = new javax.swing.JLabel();
        custStateComboBox = new javax.swing.JComboBox();
        pnlListMovies = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnListMoviesBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtListMovieTextArea = new javax.swing.JTextArea();
        movieListOrderBox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtListMovieByID = new javax.swing.JTextField();
        btnSearchByID = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        listMovieComboBox = new javax.swing.JComboBox();
        btnSubmitTopTen = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtCustSearchTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtCustSearchID = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtCustSearchMovieList = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        pnlRentMovie = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRentCustArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtRentMovieArea = new javax.swing.JTextArea();
        btnRentDisplay = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtRentalTextArea = new javax.swing.JTextArea();
        txtRentalCId = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtRentalMId = new javax.swing.JTextField();
        btnRentalClr = new javax.swing.JButton();
        btnRentalSubmit = new javax.swing.JButton();
        lblRentalError = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FlixNet Database");
        setResizable(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel21.setText("Welcome To Flix Net's Database Management Software");

        jumpAddMovie.setText("Add Movies to DB");
        jumpAddMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpAddMovieActionPerformed(evt);
            }
        });

        jumpListMovie.setText("List the Movies in the DB");
        jumpListMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpListMovieActionPerformed(evt);
            }
        });

        jumpAddCust.setText("Add Customer to DB");
        jumpAddCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpAddCustActionPerformed(evt);
            }
        });

        jumpRentMovie.setText("Rent Out a Movie from the DB");
        jumpRentMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpRentMovieActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/psu/cmpsc221/nf.gif"))); // NOI18N

        jLabel24.setText("The Movie Company With The Shamelessly Stolen Logo");

        btnJumpToSearchCust.setText("Search For Customer");
        btnJumpToSearchCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumpToSearchCustActionPerformed(evt);
            }
        });

        jButton2.setText("Quit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jumpAddMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumpListMovie))
                .addGap(94, 94, 94)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJumpToSearchCust)
                    .addComponent(jumpAddCust))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(jumpRentMovie)
                .addGap(317, 317, 317))
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel24)
                .addContainerGap(351, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHomeLayout.createSequentialGroup()
                .addContainerGap(747, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlHomeLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnJumpToSearchCust, jumpAddCust, jumpAddMovie, jumpListMovie, jumpRentMovie});

        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHomeLayout.createSequentialGroup()
                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlHomeLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlHomeLayout.createSequentialGroup()
                                        .addComponent(jumpAddMovie)
                                        .addGap(48, 48, 48)
                                        .addComponent(jumpListMovie))
                                    .addGroup(pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jumpAddCust)
                                        .addGroup(pnlHomeLayout.createSequentialGroup()
                                            .addGap(73, 73, 73)
                                            .addComponent(btnJumpToSearchCust))))
                                .addGap(47, 47, 47))
                            .addGroup(pnlHomeLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addGap(79, 79, 79)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jumpRentMovie)
                        .addGap(57, 57, 57)
                        .addComponent(jButton2))
                    .addGroup(pnlHomeLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel24)))
                .addContainerGap())
        );

        pnlHomeLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnJumpToSearchCust, jumpAddCust});

        pnlSearchCust.addTab("Home", pnlHome);

        btnClearAddMovie.setText("Clear");
        btnClearAddMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAddMovieActionPerformed(evt);
            }
        });

        btnAddMovieBtn.setText("Enter");
        btnAddMovieBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMovieBtnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setText("Add Movie To Database");

        jLabel1.setText("Title");

        jLabel2.setText("ID");

        jLabel3.setText("Genre");

        jLabel4.setText("Year");

        addMovieErrorLbl.setForeground(new java.awt.Color(255, 0, 0));
        addMovieErrorLbl.setText(" ");

        movieTextArea.setColumns(20);
        movieTextArea.setEditable(false);
        movieTextArea.setRows(5);
        jScrollPane1.setViewportView(movieTextArea);

        btnAddMovieDisplay.setText("Display");
        btnAddMovieDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMovieDisplayActionPerformed(evt);
            }
        });

        addMovieComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Title", "Genre", "Release" }));

        jLabel32.setText("Sort Displayed Movies By");

        javax.swing.GroupLayout pnlAddMovieLayout = new javax.swing.GroupLayout(pnlAddMovie);
        pnlAddMovie.setLayout(pnlAddMovieLayout);
        pnlAddMovieLayout.setHorizontalGroup(
            pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddMovieLayout.createSequentialGroup()
                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlAddMovieLayout.createSequentialGroup()
                                .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addGroup(pnlAddMovieLayout.createSequentialGroup()
                                        .addComponent(btnClearAddMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAddMovieBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlAddMovieLayout.createSequentialGroup()
                                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addGap(35, 35, 35)
                                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMID, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(30, 30, 30))
                            .addComponent(addMovieErrorLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddMovieLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(28, 28, 28)
                        .addComponent(addMovieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddMovieDisplay)))
                .addContainerGap())
        );

        pnlAddMovieLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddMovieBtn, btnClearAddMovie});

        pnlAddMovieLayout.setVerticalGroup(
            pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddMovieLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addMovieErrorLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(31, 31, 31)
                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRelease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(64, 64, 64)
                        .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddMovieBtn)
                            .addComponent(btnClearAddMovie)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAddMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMovieDisplay)
                    .addComponent(addMovieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addContainerGap())
        );

        pnlSearchCust.addTab("Add Movie", pnlAddMovie);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel7.setText("Add Customer To Database");

        jLabel8.setText("First Name");

        jLabel9.setText("Last Name");

        jLabel10.setText("Address 1");

        jLabel11.setText("State");

        jLabel12.setText("City");

        jLabel13.setText("Zip");

        jLabel14.setText("Phone");

        btnClearCustInfo.setText("Clear");
        btnClearCustInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCustInfoActionPerformed(evt);
            }
        });

        btnSubmitNewCust.setText("Enter");
        btnSubmitNewCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitNewCustActionPerformed(evt);
            }
        });

        custInfoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Name", "ID", "Address", "State", "Zip", "Phone" }));

        btnDisplayCust.setText("Display Customers");
        btnDisplayCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayCustActionPerformed(evt);
            }
        });

        jLabel20.setText("Sort By");

        txtAddCustTextArea.setColumns(20);
        txtAddCustTextArea.setRows(5);
        jScrollPane3.setViewportView(txtAddCustTextArea);

        jLabel22.setText("Address 2");

        lblAddCustError.setForeground(new java.awt.Color(255, 0, 0));
        lblAddCustError.setText(" ");

        custStateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" }));

        javax.swing.GroupLayout pnlAddCustLayout = new javax.swing.GroupLayout(pnlAddCust);
        pnlAddCust.setLayout(pnlAddCustLayout);
        pnlAddCustLayout.setHorizontalGroup(
            pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddCustLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddCustError, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlAddCustLayout.createSequentialGroup()
                            .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAddCustLayout.createSequentialGroup()
                                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtCustPhone0, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCustPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCustPhone2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCustCity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(pnlAddCustLayout.createSequentialGroup()
                                    .addComponent(btnClearCustInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(105, 105, 105)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlAddCustLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel13))
                                .addGroup(pnlAddCustLayout.createSequentialGroup()
                                    .addComponent(custStateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCustZip, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                                .addComponent(btnSubmitNewCust, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlAddCustLayout.createSequentialGroup()
                            .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCustAddr, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel8)
                                .addComponent(txtCustFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel22)
                                .addComponent(jLabel9)
                                .addComponent(txtCustLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(txtCustAddr2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddCustLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(14, 14, 14)
                .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlAddCustLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(custInfoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDisplayCust, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlAddCustLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCustPhone0, txtCustPhone1});

        pnlAddCustLayout.setVerticalGroup(
            pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddCustLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddCustLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lblAddCustError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCustFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCustAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustAddr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCustCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(custStateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCustPhone0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustPhone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustPhone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClearCustInfo)
                            .addComponent(btnSubmitNewCust)))
                    .addGroup(pnlAddCustLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlAddCustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(custInfoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDisplayCust)
                            .addComponent(jLabel20))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pnlSearchCust.addTab("Add Customer", pnlAddCust);

        pnlListMovies.setForeground(new java.awt.Color(255, 0, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel5.setText("Flix Net Movie Database");

        btnListMoviesBtn.setText("Display");
        btnListMoviesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListMoviesBtnActionPerformed(evt);
            }
        });

        txtListMovieTextArea.setColumns(20);
        txtListMovieTextArea.setRows(5);
        jScrollPane2.setViewportView(txtListMovieTextArea);

        movieListOrderBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Title", "ID", "Genre", "Release" }));

        jLabel19.setText("Order By");

        jLabel25.setText("Search By Movie ID");

        btnSearchByID.setText("Search");
        btnSearchByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByIDActionPerformed(evt);
            }
        });

        jLabel30.setText("List All Movies");

        jLabel31.setText("Top 10 By State");

        listMovieComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" }));

        btnSubmitTopTen.setText("GO!");
        btnSubmitTopTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitTopTenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlListMoviesLayout = new javax.swing.GroupLayout(pnlListMovies);
        pnlListMovies.setLayout(pnlListMoviesLayout);
        pnlListMoviesLayout.setHorizontalGroup(
            pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListMoviesLayout.createSequentialGroup()
                .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListMoviesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE))
                    .addGroup(pnlListMoviesLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlListMoviesLayout.createSequentialGroup()
                                .addComponent(listMovieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSubmitTopTen))
                            .addComponent(jLabel31))))
                .addContainerGap())
            .addGroup(pnlListMoviesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtListMovieByID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchByID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
                .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addGroup(pnlListMoviesLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(movieListOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnListMoviesBtn)))
                .addGap(23, 23, 23))
            .addGroup(pnlListMoviesLayout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        pnlListMoviesLayout.setVerticalGroup(
            pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListMoviesLayout.createSequentialGroup()
                .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListMoviesLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlListMoviesLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtListMovieByID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSearchByID))
                                        .addGroup(pnlListMoviesLayout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(movieListOrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnListMoviesBtn)
                                                .addComponent(jLabel19))
                                            .addGap(6, 6, 6)))
                                    .addGroup(pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlListMoviesLayout.createSequentialGroup()
                                            .addComponent(jLabel31)
                                            .addGap(33, 33, 33))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListMoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(listMovieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSubmitTopTen))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListMoviesLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(39, 39, 39)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                    .addGroup(pnlListMoviesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlSearchCust.addTab("List Movies", pnlListMovies);

        txtCustSearchTextArea.setColumns(20);
        txtCustSearchTextArea.setRows(5);
        jScrollPane7.setViewportView(txtCustSearchTextArea);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel26.setText("Enter Customer ID");

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        jLabel27.setText("Search For A Customer");

        txtCustSearchMovieList.setColumns(20);
        txtCustSearchMovieList.setRows(5);
        jScrollPane8.setViewportView(txtCustSearchMovieList);

        jLabel28.setText("Customer Details");

        jLabel29.setText("Rented Movies");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addComponent(txtCustSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jButton1)
                            .addComponent(txtCustSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlSearchCust.addTab("Search Customers", jPanel1);

        txtRentCustArea.setColumns(20);
        txtRentCustArea.setRows(5);
        jScrollPane4.setViewportView(txtRentCustArea);

        txtRentMovieArea.setColumns(20);
        txtRentMovieArea.setRows(5);
        jScrollPane5.setViewportView(txtRentMovieArea);

        btnRentDisplay.setText("Display");
        btnRentDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentDisplayActionPerformed(evt);
            }
        });

        jLabel15.setText("Customer ID");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel16.setText("Flix NetMovie Rental");

        txtRentalTextArea.setColumns(20);
        txtRentalTextArea.setRows(5);
        jScrollPane6.setViewportView(txtRentalTextArea);

        jLabel17.setText("Movie ID");

        btnRentalClr.setText("Clear");
        btnRentalClr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentalClrActionPerformed(evt);
            }
        });

        btnRentalSubmit.setText("Enter");
        btnRentalSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentalSubmitActionPerformed(evt);
            }
        });

        lblRentalError.setForeground(new java.awt.Color(255, 0, 0));
        lblRentalError.setText(" ");

        jLabel18.setText("Please Enter Both IDs");

        javax.swing.GroupLayout pnlRentMovieLayout = new javax.swing.GroupLayout(pnlRentMovie);
        pnlRentMovie.setLayout(pnlRentMovieLayout);
        pnlRentMovieLayout.setHorizontalGroup(
            pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRentMovieLayout.createSequentialGroup()
                        .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                                .addComponent(btnRentalClr, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)
                                                .addComponent(btnRentalSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel17)
                                                    .addComponent(jLabel15))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtRentalMId)
                                                    .addComponent(txtRentalCId, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                        .addGap(125, 125, 125)
                                        .addComponent(jLabel18))
                                    .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(jLabel16)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE))
                            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                .addComponent(lblRentalError, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRentMovieLayout.createSequentialGroup()
                        .addComponent(btnRentDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))))
        );
        pnlRentMovieLayout.setVerticalGroup(
            pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRentMovieLayout.createSequentialGroup()
                        .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRentDisplay)
                        .addGap(57, 57, 57))
                    .addGroup(pnlRentMovieLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(23, 23, 23)
                        .addComponent(lblRentalError, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addGroup(pnlRentMovieLayout.createSequentialGroup()
                                .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRentalCId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(25, 25, 25)
                                .addComponent(txtRentalMId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(pnlRentMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRentalClr)
                            .addComponent(btnRentalSubmit))
                        .addGap(172, 172, 172))))
        );

        pnlRentMovieLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane4, jScrollPane5});

        pnlSearchCust.addTab("Rent Movie", pnlRentMovie);

        jMenu3.setText("Exit");
        jMenu3.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu3MenuSelected(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSearchCust, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSearchCust, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu3MenuSelected
        processor.invalidate();
        System.exit(1);
    }//GEN-LAST:event_jMenu3MenuSelected

    private void btnRentalSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentalSubmitActionPerformed
        if(txtRentalMId.getText().equals("") ||
                txtRentalCId.getText().equals("")){
            lblRentalError.setText("");
            lblRentalError.setText("Both the Customer ID and " +
                    "Movie ID must be entered.");
        } else{
            lblRentalError.setText("");
            try{
                int mid = Integer.parseInt(txtRentalMId.getText());
                int cid = Integer.parseInt(txtRentalCId.getText());
                Customer c = processor.getCustomer(cid);
                Movie m = processor.getMovie(mid);
                if(m == null){
                    lblRentalError.setText("");
                    lblRentalError.setText("Movie Not Found");
                    return;
                }
                if(c == null){
                    lblRentalError.setText("");
                    lblRentalError.setText("Customer Not Found");
                    return;
                }
                processor.rentsMovie(c, m);
                txtRentalTextArea.setText( getRentalList() );
                txtRentalTextArea.setText( getRentalList() );
                clearRentalInfo();

            }catch(NumberFormatException ex){
                lblRentalError.setText("The Identification Numbers " +
                        "cannot include letters");
            }
        }
}//GEN-LAST:event_btnRentalSubmitActionPerformed

    private void btnRentalClrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentalClrActionPerformed
        clearRentalInfo();
}//GEN-LAST:event_btnRentalClrActionPerformed

    private void btnRentDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentDisplayActionPerformed
        txtRentCustArea.setText("");
        txtRentMovieArea.setText("");
        txtRentalTextArea.setText("");
        txtRentCustArea.setText( getCustList(null) );
        txtRentMovieArea.setText( getMovieList(null) );
        txtRentalTextArea.setText( getRentalList() );
}//GEN-LAST:event_btnRentDisplayActionPerformed

    private void btnListMoviesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListMoviesBtnActionPerformed
        String text = movieListOrderBox.getSelectedItem().toString();
        if(text.equals("Title")){text = "title";}
        if(text.equals("ID")){text = "mid";}
        if(text.equals("Genre")){text="genre";}
        if(text.equals("Release")){text="yr";}
        if(text.equals("Rating")){text="rating";}
        txtListMovieTextArea.setText("");
        txtListMovieTextArea.setText( getMovieList(text) );


}//GEN-LAST:event_btnListMoviesBtnActionPerformed

    private void btnDisplayCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayCustActionPerformed
        //Phone, city, and zip will already be correctly set.
        String temp = custInfoComboBox.getSelectedItem().toString();
        if(temp.equals("ID")){temp = "cid";}
        if(temp.equals("Name")){temp = "cname";}
        if(temp.equals("Address")){temp = "address1";}
        if(temp.equals("State")){temp = "st";}
        if(temp == null){temp = "cname";}
        txtAddCustTextArea.setText("");
        txtAddCustTextArea.setText(getCustList(temp));

    }//GEN-LAST:event_btnDisplayCustActionPerformed

    private void btnSubmitNewCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitNewCustActionPerformed
        if( txtCustFirstName.getText().equals("") ||
                txtCustLastName.getText().equals("")  ||
                txtCustPhone0.getText().equals("")    ||
                txtCustPhone1.getText().equals("")    ||
                txtCustPhone2.getText().equals("")    ||
                txtCustAddr.getText().equals("")      ||
                txtCustCity.getText().equals("")      ||
                txtCustZip.getText().equals("")
                ) {
            lblAddCustError.setText("");
            lblAddCustError.setText("All Fields Excluding " +
                    "Address 2 must be completed.");
        } else{
            Random rand = new Random();
            String addr2;
            String name =  txtCustFirstName.getText().trim() + " " +
                    txtCustLastName.getText().trim();
            String phone = txtCustPhone0.getText().trim() +
                    txtCustPhone1.getText().trim() +
                    txtCustPhone2.getText().trim();

            String state = custStateComboBox.getSelectedItem().toString();
            String addr1 = txtCustAddr.getText().trim();
            String city  = txtCustCity.getText().trim();
            String zip   = txtCustZip.getText().trim();
            int id = Math.abs(rand.nextInt()/1000);
            if( txtCustAddr2 == null){ addr2 = "";}
            else{ addr2 = txtCustAddr2.getText().trim();}

            try{
                //To test if actual numbers were entered
                int num = Integer.parseInt(phone);
                num = Integer.parseInt(zip);
                if(phone.length() != 10){
                    lblAddCustError.setText("The Phone Number is invalid, " +
                            "please check it again.");
                    throw new Exception();
                }

                Address addr = new Address(addr1, addr2, city, state, zip);
                Customer cust = new Customer(id, name, addr, phone);
                processor.addCustomer(cust);

                clearCustInfo();

            }catch(NumberFormatException ex){
                lblAddCustError.setText("The Phone Number and " +
                        "Zipcode cannot contain letters.");
            }catch(Exception e){}

        }
}//GEN-LAST:event_btnSubmitNewCustActionPerformed

    private void btnClearCustInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCustInfoActionPerformed
        clearCustInfo();
}//GEN-LAST:event_btnClearCustInfoActionPerformed

    private void btnAddMovieDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMovieDisplayActionPerformed
        String text = addMovieComboBox.getSelectedItem().toString();

        if(text.equals("Title")){text = "title";}
        if(text.equals("ID")){text = "mid";}
        if(text.equals("Genre")){text="genre";}
        if(text.equals("Release")){text="yr";}
        if(text.equals("Rating")){text="rating";}
        movieTextArea.setText("");
        movieTextArea.setText( getMovieList(text) );
}//GEN-LAST:event_btnAddMovieDisplayActionPerformed

    private void btnAddMovieBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMovieBtnActionPerformed
        String title;
        String genre;
        int id;
        int release;

        if(txtTitle.getText().equals("")   ||
           txtGenre.getText().equals("")   ||
           txtRelease.getText().equals("") ||
           txtMID.getText().equals("") )
        {
            addMovieErrorLbl.setText("");
            addMovieErrorLbl.setText("Missing Information please check input.");
            return;
        } else{
            movieTextArea.setText("");
            addMovieErrorLbl.setText("");
            try{
                id = Integer.parseInt(txtMID.getText());
                release = Integer.parseInt(txtRelease.getText());
                genre = txtGenre.getText();
                title = txtTitle.getText();
                Movie m = new Movie(id,title,genre,release);
                processor.addMovie(m);
                txtMID.setText("");
                txtGenre.setText("");
                txtTitle.setText("");
                txtRelease.setText("");
            }catch(NumberFormatException ex){
                addMovieErrorLbl.setText("");
                addMovieErrorLbl.setText("Number expected, character found!");
            }
        }
        movieTextArea.setText(getMovieList(null));
}//GEN-LAST:event_btnAddMovieBtnActionPerformed

    private void btnClearAddMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAddMovieActionPerformed
        txtTitle.setText("");
        txtGenre.setText("");
        txtRelease.setText("");
        txtMID.setText("");
}//GEN-LAST:event_btnClearAddMovieActionPerformed

    private void jumpAddMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpAddMovieActionPerformed
        pnlSearchCust.setSelectedIndex(1);
}//GEN-LAST:event_jumpAddMovieActionPerformed

    private void jumpAddCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpAddCustActionPerformed
        pnlSearchCust.setSelectedIndex(2);
    }//GEN-LAST:event_jumpAddCustActionPerformed

    private void jumpListMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpListMovieActionPerformed
        pnlSearchCust.setSelectedIndex(3);
    }//GEN-LAST:event_jumpListMovieActionPerformed

    private void jumpRentMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpRentMovieActionPerformed
        pnlSearchCust.setSelectedIndex(5);
    }//GEN-LAST:event_jumpRentMovieActionPerformed

    private void btnSearchByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByIDActionPerformed
        if( txtListMovieByID.getText().equals("") ){ return; }
        try{
            int mid = Integer.parseInt(txtListMovieByID.getText());
            Movie m = processor.getMovie(mid);
            if(m == null){
                throw new Exception();
            }
            double rating = processor.getRating(m);
            String info = printMovie(m) + "\t" + rating;
            txtListMovieTextArea.setText("");
            txtListMovieTextArea.setText( "Movie: " + m.getTitle() + " info" +
                    "\n\n" + printMovieHeader() + info);
        }catch(NumberFormatException ex){
            txtListMovieTextArea.setText("");
            txtListMovieTextArea.setText("Please enter a number in the search" +
                    "field");
        }catch(Exception ex){
            txtListMovieTextArea.setText("");
            txtListMovieTextArea.setText("A movie with that ID was not found.");
        }
    }//GEN-LAST:event_btnSearchByIDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if( txtCustSearchID.getText().equals("") ){
            txtCustSearchTextArea.setText("");
            txtCustSearchTextArea.setText("Please Enter the Customer ID when" +
                    " searching by the Customer ID");
            return;
        }
        try{
            int id = Integer.parseInt(txtCustSearchID.getText());
            Customer c = processor.getCustomer(id);
            if(c == null){
                throw new Exception();
            }
            ArrayList<Movie> temp = processor.getMovieList(c);
            if( temp.isEmpty()){
                throw new Exception();
            }
            String list = "";
            for(int i = 0; i < temp.size(); i++){
                list += temp.get(i).getTitle() + "\n";
            }

            txtCustSearchTextArea.setText("");
            txtCustSearchTextArea.setText(printCustHeader() + "\n" +c.print());
            txtCustSearchMovieList.setText("");
            txtCustSearchMovieList.setText(list);

        }catch(NumberFormatException ex){
            txtCustSearchTextArea.setText("");
            txtCustSearchTextArea.setText("Please enter an ID" +
                    " number in the search field");
        }catch(Exception ex){
            txtCustSearchTextArea.setText("");
            txtCustSearchTextArea.setText("A Customer with that" +
                    " ID was not found.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSubmitTopTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitTopTenActionPerformed
        int counter = 0;
        String temp ="";
        String state = listMovieComboBox.getSelectedItem().toString();
        HashMap<Integer,String> map = processor.getPopByState(state);

        if(map.isEmpty() || map == null){
            txtListMovieTextArea.setText("");
            txtListMovieTextArea.setText("No Movies Found");
            return;
        }
        TreeSet<Integer> sortedKeys = new TreeSet<Integer>(map.keySet());

        for(int key : sortedKeys){
            counter++;
            temp += map.get(key) + "\t" + key + "\n";

            if(counter == 10){
                break;
            }
        }
        txtListMovieTextArea.setText("");
        txtListMovieTextArea.setText("TOP 10 MOVIES IN: " + state +
                "\nTITLE \t\t RATING \n\n" + temp);

}//GEN-LAST:event_btnSubmitTopTenActionPerformed

    private void btnJumpToSearchCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumpToSearchCustActionPerformed
        pnlSearchCust.setSelectedIndex(4);
}//GEN-LAST:event_btnJumpToSearchCustActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int num = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?",
                                      "Quit?", JOptionPane.YES_NO_OPTION,
                                      JOptionPane.WARNING_MESSAGE);
       if( num == JOptionPane.OK_OPTION){
           processor.invalidate();
           System.exit(1);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void clearCustInfo(){
        txtCustFirstName.setText("");
        txtCustLastName.setText("");
        txtCustPhone0.setText("");
        txtCustPhone1.setText("");
        txtCustPhone2.setText("");
        custStateComboBox.setSelectedIndex(0);
        txtCustAddr.setText("");
        txtCustCity.setText("");
        txtCustZip.setText("");
    }

    private void clearRentalInfo(){
        txtRentalMId.setText("");
        txtRentalCId.setText("");
    }

    private String getMovieList(String arg ){
        if(processor == null){
            addMovieErrorLbl.setText("No DB Connection!");
        }
        if( arg == null){arg = "mid";}

        String temp = printMovieHeader();

        ArrayList<Movie> tempList = processor.getMovieDB(arg);
        for(int i = 0; i < tempList.size(); i++){
            Movie m = tempList.get(i);
            temp += printMovie(m) + "\t"
                    + processor.getRating(m)
                    + "\n";
        }
        return temp;
    }

    private String getCustList(String arg){
        
        if( arg == null){ arg = "cid";}
        String temp = printCustHeader();
        ArrayList<Customer> tempList = processor.getCustDB(arg);
        for(int i = 0; i < tempList.size(); i++){
            Customer c = tempList.get(i);
            temp += c.print() + "\n";
        }

        return temp;
    }
    
    private String getRentalList(){
        String sp = "------------\t";
        String temp = "CUST ID \t MOVIE ID \t TITLE \t\tCUST NAME\n" +
                      sp + sp + sp + "\t" + sp + "\n";
        ArrayList<String> list = processor.getRentalDB();
        for( int i = 0; i <list.size(); i++){
            temp += list.get(i) + "\n";
        }
        return temp;
    }

    private String printCustHeader(){
        String tab = "\t";
        String temp ="ID" + tab +
                     "NAME" + tab +
                     "ADDR1" + tab + tab + tab +
                     "ADDR2" + tab + tab + tab +
                     "CITY" + tab + tab +
                     "STATE" + tab + tab +
                     "ZIP" + tab + tab +
                     "PHONE\n\n";
        return temp;
    }

    private String printMovieHeader(){
        String tab = "\t";
        return(  "ID" + tab + tab +
                 "TITLE" + tab + tab + tab +
                 "GENRE" + tab + tab +
                 "RELEASE" + tab +
                 "RATING\n\n"
               );
    }

    private String printMovie(Movie m){
        String sp = "\t\t";
        return( m.getID() + sp +
                m.getTitle() + sp + "\t" +
                m.getGenre() + sp +
                m.getYear()
              );
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox addMovieComboBox;
    private javax.swing.JLabel addMovieErrorLbl;
    private javax.swing.JButton btnAddMovieBtn;
    private javax.swing.JButton btnAddMovieDisplay;
    private javax.swing.JButton btnClearAddMovie;
    private javax.swing.JButton btnClearCustInfo;
    private javax.swing.JButton btnDisplayCust;
    private javax.swing.JButton btnJumpToSearchCust;
    private javax.swing.JButton btnListMoviesBtn;
    private javax.swing.JButton btnRentDisplay;
    private javax.swing.JButton btnRentalClr;
    private javax.swing.JButton btnRentalSubmit;
    private javax.swing.JButton btnSearchByID;
    private javax.swing.JButton btnSubmitNewCust;
    private javax.swing.JButton btnSubmitTopTen;
    private javax.swing.JComboBox custInfoComboBox;
    private javax.swing.JComboBox custStateComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jumpAddCust;
    private javax.swing.JButton jumpAddMovie;
    private javax.swing.JButton jumpListMovie;
    private javax.swing.JButton jumpRentMovie;
    private javax.swing.JLabel lblAddCustError;
    private javax.swing.JLabel lblRentalError;
    private javax.swing.JComboBox listMovieComboBox;
    private javax.swing.JComboBox movieListOrderBox;
    private javax.swing.JTextArea movieTextArea;
    private javax.swing.JPanel pnlAddCust;
    private javax.swing.JPanel pnlAddMovie;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlListMovies;
    private javax.swing.JPanel pnlRentMovie;
    private javax.swing.JTabbedPane pnlSearchCust;
    private javax.swing.JTextArea txtAddCustTextArea;
    private javax.swing.JTextField txtCustAddr;
    private javax.swing.JTextField txtCustAddr2;
    private javax.swing.JTextField txtCustCity;
    private javax.swing.JTextField txtCustFirstName;
    private javax.swing.JTextField txtCustLastName;
    private javax.swing.JTextField txtCustPhone0;
    private javax.swing.JTextField txtCustPhone1;
    private javax.swing.JTextField txtCustPhone2;
    private javax.swing.JTextField txtCustSearchID;
    private javax.swing.JTextArea txtCustSearchMovieList;
    private javax.swing.JTextArea txtCustSearchTextArea;
    private javax.swing.JTextField txtCustZip;
    private javax.swing.JTextField txtGenre;
    private javax.swing.JTextField txtListMovieByID;
    private javax.swing.JTextArea txtListMovieTextArea;
    private javax.swing.JTextField txtMID;
    private javax.swing.JTextField txtRelease;
    private javax.swing.JTextArea txtRentCustArea;
    private javax.swing.JTextArea txtRentMovieArea;
    private javax.swing.JTextField txtRentalCId;
    private javax.swing.JTextField txtRentalMId;
    private javax.swing.JTextArea txtRentalTextArea;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

}
