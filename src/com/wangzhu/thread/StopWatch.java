package com.wangzhu.thread;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StopWatch extends JFrame {
    private final JLabel label = new JLabel("00:00:000");
    private JButton button1 = new JButton("Start");
    private JButton button2 = new JButton("Suspend");
    private JButton button3 = new JButton("Clear");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();

    private CommandThread command = null;
    private final ClockDisplay clockDisplay = new ClockDisplay();

    public StopWatch() throws HeadlessException {
	super("Ãë±í");
    }

    public void init() {
	this.setLayout(new BorderLayout());
	this.setSize(250, 120);
	panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
	panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

	panel1.add(label);
	command = new CommandThread(clockDisplay, label);
	panel2.add(button1);
	panel2.add(button2);
	panel2.add(button3);

	button1.addActionListener(command);
	button2.addActionListener(command);
	button3.addActionListener(command);

	this.add(BorderLayout.NORTH, panel1);
	this.add(BorderLayout.CENTER, panel2);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	new StopWatch().init();
    }
}

class CommandThread implements Runnable, ActionListener {

    private boolean flag = true;
    private ClockDisplay clockDisplay = null;
    private JLabel label = null;
    private boolean hasStart = false;
    private boolean start = false;

    private Thread t = null;

    public CommandThread(ClockDisplay clockDisplay, JLabel label) {
	this.clockDisplay = clockDisplay;
	this.label = label;
	t = new Thread(this);
    }

    @Override
    public void run() {
	while (flag) {
	    try {
		this.start();
		Thread.sleep(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    private void start() {
	synchronized (clockDisplay) {
	    if (start) {
		label.setText(clockDisplay.refresh());
	    } else {
		try {
		    clockDisplay.wait();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public void close() {
	flag = false;
    }

    private void command(String type) {
	synchronized (clockDisplay) {
	    if (type.equalsIgnoreCase("start")) {
		start = true;
		if (!hasStart) {
		    t = new Thread(this);
		    hasStart = true;
		    t.start();
		}
		clockDisplay.notify();
	    } else if (type.equalsIgnoreCase("clear")) {
		start = false;
		clockDisplay.clear();
		label.setText(clockDisplay.toString());
	    } else {
		start = false;
	    }
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	this.command(e.getActionCommand());
    }

}

class ClockDisplay {
    private int minute = 0;
    private int second = 0;
    private int millsecond = 0;

    public void clear() {
	minute = 0;
	second = 0;
	millsecond = 0;
    }

    public String refresh() {
	if (millsecond >= 999) {
	    second++;
	    millsecond = 0;
	} else {
	    millsecond++;
	}

	if (second >= 59) {
	    minute++;
	    second = 0;
	}
	return this.toString();
    }

    @Override
    public String toString() {
	return String.format("%02d", minute) + ":"
		+ String.format("%02d", second) + ":"
		+ String.format("%03d", millsecond);
    }
}
