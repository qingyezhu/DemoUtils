package com.wangzhu.thread;

/**
 * ����<br/>
 * notify��notifyAll������<br/>
 * ����Object��������֪ͨ���ڵȴ��ö�����̵߳ķ�����<br/>
 * notify������һ�����ڵȴ��ö�����̡߳�<br/>
 * notifyAll�������������ڵȴ��ö�����̡߳�<br>
 * �������<br/>
 * notifyAllʹ����ԭ���ڸö����ϵȴ���notify���߳�ͳͳ�˳���״̬��<br/>
 * ��ɵȴ��ö����ϵ�����һ���ö��󱻽��������Ǿͻ�ȥ������<br/>
 * notify��ֻ��ѡ��һ��wait״̬�߳̽���֪ͨ����ʹ����øö����ϵ�����<br/>
 * ������������ͬ���ڵȴ����ö���notify���̣߳�����һ���߳���������Ժ��ͷŶ����ϵ�����<br/>
 * ��ʱ����ö���û���ٴ�ʹ��notify��䣬����ö����Ѿ����У�����wait״̬�ȴ����߳�����û�еõ��ö����֪ͨ��<br/>
 * ��������wait״̬�� ֱ��������󷢳�һ��notifyAll��notify�����ǵȴ����Ǳ�notifyAll��notify������������
 * 
 * @author wangzhu
 * @date 2015-3-2����2:59:07
 * 
 */
public class DeadLockDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
	final OutTurn ot = new OutTurn();
	for (int j = 0; j < 100; j++) {
	    new Thread(new Runnable() {

		@Override
		public void run() {
		    for (int i = 0; i < 5; i++) {
			ot.sub();
		    }
		}
	    }).start();
	    new Thread(new Runnable() {

		@Override
		public void run() {
		    for (int i = 0; i < 5; i++) {
			ot.main();
		    }
		}
	    }).start();

	}

    }

}

class OutTurn {
    private boolean isSub = true;
    private int count = 0;

    public synchronized void sub() {
	try {
	    while (!isSub) {
		this.wait();
	    }
	    System.out.println("sub=====" + count);
	    isSub = false;
	    // this.notify();
	    this.notifyAll();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	count++;
    }

    public synchronized void main() {
	try {
	    while (isSub) {
		this.wait();
	    }
	    System.out.println("main=====" + count);
	    isSub = true;
	    // this.notify();
	    this.notifyAll();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	count++;
    }
}