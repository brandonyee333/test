import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import mockStore from '../../tests/mock-store';
import {modalTypes} from '../../actions/modals';
import {Modal} from '../modal';

class CreatePersonModalKit extends Component {
	created() {
		this.handleToggleModal_ = this.handleToggleModal_.bind(this);
	}

	handleToggleModal_() {
		this.state.open_ = !this.state.open_;
	}

	render() {
		const {handleToggleModal_} = this;

		const {open_} = this.state;

		return (
			<Kit header="CreatePersonModal">
				<Provider store={mockStore()}>
					<Modal
						hideModal={handleToggleModal_}
						modalIMap={
							fromJS(
								{
									modalProps: {},
									modalType: modalTypes.CREATE_PERSON,
									open: open_
								}
							)
						}
					/>
				</Provider>

				<ElementContainer>
					<Button onClick={handleToggleModal_} role="primary">{'Show'}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

CreatePersonModalKit.STATE = {
	open_: Config.value(false)
};

export default CreatePersonModalKit;