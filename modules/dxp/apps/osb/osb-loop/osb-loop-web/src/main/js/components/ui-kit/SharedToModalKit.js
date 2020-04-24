import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {modalTypes} from '../../actions/modals';
import {Modal} from '../modal';

const division = LoopAssets.getDivision();
const division2 = LoopAssets.getDivision(2);
const person = LoopAssets.getPerson();
const topic = LoopAssets.getTopic();

class SharedToModalKit extends Component {
	created() {
		bindAll(
			this,
			'handleDivisionsTab_',
			'handlePeopleTab_',
			'handleTopicsTab_',
			'toggleModal_'
		);
	}

	handleDivisionsTab_() {
		this.toggleModal_(division.entityClassNameId);
	}

	handlePeopleTab_() {
		this.toggleModal_(person.entityClassNameId);
	}

	handleTopicsTab_() {
		this.toggleModal_(topic.entityClassNameId);
	}

	toggleModal_(classNameId) {
		this.state.open_ = !this.state.open_;

		if (classNameId && this.state.open_) {
			this.state.activeClassNameId_ = classNameId;
		}
	}

	render() {
		const {activeClassNameId_, open_} = this.state;

		return (
			<Kit header="SharedToModal">
				<Provider store={mockStore()}>
					<Modal
						hideModal={this.toggleModal_}
						modalIMap={
							fromJS(
								{
									hideOnBlur: true,
									modalProps: {
										activeClassNameId: activeClassNameId_,
										entities: [
											[division, division2],
											[person],
											[topic]
										]
									},
									modalType: modalTypes.SHARED_TO,
									open: open_
								}
							)
						}
					/>
				</Provider>

				<ElementContainer>
					<ElementDisplay>
						<Button onClick={this.handleDivisionsTab_} role="primary">{'Click for Groups'}</Button>
					</ElementDisplay>

					<ElementDisplay>
						<Button onClick={this.handlePeopleTab_} role="primary">{'Click for People'}</Button>
					</ElementDisplay>

					<ElementDisplay>
						<Button onClick={this.handleTopicsTab_} role="primary">{'Click for Topics'}</Button>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

SharedToModalKit.STATE = {
	activeClassNameId_: Config.value(0),
	open_: Config.value(false)
};

export default SharedToModalKit;