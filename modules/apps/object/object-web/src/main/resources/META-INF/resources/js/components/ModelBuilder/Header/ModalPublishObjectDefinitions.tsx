/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayModal from '@clayui/modal';
import ClayCard from '@clayui/card';
import { ClayCheckbox } from '@clayui/form';
import ClayIcon from '@clayui/icon';
import { Observer } from '@clayui/modal/lib/types';
import { Elements, FlowElement } from 'react-flow-renderer';
import {
    API,
    RadioField
} from '@liferay/object-js-components-web';
import React, {
    useEffect,
    useState
} from 'react';


import './ModalPublishObjectDefinitions.scss';
import { ObjectRelationshipEdgeData } from '../types';

type IProps = {
    disableAutoClose: boolean;
    observer: Observer;
    onClose: () => void;
    elements: Elements<ObjectDefinitionNodeData | ObjectRelationshipEdgeData>,
}

interface IItem {
    id: number;
    name: string;
}


export function ModalPublishObjectDefinitions({ disableAutoClose, observer, onClose, elements }: IProps) {
    const [items, setItems] = useState<IItem[]>([]);
    const [selectedItems, setSelectedItems] = useState<number[]>([]);
    const elementsFiltered = elements.filter(element => (element as FlowElement<ObjectDefinitionNodeData>).data?.status.code === 2);

    const handleOnClickPublish = () => {
        selectedItems.forEach(item => {
            const publishObjectDefinition = async (objId : number) => {
                const element = (await API.publishObjectDefinitionById(objId));
            }
            publishObjectDefinition(item);
        })
    }
    useEffect(() => {
        const publishObjectDefinition = async () => {
            const element = (await API.publishObjectDefinitionById(12));
        }
        publishObjectDefinition();
    }, []);

    const handleCheckboxChange = (itemId: number) => {
        if (selectedItems.includes(itemId)) {
            setSelectedItems(selectedItems.filter(id => id !== itemId));
        } else {
            setSelectedItems([...selectedItems, itemId]);
        }
    };

    return (
        <ClayModal disableAutoClose={disableAutoClose} observer={observer} status="warning">
            <ClayModal.Header>Confirm Publishing</ClayModal.Header>

            <ClayModal.Body>
                <p>The following Objects contain changes that will be published and may affect your production environment. Please check before confirming:</p>

                <>
                    {elementsFiltered.map(obj => {
                        const { id, data } = obj as FlowElement<ObjectDefinitionNodeData>;
                        const isSelected = selectedItems.includes(data?.id!);

                        return (
                            <ClayCard key={id} className={`lfr-object__object-view-modal-object-definitions-card ${isSelected ? 'active' : ''}`}>
                                <ClayCard.Body>
                                    <ClayCheckbox checked={isSelected} onChange={() => handleCheckboxChange(data?.id!)} />
                                    <ClayIcon symbol="catalog" />
                                    <div>
                                        <div>{data?.name}</div>
                                        <span className={`label ${data?.status.code === 2 ? "label-info" : "label-success"}`}>
                                            <span className="label-item label-item-expand">{data?.status.label_i18n}</span>
                                        </span>
                                    </div>
                                </ClayCard.Body>
                            </ClayCard>
                        )
                    })}
                </>
            </ClayModal.Body>

            <ClayModal.Footer
                last={
                    <ClayButton.Group key={1} spaced>
                        <ClayButton
                            displayType="secondary"
                            onClick={onClose}
                        >
                            {Liferay.Language.get('Cancel')}
                        </ClayButton>

                        <ClayButton
                            displayType="primary"
                            disabled={selectedItems.length === 0}
                            onClick={handleOnClickPublish}
                        >
                            {Liferay.Language.get('publish')}
                        </ClayButton>
                    </ClayButton.Group>
                }>
            </ClayModal.Footer>
        </ClayModal>
    );
}
