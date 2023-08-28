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
import React, { useState } from 'react';
import './ModalPublishObjectDefinitions.scss';
import { ObjectRelationshipEdgeData, TAction } from '../types';
import { TYPES } from '../ModelBuilderContext/typesEnum';


type TStatus = 'danger' | 'info' | 'success' | 'warning';

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
    message?: string;
}

enum STATUS {
    INITIAL = 0,
    LOADING = 1,
    FINISHED = 2,
    REJECTED = 3
}


export function ModalPublishObjectDefinitions({ disableAutoClose, observer, onClose, elements, dispatch }: IProps) {
    const [elementsFiltered] = useState<Elements<ObjectDefinitionNodeData | ObjectRelationshipEdgeData>>(elements.filter(element => (element as FlowElement<ObjectDefinitionNodeData>).data?.status.code === 2));
    const [messageHeaderModal, setMessageHeaderModal] = useState<string>("Confirm Publishing");
    const [selectedItems, setSelectedItems] = useState<ISelectedItem[]>([]);
    const [statusPublish, setStatusPublish] = useState<number>(STATUS.INITIAL);

    const updateStatusObject = (elements: ISelectedItem[], id: number, status: 'approved' | 'loading' | 'rejected', message?: string) => {
        return elements.map(item => {
            if (item.id === id) {
                return { id: id, status: status, ...(status === "rejected" && { message: message }) };
            } else {
                return item;
            }
        }) as ISelectedItem[]
    }

    const handleOnClickPublish = async () => {
        setStatusPublish(STATUS.LOADING);
        setMessageHeaderModal("Publishing");

        const publishObjectDefinition = (objectId: number): Promise<number> => {
            return new Promise<number>(async (resolve) => {
                try {
                    const response = await API.publishObjectDefinitionById(objectId);

                    if (!response.ok) {
                        const data = await response.json();
                        throw new Error(data.title);
                    }

                    setSelectedItems(prevState => updateStatusObject(prevState, objectId, 'approved'));

                    resolve(objectId);

                } catch (error: any) {
                    setSelectedItems(prevState => updateStatusObject(prevState, objectId, 'rejected', error.message));
                    //don't throw reject, so that it doesn't go to the catch flow of the promise.all
                    resolve(0);
                }
            });
        }

        const publishPromises = selectedItems.map(item => {
            setSelectedItems(prevState => updateStatusObject(prevState, item.id, 'loading'));
            return publishObjectDefinition(item.id);
        });

        try {
            const responses = await Promise.all(publishPromises);
            const hasErrorsResponse = responses.some(response => response === 0);
            let filteredResponses = responses;

            if (hasErrorsResponse) filteredResponses = responses.filter(response => response !== 0);

            setMessageHeaderModal(!hasErrorsResponse ? "Successfully published!" : "Published with errors");
            setStatusPublish(!hasErrorsResponse ? STATUS.FINISHED : STATUS.REJECTED);

            const newArrayItems = elements.map(element => {
                const elementId = (element as FlowElement<ObjectDefinitionNodeData>).data?.id || 0;

                if (filteredResponses.includes(elementId)) {
                    return {
                        ...element, data: {
                            ...element.data,
                            status: {
                                code: 0,
                                label: 'approved',
                                label_i18n: 'Approved'
                            }
                        }
                    }
                }
                return element;
            })

            dispatch({
                payload: {
                    newElements: newArrayItems,
                },
                type: TYPES.SET_ELEMENTS,
            });

        } catch (error) {
            setMessageHeaderModal("Confirm publishing");
            setStatusPublish(STATUS.REJECTED);
        }
    }

    const handleCheckboxChange = (itemId: number) => {
        if (selectedItems.some(item => item.id === itemId)) {
            setSelectedItems(selectedItems.filter(item => item.id !== itemId));
        } else {
            setSelectedItems([...selectedItems, { id: itemId }]);
        }
    };

    const renderStatusModal = (): TStatus => {
        switch (statusPublish) {
            case 3: return 'warning';
            case 1: return 'info';
            case 2: return 'success';
            default: return 'warning';
        }
    }

    return (
        <ClayModal className="lfr-object__object-view-modal-object-definitions" disableAutoClose={disableAutoClose} observer={observer} status={renderStatusModal()}>
            <ClayModal.Header>{messageHeaderModal}</ClayModal.Header>

            <ClayModal.Body>
                <div className="c-mb-sm-4">
                    <Text size={3}>The following Objects contain changes that will be published and may affect your production environment. Please check before confirming:</Text>
                </div>
                <div className="container-card">
                    {elementsFiltered.map(object => {
                        const { id, data } = object as FlowElement<ObjectDefinitionNodeData>;
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
                                                <Text size={3} weight="semi-bold">{data?.name}</Text>
                                            </div>
                                            {(!selectedItem?.status || !["approved", "rejected"].includes(selectedItem.status)) &&
                                                <ClayLabel displayType="info">
                                                    {Liferay.Language.get('draft')}
                                                </ClayLabel>
                                            }

                                            {selectedItem?.status === "rejected" &&
                                                <span className="rejected text-danger">
                                                    <ClayIcon symbol="exclamation-full" color="danger" />
                                                    <Text size={2}>{selectedItem?.message}</Text>
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
                </div>
            </ClayModal.Body>

            <ClayModal.Footer
                last={
                    statusPublish === STATUS.FINISHED || statusPublish === STATUS.REJECTED ?
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
                                    disabled={selectedItems.length === 0 || statusPublish === STATUS.LOADING}
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
