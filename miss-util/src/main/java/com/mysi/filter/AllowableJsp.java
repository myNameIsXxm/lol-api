package com.mysi.filter;

import java.util.ArrayList;
import java.util.List;

public class AllowableJsp {
	private AllowableJsp() {
	}

	public static List<String> authorisedList;

	public static List<String> getAuthorised() {
		if (authorisedList == null) {
			synchronized (AllowableJsp.class) {
				if (authorisedList == null) {
					authorisedList = new ArrayList<String>();
					authorisedList.add("login.html");
					authorisedList.add("main.html");
					authorisedList.add("index.jsp");
					authorisedList.add("/");
				}
			}
		}
		return authorisedList;
	}
}
