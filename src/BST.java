

public class BST<K extends Comparable<K>, T> implements Map<K, T> {
class BSTNode<K extends Comparable<K>, T> {
   public K key;
   public T data;
   public BSTNode<K,T> Left, Right;

//Creates a new instance of BSTNode
public BSTNode(K k, T val) {
key = k;
data = val;
Left = Right = null;
}

public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
key = k;
data = val;
Left = l;
Right = r;
}
    }
    
 BSTNode<K, T> root; 
 BSTNode<K, T > current;
 int count ;
                
public BST(){
   root = current = null;
   count = 0;
}
        
//Returns the number of elements in the map.
public int size(){
return count;}

//Return true if the tree is empty. Must be O(1).
public boolean empty(){
return root == null;}

//Removes all elements in the map.
public void clear(){
   root = current = null;
   count = 0;
}

//Return the key and data of the current element
public T retrieve()
{
   T data =null;
   if (current != null)
   data = current.data;
   return data;
}

//Update the data of current element.
public void update(T e)
{
    if (current != null)
    current.data = e;
}

//Search for element with key k and make it the current element if it exists.
//If the element does not exist the current is unchanged and false is returned.
//This method must be O(log(n)) in average.
public boolean find(K key)
{
BSTNode<K,T> p = root;
if(empty())
return false;

while(p != null) {
if(p.key.compareTo(key) == 0) {
   current = p;
   return true;
} 
 else
if(key.compareTo(p.key) < 0)
   p = p.Left;
 else
   p = p.Right;
}
   return false;
}

//Return the number of key comparisons needed to find key.
public int nbKeyComp(K key)
{
int nbKeycomp = 0;
BSTNode<K,T> p = root;

if(empty())
return nbKeycomp;

while(p!= null) {
      nbKeycomp++;
     if(p.key.compareTo(key) == 0) {
        current = p;
        break;
}
  else
  
if(key.compareTo(p.key) < 0)
   p = p.Left;
  else
   p = p.Right;
}
   return nbKeycomp;    
   }

//Insert a new element if does not exist and return true. The current points to the new element.
//If the element already exists, current does not change and false is returned.
//This method must be O(log(n)) in average.

public boolean insert(K key, T data)
{

if(empty()){
  current = root = new BSTNode <K, T> (key, data);
  count++;
  return true;
}
  BSTNode<K,T> par = null;
  BSTNode<K,T> child = root;
            
while(child != null) {
if(child.key.compareTo(key) == 0) {
   return false; }    
                   
else 

if(key.compareTo(child.key) < 0) {
par = child;
child = child.Left; }

else

{ par = child;
child = child.Right;}

}
           
if(key.compareTo(par.key) < 0)
{
par.Left= new BSTNode <K, T> (key, data);
current= par.Left;
}
            
else
{
par.Right= new BSTNode <K, T> (key, data);
current= par.Right;
}
count++;
return true;
        
 }

//Remove the element with key k if it exists and return true. If the element
//does not exist false is returned (the position of current is unspecified
//after calling this method). This method must be O(log(n)) in average.
public boolean remove(K key)
{
boolean removed = false;
BSTNode<K,T> p;
            
p = remove_aux(key, root, removed);
root = p;
            
if (current.key.compareTo(key) == 0)
    current= root;
if (removed)
    count-- ;
            
return removed;
 }
    
private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, boolean flag) {
BSTNode<K,T> q, child = null;
if(p == null)
return null;
if(key.compareTo(p.key) < 0)
p.Left = remove_aux(key, p.Left, flag); //go Left

 else 

if(key.compareTo(p.key) > 0)
p.Right = remove_aux(key, p.Right, flag); //go Right
 
 else {
                    
flag = true;
if (p.Left != null && p.Right != null)
{ 
//two children
 q = find_min(p.Right);
 p.key = q.key;
 p.data = q.data;
 p.Right = remove_aux(q.key, p.Right, flag);
    }
                   
  else {
  
if (p.Right == null) 
//one child
child = p.Left;

  else
                            
if (p.Left == null) 
//one child
child = p.Right;
return child;
                    }
                }
return p;
}
private BSTNode<K,T> find_min(BSTNode<K,T> p)
  {
if(p == null)
return null;

while(p.Left != null){
p = p.Left;
}
return p;
}

//Return all keys in the map as a list sorted in increasing order.
public DLLComp<K> getKeys()
{
  DLLComp<K> dllcomp = new DLLCompImp<K>();
  inOrderTraversal(root, dllcomp);
  return dllcomp;
}
        
private void inOrderTraversal(BSTNode<K,T> node,DLLComp<K> dllcomp)
{
if (node == null)
return;
inOrderTraversal(node.Left, dllcomp);
dllcomp.insert(node.key);
inOrderTraversal(node.Right, dllcomp);
}
}

