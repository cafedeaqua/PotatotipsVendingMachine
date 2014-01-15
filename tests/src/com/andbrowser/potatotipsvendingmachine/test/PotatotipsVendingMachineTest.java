
package com.andbrowser.potatotipsvendingmachine.test;

import android.test.AndroidTestCase;

import com.andbrowser.potatotipsvendingmachine.Potatotips;
import com.andbrowser.potatotipsvendingmachine.PotatotipsVendingMachine;
import com.andbrowser.potatotipsvendingmachine.PotatotipsVendingMachine.INVENTORY;

public class PotatotipsVendingMachineTest extends AndroidTestCase {

    private PotatotipsVendingMachine mVm;

    @Override
    protected void setUp() throws Exception {
        mVm = new PotatotipsVendingMachine();
    }

    public void test_初期値が０円になること() throws Exception {
        mVm = new PotatotipsVendingMachine();
        assertEquals(0, mVm.getTotal());
    }

    public void test_１００円が入金できること() throws Exception {
        mVm.insert(100);
        assertEquals(100, mVm.getTotal());
    }

    public void test_１００円が2枚入金できること() throws Exception {
        mVm.insert(100);
        mVm.insert(100);
        assertEquals(200, mVm.getTotal());
    }

    public void test_１００円が１０枚入金できること() throws Exception {
        for (int i = 0; i < 10; i++) {
            mVm.insert(100);
        }
        assertEquals(1000, mVm.getTotal());
    }

    // ポテチ自販機の入金の上限は１０００円という仕様
    public void test_１０００円を超える入金できないこと() throws Exception {
        for (int i = 0; i < 10; i++) {
            mVm.insert(100);
        }
        assertEquals(1000, mVm.getTotal());
        mVm.insert(100);
        assertEquals(1000, mVm.getTotal());
    }

    public void test_初期状態に０円が払い戻されること() throws Exception {
        assertEquals(0, mVm.refund());
    }

    public void test_１００円が入金されて払戻によって１００円が払い戻されること() throws Exception {
        mVm.insert(100);
        assertEquals(100, mVm.refund());
        assertEquals(0, mVm.getTotal());
        assertEquals(0, mVm.refund());
    }

    public void test_１００円が１０枚入金できて払戻によって１０００円払い戻されること() throws Exception {
        for (int i = 0; i < 10; i++) {
            mVm.insert(100);
        }
        assertEquals(1000, mVm.refund());
        assertEquals(0, mVm.getTotal());
        assertEquals(0, mVm.refund());
    }

    public void test_初期状態でID_1から購入できないこと() throws Exception {
        Potatotips poteti = mVm.purchase(PotatotipsVendingMachine.INVENTORY.ID_1);
        assertNull(poteti);
    }

    public void test_初期状態でID_2から購入できないこと() throws Exception {
        Potatotips poteti = mVm.purchase(PotatotipsVendingMachine.INVENTORY.ID_2);
        assertNull(poteti);
    }

    public void test_初期状態でID_1に入庫して購入できないこと() throws Exception {
        Potatotips solt = new Potatotips("うすしお", 100);
        mVm.add(INVENTORY.ID_1, solt);
        Potatotips purchase_solt = mVm.purchase(INVENTORY.ID_1);
        assertNull(purchase_solt);
    }

    public void test_初期状態でID_1に入庫して１００円いれて購入できること() throws Exception {
        Potatotips solt = new Potatotips("うすしお", 100);
        mVm.add(INVENTORY.ID_1, solt);
        mVm.insert(100);
        Potatotips purchase_solt = mVm.purchase(INVENTORY.ID_1);
        assertNotNull(purchase_solt);
        assertEquals("うすしお", purchase_solt.getName());
        assertEquals(0, mVm.getTotal());
    }

    public void test_初期状態でID_1に入庫して２００円いれて購入して１００円払い戻せること() throws Exception {
        Potatotips solt = new Potatotips("うすしお", 100);
        mVm.add(INVENTORY.ID_1, solt);
        mVm.insert(100);
        mVm.insert(100);
        Potatotips purchase_solt = mVm.purchase(INVENTORY.ID_1);
        assertNotNull(purchase_solt);
        assertEquals("うすしお", purchase_solt.getName());
        assertEquals(100, mVm.getTotal());
        assertEquals(100, mVm.refund());
        assertEquals(0, mVm.getTotal());
    }

    public void test_初期状態でID_2に入庫して１００円いれてコンソメが購入できないこと() throws Exception {
        Potatotips consomme = new Potatotips("コンソメ", 200);
        mVm.add(INVENTORY.ID_2, consomme);
        mVm.insert(100);
        Potatotips purchased = mVm.purchase(INVENTORY.ID_2);
        assertNull(purchased);
    }

    public void test_初期状態でID_2に入庫して２００円いれてコンソメが購入できる() throws Exception {
        Potatotips consomme = new Potatotips("コンソメ", 200);
        mVm.add(INVENTORY.ID_2, consomme);
        mVm.insert(100);
        mVm.insert(100);
        Potatotips purchased = mVm.purchase(INVENTORY.ID_2);
        assertNotNull(purchased);
        assertEquals("コンソメ", purchased.getName());
        assertEquals(0, mVm.getTotal());
    }

}
