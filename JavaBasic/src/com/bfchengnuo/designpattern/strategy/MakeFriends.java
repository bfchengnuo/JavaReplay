package com.bfchengnuo.designpattern.strategy;

/**
 * Created by 冰封承諾Andy on 5/10/2017.
 * 环境类，也就是具体的使用场景
 */
public class MakeFriends {
	private Loli mLoli;

	public MakeFriends(Loli loli) {
		mLoli = loli;
	}

    public void start() {
	    mLoli.hug();
    }
}
