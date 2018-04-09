import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;


public class mainScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextPane textPane_3 ;
	private Choice choice;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen window = new mainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(63, 26, 230, 20);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane.setText("\u05D8\u05E7\u05E1\u05D8");
		textPane.setBounds(303, 26, 72, 20);
		frame.getContentPane().add(textPane);

		textField_1 = new JTextField();
		textField_1.setBounds(63, 57, 230, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane_1.setText("\u05D2\u05D5\u05D3\u05DC");
		textPane_1.setBounds(303, 57, 72, 20);
		frame.getContentPane().add(textPane_1);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane_2.setText("\u05E4\u05D5\u05E0\u05D8");
		textPane_2.setBounds(304, 88, 72, 20);
		frame.getContentPane().add(textPane_2);

		final Choice choice = new Choice();
		choice.setBounds(63, 88, 230, 20);
		frame.getContentPane().add(choice);
		choice.setFocusable(false);

		JButton button = new JButton("\u05D4\u05E4\u05E2\u05DC");
		button.setBounds(180, 199, 89, 23);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double fontsize =0;
				try{
					if(!textField_1.getText().isEmpty())
						fontsize = Double.parseDouble(textField_1.getText());
				}catch(Exception e){return;}
				double distance = 0;
				try{
					if(!textField_2.getText().isEmpty())
						distance = Double.parseDouble(textField_2.getText());
				}catch(Exception e){return;}
				String text = textField.getText();
				String path = textPane_3.getText();
				String font = choice.getSelectedItem().toString();

				System.out.println(path);
				System.out.println(text);
				System.out.println(font);
				System.out.println(fontsize);
				System.out.println(distance);

				Main.play(path, text, font, (float)fontsize, (float) distance);

				//				System.exit(1);
			}
		});

		frame.getContentPane().add(button);

		textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBounds(63, 145, 312, 20);
		frame.getContentPane().add(textPane_3);
		String[] fonts = Main.fontList();

		choice.add("Arial");
		for(int i=0; i<fonts.length; i++){
			choice.add(fonts[i]);
		}




		//		frame = new JFrame();
		//		frame.setBounds(100, 100, 450, 300);
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		frame.getContentPane().setLayout(null);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "xlsm", "xls");
		//		fileChooser.setFileFilter(filter);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(fileChooser);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "";
		int result = fileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			path = selectedFile.getAbsolutePath();

			System.out.println("Selected file: " + path);
			
		}

		textPane_3.setText(path);
		if(path == null || path.isEmpty()) System.exit(1);

		textField_2 = new JTextField();
		textField_2.setBounds(63, 114, 230, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JTextPane textPane_4 = new JTextPane();
		textPane_4.setFont(new Font("Arial", Font.PLAIN, 14));
		textPane_4.setEditable(false);
		textPane_4.setText("\u05DE\u05E8\u05D7\u05E7 \u05DE\u05D4\u05EA\u05D7\u05EA\u05D9\u05EA");
		textPane_4.setBounds(304, 114, 120, 20);
		frame.getContentPane().add(textPane_4);

		
//		Popup popup = new Popup();
//		popup.add(textField);
//		
//		Popup popup1 = new Popup();
//		popup1.add(textField_1);
//		
//		Popup popup2 = new Popup();
//		popup2.add(textPane_2);
//		
//		Popup popup3 = new Popup();
//		popup3.add(textPane_3);

		//		try {
		//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		//			SwingUtilities.updateComponentTreeUI(frame);
		//
		//		} catch (ClassNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (InstantiationException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IllegalAccessException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (UnsupportedLookAndFeelException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

	}
}
