import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;

import java.io.InputStream;
import java.util.Properties;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Connection{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection window = new Connection();
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
	public Connection() {
		initialize();
		
		//sshConnect();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblBuildItYourslef = new JLabel("Build It Yourself Robot!");
		lblBuildItYourslef.setFont(new Font("Silom", Font.PLAIN, 15));
		lblBuildItYourslef.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblBuildItYourslef, BorderLayout.NORTH);
		
		JButton btnPrintHello = new JButton("print Hello");
		btnPrintHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sshCommand("python Documents/pyTest.py");
			}
		});
		frame.getContentPane().add(btnPrintHello, BorderLayout.SOUTH);
	}
	
	private void sshCommand(String input) {
		String host = "172.16.204.32";
		String user = "parthsharma";
		String password = "Akshita101";
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
			while (true) {
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
			channel.disconnect();
			session.disconnect();
			System.out.println("DONE!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	


