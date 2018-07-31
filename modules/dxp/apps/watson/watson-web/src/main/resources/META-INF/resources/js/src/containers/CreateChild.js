import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Router from 'metal-router';

import Button from '../components/Button';
import ChildForm from './forms/Child';
import Modal from '../components/Modal';
import Navigation from '../components/Navigation';

import {addChildren, updateChildrenFormData} from '../actions/children';

import {updatePageTitle} from '../actions/display';

class CreateChild extends JSXComponent {
	attached() {
		Router.router().on('beforeNavigate', this._handleBeforeLeave);
	}

	created() {
		bindAll(
			this,
			'_handleBeforeLeave',
			'_handleClearFormData',
			'_handleClose',
			'_handleLeave',
			'_handleSubmit'
		);
	}

	detached() {
		Router.router().off('beforeNavigate', this._handleBeforeLeave);
	}

	_handleBeforeLeave(data) {
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

	_handleClearFormData() {
		const {
			updateChildrenFormData
		} = this.props;

		updateChildrenFormData({}, 0);
	}

	_handleClose() {
		this.setState({showLeaveModal: false});
	}

	_handleLeave() {
		this._handleClearFormData();

		this._handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	_handleSubmit() {
		const {props} = this;

		const {childFormData} = props;

		if (childFormData) {
			delete childFormData.watsonChildId;

			props.addChildren(childFormData);

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
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('leave')} onClick={this._handleLeave} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('stay')} onclick={this._handleClose} />
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
						close={this._handleClose}
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
					submitMethod={this._handleSubmit}
				/>
			</div>
		);
	}

	rendered(firstRender) {
		if (firstRender) {
			this.props.updatePageTitle(Liferay.Language.get('create-child'));
		}
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
		addChildren: data => {
			dispatch(
				addChildren(data)
			);
		},
		updateChildrenFormData: data => {
			dispatch(
				updateChildrenFormData(data)
			);
		},
		updatePageTitle: data => {
			dispatch(
				updatePageTitle(data)
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