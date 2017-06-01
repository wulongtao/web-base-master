package com.xxh.web.core;

/**
 * 循环队列实现类
 * Created by wulongtao on 2017/6/1.
 */
public class CircularQueue<E> extends AbstractCircularQueue {

    private int size;




    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public Object prev() {
        return null;
    }

    @Override
    public Object remove(Object o) {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
