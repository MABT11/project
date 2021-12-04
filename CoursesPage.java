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
import javax.swing.table.*;

public class CoursesPage extends JFrame implements ActionListener, MouseListener, DocumentListener{

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
	private JScrollPane scroll;
	private DefaultTableModel model;

	public CoursesPage(){
		setTitle("Banner Self Service");
		
		init();
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		//square box around the word
		backButton.setFocusable(false);
		//to add functionality to the box
		backButton.addActionListener(this);
		
		//to add the dynamic search update while typing
		filter.getDocument().addDocumentListener(this);
		
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
		String[] colName= {"Code", "Name", "Hours","Section","Time","Room #","Instructor","Registered Students","Maximum Students","Start date", "End date","Department"};
		Courses courses = new Courses();
		/*
		 * creating the table and configuring its dimensions
		 */
		model = new DefaultTableModel(courses.coursesInfo(),colName);
		table = new JTable(model){
		    @Override					// set all cells to uneditable
		    public boolean isCellEditable(int row, int column) {
		    	return false;
		    	}};
		table.setPreferredScrollableViewportSize(new Dimension(400, 320));
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
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Courses"));

		addComponents();
		pack();
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(880, 500));
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
		//to span horizontally
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill =  GridBagConstraints.HORIZONTAL;

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
			new AddCourse();
			dispose();
		}
		if(e.getSource()==removeLabel) {
			new AddDropInstructor();
			dispose();
		}
		if(e.getSource()==modifyLabel) {
			new CoursesPage();
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
		
	}
}