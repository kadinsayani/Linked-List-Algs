/**
 * Implementation of linked list data structure.
 *
 * @author Kadin Sayani, UCID: 30061129
 *
 */

package Assignment2;

public class MyLinkedList<ElementType> implements MyLinkedListInterface<ElementType> {

    private MyLinkedListNode<ElementType> head;

    public MyLinkedList() {
        head = null;
    }

    /**
     * Returns the head node of the linked list.
     * 
     * Precondition: this is a linked list.
     * Postcondition: The head node is returned, if the list is empty null is
     * returned instead.
     * 
     * @return MyLinkedListNode<ElementType>
     */
    public MyLinkedListNode<ElementType> getHeadNode() {
        // get head node of linked list
        if (head == null) {
            return null;
        } else {
            return head;
        }
    }

    /**
     * Append to tail by value.
     * 
     * Precondition: This is a linked list, and value is a variable with appropriate
     * type for this list.
     * Postcondition: The tail node of this list is a new node with node.value =
     * value, the length of the list is the previous length + 1, no other nodes have
     * been changed. If a node with the given value already exists, an exception is
     * generated.
     * 
     * @param value
     */
    public void appendToTail(ElementType value) throws LinkedListValueExistsException {
        // append to tail of linked list given value, if value already exists throw
        // exception
        if (contains(value)) {
            throw new LinkedListValueExistsException("Value already exists in list.");
        }
        // create new node with value
        MyLinkedListNode<ElementType> newNode = new MyLinkedListNode<ElementType>(value);
        // if list is empty set head to new node
        if (head == null) {
            head = newNode;
        } else {
            // set new node to tail of linked list
            MyLinkedListNode<ElementType> tail = getTailNode();
            tail.setNext(newNode);
        }
    }

    /**
     * Return tail node of linked list.
     * 
     */
    public MyLinkedListNode<ElementType> getTailNode() {
        // return tail node of linked list
        if (head == null) {
            return null;
        } else {
            MyLinkedListNode<ElementType> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            return current;
        }
    }

    /**
     * Check if value exists in linked list.
     * 
     * @param value
     */
    public boolean contains(ElementType value) {
        // check if linked list contains value
        MyLinkedListNode<ElementType> current = head;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Append to tail by node.
     * 
     * Precondition: This is a linked list, and value is a variable with appropriate
     * type for this list.
     * 
     * Postcondition: The tail node of this list is node, node.value and node.next
     * have not been changed, the length of the list is the previous length + 1, no
     * other nodes have been changed. If a node with the given value already exists,
     * an exception is generated.
     * 
     * @param node
     */
    public void appendToTail(MyLinkedListNode<ElementType> node) throws LinkedListValueExistsException {
        // append to tail of linked list by node, if value already exists throw
        // exception
        if (contains(node.getValue())) {
            throw new LinkedListValueExistsException("Value already exists in list.");
        }
        // if list is empty set head to node
        if (head == null) {
            head = node;
        } else {
            // set node to tail of linked list
            MyLinkedListNode<ElementType> tail = getTailNode();
            tail.setNext(node);
        }
    }

    /**
     * Return length of linked list.
     * 
     * Precondition: this is a linked list.
     * Postcondition: The number of nodes in this list is returned, 0 is returned if
     * the list is empty.
     * 
     * @return int
     */
    public int length() {
        // get length of linked list
        int length = 0;
        MyLinkedListNode<ElementType> current = head;
        while (current != null) {
            length++;
            current = current.getNext();
        }
        return length;
    }

    /**
     * Delete node by value.
     * 
     * Precondition: this is a linked list and a value is a variable of appropriate
     * type for the list.
     * Postcondition: If a node with node.value = value is present in the list it is
     * removed, the order of elements in the list is otherwise unchanged. If no node
     * with node.value = value is present in the list, the list is unchanged. If the
     * list is empty an exception is generated.
     * 
     * @param value
     */
    public void deleteByValue(ElementType value) throws LinkedListListEmptyException {
        // delete linked list node by value
        MyLinkedListNode<ElementType> current = head;
        MyLinkedListNode<ElementType> previous = null;
        while (current != null) {
            if (current.getValue() == value) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
            }
            previous = current;
            current = current.getNext();
        }
    }

    /**
     * Search linked list by value.
     * 
     * Precondition: this is a linked list and a value is a variable of appropriate
     * type for the list.
     * Postcondition: If a node with node.value = value is present in the list, it
     * is returned. If no node with node.value = value is present in the list, null
     * is returned. If the list is empty an exception is generated.
     * 
     * @param value
     * @return MyLinkedListNode<ElementType>
     */
    public MyLinkedListNode<ElementType> searchByValue(ElementType value) throws LinkedListListEmptyException {
        // search linked list by value, return node.value if found, null if not found,
        // exception if list is empty
        if (head == null) {
            throw new IllegalArgumentException("List is empty");
        } else {
            MyLinkedListNode<ElementType> current = head;
            while (current != null) {
                if (current.getValue() == value) {
                    return current;
                }
                current = current.getNext();
            }
            return null;
        }
    }

    /**
     * Convert linked list to string.
     * 
     * Precondition: this is a linked list.
     * Precondition: A string representing this list is returned, the string should
     * contain all of values present in the list in the same order that they are
     * present in the list.
     * 
     * @return String
     */
    public String toString() {
        // convert linked list to string
        StringBuilder sb = new StringBuilder();
        MyLinkedListNode<ElementType> current = head;
        while (current != null) {
            sb.append(current.toString() + " -> ");
            current = current.getNext();
        }
        return sb.toString();
    }
}