import Component, {Config} from 'metal-jsx';
import {findIndex, get} from 'lodash';

import EntityDisplay from './EntityDisplay';
import LoopConstants from '../lib/loop-constants';
import Modal from './modal';
import Tabs from './Tabs';

const {classNameIds} = LoopConstants;

const MAP_LANG_KEYS = {
	[classNameIds.divisions]: Liferay.Language.get('groups'),
	[classNameIds.people]: Liferay.Language.get('people'),
	[classNameIds.topics]: Liferay.Language.get('topics')
};

class SharedToModal extends Component {
	created() {
		const {activeClassNameId, entities} = this.props;

		this.state.tabIndex_ = Math.max(
			findIndex(
				entities,
				entityArr => get(entityArr, '[0].entityClassNameId') === activeClassNameId
			),
			0
		);

		this.handleTabIndexChange_ = this.handleTabIndexChange_.bind(this);
	}

	handleTabIndexChange_(index) {
		this.state.tabIndex_ = index;
	}

	render() {
		const {entities, hideModal} = this.props;

		return (
			<div class="shared-to-modal-container">
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('shared-with')}
				</Modal.Header>

				<Modal.Body>
					<Tabs
						index={this.state.tabIndex_}
						onIndexChange={this.handleTabIndexChange_}
					>
						{
							entities.map(
								entityArr => {
									const entityClassNameId = get(entityArr, '[0].entityClassNameId');

									return (
										<Tabs.Content key={entityClassNameId} name={`${MAP_LANG_KEYS[entityClassNameId]} (${entityArr.length})`}>
											<ul>
												{
													entityArr.map(
														entity => (
															<li key={get(entity, 'entityClassPK')}>
																<EntityDisplay entity={entity} />
															</li>
														)
													)
												}
											</ul>
										</Tabs.Content>
									);
								}
							)
						}
					</Tabs>
				</Modal.Body>
			</div>
		);
	}
}

SharedToModal.PROPS = {
	activeClassNameId: Config.number(),
	entities: Config.array(),
	hideModal: Config.func()
};

SharedToModal.STATE = {
	tabIndex_: Config.value(0)
};

export default SharedToModal;