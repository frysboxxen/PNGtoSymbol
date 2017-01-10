import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




public class CircleImage extends JPanel /*implements ActionListener*/{
	private static boolean end = true;
	private static String pictureName, action;
	private static Picture orginal, blank;
	private static Bubble test;

	private static JFrame f;
	private static JPanel p;
	private static JButton startCirclebtn, startSquarebtn, save, stop, submit, quit;
	private static JLabel pictureLable, penSizeLable, circleSizeLable;
	private static JTextField pictureInput, penSizeInput, circleSizeInput;
	private Timer loop;
	private static CircleImage ciStart;
	private static ImageIcon img;

	public static void main(String[] args){
		f = new JFrame("CircleImageController");
		f.setSize(400, 130);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		img = new ImageIcon("fIcon.png");		//icon, not working
		f.setIconImage(img.getImage());			//icon, notworking
		p = new JPanel();
		p.setBackground(Color.GRAY);

		pictureLable = new JLabel("Picture name:");
		penSizeLable = new JLabel("Set pen size:");
		circleSizeLable = new JLabel("Set circle size:");

		pictureInput = new JTextField("", 8);
		penSizeInput = new JTextField("", 5);
		circleSizeInput = new JTextField("", 5);

		submit = new JButton ("Submit");
		quit = new JButton("Quit");

		startSquarebtn = new JButton("StartSquare");
		startCirclebtn = new JButton("StartCircle");
		stop = new JButton("Stop");
		save = new JButton("Save");

		submit.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent e){
				pictureName = pictureInput.getText();
				Bubble.CircleSize = Integer.parseInt(circleSizeInput.getText());
				Bubble.pensize = Double.parseDouble(penSizeInput.getText());
				orginal = new Picture(pictureName+".png");
				blank = new Picture(orginal.width(), orginal.height());
				StdDraw.setCanvasSize(orginal.width(), orginal.height());
				StdDraw.setXscale(0,orginal.width());
				StdDraw.setYscale(orginal.height(),0);
				test = new Bubble();
				StdDraw.clear(StdDraw.BLACK);
				test.Bubble(orginal);
				f.toFront();
				submit.setEnabled(false);
				startSquarebtn.setEnabled(true);
				startCirclebtn.setEnabled(true);
				stop.setEnabled(true);
				save.setEnabled(true);
			}
		});
		startCirclebtn.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent e){
				action = e.getActionCommand();
				startSquarebtn.setEnabled(false);
				startCirclebtn.setEnabled(false);
				ciStart = new CircleImage(action);
				ciStart.start();
			}
		});
		startSquarebtn.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent e){
				action = e.getActionCommand();
				startCirclebtn.setEnabled(false);
				startSquarebtn.setEnabled(false);
				ciStart = new CircleImage(action);
				ciStart.start();
			}
		});
		stop.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent e){
				ciStart.stop();
				startSquarebtn.setEnabled(true);
				startCirclebtn.setEnabled(true);
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent e){
				Bubble.save(pictureName);
			}
		});
		quit.addActionListener(new ActionListener() {
			@Override
			public void	actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});


		startSquarebtn.setEnabled(false);
		startCirclebtn.setEnabled(false);
		stop.setEnabled(false);
		save.setEnabled(false);

		p.add(pictureLable);
		p.add(pictureInput);
		p.add(circleSizeLable);
		p.add(circleSizeInput);
		
		p.add(penSizeLable);
		p.add(penSizeInput);
		p.add(submit);
		p.add(quit);
		
		p.add(startSquarebtn);
		p.add(startCirclebtn);
		p.add(stop);
		p.add(save);

		f.add(p);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);
	}
	
	public static void loopCircle(){
			test.SearchPic();
			test.Compare();
			test.paintCircle();
	}
	public static void loopSquare(){
			test.SearchPic();
			test.Compare();
			test.paintSquare();
	}
	public CircleImage(String actionString){
		loop = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if (actionString.equals("StartCircle")) {
					loopCircle();	
				}
				else if (actionString.equals("StartSquare")) {
					loopSquare();
				}
            }
        });
	}
	public void start(){
		loop.start();
	}
	public void stop(){
		loop.stop();
	}
}