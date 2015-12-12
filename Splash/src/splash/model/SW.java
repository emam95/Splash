package splash.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hesham
 */
public class SW {

    static long stime;

    public SW()
    {
        stime = System.currentTimeMillis();
    }
    
    public void restart() {
        stime = System.currentTimeMillis();
    }

    public long getElapsed() {
        return System.currentTimeMillis() - stime;
    }
}
