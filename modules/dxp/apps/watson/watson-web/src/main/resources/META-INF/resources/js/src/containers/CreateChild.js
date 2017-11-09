import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import Button from '../components/Button';
import ChildForm from './forms/Child';
import Modal from '../components/Modal';
import Navigation from '../components/Navigation';

import {addChild, updateChildrenFormData} from '../actions/children';

import {updateDOMTitle} from '../lib/util';

class CreateChild extends JSXComponent {
	attached() {
		Router.router().on('beforeNavigate', this.handleBeforeLeave);
	}

	created() {
		bindAll(
			this,
			'handleBeforeLeave',
			'handleClearFormData',
			'handleClose',
			'handleLeave',
			'handleSubmit'
		);
	}

	detached() {
		Router.router().off('beforeNavigate', this.handleBeforeLeave);
	}

	handleBeforeLeave(data) {
		const {
			childFormData = {}
		} = this.props;

		const {
			dataSent,
			unlockNavigate
		} = this.state;

		if (!Object.is({}, childFormData) && !dataSent && !unlockNavigate) {
			this.setState(
				{
					navigateAwayPath: data.path,
					showLeaveModal: true
				}
			);

			if (data.event) {
				data.event.preventDefault();
			}

			throw new Error();
		}
	}

	handleClearFormData() {
		const {
			updateChildrenFormData
		} = this.props;

		updateChildrenFormData({}, 0);
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleLeave() {
		this.handleClearFormData();

		this.handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	handleSubmit() {
		const {props} = this;

		const {childFormData} = props;

		if (childFormData) {
			props.addChild(childFormData);

			this.state.dataSent = true;
		}
		else {
			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/create`);
		}
	}

	render() {
		const {
			action,
			childFormData,
			errors,
			loading,
			model,
			response
		} = this.props;

		const {
			dataSent,
			showLeaveModal
		} = this.state;

		if (dataSent && !loading && response) {
			if (response.get('status') === 'success') {
				const responseData = response.get('data') || new Map();

				const watsonChildId = responseData.get('watsonChildId');

				if (watsonChildId) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/${watsonChildId}/edit`);
				}
			}
			else if (errors) {
				this.state.dataSent = false;

				if (errors.get('errors')) {
					Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/create`);
				}
			}
		}

		const nav = [
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/children/create`,
				selected: !model,
				text: Liferay.Language.get('child')
			},
			{
				collapsible: false,
				href: `${WatsonConstants.urls.baseURL}/children`,
				selected: false,
				text: Liferay.Language.get('back')
			}
		];

		const modalFooter = [
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('leave')} onClick={this.handleLeave} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('stay')} onclick={this.handleClose} />
		];

		const cancelMethod = () => Router.router().navigate(WatsonConstants.urls.children);

		const storeData = new Map();

		return (
			<div class="page-container incidents-create hidden-print">
				<div class="navigation-sidebar">
					<Navigation entries={nav} />
				</div>

				{showLeaveModal &&
					<Modal
						body={Liferay.Language.get('you-have-unsaved-changes-that-will-be-lost-if-you-continue')}
						close={this.handleClose}
						footer={modalFooter}
						header={Liferay.Language.get('do-you-want-to-leave-this-page')}
					/>
				}

				<ChildForm
					action={action}
					cancelMethod={cancelMethod}
					formData={childFormData}
					headerStringLeft={Liferay.Language.get('create-child')}
					headerStringRight={Liferay.Language.get('unsaved')}
					storeData={storeData}
					submitMethod={this.handleSubmit}
				/>
			</div>
		);
	}

	rendered() {
		updateDOMTitle(Liferay.Language.get('create-child'));
	}
}

CreateChild.PROPS = {
	childFormData: Config.object().value({}),
	errors: Config.value(new Map()),
	loading: Config.bool().value(false),
	response: Config.object()
};

CreateChild.STATE = {
	dataSent: Config.bool().value(false),
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

CreateChild.SYNC_UPDATES = true;

function mapDispatchToProps(dispatch) {
	return {
		addChild: data => {
			dispatch(
				addChild(data)
			);
		},
		updateChildrenFormData: data => {
			dispatch(
				updateChildrenFormData(data)
			);
		}
	};
}

function mapStateToProps(state, props) {
	const {action = 'create', model} = props.router.params;
	const childFormData = state.getIn(['children', 'formData', 0]) || {};

	return {
		action,
		childFormData,
		errors: state.getIn(
			[
				'children',
				'errors'
			]
		),
		loading: state.getIn(
			[
				'children',
				'loading'
			]
		),
		model,
		response: state.getIn(
			[
				'children',
				'response'
			]
		)
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(CreateChild);