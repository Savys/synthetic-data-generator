package com.salesforce.industries.sample;

import com.salesforce.industries.service.BulkAccountService;

public class SampleThread implements Runnable{

	private Thread t;
	private String threadName;

	SampleThread( String name) {
		threadName = name;
		System.out.println("Creating " +  threadName );
	}

	public void run() {
		System.out.println("Running " +  threadName );
		try {
			BulkAccountService bulkAccount = new BulkAccountService();
			bulkAccount.generateAndPush(2);

		}catch (InterruptedException e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thread " +  threadName + " Exception.");
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
		System.out.println("Starting " +  threadName );
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}

	public static void main(String args[]) {
		int noOfThreads = 2;
		for(int i=0;i<noOfThreads;i++) {
			SampleThread R1 = new SampleThread( "Thread-"+i);
			R1.start();
		}
	}

}