/*
 * Dashboard gui not completed at all
 */
import java.util.HashMap;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Welcome extends JFrame implements ActionListener {
	HashMap<String,String> logininfo = new HashMap<String,String>();
	JComboBox comboBox;
	
	public Welcome(HashMap<String,String> loginInfo, String usrid){
		logininfo = loginInfo;
		setTitle("Banner Self Service");
		try {
			setIconImage(ImageIO.read(new File("C:/Users/Mubarak/Desktop/ku.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		String subjects[] = {"calc1","calc2","phy1","phy2","c++","oop"};
		comboBox = new JComboBox(subjects);
		comboBox.addActionListener(this);
//		comboBox.setEditable(true);
//		comboBox.getItemCount();
//		comboBox.addItem("calc3");
//		comboBox.insertItemAt("calc3", 0);
//		comboBox.setSelectedIndex(0);
//		comboBox.removeItem("calc1");
		setSize(520,400);
		setLocationRelativeTo(null);
		add(comboBox);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==comboBox) {
			System.out.println(comboBox.getSelectedItem());
//			System.out.println(comboBox.getSelectedIndex());
		}
	}
}
