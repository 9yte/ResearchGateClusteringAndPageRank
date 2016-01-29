package main;

import progressBar.ProgressBar;
import crawler.Constant;
import crawler.Scheduler;

public class Main {
	public static void main(String[] args) {
		ProgressBar.initialize(Constant.maxNodes);
		try {
			new Scheduler().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
