package com.androidsrc.client;

/**
 * Created by Tahir on 4/28/2017.
 */

import java.io.Serializable;

public class GameState implements Serializable {
    String dstAddress = "35.185.103.105";
    int dstPort = 5554;
    boolean connected = false;
    int playerWins = 0;
    int playerLosses = 0;
    int playerDrosses = 0;
    String status = "Not connected";
}
