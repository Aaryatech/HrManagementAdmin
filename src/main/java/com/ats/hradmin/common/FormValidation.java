package com.ats.hradmin.common;

public class FormValidation {

	public static Boolean Validaton(String str, String type) {

		try {
			if (str != null && !str.trim().isEmpty()) {

				if (type.equals("email")) {

					String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
					java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
					java.util.regex.Matcher m = p.matcher(str);

					if (m.matches()) {
						return false;
					} else {
						return true;
					}

				}
				if (type.equals("mobile")) {

					String ePattern = "^[0-9]{10}$";
					java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
					java.util.regex.Matcher m = p.matcher(str);
					if (m.matches()) {
						return false;
					} else {
						return true;
					}
				}

				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

}
