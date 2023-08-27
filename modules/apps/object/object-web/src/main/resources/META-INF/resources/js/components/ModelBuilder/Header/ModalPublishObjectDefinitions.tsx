/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayModal from '@clayui/modal';
import ClayCard from '@clayui/card';
import { ClayCheckbox } from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import { Observer } from '@clayui/modal/lib/types';
import { Elements, FlowElement } from 'react-flow-renderer';
import {
    API
} from '@liferay/object-js-components-web';
import React, {
    useEffect,
    useState
} from 'react';


import './ModalPublishObjectDefinitions.scss';
import { ObjectRelationshipEdgeData } from '../types';

interface IProps {
    disableAutoClose: boolean;
    observer: Observer;
    onClose: () => void;
    elements: Elements<ObjectDefinitionNodeData | ObjectRelationshipEdgeData>,
}

interface ISelectedItem {
    id: number;
    status?: 'approved' | 'loading' | 'rejected';
    msg?: string;
}


export function ModalPublishObjectDefinitions({ disableAutoClose, observer, onClose, elements }: IProps) {
    const [selectedItems, setSelectedItems] = useState<ISelectedItem[]>([]);
    const elementsFiltered = elements.filter(element => (element as FlowElement<ObjectDefinitionNodeData>).data?.status.code === 2);

    const handleOnClickPublish = () => {

        const publishObjectDefinition = async (objId: number) => {
            try {
                const response = await API.publishObjectDefinitionById(objId);

                if (!response.ok) throw new Error("mudar erro");

                setSelectedItems(prevState => prevState.map(prevItem => {
                    if (prevItem.id === objId) {
                        return { id: objId, status: 'approved' };
                    } else {
                        return prevItem;
                    }
                }));

            } catch (error: any) {
                setSelectedItems(prevState => prevState.map(prevItem => {
                    if (prevItem.id === objId) {
                        return { id: objId, status: 'rejected', msg: error.message };
                    } else {
                        return prevItem;
                    }
                }));
            }

        }

        selectedItems.forEach(item => {
            setSelectedItems(prevState => prevState.map(prevItem => {
                if (prevItem.id === item.id) {
                    return { id: item.id, status: 'loading' };
                } else {
                    return prevItem;
                }
            }));
            publishObjectDefinition(item.id);
        })
    }

    const handleCheckboxChange = (itemId: number) => {
        if (selectedItems.some(item => item.id === itemId)) {
            setSelectedItems(selectedItems.filter(item => item.id !== itemId));
        } else {
            setSelectedItems([...selectedItems, { id: itemId }]);
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
                        const selectedItem = selectedItems.find(item => item.id === data?.id!)
                        const isSelected = selectedItem?.id === data?.id!;

                        return (
                            <ClayCard key={id} className={`lfr-object__object-view-modal-object-definitions-card ${isSelected ? 'active' : ''}`}>
                                <ClayCard.Body>
                                    <div>
                                        <ClayCheckbox checked={isSelected} onChange={() => handleCheckboxChange(data?.id!)} />
                                        <ClayIcon symbol="catalog" />
                                        <div>
                                            <div>{data?.name}</div>
                                            {(!selectedItem?.status || !["approved", "rejected"].includes(selectedItem.status)) &&
                                                <span className={`label label-info`}>
                                                    <span className="label-item label-item-expand">{data?.status.label_i18n}</span>
                                                </span>
                                            }

                                            {selectedItem?.status === "rejected" &&
                                                <span className="rejected">
                                                    <ClayIcon symbol="exclamation-full" />
                                                    <span>{selectedItem?.msg}</span>
                                                </span>
                                            }

                                        </div>
                                    </div>
                                    <div>
                                        {selectedItem?.status === "loading" && <ClayLoadingIndicator
                                            displayType="secondary"
                                            size="sm"
                                        />}

                                        {selectedItem?.status === "approved" && <ClayIcon symbol="check" />}
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
