
package com.andbrowser.potatotipsvendingmachine.test;

import android.test.AndroidTestCase;

import com.andbrowser.potatotipsvendingmachine.Potatotips;

public class PotatotipsTest extends AndroidTestCase {

    public void test_商品名がうすしおであること() throws Exception {
        Potatotips solt = new Potatotips("うすしお", 100);
        assertEquals(100, solt.getPrice());
        assertEquals("うすしお", solt.getName());
    }

    public void test_商品名がコンソメであること() throws Exception {
        Potatotips consomme = new Potatotips("コンソメ", 200);
        assertEquals(200, consomme.getPrice());
        assertEquals("コンソメ", consomme.getName());
    }

    public void test_商品名がのりしおであること() throws Exception {
        Potatotips consomme = new Potatotips("のりしお", 300);
        assertEquals(300, consomme.getPrice());
        assertEquals("のりしお", consomme.getName());
    }

    public void test_商品名がわさびであること() throws Exception {
        Potatotips consomme = new Potatotips("わさび", 400);
        assertEquals(400, consomme.getPrice());
        assertEquals("わさび", consomme.getName());
    }

}
