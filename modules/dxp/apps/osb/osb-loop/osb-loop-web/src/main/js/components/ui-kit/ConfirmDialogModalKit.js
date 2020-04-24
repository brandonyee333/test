import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';
import {noop} from 'lodash';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import {Modal} from '../modal';
import {modalTypes} from '../../actions/modals';

class ConfirmDialogModalKit extends Component {
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
			<Kit header="ConfirmDialogModal">
				<Modal
					hideModal={handleToggleModal_}
					modalIMap={
						fromJS(
							{
								modalProps: {
									message: 'Are you sure you want to delete this post?',
									onConfirm: noop
								},
								modalType: modalTypes.CONFIRM_DIALOG,
								open: open_
							}
						)
					}
				/>

				<ElementContainer>
					<Button onClick={handleToggleModal_} role="primary">{'Show'}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

ConfirmDialogModalKit.STATE = {
	open_: Config.value(false)
};

export default ConfirmDialogModalKit;