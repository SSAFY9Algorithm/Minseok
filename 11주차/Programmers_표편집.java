import java.util.ArrayDeque;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        LinkedList linkedList = new LinkedList();
        linkedList.init(n);
        Node pointer = linkedList.head.next;
        pointer = pointer.goNext(k);
        boolean[] check = new boolean[n];
        ArrayDeque<Node> stack = new ArrayDeque<>();
        for (String c : cmd) {
            String[] split = c.split(" ");
            if (split[0].equals("D")) {
                pointer = pointer.goNext(Integer.parseInt(split[1]));
            }
            else if (split[0].equals("U")) {
                pointer = pointer.goPrev(Integer.parseInt(split[1]));
            }
            else if (split[0].equals("C")) {
                pointer.next.prev = pointer.prev;
                pointer.prev.next = pointer.next;
                Node node = pointer.next;
                if (node.idx == -1) {
                    node = pointer.prev;
                }
                check[pointer.idx] = true;
                stack.addLast(pointer);
                pointer = node;
            }

            else if (split[0].equals("Z")) {
                Node node = stack.pollLast();
                node.prev.next = node;
                node.next.prev = node;
                check[node.idx] = false;
            }
        }
        for (int i=0; i<n; i++)
            answer.append(check[i] ? "X" : "O");
        System.out.println(pointer.idx);
        return answer.toString();

    }
    public static class Node {
        Node next = null;
        Node prev = null;
        int idx;

        public Node(int idx) {
            this.idx = idx;
        }

        public Node goNext(int x) {
            Node node = this;
            for (int i=0; i<x; i++)
                node = node.next;
            return node;
        }
        public Node goPrev(int x) {
            Node node = this;
            for (int i=0; i<x; i++)
                node = node.prev;
            return node;
        }
    }

    public static class LinkedList {
        Node head;
        Node tail;

        public LinkedList() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }

        public void init(int n) {
            for (int i=0; i<n; i++) {
                Node newNode = new Node(i);
                newNode.prev = tail.prev;
                newNode.next = tail;
                tail.prev.next = newNode;
                tail.prev = newNode;
            }

        }

    }
}


