package methods;

import org.openqa.selenium.By;

/**
 * @author xl
 *
 */
public class SelectElementByType {
	public By getelementbytype(String type, String access_name) {
		String s;
		switch ((s = type).hashCode()) {
		default:
			break;

		case -1549184699:
			if (s.equals("tagName"))
				return By.tagName(access_name);
			break;

		case 3355:
			if (s.equals("id"))
				return By.id(access_name);
			break;

		case 98819:
			if (s.equals("css"))
				return By.cssSelector(access_name);
			break;

		case 3373707:
			if (s.equals("name"))
				return By.name(access_name);
			break;

		case 94742904:
			if (s.equals("class"))
				return By.className(access_name);
			break;

		case 114256029:
			if (s.equals("xpath"))
				return By.xpath(access_name);
			break;

		case 292026600:
			if (s.equals("partialLinkText"))
				return By.partialLinkText(access_name);
			break;

		case 1194187847:
			if (s.equals("linkText"))
				return By.linkText(access_name);
			break;
		}
		return null;
	}

//	public String conventClassName(String className) {
//		String using = "";
//		if (className.equalsIgnoreCase("EditText")) {
//			using = "android.widget.EditText";
//		}
//		if (className.equalsIgnoreCase("TextView")) {
//			using = "android.widget.TextView";
//		}
//		if (className.equalsIgnoreCase("ImageView")) {
//			using = "android.widget.ImageView";
//		}
//
//		return using;
//	}
}