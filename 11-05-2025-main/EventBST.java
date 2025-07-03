public class EventBST {
    private class Node {
        Event event;
        Node left, right;

        Node(Event event) {
            this.event = event;
        }
    }

    private Node root;

    public void insert(Event event) {
        root = insertRec(root, event);
    }

    private Node insertRec(Node root, Event event) {
        if (root == null) {
            root = new Node(event);
            return root;
        }
        if (event.getEventId() < root.event.getEventId())
            root.left = insertRec(root.left, event);
        else if (event.getEventId() > root.event.getEventId())
            root.right = insertRec(root.right, event);
        return root;
    }

    public Event search(int eventId) {
        return searchRec(root, eventId);
    }

    private Event searchRec(Node root, int eventId) {
        if (root == null)
            return null;
        if (eventId == root.event.getEventId())
            return root.event;
        if (eventId < root.event.getEventId())
            return searchRec(root.left, eventId);
        return searchRec(root.right, eventId);
    }

    public void inorder() {
        System.out.println("\n=== List of Events ===");
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.event);
            inorderRec(root.right);
        }
    }

    public boolean delete(int eventId) {
        if (search(eventId) == null)
            return false;
        root = deleteRec(root, eventId);
        return true;
    }

    private Node deleteRec(Node root, int eventId) {
        if (root == null)
            return root;
        if (eventId < root.event.getEventId())
            root.left = deleteRec(root.left, eventId);
        else if (eventId > root.event.getEventId())
            root.right = deleteRec(root.right, eventId);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.event = minValue(root.right);
            root.right = deleteRec(root.right, root.event.getEventId());
        }
        return root;
    }

    private Event minValue(Node root) {
        Event minv = root.event;
        while (root.left != null) {
            minv = root.left.event;
            root = root.left;
        }
        return minv;
    }
}
