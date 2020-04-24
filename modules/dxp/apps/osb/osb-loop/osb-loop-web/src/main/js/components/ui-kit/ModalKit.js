import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {fromJS} from 'immutable';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import DefaultModal, {COMPONENT_MAP, Modal} from '../modal';

const BUTTON_TEXT = 'Show';

const MODAL_TEXT = 'Ipsum earum debitis tenetur earum eveniet. Repudiandae rerum nobis quia blanditiis optio. Error soluta cumque ab eaque perspiciatis! Placeat ipsum cupiditate pariatur illo tenetur. Hic magni minima ratione aspernatur sapiente reiciendis! Placeat praesentium facilis ab pariatur alias dignissimos. Praesentium nam temporibus ullam esse eius sed. Alias vitae architecto minus accusamus!\n';

const LONG_MODAL_TEXT = MODAL_TEXT.repeat(15);

const bodyStyles = {
	padding: '24px'
};

class TestModal extends Component {
	render() {
		const {hideModal, scrollable, text} = this.props;

		return (
			<div class="test-modal-container">
				<DefaultModal.Header onClose={hideModal}>
					{'Modal'}
				</DefaultModal.Header>

				<DefaultModal.Body scrollable={scrollable} style={bodyStyles}>
					{text}
				</DefaultModal.Body>

				<DefaultModal.Footer>
					<Button role="primary">{'Button'}</Button>
				</DefaultModal.Footer>
			</div>
		);
	}
}

TestModal.PROPS = {
	scrollable: Config.bool().value(false),
	text: Config.string()
};

const modalType = 'TEST_MODAL';

COMPONENT_MAP[modalType] = TestModal;

const modalStoreStates = {
	closed: fromJS(
		{
			hideOnBlur: true,
			modalProps: {},
			modalType: null,
			open: false
		}
	),
	long: fromJS(
		{
			hideOnBlur: true,
			modalProps: {
				text: LONG_MODAL_TEXT
			},
			modalType,
			open: true
		}
	),
	normal: fromJS(
		{
			hideOnBlur: true,
			modalProps: {
				text: MODAL_TEXT
			},
			modalType,
			open: true
		}
	),
	scrollable: fromJS(
		{
			hideOnBlur: true,
			modalProps: {
				scrollable: true,
				text: LONG_MODAL_TEXT
			},
			modalType,
			open: true
		}
	)
};

class ModalKit extends Component {
	created() {
		bindAll(
			this,
			'hideModal_',
			'showLongModal_',
			'showNormalModal_',
			'showScrollableModal_'
		);
	}

	hideModal_() {
		this.state.storeState_ = 'closed';
	}

	showLongModal_() {
		this.state.storeState_ = 'long';
	}

	showNormalModal_() {
		this.state.storeState_ = 'normal';
	}

	showScrollableModal_() {
		this.state.storeState_ = 'scrollable';
	}

	render() {
		const modalIMap = modalStoreStates[this.state.storeState_];

		return (
			<Kit header="Modal">
				<Modal hideModal={this.hideModal_} modalIMap={modalIMap} />

				<ElementContainer header="Normal">
					<Button onClick={this.showNormalModal_} role="primary">{BUTTON_TEXT}</Button>
				</ElementContainer>

				<ElementContainer header="Long Content">
					<Button onClick={this.showLongModal_} role="primary">{BUTTON_TEXT}</Button>
				</ElementContainer>

				<ElementContainer header="Scrollable">
					<Button onClick={this.showScrollableModal_} role="primary">{BUTTON_TEXT}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

ModalKit.STATE = {
	storeState_: Config.value('closed')
};

export default ModalKit;