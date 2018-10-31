/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.release.notes.web.internal.util.apichange;

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.web.internal.util.comparator.APIChangeMethodComparator;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Samuel Kong
 */
public class APIChangeClass extends APIChangeBase {

	public APIChangeClass(String apiChangeClassText, JIRAIssue jiraIssue) {
		setJIRAIssue(jiraIssue);

		String[] tokens = StringUtil.split(apiChangeClassText, "\n\n");

		String curToken = tokens[0].trim();

		int x = curToken.indexOf(StringPool.NEW_LINE);

		if (x == -1) {
			_className = curToken;
		}
		else {
			_className = curToken.substring(0, x);

			String details = curToken.substring(x);

			setDetails(details.trim());
		}

		for (int i = 1; i < tokens.length; i++) {
			if (Validator.isNotNull(curToken)) {
				addAPIChangeMethod(new APIChangeMethod(curToken, jiraIssue));
			}
		}
	}

	public void addAPIChangeMethod(APIChangeMethod apiChangeMethod) {
		if (_apiChangeMethods == null) {
			_apiChangeMethods = new TreeSet<>(new APIChangeMethodComparator());
		}

		_apiChangeMethods.add(apiChangeMethod);
	}

	public Set<APIChangeMethod> getAPIChangeMethods() {
		return _apiChangeMethods;
	}

	public int getAPIChangeMethodsCount() {
		if (_apiChangeMethods == null) {
			return 0;
		}

		return _apiChangeMethods.size();
	}

	public String getClassName() {
		return _className;
	}

	public String getName() {
		int x = _className.lastIndexOf(StringPool.PERIOD);

		if (x == -1) {
			return _className;
		}

		x += 1;

		return _className.substring(x);
	}

	public String getPackage() {
		int x = _className.lastIndexOf(StringPool.PERIOD);

		if (x == -1) {
			return StringPool.BLANK;
		}

		return _className.substring(0, x);
	}

	public void mergeWith(APIChangeClass apiChangeClass) throws Exception {
		Set<APIChangeMethod> apiChangeMethods =
			apiChangeClass.getAPIChangeMethods();

		if (apiChangeMethods != null) {
			for (APIChangeMethod apiChangeMethod : apiChangeMethods) {
				addAPIChangeMethod(apiChangeMethod);
			}
		}

		if ((getDetails() != null) && (apiChangeClass.getDetails() != null)) {
			StringBundler sb = new StringBundler(7);

			sb.append("API change conflict with class ");
			sb.append(getClassName());
			sb.append(" (");

			JIRAIssue jiraIssue = getJIRAIssue();

			sb.append(jiraIssue.getKey());

			sb.append(", ");

			JIRAIssue apiChangeJIRAIssue = apiChangeClass.getJIRAIssue();

			sb.append(apiChangeJIRAIssue.getKey());

			sb.append(")");

			throw new Exception(sb.toString());
		}

		if (apiChangeClass.getDetails() != null) {
			setJIRAIssue(apiChangeClass.getJIRAIssue());

			for (KeyValuePair detail : apiChangeClass.getDetails()) {
				addDetail(detail);
			}
		}
	}

	private Set<APIChangeMethod> _apiChangeMethods;
	private final String _className;

}