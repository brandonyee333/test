import {fromJS, Map} from 'immutable';
import Component from 'metal-jsx';

import {COMPONENT_MAP, default as DefaultModal, Modal} from '../index';
import mockStore from '../../../tests/mock-store';

describe(
	'Modal',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'should render',
			() => {
				component = new DefaultModal(
					{
						store: mockStore(Map().setIn(['modals'], Map()))
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render a specific modal component',
			() => {
				const BODY_TEXT = 'Test Body';

				const FOOTER_TEXT = 'Test Footer';

				const HEADER_TEXT = 'Test Header';

				class TestModal extends Component {
					render() {
						return (
							<div>
								<DefaultModal.Header>{HEADER_TEXT}</DefaultModal.Header>

								<DefaultModal.Body>{BODY_TEXT}</DefaultModal.Body>

								<DefaultModal.Footer>{FOOTER_TEXT}</DefaultModal.Footer>
							</div>
						);
					}
				}

				const modalType = 'TEST_MODAL';

				COMPONENT_MAP[modalType] = TestModal;

				component = new Modal(
					{
						modalIMap: fromJS(
							{
								modalProps: {},
								modalType,
								open: true
							}
						)
					}
				);

				expect(component.element.querySelector('.modal-header-container').innerHTML).toContain(HEADER_TEXT);
				expect(component.element.querySelector('.modal-body-container').innerHTML).toContain(BODY_TEXT);
				expect(component.element.querySelector('.modal-footer-container').innerHTML).toContain(FOOTER_TEXT);
			}
		);

		it(
			'should add a class to the body when open',
			() => {
				const modalIMap = fromJS(
					{
						modalProps: {},
						open: false
					}
				);

				component = new Modal(
					{
						modalIMap
					}
				);

				expect(document.body.classList.contains('modal-page-body-open')).toBe(false);

				component.props.modalIMap = modalIMap.set('open', true);

				jest.runAllTimers();

				expect(document.body.classList.contains('modal-page-body-open')).toBe(true);
			}
		);

		it(
			'should call `hideModal_` if overlay mask is clicked',
			() => {
				const modalIMap = fromJS(
					{
						hideOnBlur: true,
						modalProps: {},
						open: false
					}
				);

				component = new Modal(
					{
						modalIMap
					}
				);

				component.hideModal_ = jest.fn();

				const event = {
					target: component.refs.wrapper
				};

				component.handleOverlayClick_(event);

				expect(component.hideModal_).toHaveBeenCalled();
			}
		);

		it(
			'should call `hideModal` prop',
			() => {
				const modalIMap = fromJS(
					{
						modalProps: {},
						open: false
					}
				);

				component = new Modal(
					{
						hideModal: jest.fn(),
						modalIMap
					}
				);

				component.hideModal_();

				expect(component.props.hideModal).toHaveBeenCalled();
			}
		);
	}
);