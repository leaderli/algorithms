package test;

import fn.ThreadUtil;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
	public static void main(String[] args) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				ThreadUtil.print();
				System.out.println("it's ok");
				ThreadUtil.sleep(600);
			}
		}, 2000);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				ThreadUtil.print();
				System.out.println("it's ok");
				timer.cancel();
			}
		}, 2500);
		ThreadUtil.print();
		System.out.println("start");
		Timer timer2 = new Timer();
		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				ThreadUtil.print();
				System.out.println("it's ok");
				ThreadUtil.sleep(600);
			}
		}, 2000);
		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				ThreadUtil.print();
				System.out.println("it's ok");
				timer2.cancel();
			}
		}, 2500);
		ThreadUtil.print();
		System.out.println("start");
	}
}
