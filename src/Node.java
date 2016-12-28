
public class Node {
	private String code;
	private String data;
	private Node left,right;
	private int value;
	Node()
	{
		code=null;
		data=null;
		left=null;
		right=null;
		value=(Integer) null;
	}
	Node(int val,String c,String d,Node l,Node r)
	{
		value=val;
		code=c;
		data=d;
		left=l;
		right=r;
	}
	public Node GetLeft()
	{
		return left;
	}
	public Node GetRight()
	{
		return right;
	}
	public void SetRight(Node r)
	{
		right=r;
	}
	public void SetLeft(Node l)
	{
		left=l;
	}
	public String GetData()
	{
		return data;
	}
	public String GetCode()
	{
		return code;
	}
	public void SetData(String r)
	{
		data=r;
	}
	public void SetCode(String l)
	{
		code=l;
	}
	public int GetValue()
	{
		return value;
	}
	public void SetValue(int r)
	{
		value=r;
	}
}
