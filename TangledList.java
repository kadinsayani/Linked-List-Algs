/**
 * Implementation of removeSharedLinkedListNodes, detectCycleAndPeriod, and removeCycle algorithms, and their tests.
 *
 * @author Kadin Sayani, UCID: 30061129
 *
 */

package Assignment2;

import Assignment2.MyLinkedListInterface.MyLinkedListNode;

class TangledList {

    /**
     * 
     * Removes shared nodes from shorter linked list.
     * 
     * Precondition: list0 and list1 are linked lists, there is at least one
     * non-shared node in each list.
     * Postcondition: list0 and list1 do not share any nodes. If they did have
     * shared nodes to
     * begin with, those nodes have been removed from whichever list was shorter to
     * begin
     * with.
     * 
     * @param list0
     * @param list1
     */
    public static void removeSharedLinkedListNodes(MyLinkedList<String> list0, MyLinkedList<String> list1) {
        // compute difference in list length
        int diff = list0.length() - list1.length();
        // swap list0 and list1 if list0 is shorter than list 1
        if (diff < 0) {
            MyLinkedList<String> temp = list0;
            list0 = list1;
            list1 = temp;
            diff = -diff;
        }
        // initalize index nodes to heads
        MyLinkedListNode<String> index0 = list0.getHeadNode();
        MyLinkedListNode<String> index1 = list1.getHeadNode();
        // advance index0 by diff
        for (int i = 0; i < diff; i++) {
            index0 = index0.getNext();
        }
        int steps = 0;
        // advance both index nodes until they are the same
        while (index0 != index1) {
            if (index0 == null || index1 == null) {
                break;
            }
            index0 = index0.getNext();
            index1 = index1.getNext();
            steps++;
        }
        // if a shared node is found then reset index1 to head of list1
        if (steps < list1.length()) {
            index1 = list1.getHeadNode();
        }
        // iterate through list1 steps-1 times
        for (int i = 0; i < steps - 1; i++) {
            index1 = index1.getNext();
        }
        // set index1 to null
        index1.setNext(null);
    }

    /**
     * 
     * Detect cycles in a linked list and return period of cycle.
     * 
     * Precondition: List0 is a linked list.
     * Postcondition: If list0 contains a cycle, the period is returned. If list0
     * does not contain a cycle, 0 is returned.
     * 
     * @param list0
     * @return int
     */
    public static int detectCycleAndPeriod(MyLinkedList<String> list0) {
        // floyds turtoise and hare algorithm
        // detect cycle and return period, return -1 if no period
        MyLinkedListNode<String> tortoise = list0.getHeadNode();
        MyLinkedListNode<String> hare = list0.getHeadNode();
        int period = 0;
        while (hare != null) {
            tortoise = tortoise.getNext();
            hare = hare.getNext();
            if (hare != null) {
                hare = hare.getNext();
            }
            period++;
            if (tortoise == hare) {
                return period;
            }
        }
        return -1;
    }

    /**
     * 
     * Removes cycle from linked list.
     * 
     * Precondition: List0 is a linked list with a cycle, period is the number of
     * nodes in the cycle.
     * Postcondition: List0 does not have a cycle, the order of the nodes in the
     * list is unchanged (except for the removal of the repeated nodes in the
     * order), the number of the nodes in the list is unchanged.
     * 
     * @param list0
     * @param period
     */
    public static void removeCycle(MyLinkedList<String> list0, int period) {
        MyLinkedListNode<String> tortoise = list0.getHeadNode();
        MyLinkedListNode<String> hare = list0.getHeadNode();
        // remove cycle and remove repeated nodes in order
        for (int i = 0; i < period; i++) {
            hare = hare.getNext();
        }
        while (tortoise != hare) {
            tortoise = tortoise.getNext();
            hare = hare.getNext();
        }
        for (int i = 0; i < period - 1; i++) {
            hare = hare.getNext();
        }
        hare.setNext(null);
    }

    /**
     * @param list0
     */
    public static void removeLinkedListCycles(MyLinkedList<String> list0) {
        int period = detectCycleAndPeriod(list0);
        if (period != -1) {
            removeCycle(list0, period);
        }
    }

    // Code to setup one test case for eliminating shared nodes from two linked
    // lists
    public static void createAndTestSharedNode() {
        // Your assignment submission should have more specific error handling
        try {
            MyLinkedList<String> stage0 = new MyLinkedList<String>();
            stage0.appendToTail("Arkells");
            stage0.appendToTail("Bruno Mars");
            stage0.appendToTail("Coldplay");
            stage0.appendToTail("David Bowie");
            stage0.appendToTail("Earth, Wind & Fire");

            MyLinkedList<String> stage1 = new MyLinkedList<String>();
            stage1.appendToTail("Foo Fighters");
            stage0.appendToTail("Gorillaz");

            MyLinkedListNode<String> node;
            node = stage0.searchByValue("Coldplay");
            stage1.appendToTail(node);

            System.out.println("Shared Nodes: Stage 0 Lineup");
            System.out.println(stage0.toString());
            System.out.println("Shared Nodes: Stage 1 Lineup");
            System.out.println(stage1.toString());
            System.out.println("\n");

            removeSharedLinkedListNodes(stage0, stage1);

            System.out.println("Shared Nodes Fixed: Stage 0 Lineup");
            System.out.println(stage0.toString());
            System.out.println("Shared Nodes Fixed: Stage 1 Lineup");
            System.out.println(stage1.toString());
            System.out.println("\n");

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    // Code to setup one test case for eliminating cycles from a linked list
    public static void createAndTestCycle() {
        // Your assignment submission should have more specific error handling
        try {
            MyLinkedList<String> stage0 = new MyLinkedList<String>();
            stage0.appendToTail("Arkells");
            stage0.appendToTail("Bruno Mars");
            stage0.appendToTail("Coldplay");
            stage0.appendToTail("David Bowie");
            stage0.appendToTail("Earth, Wind & Fire");
            stage0.appendToTail("Foo Fighters");
            stage0.appendToTail("Gorillaz");

            MyLinkedListNode<String> loopToNode;
            loopToNode = stage0.searchByValue("Coldplay");

            MyLinkedListNode<String> tail;
            tail = stage0.searchByValue("Gorillaz");
            if (tail != null)
                tail.setNext(loopToNode);

            System.out.println("Cyclic Nodes: Stage 0 Lineup");
            MyLinkedListNode<String> currentNode;
            currentNode = stage0.searchByValue("Arkells");

            // If we use the toString method it will never terminate.
            // Most linked list operations on this list will not work, so be careful!
            for (int i = 0; i < 10 & currentNode != null; i++) {
                System.out.println("Element: " + currentNode.toString());
                currentNode = currentNode.getNext();
            }
            System.out.println("\n");

            removeLinkedListCycles(stage0);

            // print list0 to show that cycles have been removed
            System.out.println("Cyclic Nodes Fixed: Stage 0 Lineup");
            currentNode = stage0.searchByValue("Arkells");
            for (int i = 0; i < stage0.length(); i++) {
                System.out.println("Element: " + currentNode.toString());
                currentNode = currentNode.getNext();
            }
            System.out.println("\n");

        } catch (Exception e) {
        }
    }

    /**
     * 
     * Test.
     * 
     * @param args
     */
    public static void main(String[] args) {
        createAndTestSharedNode();
        createAndTestCycle();
    }
}