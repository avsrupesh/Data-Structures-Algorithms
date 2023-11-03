
public class BSTNode<T> {

	private T info;
	private BSTNode<T> leftNode;
	private BSTNode<T> rightNode;
	
	public BSTNode(T t) {
		// TODO Auto-generated constructor stub
		this.info = t;
		leftNode = null;
		rightNode = null;
		
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public BSTNode<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BSTNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	public BSTNode<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(BSTNode<T> rightNode) {
		this.rightNode = rightNode;
	}

}
