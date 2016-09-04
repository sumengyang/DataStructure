package tree;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author smy
 * 2016-07-24
 */
public class BinarySearchTree {

	//树的根节点
	BSTNode root = null;
	
	//存储树的遍历结点
	List<BSTNode> list = new ArrayList<BSTNode>();
	
	public static void main(String[] args) throws Exception {
		BinarySearchTree bst = new BinarySearchTree();
		System.out.println("当前查找树是否为空？" + (bst.isEmpty() ? "是" : "否"));
		int[] keys = new int[] { 15, 6, 18, 3, 7, 13, 20, 2, 9, 4 };
		for(int key : keys)
			bst.insert(key);
		System.out.println("当前查找树是否为空？" + (bst.isEmpty() ? "是" : "否"));
		
		BSTNode minBstNode = bst.minBstNode(bst.getRoot());
		System.out.println("当前树的最小关键字:" + minBstNode.value);
		testNode(bst, minBstNode);
		
		BSTNode maxBstNode = bst.maxBstNode(bst.getRoot());
		System.out.println("当前树的最大关键字:" + maxBstNode.value);
		testNode(bst, maxBstNode);
		
		BSTNode rootNode =bst.getRoot();
		System.out.println("当前树的根结点关键字:" + rootNode.value);
		testNode(bst, rootNode);
		
		testTraverse(bst);
		System.out.println("************************************");
		testTraverse(bst);

	}
	
	//判断是否是一颗空树
	public boolean isEmpty(){
		if(root == null)
			return true;
		else
			return false;
	}
	
	
	//查询给定的关键字
	public BSTNode search(int value){
		BSTNode pNode = root;
		while(pNode != null && pNode.value != value){
			if(pNode.value < value)
				pNode = pNode.right;
			else
				pNode = pNode.left;
		}
		return pNode;
	}
	
	//查找关键字最小的节点
	public BSTNode minBstNode(BSTNode node) throws Exception{
		if(root == null)
			throw new Exception("树为空！");
		BSTNode pNode = node;
		while(pNode.left != null){
			pNode = pNode.left;
		}
		return pNode;
	} 
	//查找关键字最大的节点
	public BSTNode maxBstNode(BSTNode node) throws Exception{
		if(root == null)
			throw new Exception("树为空");
		BSTNode pNode = node;
		while(pNode.right != null)
			pNode = pNode.right;
		return pNode;
	}

	//查找给定结点在中序遍历下的后续结点
	public BSTNode successor(BSTNode node) throws Exception{
		if(node == null)
			return null;
		// 若该结点的右子树不为空，则其后继结点就是右子树中的最小关键字结点
		if(node.right != null)
			return minBstNode(node.right);
		BSTNode parentNode = node.parent;
		while(parentNode != null && node == parentNode.right){
			node = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}
	//查找给定结点在中序遍历下的前驱结点
	public BSTNode precessor(BSTNode node) throws Exception{
		if(node == null)
			return null;
		if(node.left != null)
			return maxBstNode(node.left);
		BSTNode parentNode = node.parent;
		while(parentNode != null && node == parentNode.left){
			node = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}
	
	//将给定关键字插入到二叉查找树中
	public void insert(int value){
		BSTNode parent = null;
		BSTNode newNode = new BSTNode(value);
		BSTNode pNode = root;
		if(root == null){
			root = newNode;
			return ;
		}
		while(pNode != null){
			parent = pNode;
			if(pNode.value > value)
				pNode = pNode.left;
			else if(pNode.value < value)
				pNode = pNode.right;
			else
				return;//树中已经存在给定关键字的结点，直接返回；
		}
		if(value < parent.value){
			parent.left = newNode;
			newNode.parent = parent;
		}
		else if(value > parent.value){
			parent.right = newNode;
			newNode.parent = parent;
		}
	}
	
	//从二叉查找树中删除匹配给定关键字的结点
	public void delete(int value) throws Exception{
		BSTNode node = search(value);
		if(node == null)
			throw new Exception("树中不存在给定关键字的结点");
		delete(node);
	}
	
	//从二叉查找树中删除给定的结点
	//前提条件：给定结点在树中存在
	private void delete(BSTNode node) throws Exception {
		//1.该结点无左孩子结点和右孩子结点
		if(node.left == null && node.right == null){
			BSTNode parent = node.parent;
			if(node == parent.left)
				parent.left = null;
			else
				parent.right = null;
		}
		//2.该结点无左孩子结点，有右孩子结点
		if(node.left == null && node.right != null){
			BSTNode parent = node.parent;
			if(node == parent.left){
				parent.left = node.right;
				node.right.parent = parent;
			}
			else{
				parent.right = node.right;
				node.right.parent = parent;
			}
			return;
		}
		//3.该结点有左孩子结点，无右孩子结点
		if(node.left != null && node.right == null){
			BSTNode parent = node.parent;
			if(node == parent.left){
				parent.left = node.left;
				node.left.parent = parent;
			}
			else{
				parent.right = node.left;
				node.left.parent = parent;
			}
		}
		// 4.该结点同时有左孩子结点和右孩子结点
		if (node.left != null && node.right != null) {
			//该结点左右孩子结点均非空，则删除该结点的后继结点，并用该后继结点取代该结点
			BSTNode successorNode = successor(node);
			delete(successorNode);
			node.value = successorNode.value;
		}
	}
	
	//中序遍历BST
	public List<BSTNode> inorderTraverseList(){
		if(list != null)
			list.clear();
		inorderTraverseRecursion(root);
		return list;
	} 
	//迭代中序遍历
	private void inorderTraverse(BSTNode node){
		if(node != null){
			inorderTraverse(node.left);
			list.add(node);
			inorderTraverse(node.right);
		}
	}
	//递归中序遍历
	private void inorderTraverseRecursion(BSTNode node){
		Stack<BSTNode> stack = new Stack<BSTNode>();
		while(node != null){
			stack.push(node);
			node = node.left;
		}
		BSTNode pNode = null;
		while(stack != null){
			pNode = stack.pop();
			list.add(pNode);
			pNode = pNode.right;
			while(pNode != null){
				stack.push(pNode.left);
			}
		}
	}
	
	//二叉树有序的关键字列表
	public String toStringofOrderList(){
		StringBuilder sBuilder = new StringBuilder(" [ ");
		for(BSTNode node : inorderTraverseList()){
			sBuilder.append(node.value);
			sBuilder.append(" ");
		}
		sBuilder.append("]");
		return sBuilder.toString();
	}
	
	//二叉树的字符串表示
	public String toString(){
		StringBuilder sBuilder = new StringBuilder(" [ ");
		for(BSTNode node : inorderTraverseList()){
			sBuilder.append(node).append(" ");
		}
		sBuilder.append("]");
		return sBuilder.toString();
	}
	
	//取得根节点
	public BSTNode getRoot(){
		return root;
	}
	
	public static void testNode(BinarySearchTree tree,BSTNode node) throws Exception{
		System.out.println("当前结点 ：" + node);
		System.out.println("前驱结点 : " + tree.precessor(node));
		System.out.println("后序结点 : " + tree.successor(node));
	}
	
	public static void testTraverse(BinarySearchTree tree) {
		System.out.println("二叉树遍历 : " + tree);
		System.out.println("二叉查找树转换为有序列表 ： " + tree.toStringofOrderList());
	}
	
}
class BSTNode{
	int value;
	BSTNode left;
	BSTNode right;
	BSTNode parent;
	public BSTNode(int value) {
		this.value = value;
	}
}