/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

/**
 *
 * @author Hesham
 */
public class SNode {

    private int data;
    private int startidx = 0;
    private int endidx = 0;
    private SNode next = null;

    /**
     * @return the next
     */
    public SNode getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(SNode next) {
        this.next = next;
    }

    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * @return the startidx
     */
    public int getStartidx() {
        return startidx;
    }

    /**
     * @param startidx the startidx to set
     */
    public void setStartidx(int startidx) {
        this.startidx = startidx;
    }

    /**
     * @return the endidx
     */
    public int getEndidx() {
        return endidx;
    }

    /**
     * @param endidx the endidx to set
     */
    public void setEndidx(int endidx) {
        this.endidx = endidx;
    }
}
