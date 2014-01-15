
package com.andbrowser.potatotipsvendingmachine;

public class Potatotips {

    private String mName;
    private int mPrice;

    private Potatotips() {
    }

    public Potatotips(String name, int price) {
        mName = name;
        mPrice = price;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getName() {
        return mName;

    }
}
