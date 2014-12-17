package opencv;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.bytedeco.javacv.FrameRecorder.Exception;

public class MainFrame extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton openCam = new JButton("Open Camera");
	private JButton picture = new JButton("Take Picture");
	private JButton video = new JButton("Start Record");
	private JButton videostop = new JButton("Stop Record");
	private JButton settings = new JButton("Settings");
	public static JFrame frame = new JFrame();
	public MainFrame(){
		
		frame.setSize(320,240);
		frame.setTitle("openCAM");
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(5,1));
		frame.add(openCam);
		frame.add(picture);
		frame.add(video);
		frame.add(videostop);
		frame.add(settings);
		openCam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				OpenCamera open = new OpenCamera();
				//frame.dispose();
			}
		});
		video.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					@SuppressWarnings("unused")
					Record rec = new Record();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videostop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Record.rec1 = false;
			}
		});
		picture.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				Picture pic = new Picture();
			}
		});
		frame.setVisible(true);
	}
}
