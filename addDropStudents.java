/*
 * to add drop and modify students details
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//add drop students to the courses
public class AddDropStudents extends JFrame implements ActionListener, MouseListener, DocumentListener{

	private JButton backButton = new JButton("Back");
	private JLabel addLabel = new JLabel("Add");
	private JLabel removeLabel = new JLabel("Remove");
	private JLabel modifyLabel = new JLabel("Modify");

	private GridBagLayout gbl_panel = new GridBagLayout();
	private JPanel panel = new JPanel(gbl_panel);
	private JTable table;
	//search bar
	private JTextField filter = new JTextField(30);
	//for the table sort when clicking the headers of the table
	private TableRowSorter<TableModel> rowSorter;
	
	public AddDropStudents(){
		/*
		 * for making the jtable exapd to the left and write and shrink but it still disappers
		 */
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		setTitle("Banner Self Service");
		try {
			setIconImage(ImageIO.read(new File("ku.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//square box around the word
		backButton.setFocusable(false);
		//to add functionality to the box
		backButton.addActionListener(this);
		//to add the dynamic search update while typing
		filter.getDocument().addDocumentListener(this);
		filter.setToolTipText("Search for students by name, id, courses");

		
		GridBagConstraints constraints = new GridBagConstraints();
//		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
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
		Vector<Users> s =new Vector<Users>();
		s = users.getUsers();
		int len =s.size();
		int count = 0;
		for(int i = 0; i<len;i++) 
			if(s.elementAt(i).getOccupation().trim().equals(Main.type.STUDENT.name())) 
				count++;
			
		Object[][] students =new Object[count][3];
		for(int i = 0; i < count;i++) {
			for(int j = 0; j < len; j++) {
				if(s.elementAt(j).getOccupation().trim().equals(Main.type.STUDENT.name())) {
					students[i][0]=s.elementAt(j).getID();
					students[i][1]=s.elementAt(j).getFirstName()+" "+s.elementAt(j).getLastName();
					students[i][2]=s.elementAt(j).getPassword();
					s.removeElementAt(j);
					break;
				}
			}
		}
		/*
		 * creating the table and configuring its dimensions
		 */
		table = new JTable(new DefaultTableModel(students,colName)){
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

		JScrollPane scroll = new JScrollPane(table);
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
		//to span horizontally
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
		pack();
		setLocationRelativeTo(null);
		//#cols,#rows
		setMinimumSize(new Dimension(420, 420));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
