package xyz.zzzxb;

import org.junit.Test;
import xyz.zzzxb.client.CmppClient;

/**
 * zzzxb
 * 2023/10/16
 */
public class CmppClientTests {

    @Test
    public void connection() {
        CmppClient cmppClient = new CmppClient();
        cmppClient.bootstrap("localhost", 8080);
    }
}
