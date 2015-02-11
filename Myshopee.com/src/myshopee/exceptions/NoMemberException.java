
@SuppressWarnings("serial")
public class NoMemberException extends Exception {
	public NoMemberException(String s){
		super(s);
	}

	public  NoMemberException()
	{
		super("No data corresponding to the toDate and fromDate");
	}
}
