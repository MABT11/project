/*
 * Add drop students from courses, modify student details
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ModifyInstructorDetails extends JFrame implements  ActionListener, MouseListener, DocumentListener, TableModelListener{

	private JButton backButton;
	
	
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

	private String[] colName= {"ID", "Name","Department", "Courses"};
	
	public ModifyInstructorDetails(){
		/*
		 * for making the jtable exapd to the left and write and shrink but it still disappers
		 */
		setTitle("Modify Instructor Details");
		init();
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		
		//square box around the word
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		
		//to add the dynamic search update while typing
		filter.getDocument().addDocumentListener(this);
		filter.setToolTipText("Search for instructors by name, id, courses");
		
		modifyLabel.setForeground(Color.BLUE.darker());
		modifyLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modifyLabel.addMouseListener(this);
		/*
		 * setting the table cols
		 * creating a vector of users to use it in the jtable
		 * count students assign it to object rows
		 */
		Users users = new Users();
		/*
		 * creating the table and configuring its dimensions
		 */
		model = new DefaultTableModel(users.getInstructorInfo(), colName);
		table = new JTable(model) {
			@Override					// set all cells to uneditable
		    public boolean isCellEditable(int row, int column) {
		    	return column==0||column==1||column==2;
		    	}
            //  Determine editor to be used by row
            public TableCellEditor getCellEditor(int row, int column){
                int modelColumn = convertColumnIndexToModel( column );
               //making the department col dropdown
                String []s={"ECCE","PHYSICS","CHEMISTRY"};
                if (modelColumn == 2){
                    JComboBox<String> comboBox1 = new JComboBox<String>(s);
                    return new DefaultCellEditor( comboBox1 );
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
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
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Instructors details"));
		
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
		 
		 modifyLabel = new JLabel("Modify");
		
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
		constraints.gridy = 2;
		constraints.gridheight = 100;
		panel.add(scroll,constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridheight = 1;
		panel.add(modifyLabel,constraints);
		getContentPane().add(panel);
	}
	/*
	 * button events
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==backButton) {
			new AddDropInstructor();
			dispose();
		}
	}
	/*
	 * mouse actions for hyperlink text
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
//to commit the changes made to the instructor details you need to press on modify in order to change their details
		if(e.getSource()==modifyLabel) {
			mod();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	/*
	 * When the admin updates the user information from the table it also gets updated in the file
	 */
	public void mod(){
		Vector<RegistaredStudents>c=new Vector<RegistaredStudents>();
		Courses course = new Courses();
		c=course.getSenseiCourses();
		Vector<Users> load = new Vector<Users>();
		Users user = new Users();
		load = user.getInstructorVector();
		int length =user.getInstructors();
		int colnum=4;
		Object[][]data=new Object[length][colnum];
		String[]crn=new String[5];
		for(int i =0;i<length;i++){
			for(int j =0;j<colnum;j++){
				data[i][j]=table.getModel().getValueAt(i,j);
				if(Verify.IDVerifier(""+data[i][j])){
					c.elementAt(i).setID(""+data[i][j]);
					load.elementAt(i).setID(""+data[i][j]);
				}
				else if(Verify.NameVerifier((""+data[i][j]).replaceAll("\\s", ""))){
					if(data[i][j].equals("ECCE")) {
						c.elementAt(i).setDepartment("ECCE");
						load.elementAt(i).setDepartment("ECCE");
					}
					else if(data[i][j].equals("PHYSICS")) {
						c.elementAt(i).setDepartment("PHYSICS");
						load.elementAt(i).setDepartment("PHYSICS");
					}
					else if(data[i][j].equals("CHEMISTRY")) {
						c.elementAt(i).setDepartment("CHEMISTRY");
						load.elementAt(i).setDepartment("CHEMISTRY");
					}
					else {
						String []name=(""+data[i][j]).split(" ");
						c.elementAt(i).setFirstName(name[0]);
						c.elementAt(i).setLastName(name[1]);
						load.elementAt(i).setFirstName(name[0]);
						load.elementAt(i).setLastName(name[1]);
					}
				}
				else{
					crn = (""+data[i][j]).replaceAll("\\s", "").split(",");
					int l =crn.length;
					for(int k=0;k<l;k++) {
						if(Verify.crnVerifier(crn[k])) {
							c.elementAt(i).setCourse2(l-k-1,crn[k]);
						}
					}
				}
			}	
		}
		course.saveSenseiCoursesAdd(c);
		user.saveModifiedInstructors(load);
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
	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getColumn()==2) {
			JOptionPane.showMessageDialog(null, "To avoid problems with the system please go change the student courses\n"
					+ "1) Remove all the courses that the instructor is teaching\n"
					+ "2) Add applicable courses considering his new department", "Registration Error", JOptionPane.WARNING_MESSAGE);
		}
		if(e.getColumn()==0) {
			JOptionPane.showMessageDialog(null, "To avoid problems with the system please check assigned ID\n"
					+ "Make sure the ID assigned is unique","Registration Error", JOptionPane.WARNING_MESSAGE);
		}
	}
}