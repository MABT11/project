/*
 * to add drop and modify students details
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//add drop students to the courses
public class AddDropInstructor extends JFrame implements ActionListener, MouseListener, DocumentListener{

	private JButton backButton;
	
	private JLabel addLabel;
	private JLabel removeLabel;
	private JLabel modifyLabel;
	
	private GridBagLayout gbl_panel;
	private JPanel panel;
	private JTable table;
	private JTextField filter;
	private TableRowSorter<TableModel> rowSorter;
	private DefaultTableModel model;
	private JScrollPane scroll;
	
	public AddDropInstructor(){
		
		setTitle("Banner Self Service");
		setIconImage(Main.getIcon());
		
		init();
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		//square box around the word
		backButton.setFocusable(false);
		//to add functionality to the box
		backButton.addActionListener(this);
		
		filter.getDocument().addDocumentListener(this);
		filter.setToolTipText("Search for instructors by name, id, courses");
		
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
		String[] colName= {"ID", "Name", "Courses"};
		Users users = new Users();
		
		/*
		 * creating the table and configuring its dimensions
		 */
		model = new DefaultTableModel(users.getInstructorInfo(),colName);
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
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Add drop instructors"));
		
		addComponents();
		pack();
		setLocationRelativeTo(null);
		//#cols,#rows
		setMinimumSize(new Dimension(300, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*
	 * Initializing all attributes
	 */
	private void init() {
		
		 backButton = new JButton("Back");
		 addLabel = new JLabel("Add");
		 removeLabel = new JLabel("Remove");
		 modifyLabel = new JLabel("Modify");
		
		 gbl_panel = new GridBagLayout();
		 
		 panel = new JPanel(gbl_panel);
		 
		 filter = new JTextField(30);
	}
	/*
	 * adding components to the panel
	 */
	private void addComponents() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.fill =  GridBagConstraints.BOTH;
		
		constraints.gridx = 0;
        constraints.gridy = 0;
		panel.add(backButton,constraints);
		constraints.gridx = 1;
		panel.add(filter,constraints);
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
		add(panel);
	
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
	 * document event handler
	 * for the dynamic search
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String text = filter.getText();

        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
