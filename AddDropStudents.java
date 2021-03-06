/*
 * Add drop students from courses, modify student details
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AddDropStudents extends JFrame implements TableModelListener, ActionListener, MouseListener, DocumentListener{

	private JButton backButton;
	
	private JLabel addLabel;
	private JLabel removeLabel;
	private JLabel modifyLabel;

	private GridBagLayout gbl_panel;
	private JPanel panel;
	private JTable table;
	//search bar
	private JTextField filter;
	//for the table sort when clicking the headers of the table
	private TableRowSorter<TableModel> rowSorter;
	private DefaultTableModel model;
	private JScrollPane scroll;
	
	public AddDropStudents(){
		/*
		 * for making the jtable exapd to the left and write and shrink but it still disappers
		 */
		setTitle("Banner Self Service");
		init();
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		
		//square box around the word
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		
		//to add the dynamic search update while typing
		filter.getDocument().addDocumentListener(this);
		filter.setToolTipText("Search for students by name, id, courses");

		addLabel.setForeground(Color.BLUE.darker());
		addLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addLabel.addMouseListener(this);
		
		removeLabel.setForeground(Color.BLUE.darker());
		removeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeLabel.addMouseListener(this);
		
		modifyLabel.setForeground(Color.BLUE.darker());
		modifyLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modifyLabel.addMouseListener(this);
		/*
		 * setting the table cols
		 * creating a vector of users to use it in the jtable
		 * count students assign it to object rows
		 */
		String[] colName= {"ID", "Name","Department", "Courses"};
		Users users = new Users();
		/*
		 * creating the table and configuring its dimensions
		 */
		model = new DefaultTableModel(users.getStudentInfo(), colName);
		table = new JTable(model){
		    @Override					// set all cells to uneditable
		    public boolean isCellEditable(int row, int column) {
		    	return false;
		    }};
		table.setPreferredScrollableViewportSize(new Dimension(200, 250));
		/*
		 * adding it to jscroll and adding the sorting functionality
		 */
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(this);
		table.getModel().addTableModelListener(this);
		setLayout(new BorderLayout());

		scroll = new JScrollPane(table);
		/*
		 * creating the search option
		 */
	    rowSorter= new TableRowSorter<>(table.getModel());
	    table.setRowSorter(rowSorter);
	    /*
	     * configuring the panel
	     */
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add drop students"));
		
		addComponents();
		pack();
		setLocationRelativeTo(null);
		//#cols,#rows
		setMinimumSize(new Dimension(800, 450));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*
	 * Initializing all attributes
	 */
	public void init() {
		
		 backButton = new JButton("Back");
		 addLabel = new JLabel("Add courses");
		 removeLabel = new JLabel("Remove courses");
		 modifyLabel = new JLabel("Modify student details");
		
		 gbl_panel = new GridBagLayout();
		 
		 panel = new JPanel(gbl_panel);
		 
		 filter = new JTextField(30);
	}
	public void addComponents() {
		//to span horizontally
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);		
		constraints.fill =  GridBagConstraints.BOTH;
		/*
		 * adding components to the panel
		 */
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(backButton,constraints);
		constraints.gridx = 1;
		panel.add(filter, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(addLabel,constraints);
		constraints.gridx = 1;
		constraints.gridheight = 100;
		panel.add(scroll,constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridheight = 1;
		panel.add(removeLabel,constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(modifyLabel,constraints);
		getContentPane().add(panel);
	}
	/*
	 * button events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==backButton) {
			new AdminPage();
			dispose();
		}
		
	}
	/*
	 * mouse actions for hyperlink text
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==addLabel) {
			new AddStudentCourses();
			dispose();
		}
		if(e.getSource()==removeLabel) {
			new RemoveStudentCourses();
			dispose();
		}
		if(e.getSource()==modifyLabel) {
			new ModifyStudentDetails();
			dispose();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * When the admin updates the user information from the table it also gets updated in the file
	 */
	@Override
	public void tableChanged(TableModelEvent e){
//		if(e.getColumn()>=0){
//			//				PrintWriter w = new PrintWriter(new FileOutputStream(new File("Test.txt"),true));
//							Object[][]data=new Object[15][4];
//							String[]crn=new String[5];
//							for(int i =0;i<15;i++){
//								for(int j =0;j<4;j++){
//									data[i][j]=table.getModel().getValueAt(i,j);
//									if(Verify.IDVerifier(""+data[i][j])){
//										System.out.print(data[i][j]+" ");
//									}
//									else if(Verify.NameVerifier((""+data[i][j]).replaceAll("\\s", ""))){
//										System.out.print(data[i][j]+" ");
//									}
//									else{
//										System.out.println("Error in ID field input");
//										table=new JTable(model);
//										return ;
//									}
//								}	
//								System.out.println();
//							}	
//		}
	}
	/* 
	 * document event handler
	 * for the dynamic search
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		String text = filter.getText();

        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
        	//(?i) means case insensitive
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		
		String text = filter.getText();

        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}