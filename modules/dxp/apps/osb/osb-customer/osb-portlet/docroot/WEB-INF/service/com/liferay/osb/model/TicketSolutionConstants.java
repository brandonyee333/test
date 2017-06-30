/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

/**
 * @author Amos Fong
 */
public class TicketSolutionConstants {

	public static final long DEFAULT_SOLUTION_ID = 0;

	public static final int ISSUE_TYPE_BUG = 1;

	public static final int ISSUE_TYPE_CONFIGURATION = 2;

	public static final String NOT_AVAILABLE = "N/A";

	public static final int STATUS_INVALID = 0;

	public static final int STATUS_REASON_ATTACHMENT_DID_NOT_WORK = 1;

	public static final int STATUS_REASON_CREATED_ANOTHER_PROBLEM = 2;

	public static final int STATUS_REASON_DID_NOT_DEPLOY = 3;

	public static final int STATUS_REASON_DID_NOT_FIX = 4;

	public static final int STATUS_REASON_UNCLEAR = 5;

	public static final int[] STATUS_REASONS = {
		STATUS_REASON_ATTACHMENT_DID_NOT_WORK,
		STATUS_REASON_CREATED_ANOTHER_PROBLEM, STATUS_REASON_DID_NOT_DEPLOY,
		STATUS_REASON_DID_NOT_FIX, STATUS_REASON_UNCLEAR
	};

	public static final int STATUS_RESOLVED = 1;

	public static final int STATUS_RESOLVED_IN_PRODUCTION = 2;

	public static final int STATUS_RESOLVED_MANUALLY = 6;

	public static final int STATUS_TESTING = 3;

	public static final int STATUS_UNABLE_TO_TEST = 5;

	public static final int STATUS_UNRESOLVED = 4;

	public static final int TYPE_PRODUCTION = 0;

	public static final int TYPE_SERVICE = 2;

	public static final int TYPE_TEMP = 1;

	public static String getIssueTypeLabel(int issueType) {
		if (issueType == ISSUE_TYPE_BUG) {
			return "bug";
		}
		else if (issueType == ISSUE_TYPE_CONFIGURATION) {
			return "configuration";
		}
		else {
			return null;
		}
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_INVALID) {
			return "invalid";
		}
		else if (status == STATUS_RESOLVED) {
			return "resolved";
		}
		else if (status == STATUS_RESOLVED_IN_PRODUCTION) {
			return "resolved-in-production";
		}
		else if (status == STATUS_RESOLVED_MANUALLY) {
			return "resolved-manually";
		}
		else if (status == STATUS_TESTING) {
			return "testing";
		}
		else if (status == STATUS_UNABLE_TO_TEST) {
			return "unable-to-test";
		}
		else if (status == STATUS_UNRESOLVED) {
			return "unresolved";
		}
		else {
			return null;
		}
	}

	public static String getStatusReasonLabel(int statusReason) {
		if (statusReason == STATUS_REASON_ATTACHMENT_DID_NOT_WORK) {
			return "the-attachment-link-did-not-work";
		}
		else if (statusReason == STATUS_REASON_CREATED_ANOTHER_PROBLEM) {
			return "the-solution-created-another-problem";
		}
		else if (statusReason == STATUS_REASON_DID_NOT_DEPLOY) {
			return "the-fix-did-not-deploy";
		}
		else if (statusReason == STATUS_REASON_DID_NOT_FIX) {
			return "the-solution-did-not-fix-the-issue";
		}
		else if (statusReason == STATUS_REASON_UNCLEAR) {
			return "the-solution-was-unclear";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getTypeLabel(int type) {
		if (type == TYPE_PRODUCTION) {
			return "production";
		}
		else if (type == TYPE_SERVICE) {
			return "service";
		}
		else if (type == TYPE_TEMP) {
			return "temp";
		}
		else {
			return null;
		}
	}

}