
public class BST<T>{
    public BSTNode<T> root, current;

    /** Creates a new instance of BST */
    public BST() {
     root = current = null;
    }

    public boolean empty() {
     return root == null;
    }
    public boolean full() {
     return false;
    }
    public T retrieve () {
     return current.data;
    }
       public boolean update(String key, T data){
     remove_key(current.key);
     return insert(key, data);
    }
       public void deleteSubtree(){
     if(current == root){
      current = root = null;
     }
     else {
      BSTNode<T> p = current;
      find(Relative.Parent);
      if(current.left == p)
       current.left = null;
      else 
       current.right = null;
      current = root;
     }
    }
       public boolean find(Relative rel){
     switch (rel) {
        case Root: // Easy case
      current = root;
      return true;
        case Parent:
      if(current == root)
                   return false;
      current = findparent(current, root);
      return true;
        case LeftChild:
      if(current.left == null)
                   return false;
      current = current.left;
      return true;
        case RightChild:
      if(current.right == null)
                   return false;
      current = current.right;
      return true;
        default:
      return false;
     }
    }
       private BSTNode<T> findparent(BSTNode<T> p, BSTNode<T> t) {
     if(t == null)
      return null; // empty tree
     if(t.right == null && t.left == null)
      return null;
     else if(t.right == p || t.left == p)
      return t; // parent is t
     else {
      BSTNode<T> q = findparent(p, t.left);
      if (q != null)
       return q;
      else
       return findparent(p, t.right);
     }
    }
       public boolean findkey(String tkey) {
     BSTNode<T> p = root, q = root; 
     int c =0;
     if(empty())
      return false; 
     while(p != null) {
      q = p;
      c = tkey.compareToIgnoreCase(p.key);
      if(c == 0) {
       current = p;
       return true;
      }
      else if(c < 0)
       p = p.left;
      else
       p = p.right;
     } 
     current = q;
     return false;
    }
       public boolean insert(String k, T val) {
     BSTNode<T> p, q = current;
     if(findkey(k)) {
      current = q;  // findkey() modified current
      return false; // key already in the BST
     }
     p = new BSTNode<T>(k, val);
     if (empty()) {
      root = current = p;
      return true;
     }
     else {
      int c = k.compareToIgnoreCase(current.key);
      if (c < 0)
       current.left = p;
      else
       current.right = p;
      current = p;
      return true;
     }
    }
       public boolean remove_key(String tkey){
     BooleanWrapper removed = new BooleanWrapper(false);
     BSTNode<T> p;
     p = remove_aux(tkey, root, removed);
     current = root = p;
     return removed.getValue();
    }
       private BSTNode<T> remove_aux(String key, BSTNode<T> p, BooleanWrapper flag) {
     BSTNode<T> q, child = null;
     if(p == null)
      return null;
     int c = key.compareToIgnoreCase(p.key);
     if(c < 0)
      p.left = remove_aux(key, p.left, flag); //go left
     else if(c > 0)
      p.right = remove_aux(key, p.right, flag); //go right
     else { // key is found
      flag.setValue(true);
      if (p.left != null && p.right != null) { //two children
       q = find_min(p.right);
       p.key = q.key;
       p.data = q.data;
       p.right = remove_aux(q.key, p.right, flag);
      }
               else {
          if (p.right == null) //one child
           child = p.left;
          else if (p.left == null) //one child
           child = p.right;
          return child;
         }
        }
     return p;
       }
       private BSTNode<T> find_min(BSTNode<T> p){
     if(p == null)
      return null;
     while(p.left != null){
      p = p.left;
     }
     return p;
    }
   
       public String giveRoot(){
           return root.key;
       }
public String toString() {
		if(empty())
			return "No photos in the album";
		tra(root);
		return "";
	}
	private void tra(BSTNode<T>b) {
		if(b == null)
			return;
		System.out.print(b.key + "-> ");
		System.out.println(b.data);
		tra(b.left);
		tra(b.right);
	}
}



    
