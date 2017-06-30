<%--
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
--%>

<%@ include file="/init.jsp" %>

<div class="comment-help">
	<h1 class="section-heading">
		<liferay-ui:message key="ticket-comment-bbcode-help" />
	</h1>

	<div class="callout-a">
		<div class="callout-content">
			<table>
			<tr>
				<th class="first">
					<strong><liferay-ui:message key="bbcode" /></strong>
				</th>
				<th>
					<strong><liferay-ui:message key="shortcut" /></strong>
				</th>
				<th>
					<strong><liferay-ui:message key="rendering" /></strong>
				</th>
			</tr>
			<tr>
				<td>
					[b]bolded text[/b]
				</td>
				<td class="col-2">
					ctrl+b
				</td>
				<td>
					<strong>bolded text</strong>
				</td>
			</tr>
			<tr>
				<td>
					[i]italicized text[/i]
				</td>
				<td class="col-2">
					ctrl+i
				</td>
				<td>
					<em>italicized text</em>
				</td>
			</tr>
			<tr>
				<td>
					[u]underlined text[/u]
				</td>
				<td class="col-2">
					ctrl+u
				</td>
				<td>
					<u>underlined text</u>
				</td>
			</tr>
			<tr>
				<td>
					[url=https://www.liferay.com]Hyperlinked Text[/url]
				</td>
				<td class="col-2">
					ctrl+k
				</td>
				<td>
					<a href="https://www.liferay.com" target="_blank">Hyperlinked Text</a>
				</td>
			</tr>
			<tr>
				<td>
					[quote]quoted text[/quote]
				</td>
				<td class="col-2">
					ctrl+q
				</td>
				<td>
					<div class="quote">
						<div class="quote-content">
							quoted text
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					[code]coded text line one<br />
					coded text line two<br />
					coded text line three[/code]
				</td>
				<td class="col-2">
					ctrl+o
				</td>
				<td>
					<div class="code">
						<span class="code-lines">1</span>coded text line one<br />
						<span class="code-lines">2</span>coded text line two<br />
						<span class="code-lines">3</span>coded text line three
					</div>
				</td>
			</tr>
			</table>
		</div>
	</div>

	<div>
		<input class="aui-button-input" onClick="window.close();" type="button" value="<liferay-ui:message key="close" />" />
	</div>
</div>