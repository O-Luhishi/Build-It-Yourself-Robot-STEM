import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.io.InputStream;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCmdLine = new JLabel("Command Line Inputs");
		lblCmdLine.setBounds(62, 39, 161, 16);
		lblCmdLine.setForeground(Color.WHITE);
		lblCmdLine.setFont(new Font("Silom", Font.BOLD, 14));
		frame.getContentPane().add(lblCmdLine);
		
		JLabel lblCmdBox = new JLabel("<html>Python 2.7.10 (default, Jul 15 2017, 17:16:57)<BR>[GCC 4.2.1 Compatible Apple LLVM 9.0.0 (clang-900.0.31)] on darwin<BR>Type \"help\", \"copyright\", \"credits\" or \"license\" for more information.<BR>>>></html>");
		lblCmdBox.setForeground(Color.GREEN);
		lblCmdBox.setFont(new Font("Courier", Font.PLAIN, 10));
		lblCmdBox.setVerticalAlignment(SwingConstants.TOP);
        lblCmdBox.setOpaque(true);
		lblCmdBox.setBackground(Color.BLACK);
		lblCmdBox.setBounds(6, 67, 298, 152);
		frame.getContentPane().add(lblCmdBox);
		
		JLabel lblRobotImg = new JLabel("New label");
		lblRobotImg.setIcon(new ImageIcon("src/SCERobot.png"));
		lblRobotImg.setBounds(316, 39, 249, 175);
		frame.getContentPane().add(lblRobotImg);
		
		
		JLabel lblMakeItYourself = new JLabel("Make It Yourself Robot !");
		lblMakeItYourself.setForeground(Color.WHITE);
		lblMakeItYourself.setFont(new Font("Silom", Font.BOLD, 20));
		lblMakeItYourself.setBounds(174, 0, 274, 27);
		frame.getContentPane().add(lblMakeItYourself);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 226, 600, 152);
		frame.getContentPane().add(tabbedPane);
		
		JPanel tab_RT = new JPanel();
		tab_RT.setForeground(Color.BLUE);
		tab_RT.setBackground(Color.WHITE);
		tabbedPane.addTab("Real Time", null, tab_RT, null);
		tab_RT.setLayout(null);
		
		JButton btnUp = new JButton();
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCmdBox.setText("<html>Python 2.7.10 (default, Jul 15 2017, 17:16:57)<BR>[GCC 4.2.1 Compatible Apple LLVM 9.0.0 (clang-900.0.31)] on darwin<BR>Type \"help\", \"copyright\", \"credits\" or \"license\" for more information.<BR>>>> Stepper(A). OutputGPIO(HIGH)</html>");
				sshCommand("python Forward.py");
				
			}
		});
		btnUp.setIcon(new ImageIcon("src/btnUp.png"));
		btnUp.setBackground(Color.WHITE);
		btnUp.setForeground(Color.BLUE);
		btnUp.setBounds(274, 6, 40, 35);
		tab_RT.add(btnUp);
		
		
		JButton btnLeft = new JButton();
		btnLeft.setIcon(new ImageIcon("src/btnLeft.png"));
		btnLeft.setForeground(Color.BLUE);
		btnLeft.setBounds(222, 35, 40, 35);
		tab_RT.add(btnLeft);
		
		JButton btnRight = new JButton();
		btnRight.setIcon(new ImageIcon("src/btnRight.png"));
		btnRight.setForeground(Color.BLUE);
		btnRight.setBounds(326, 35, 40, 35);
		tab_RT.add(btnRight);
		
		JButton btnDown = new JButton();
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCmdBox.setText("<html>Python 2.7.10 (default, Jul 15 2017, 17:16:57)<BR>[GCC 4.2.1 Compatible Apple LLVM 9.0.0 (clang-900.0.31)] on darwin<BR>Type \"help\", \"copyright\", \"credits\" or \"license\" for more information.<BR>>>> Stepper(B). OutputGPIO(HIGH)</html>");
				sshCommand("python Backward.py");
			}
		});
		btnDown.setIcon(new ImageIcon("src/btnDown.png"));
		btnDown.setForeground(Color.BLUE);
		btnDown.setBounds(274, 62, 40, 35);
		tab_RT.add(btnDown);
		
		JButton btnLed = new JButton("Lights");
		btnLed.setForeground(Color.BLUE);
		btnLed.setBackground(Color.WHITE);
		btnLed.setBounds(48, 68, 90, 29);
		tab_RT.add(btnLed);
		
		JPanel tab_SQ = new JPanel();
		tab_SQ.setForeground(Color.BLUE);
		tabbedPane.addTab("Sequence", null, tab_SQ, null);
		tab_SQ.setLayout(null);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setIcon(new ImageIcon("src/BackgroundSCE.jpg"));
		lblBackground.setBounds(0, 0, 600, 378);
		frame.getContentPane().add(lblBackground);
	}
	
	
	private void sshCommand(String input) {
		String host = "ENTER IP ADDRESS HERE";
		String user = "USERNAME";
		String password = "PASSWORD";
		String command = input;
		try {
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			// Create a JSch session to connect to the server
			Session session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			// Establish the connection
			session.connect();
			System.out.println("Connected...");

			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			channel.setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			boolean bool = false;
			while (bool = false) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0) {
						break;
					}
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					System.out.println("Exit Status: "
							+ channel.getExitStatus());
					break;
				}
				Thread.sleep(1000);
			}
			Thread.sleep(10000);
			channel.disconnect();
			session.disconnect();
			System.out.println("DONE!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
