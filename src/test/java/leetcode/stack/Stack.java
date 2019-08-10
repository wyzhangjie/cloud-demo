package leetcode.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @Description:    栈相关 支持动态扩容的栈数据结构
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/11$ 9:44$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/11$ 9:44$
 * @Version:        1.0
 */
public class Stack<E> {

    public Stack() {
        elementData = new Object[]{};
    }

    protected Object[] elementData;
    /**
     * The maximum size of array to allocate. Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in OutOfMemoryError: Requested array size
     * exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    protected int elementCount;

    protected int capacityIncrement;

    public E push(E item) {
        addElement(item);
        elementCount = elementData.length;
        return item;
    }

    public synchronized void addElement(E item) {
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = item;

    }

    private void ensureCapacityHelper(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this
     * function.
     *
     * @return The object at the top of this stack (the last item of the <tt>Vector</tt> object).
     * @throws EmptyStackException if this stack is empty.
     */
    public synchronized E pop() {
        E obj;
        int len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

    private void removeElementAt(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        } else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null; /* to let gc do its work */
    }

    public int size() {
        return elementCount;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     *
     * @return the object at the top of this stack (the last item of the <tt>Vector</tt> object).
     * @throws EmptyStackException if this stack is empty.
     */
    public synchronized E peek() {
        int len = size();

        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

    private E elementAt(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }
        return (E) elementData[index];
    }

    /**
     * Tests if this stack is empty.
     *
     * @return <code>true</code> if and only if this stack contains
     * no items; <code>false</code> otherwise.
     */
    public boolean empty() {
        return size() == 0;
    }

    /**
     * Returns the 1-based position where an object is on this stack. If the object <tt>o</tt>
     * occurs as an item in this stack, this method returns the distance from the top of the stack
     * of the occurrence nearest the top of the stack; the topmost item on the stack is considered
     * to be at distance <tt>1</tt>. The <tt>equals</tt> method is used to compare <tt>o</tt> to the
     * items in this stack.
     *
     * @param o the desired object.
     * @return the 1-based position from the top of the stack where the object is located; the
     * return value <code>-1</code> indicates that the object is not on the stack.
     */
    public synchronized int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }

    private int lastIndexOf(Object o) {
        return lastIndexOf(o, elementCount - 1);
    }

    public synchronized int lastIndexOf(Object o, int index) {
        if (index >= elementCount)
            throw new IndexOutOfBoundsException(index + " >= " + elementCount);

        if (o == null) {
            for (int i = index; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = index; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
}
