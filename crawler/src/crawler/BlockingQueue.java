package crawler;

import file.SaveResultsIntoJson;
import info.ArticleFactory;

import java.util.LinkedList;

class BlockingQueue {

	private final LinkedList list = new LinkedList();
	private boolean closed = false;
	private boolean wait = false;

	synchronized public void enqueue(Object o) {
		if (closed) {
			throw new ClosedException();
		}
		list.add(o);
		notify();
	}

	synchronized public Object dequeue() {
		while (!closed && list.size() == 0) {
			try {
				if(!ArticleFactory.needForNewArticle()){
					new SaveResultsIntoJson().saveArticles();
					System.exit(0);
				}
				wait();
			} catch (InterruptedException e) {
				// ignore
			}
		}
		if (list.size() == 0) {
			return null;
		}
		try {
			if(!ArticleFactory.needForNewArticle())
				System.err.println(list.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.removeFirst();
	}

	synchronized public int size() {
		return list.size();
	}

	synchronized public void close() {
		closed = true;
		notifyAll();
	}

	synchronized public void open() {
		closed = false;
	}

	public static class ClosedException extends RuntimeException {
		ClosedException() {
			super("Queue closed.");
		}
	}
}