package tree;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author smy
 * 2016-07-24
 */
public class BinarySearchTree {

	//���ĸ��ڵ�
	BSTNode root = null;
	
	//�洢���ı������
	List<BSTNode> list = new ArrayList<BSTNode>();
	
	public static void main(String[] args) throws Exception {
		BinarySearchTree bst = new BinarySearchTree();
		System.out.println("��ǰ�������Ƿ�Ϊ�գ�" + (bst.isEmpty() ? "��" : "��"));
		int[] keys = new int[] { 15, 6, 18, 3, 7, 13, 20, 2, 9, 4 };
		for(int key : keys)
			bst.insert(key);
		System.out.println("��ǰ�������Ƿ�Ϊ�գ�" + (bst.isEmpty() ? "��" : "��"));
		
		BSTNode minBstNode = bst.minBstNode(bst.getRoot());
		System.out.println("��ǰ������С�ؼ���:" + minBstNode.value);
		testNode(bst, minBstNode);
		
		BSTNode maxBstNode = bst.maxBstNode(bst.getRoot());
		System.out.println("��ǰ�������ؼ���:" + maxBstNode.value);
		testNode(bst, maxBstNode);
		
		BSTNode rootNode =bst.getRoot();
		System.out.println("��ǰ���ĸ����ؼ���:" + rootNode.value);
		testNode(bst, rootNode);
		
		testTraverse(bst);
		System.out.println("************************************");
		testTraverse(bst);

	}
	
	//�ж��Ƿ���һ�ſ���
	public boolean isEmpty(){
		if(root == null)
			return true;
		else
			return false;
	}
	
	
	//��ѯ�����Ĺؼ���
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
	
	//���ҹؼ�����С�Ľڵ�
	public BSTNode minBstNode(BSTNode node) throws Exception{
		if(root == null)
			throw new Exception("��Ϊ�գ�");
		BSTNode pNode = node;
		while(pNode.left != null){
			pNode = pNode.left;
		}
		return pNode;
	} 
	//���ҹؼ������Ľڵ�
	public BSTNode maxBstNode(BSTNode node) throws Exception{
		if(root == null)
			throw new Exception("��Ϊ��");
		BSTNode pNode = node;
		while(pNode.right != null)
			pNode = pNode.right;
		return pNode;
	}

	//���Ҹ����������������µĺ������
	public BSTNode successor(BSTNode node) throws Exception{
		if(node == null)
			return null;
		// ���ý�����������Ϊ�գ������̽������������е���С�ؼ��ֽ��
		if(node.right != null)
			return minBstNode(node.right);
		BSTNode parentNode = node.parent;
		while(parentNode != null && node == parentNode.right){
			node = parentNode;
			parentNode = parentNode.parent;
		}
		return parentNode;
	}
	//���Ҹ����������������µ�ǰ�����
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
	
	//�������ؼ��ֲ��뵽�����������
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
				return;//�����Ѿ����ڸ����ؼ��ֵĽ�㣬ֱ�ӷ��أ�
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
	
	//�Ӷ����������ɾ��ƥ������ؼ��ֵĽ��
	public void delete(int value) throws Exception{
		BSTNode node = search(value);
		if(node == null)
			throw new Exception("���в����ڸ����ؼ��ֵĽ��");
		delete(node);
	}
	
	//�Ӷ����������ɾ�������Ľ��
	//ǰ��������������������д���
	private void delete(BSTNode node) throws Exception {
		//1.�ý�������ӽ����Һ��ӽ��
		if(node.left == null && node.right == null){
			BSTNode parent = node.parent;
			if(node == parent.left)
				parent.left = null;
			else
				parent.right = null;
		}
		//2.�ý�������ӽ�㣬���Һ��ӽ��
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
		//3.�ý�������ӽ�㣬���Һ��ӽ��
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
		// 4.�ý��ͬʱ�����ӽ����Һ��ӽ��
		if (node.left != null && node.right != null) {
			//�ý�����Һ��ӽ����ǿգ���ɾ���ý��ĺ�̽�㣬���øú�̽��ȡ���ý��
			BSTNode successorNode = successor(node);
			delete(successorNode);
			node.value = successorNode.value;
		}
	}
	
	//�������BST
	public List<BSTNode> inorderTraverseList(){
		if(list != null)
			list.clear();
		inorderTraverseRecursion(root);
		return list;
	} 
	//�����������
	private void inorderTraverse(BSTNode node){
		if(node != null){
			inorderTraverse(node.left);
			list.add(node);
			inorderTraverse(node.right);
		}
	}
	//�ݹ��������
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
	
	//����������Ĺؼ����б�
	public String toStringofOrderList(){
		StringBuilder sBuilder = new StringBuilder(" [ ");
		for(BSTNode node : inorderTraverseList()){
			sBuilder.append(node.value);
			sBuilder.append(" ");
		}
		sBuilder.append("]");
		return sBuilder.toString();
	}
	
	//���������ַ�����ʾ
	public String toString(){
		StringBuilder sBuilder = new StringBuilder(" [ ");
		for(BSTNode node : inorderTraverseList()){
			sBuilder.append(node).append(" ");
		}
		sBuilder.append("]");
		return sBuilder.toString();
	}
	
	//ȡ�ø��ڵ�
	public BSTNode getRoot(){
		return root;
	}
	
	public static void testNode(BinarySearchTree tree,BSTNode node) throws Exception{
		System.out.println("��ǰ��� ��" + node);
		System.out.println("ǰ����� : " + tree.precessor(node));
		System.out.println("������ : " + tree.successor(node));
	}
	
	public static void testTraverse(BinarySearchTree tree) {
		System.out.println("���������� : " + tree);
		System.out.println("���������ת��Ϊ�����б� �� " + tree.toStringofOrderList());
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