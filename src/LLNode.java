
public class LLNode<T>{
	
	protected T info;
	protected LLNode<T> link;

	public LLNode(T info) {
		// TODO Auto-generated constructor stub

		this.info = info;
		link = null;
	}
	
	public LLNode() {
		// TODO Auto-generated constructor stub
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	public T getInfo() {
		return info;
	}	
	
	public void setLink(LLNode<T> link) {
		this.link = link;
	}
	
	public LLNode<T> getLink(){
		return link;
	}
		

}
