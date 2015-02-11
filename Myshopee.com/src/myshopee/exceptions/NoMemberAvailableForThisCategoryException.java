

@SuppressWarnings("serial")
public class NoMemberAvailableForThisCategoryException extends Exception {

	public NoMemberAvailableForThisCategoryException() {
		super("There are no members available for the selected category");
	}
}
