<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the applicable
 * Liferay software end user license agreement ("License Agreement")
 * found on www.liferay.com/legal/eulas. You may also contact Liferay, Inc.
 * for a copy of the License Agreement. You may not use this file except in
 * compliance with the License Agreement.
 * See the License Agreement for the specific language governing
 * permissions and limitations under the License Agreement, including
 * but not limited to distribution rights of the Software.
 *
 */
--%>

<%!
private static boolean _isPendingApproval(WikiPage wikiPage) {
	if ((wikiPage == null) || !Validator.equals(wikiPage.getSummary(), _AKISMET_CONSTANTS_WIKI_PAGE_PENDING_APPROVAL)) {
		return false;
	}

	return true;
}

private static boolean _isSpam(WikiPage wikiPage) {
	if ((wikiPage == null) || !Validator.equals(wikiPage.getSummary(), _AKISMET_CONSTANTS_WIKI_PAGE_MARKED_AS_SPAM)) {
		return false;
	}

	return true;
}

private static final String _AKISMET_CONSTANTS_WIKI_PAGE_MARKED_AS_SPAM = "Marked as Spam";

private static final String _AKISMET_CONSTANTS_WIKI_PAGE_PENDING_APPROVAL = "Pending Approval";
%>