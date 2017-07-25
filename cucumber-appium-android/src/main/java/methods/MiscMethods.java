package methods;

import java.util.Arrays;

public class MiscMethods {

	public boolean valid_locator_type(String type) {
		return Arrays.asList(new String[] { "id","accessibilityId", "class", "css", "name", "xpath" }).contains(type);
	}

	public void validateLocator(String type) throws Exception {
		if (!valid_locator_type(type))
			throw new Exception((new StringBuilder("Invalid locator type - ")).append(type).toString());
		else
			return;
	}

	public boolean valid_option_by(String option_by) {
		return Arrays.asList(new String[] { "text", "value", "index" }).contains(option_by);
	}

	public void validateOptionBy(String optionBy) throws Exception {
		if (!valid_option_by(optionBy))
			throw new Exception((new StringBuilder("Invalid option by - ")).append(optionBy).toString());
		else
			return;
	}
}