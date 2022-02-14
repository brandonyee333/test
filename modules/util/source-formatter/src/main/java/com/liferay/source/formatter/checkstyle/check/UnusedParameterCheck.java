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

package com.liferay.source.formatter.checkstyle.check;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public class UnusedParameterCheck extends BaseCheck {

	@Override
	public int[] getDefaultTokens() {
		return new int[] {
			TokenTypes.CLASS_DEF, TokenTypes.ENUM_DEF, TokenTypes.INTERFACE_DEF
		};
	}

	@Override
	protected void doVisitToken(DetailAST detailAST) {
		DetailAST parentDetailAST = detailAST.getParent();

		if (parentDetailAST != null) {
			return;
		}

		List<DetailAST> constructorsAndMethodsASTList = getAllChildTokens(
			detailAST, true, TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF);

		for (DetailAST constructorOrMethodDetailAST :
				constructorsAndMethodsASTList) {

			_checkUnusedParameters(detailAST, constructorOrMethodDetailAST);
		}
	}

	private void _checkUnusedParameters(
		DetailAST classDetailAST, DetailAST detailAST) {

		DetailAST modifiersDetailAST = detailAST.findFirstToken(
			TokenTypes.MODIFIERS);

		if (!modifiersDetailAST.branchContains(TokenTypes.LITERAL_PRIVATE)) {
			return;
		}

		String name = getIdentifier(detailAST);

		if (name.equals("readObject") || name.equals("writeObject")) {
			return;
		}

		List<String> parameterNames = getParameterNames(detailAST);

		if (parameterNames.isEmpty()) {
			return;
		}

		DetailAST statementsDetailAST = detailAST.findFirstToken(
			TokenTypes.SLIST);

		List<String> identifiers = getIdentifiers(statementsDetailAST, true);

		parameterNameLoop:
		for (String parameterName : getParameterNames(detailAST)) {
			for (String identifier : identifiers) {
				if (parameterName.equals(identifier)) {
					continue parameterNameLoop;
				}
			}

			if (!_isReferencedMethod(classDetailAST, detailAST)) {
				log(detailAST, _MSG_UNUSED_PARAMETER, parameterName);
			}
		}
	}

	private boolean _isReferencedMethod(
		DetailAST classDetailAST, DetailAST detailAST) {

		List<DetailAST> methodReferenceDetailASTList = getAllChildTokens(
			classDetailAST, true, TokenTypes.METHOD_REF);

		if (methodReferenceDetailASTList.isEmpty()) {
			return false;
		}

		String name = getIdentifier(detailAST);

		for (DetailAST methodReferenceDetailAST :
				methodReferenceDetailASTList) {

			for (String identifier :
					getIdentifiers(methodReferenceDetailAST, true)) {

				if (name.equals(identifier)) {
					return true;
				}
			}
		}

		return false;
	}

	private static final String _MSG_UNUSED_PARAMETER = "parameter.unused";

}