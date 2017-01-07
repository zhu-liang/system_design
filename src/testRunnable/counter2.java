package testRunnable;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import testRunnable.Counter1.OnOffL;
import testRunnable.Counter1.StartL;

public class counter2 extends Applet implements Runnable {

	  private int count = 0;
	  private Button 
	    onOff = new Button("Toggle"),
	    start = new Button("Start");
	  TextField t = new TextField(10);
	  private boolean runFlag = true;
	  private Thread ps = null;
	  public void init() {
	    add(t);
	    start.addActionListener(new StartL());
	    add(start);
	    onOff.addActionListener(new OnOffL());
	    add(onOff);
	  }
	  
	  class StartL implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		      if(null == ps) {
		    	  ps = new Thread(counter2.this);
		    	  ps.start();
		      }
		    }
		  }
	  class OnOffL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      if(null != ps)
	      {
	    	  runFlag = !runFlag;
	      }
	    }
	  }
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		counter2 applet = new counter2();
		Frame aFrame = new Frame("Zhu Liang Test");
	    aFrame.addWindowListener(
	    	      new WindowAdapter() {
	    	        public void windowClosing(WindowEvent e) {
	    	          System.exit(0);
	    	        }
	    	      });
	    aFrame.add(applet, BorderLayout.CENTER);
	    aFrame.setSize(300,200);
	    applet.init();
	    applet.start();
	    aFrame.setVisible(true);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try{
				ps.sleep(1000);
			} catch (InterruptedException e) {}
			if(runFlag) {
				this.t.setText(Integer.toString(count));
				System.out.println(count++);
			}
		}
	}

}
