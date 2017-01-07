package testRunnable;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

class addingThread extends Thread {
	  private int count;
	  private Counter1 counter;
	  private boolean runningFlag;
	  public void toggleFlag()
	  {
		  runningFlag = ! runningFlag; 
	  }
	  addingThread(Counter1 param)
	  {
		  counter = param;
		  runningFlag = true;
		  start();
	  }
	  
	  public void run() {
		    while (true) {
		      try {
		        Thread.currentThread().sleep(1000);
		      } catch (InterruptedException e){}
		      if(runningFlag) 
		      System.out.println(count++);
		      counter.t.setText(Integer.toString(count));
		    }
		  }
}

public class Counter1 extends Applet {
  private int count = 0;
  private Button 
    onOff = new Button("Toggle"),
    start = new Button("Start");
  TextField t = new TextField(10);
  private boolean runFlag = true;
  private addingThread ps = null;
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
    	  ps = new addingThread(Counter1.this);
      }
    }
  }
  class OnOffL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(null != ps)
      {
    	  ps.toggleFlag();
      }
    }
  }
  public static void main(String[] args) {
    Counter1 applet = new Counter1();
    Frame aFrame = new Frame("Counter1");
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
}