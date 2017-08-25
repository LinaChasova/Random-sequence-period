/**
 * Created by AlinaCh on 24.03.2017.
 */
public class MyAVLMap<K extends Comparable, V> {

    public Node root;

    /**
     * adds new element to the tree
     * @param key index of the element to be added
     * @param value value of an element to be addded
     */
    public void add(K key, V value) {
        if (key != null && value != null) {
            root = insert(root, key, value);
        }
    }

    /**
     * search operation
     * @param key index of element to be searched
     * @return the value of an element
     */
    public V search(K key) {
        return get(root, key);
    }

    /**
     * search operation starting with root and performs
     * recursive operation until finds the required element
     * or searches the whole tree
     * @param cur root of sub-tree
     * @param key index of element to be searched
     * @return the calue of an element
     */
    private V get(Node cur, K key) {
        if (cur == null) {
            return null;
        }
        if (cur.key.equals(key)){
            return (V) cur.getValue();
        } else if (cur.key.compareTo(key) > 0) {
            return get(cur.left, key);
        } else if (cur.key.compareTo(key) < 0) {
            return get(cur.right, key);
        }
        return null;
    }

    /**
     * adds new node to the tree and performs the balancing of the tree
     * @param cur the root of sub-tree
     * @param key index of element to be added
     * @param value value of element to be added
     * @return
     */
    private Node insert(Node cur, K key, V value) {
        if (cur == null) {
            cur = new Node(key, value);
        } else if (cur.key.equals(key)) {
            cur.value = value;
        } else if (cur.key.compareTo(key) > 0) {
            cur.left = insert(cur.left, key, value);
        } else if (cur.key.compareTo(key) < 0) {
            cur.right = insert(cur.right, key, value);
        }
        cur.height = Math.max(getHeight(cur.left), getHeight(cur.right)) + 1;

        int balance = balanceFactor(cur);
        if (balance > 1) {
            if (key.compareTo(cur.left.key) < 0) {
                return rightRotation(cur);
            } else {
                cur.left = leftRotation(cur.left);
                return rightRotation(cur);
            }
        } else if (balance < -1) {
            if (key.compareTo(cur.right.key) > 0) {
                return leftRotation(cur);
            } else {
                cur.right = rightRotation(cur.right);
                return leftRotation(cur);
            }
        }
        return cur;
    }

    /**
     * @param cur root of the tree
     * @return height of the sub-tree
     */
    private int getHeight(Node cur) {
        if (cur == null) {
            return 0;
        }
        return cur.height;
    }

    /**
     * @param cur given node
     * @return balance factor of cur
     */
    private int balanceFactor (Node cur) {
            if (cur == null)    return 0;
        return getHeight(cur.left) - getHeight(cur.right);
    }

    /**
     * re-balancing of the tree
     * rotate binary tree with left child
     * update heights
     * @param element the un-balanced node
     * @return new root of the tree
     */
    private Node rightRotation(Node element) {
        Node temp = element.left;
        Node temp2 = temp.right;
        temp.right = element;
        element.left = temp2;
        element.height = Math.max(getHeight(element.left), getHeight(element.right)) + 1;
        temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
        return temp;
    }

    /**
     * re-balancing of the tree
     * rotate binary tree with right child
     * update heights
     * @param element the un-balanced node
     * @return new root of the tree
     */
    private Node leftRotation(Node element) {
        Node temp = element.right;
        Node temp2 = temp.left;
        temp.left = element;
        element.right = temp2;
        element.height = Math.max(getHeight(element.left), getHeight(element.right)) + 1;
        temp.height = Math.max(getHeight(temp.left), getHeight(temp.right)) + 1;
        return temp;
    }


    /**
     * class for the elements in the tree, containing links
     * to the right and left children
     * @param <K>
     * @param <V>
     */
    public class Node<K extends Comparable, V> {

        public K key;
        public V value;
        public Node right;
        public Node left;
        public int height;

        /**
         * constructor if the node in the AVL Tree
         * initializes the node
         * @param key the 'index' of an element in the map
         * @param value the value of the index
         */
        public Node(Object key, Object value) {
            this(key, value, null, null);
            this.height = 0;
        }

        /**
         * constructor
         * @param key
         * @param value
         * @param left
         * @param right
         */
        private Node(Object key, Object value, Node left, Node right) {
            this.key = (K) key;
            this.value = (V) value;
            this.left = left;
            this.right = right;

            height = 0;
        }

        public V getValue() {
            return this.value;
        }
    }
}
