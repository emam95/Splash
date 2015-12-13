/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splash.model;

import java.lang.reflect.Array;

/**
 *
 * @author Hesham partial implementation
 * @param <T>
 */
public class SparseArray<T> {

    SNode<T> head;
    SNode<T> tail;
    int length = 0;

    public void add(T data) {
        if (head == null) {
            head = tail = new SNode<>();
            head.setData(data);
        } else if (tail.getData() == data) {
            tail.endidx++;
        } else {
            tail.setNext(tail = new SNode());
            tail.setData(data);
            tail.startidx = tail.endidx = length;
        }
        length++;
    }
    private SNode<T> node;

    public T get(int i) {
        if (i < 0 || i >= length) {
            return null;
        }
        if (node == null || node.startidx > i) {
            node = head;
        }
        while (node != null) {
            if (i >= node.startidx && i <= node.endidx) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public Object[] getArray() {
        Object[] ar = new Object[length];
        int i = 0;
        SNode<T> ite = head;
        while (ite != null) {
            while (i <= ite.endidx) {
                ar[i++] = ite.getData();
            }
        }
        return ar;
    }

}

class SNode<T> {

    private SNode next = null;
    private T data;
    int startidx = 0;
    int endidx = 0;

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
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }
}
