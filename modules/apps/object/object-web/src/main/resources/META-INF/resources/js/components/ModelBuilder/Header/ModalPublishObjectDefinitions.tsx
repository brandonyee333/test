/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayModal from '@clayui/modal';
import ClayCard from '@clayui/card';
import { Text } from '@clayui/core';
import { ClayCheckbox } from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import { Observer } from '@clayui/modal/lib/types';
import { Elements, FlowElement } from 'react-flow-renderer';
import { API } from '@liferay/object-js-components-web';
import React, {
    useState
} from 'react';


import './ModalPublishObjectDefinitions.scss';
import { ObjectRelationshipEdgeData, TAction } from '../types';
import { TYPES } from '../ModelBuilderContext/typesEnum';

interface IProps {
    disableAutoClose: boolean;
    observer: Observer;
    onClose: () => void;
    elements: Elements<ObjectDefinitionNodeData | ObjectRelationshipEdgeData>,
    dispatch: React.Dispatch<TAction>;
}

interface ISelectedItem {
    id: number;
    status?: 'approved' | 'loading' | 'rejected';
    msg?: string;
}


export function ModalPublishObjectDefinitions({ disableAutoClose, observer, onClose, elements, dispatch }: IProps) {
    const [selectedItems, setSelectedItems] = useState<ISelectedItem[]>([]);
    const [elementsFiltered, setElementsFiltered] = useState<Elements<ObjectDefinitionNodeData | ObjectRelationshipEdgeData>>(elements.filter(element => (element as FlowElement<ObjectDefinitionNodeData>).data?.status.code === 2));
    const [statusPublish, setStatusPublish] = useState<number>(0);
    const [msgHeaderModal, setMsgHeaderModal] = useState<string>("Confirm Publishing");

    const updateStatusObject = (elements: ISelectedItem[], id: number, status: 'approved' | 'loading' | 'rejected', msg?: string) => {
        return elements.map(item => {
            if (item.id === id) {
                return { id: id, status: status, ...(status === "rejected" && { msg: msg }) };
            } else {
                return item;
            }
        }) as ISelectedItem[]
    }

    const handleOnClickPublish = async () => {
        setStatusPublish(1);
        setMsgHeaderModal("Publishing");

        const publishObjectDefinition = (objId: number): Promise<void> => {
            return new Promise<void>(async (resolve, reject) => {
                try {
                    const response = await API.publishObjectDefinitionById(objId);

                    if (!response.ok) {
                        const data = await response.json();
                        throw new Error(data.title);
                    }

                    setSelectedItems(prevState => updateStatusObject(prevState, objId, 'approved'));

                    resolve();

                } catch (error: any) {
                    setSelectedItems(prevState => updateStatusObject(prevState, objId, 'rejected', error.message));
                    reject(error);
                }
            });

        }

        const publishPromises = selectedItems.map(item => {
            setSelectedItems(prevState => updateStatusObject(prevState, item.id, 'loading'));
            return publishObjectDefinition(item.id);
        });

        try {
            await Promise.all(publishPromises);
            setMsgHeaderModal("Successfully published!");
            debugger;
            const approvedList = selectedItems.filter(selectedItem => selectedItem.status === "approved");
            let newArrayElements = [] as any;

            approvedList.forEach(approvedItem => {
                newArrayElements = [...newArrayElements, elements.find(element => (element as FlowElement<ObjectDefinitionNodeData>).data?.id === approvedItem.id)];
            });
            console.log(newArrayElements);

            dispatch({
                payload: {
                    newElements: [],
                },
                type: TYPES.SET_ELEMENTS,
            });
        } catch (error) {
            setMsgHeaderModal("Confirm publishing");
        } finally {
            setStatusPublish(2);
        }
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
            <ClayModal.Header>{msgHeaderModal}</ClayModal.Header>

            <ClayModal.Body>
                <div className="c-mb-sm-4">
                    <Text size={3}>The following Objects contain changes that will be published and may affect your production environment. Please check before confirming:</Text>
                </div>
                <>
                    {elementsFiltered.map(obj => {
                        const { id, data } = obj as FlowElement<ObjectDefinitionNodeData>;
                        const selectedItem = selectedItems.find(item => item.id === data?.id!)
                        const isSelected = selectedItem?.id === data?.id!;

                        return (
                            <ClayCard key={id} className={`lfr-object__object-view-modal-object-definitions-card ${isSelected ? 'active' : ''}`}>
                                <ClayCard.Body>
                                    <div>
                                        <ClayCheckbox checked={isSelected} disabled={(selectedItem?.status && ["approved", "loading"].includes(selectedItem?.status))} onChange={() => handleCheckboxChange(data?.id!)} />
                                        <ClayIcon symbol="catalog" />
                                        <div>
                                            <div>
                                                <Text size={3} weight="bold">{data?.name}</Text>
                                            </div>
                                            {(!selectedItem?.status || !["approved", "rejected"].includes(selectedItem.status)) &&
                                                <ClayLabel displayType="info">
                                                    {Liferay.Language.get('draft')}
                                                </ClayLabel>
                                            }

                                            {selectedItem?.status === "rejected" &&
                                                <span className="rejected text-danger">
                                                    <ClayIcon symbol="exclamation-full" color="danger" />
                                                    <Text size={3}>{selectedItem?.msg}</Text>
                                                </span>
                                            }

                                        </div>
                                    </div>
                                    <div>
                                        {selectedItem?.status === "loading" && <ClayLoadingIndicator
                                            displayType="secondary"
                                            size="sm"
                                        />}

                                        {selectedItem?.status === "approved" && <Text color="success"><ClayIcon symbol="check" /></Text>}
                                    </div>
                                </ClayCard.Body>
                            </ClayCard>
                        )
                    })}
                </>
            </ClayModal.Body>

            <ClayModal.Footer
                last={
                    statusPublish === 2 ?
                        <ClayButton.Group key={1} spaced>
                            <ClayButton
                                displayType="primary"
                                onClick={onClose}
                            >
                                {Liferay.Language.get('close')}
                            </ClayButton>
                        </ClayButton.Group>
                        :
                        <ClayButton.Group key={2} spaced>
                            <>
                                <ClayButton
                                    displayType="secondary"
                                    onClick={onClose}
                                    className="c-mr-sm-2"
                                >
                                    {Liferay.Language.get('Cancel')}
                                </ClayButton>

                                <ClayButton
                                    displayType="primary"
                                    disabled={selectedItems.length === 0 || statusPublish === 1}
                                    onClick={handleOnClickPublish}
                                >
                                    {Liferay.Language.get('publish')}
                                </ClayButton>
                            </>

                        </ClayButton.Group>
                }>
            </ClayModal.Footer>
        </ClayModal >
    );
}
