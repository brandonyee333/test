/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Observer} from '@clayui/modal/lib/types';
import {Elements} from 'react-flow-renderer';
import React from 'react';
import './ModalPublishObjectDefinitions.scss';
import {ObjectRelationshipEdgeData, TAction} from '../types';
interface IProps {
	disableAutoClose: boolean;
	observer: Observer;
	onClose: () => void;
	elements: Elements<ObjectDefinitionNodeData | ObjectRelationshipEdgeData>;
	dispatch: React.Dispatch<TAction>;
}
export declare function ModalPublishObjectDefinitions({
	disableAutoClose,
	observer,
	onClose,
	elements,
	dispatch,
}: IProps): JSX.Element;
export {};
