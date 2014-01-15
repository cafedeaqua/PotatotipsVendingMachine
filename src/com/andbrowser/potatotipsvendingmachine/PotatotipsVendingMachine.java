
package com.andbrowser.potatotipsvendingmachine;

import java.util.ArrayList;

public class PotatotipsVendingMachine {

    private static final int MAX = 1000;

    public enum INVENTORY {
        ID_1, ID_2
    }

    private int mTotal = 0;
    private final ArrayList<Potatotips> mInventory1List;
    private final ArrayList<Potatotips> mInventory2List;

    public PotatotipsVendingMachine() {
        mTotal = 0;
        mInventory1List = new ArrayList<Potatotips>();
        mInventory2List = new ArrayList<Potatotips>();
    }

    public int getTotal() {
        return mTotal;
    }

    public void insert(Integer money) {
        if (mTotal >= MAX) {
            return;
        }
        mTotal += money;
    }

    public Potatotips purchase(INVENTORY id) {
        if (isInventoryEmpty(id)) {
            return null;
        }
        Potatotips poteti = getPoteti(id);
        int price = poteti.getPrice();
        if (mTotal < price) {
            return null;
        }
        mTotal -= price;
        removePoteti(id);
        return poteti;
    }

    private void removePoteti(INVENTORY id) {
        switch (id) {
            case ID_1:
                if (!mInventory1List.isEmpty()) {
                    mInventory1List.remove(0);
                }
                return;
            case ID_2:
                if (!mInventory2List.isEmpty()) {
                    mInventory2List.remove(0);
                }
                return;
            default:
                return;
        }
    }

    private Potatotips getPoteti(INVENTORY id) {
        switch (id) {
            case ID_1:
                return mInventory1List.get(0);
            case ID_2:
                return mInventory2List.get(0);
            default:
                return null;
        }

    }

    private boolean isInventoryEmpty(INVENTORY id) {
        switch (id) {
            case ID_1:
                return mInventory1List.isEmpty();
            case ID_2:
                return mInventory2List.isEmpty();
            default:
                return false;
        }
    }

    public int refund() {
        int refund = mTotal;
        mTotal = 0;
        return refund;
    }

    public void add(INVENTORY id, Potatotips poteti) {
        switch (id) {
            case ID_1:
                mInventory1List.add(poteti);
                break;
            case ID_2:
                mInventory2List.add(poteti);
                break;
            default:
                break;
        }
    }

    public int getInventoryCount(INVENTORY id) {
        switch (id) {
            case ID_1:
                return mInventory1List.size();
            case ID_2:
                return mInventory2List.size();
            default:
                break;
        }
        return 0;
    }
}
