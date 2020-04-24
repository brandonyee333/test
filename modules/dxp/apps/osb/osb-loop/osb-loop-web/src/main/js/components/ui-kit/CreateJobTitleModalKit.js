import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import mockStore from '../../tests/mock-store';
import {modalTypes} from '../../actions/modals';
import {Modal} from '../modal';

class CreateJobTitleModalKit extends Component {
	created() {
		this.handleToggleModal_ = this.handleToggleModal_.bind(this);
	}

	handleToggleModal_() {
		this.state.open_ = !this.state.open_;
	}

	render() {
		return (
			<Kit header="CreateJobTitleModal">
				<Provider store={mockStore()}>
					<Modal
						hideModal={this.handleToggleModal_}
						modalIMap={
							fromJS(
								{
									modalProps: {},
									modalType: modalTypes.CREATE_JOB_TITLE,
									open: this.state.open_
								}
							)
						}
					/>
				</Provider>

				<ElementContainer>
					<Button onClick={this.handleToggleModal_} role="primary">{'Show'}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

CreateJobTitleModalKit.STATE = {
	open_: Config.value(false)
};

export default CreateJobTitleModalKit;