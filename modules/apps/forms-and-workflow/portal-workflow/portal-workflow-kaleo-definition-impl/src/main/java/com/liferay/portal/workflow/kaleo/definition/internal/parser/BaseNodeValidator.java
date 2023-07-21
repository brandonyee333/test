/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.parser;

import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.definition.exception.KaleoDefinitionValidationException;
import com.liferay.portal.workflow.kaleo.definition.parser.NodeValidator;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseNodeValidator<T extends Node>
	implements NodeValidator<T> {

	@Override
	public void validate(Definition definition, T node)
		throws KaleoDefinitionValidationException {

		doValidate(definition, node);

		validateTransitions(node.getOutgoingTransitions());
	}

	protected abstract void doValidate(Definition definition, T node)
		throws KaleoDefinitionValidationException;

	protected void validateTransition(Transition transition)
		throws KaleoDefinitionValidationException {

		if (transition.getTargetNode() == null) {
			throw new KaleoDefinitionValidationException.MustSetTargetNode(
				transition.getName());
		}
	}

	protected void validateTransitions(Map<String, Transition> transitions)
		throws KaleoDefinitionValidationException {

		for (Transition transition : transitions.values()) {
			validateTransition(transition);
		}
	}

}