package com.wangzhu.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo2 {
    private static final int PLAYER_AMOUNT = 5;

    public static void main(String[] args) {
	CountDownLatch begin = new CountDownLatch(1);
	CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
	Player[] players = new Player[PLAYER_AMOUNT];

	for (int i = 0; i < PLAYER_AMOUNT; i++) {
	    players[i] = new Player(i + 1, begin, end);
	}

	ExecutorService service = Executors.newFixedThreadPool(PLAYER_AMOUNT);

	// 分配线程
	for (Player player : players) {
	    service.execute(player);
	}

	System.out.println("Reace begin!");

	// 比赛开始，所有运动员一起开始行动
	begin.countDown();

	try {
	    // 等待比赛结束
	    end.await();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	    System.out.println("Race end!");
	}
	service.shutdown();

    }
}

class Player implements Runnable {

    private int id;
    private CountDownLatch begin;
    private CountDownLatch end;

    public Player(int id, CountDownLatch begin, CountDownLatch end) {
	super();
	this.id = id;
	this.begin = begin;
	this.end = end;
    }

    @Override
    public void run() {
	try {
	    System.out.println("Play " + id + " 准备好了！");
	    begin.await();
	    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
	    System.out.println("Play " + id + " over!");
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	    end.countDown();
	}
    }

}