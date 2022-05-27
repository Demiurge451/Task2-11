import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class SimpleLinkedList<T> implements Iterable<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node{
        public T value;
        public Node next;

        public Node(T value, Node next){
            this.value = value;
            this.next = next;
        }

        public Node(T value){ this(value, null);}
    }

    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T>{
            Node curr = head;


            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }

    private void checkEmptyError() throws Exception {
        if (size == 0){
            throw new Exception("Список пуст");
        }
    }

    public void addFirst(T value){
        head = new Node(value, head);
        if (size == 0){
            tail = head;
        }
        size++;
    }

    public void addLast(T value){
        if (size == 0){
            head = tail = new Node(value);
        }else{
            tail.next = new Node(value);
            tail = tail.next;
        }
        size++;
    }

    public T getFirst() throws Exception {
        checkEmptyError();
        return head.value;
    }

    public T getLast() throws Exception {
        checkEmptyError();
        return tail.value;
    }

    private Node getNode(int index){
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr;
    }

    public T get(int index) throws Exception {
        checkEmptyError();
        if (index == 0 || index >= size){
            throw new Exception("Некорректный индекс");
        }

        return getNode(index).value;
    }

    public void removeFirst() throws Exception {
        checkEmptyError();
        head = head.next;
        if (size == 1){
            tail = null;
        }
        size--;
    }

    public void removeLast() throws Exception {
        checkEmptyError();
        if (size == 1){
            head = tail = null;
        }else{
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    public void remove(int index) throws Exception {
        if (index < 0 || index >= size){
            throw new Exception("Некорректный индекс");
        }else{
            Node prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            if (prevNode.next == null){
                tail = prevNode;
            }
        }
        size--;
    }

    public int size(){return size;}

    public boolean empty(){return size == 0;}

    public T func(Comparator<T> c, T zero){
        Node curr = head;
        int count = 0;
        int bestCount = 0;
        Node nodeBestCount = null;
        Node maybeNodeBestCount = null;
        for (int i = 0; i < this.size; i++) {
            if (c.compare((T) curr.value, zero) > 0){
                count = 1;
                maybeNodeBestCount = curr;
            }
            if (count != 0 && c.compare((T) curr.value, zero) < 0){
                count++;
            }

            if (count != 0 && count >= bestCount) {
                nodeBestCount = maybeNodeBestCount;
                bestCount = count;
            }
            curr = curr.next;
        }

        return nodeBestCount != null ? nodeBestCount.value : zero;
    }

    public static String readLinkedListFromFile(String filename){
        try(Scanner scanner = new Scanner(new File(filename), "UTF-8")) {
            String s = new String();
            while(scanner.hasNext()){
                s += scanner.next() + " ";
            }
            return s;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
