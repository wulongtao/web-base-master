package com.xxh.web.core;

/**
 * 循环队列抽象基类，主要定义方法
 * Created by wulongtao on 2017/6/1.
 */
public abstract class AbstractCircularQueue<E> {

    /**
     * 添加元素
     * @param e
     * @return
     */
    public abstract boolean add(E e);

    /**
     * 下一个元素
     * @return
     */
    public abstract E next();

    /**
     * 前一个元素
     * @return
     */
    public abstract E prev();

    /**
     * 获取并删除队列指针指向的当前元素
     * @param e
     * @return
     */
    public abstract E remove(E e);

    /**
     * 获取队列指针指向的当前元素
     * @return
     */
    public abstract E peek();
}
