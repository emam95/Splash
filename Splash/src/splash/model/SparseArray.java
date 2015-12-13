/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

/**
 *
 * @author Hesham partial implementation
 */
public class SparseArray {

    private SNode head;
    private SNode tail;
    private int length;
    private int tolerance = 10000;

    public void add(int data) {
        if (getHead() == null) {
            setHead(tail = new SNode());
            getHead().setData(data);
        } else if (Math.abs(getTail().getData() - data) <= tolerance) {
            tail.setEndidx(tail.getEndidx() + 1);
        } else {
            getTail().setNext(tail = new SNode());
            getTail().setData(data);
            tail.setStartidx(getLength());
            tail.setEndidx(getLength());
        }
        setLength(getLength() + 1);
    }
    private SNode node;

    public Integer get(int i) {
        if (i < 0 || i >= getLength()) {
            return null;
        }
        if (node == null || node.getStartidx() > i) {
            node = getHead();
        }
        while (node != null) {
            if (i >= node.getStartidx() && i <= node.getEndidx()) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public Object[] getArray() {
        Object[] ar = new Object[getLength()];
        int i = 0;
        SNode ite = getHead();
        while (ite != null) {
            while (i <= ite.getEndidx()) {
                ar[i++] = ite.getData();
            }
        }
        return ar;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the head
     */
    public SNode getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(SNode head) {
        this.head = head;
    }

    /**
     * @return the tail
     */
    public SNode getTail() {
        return tail;
    }

    /**
     * @param tail the tail to set
     */
    public void setTail(SNode tail) {
        this.tail = tail;
    }

    /**
     * @return the tolerance
     */
    public int getTolerance() {
        return tolerance;
    }

    /**
     * @param tolerance the tolerance to set
     */
    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

}
