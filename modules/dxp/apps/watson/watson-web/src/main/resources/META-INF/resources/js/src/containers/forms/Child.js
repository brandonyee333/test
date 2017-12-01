import {bindAll, isEmpty} from 'lodash';
import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import {convertMapToObject, deepCompareIsEqual, getModifiedMoment, updateDOMTitle} from '../../lib/util';
import Form from '../../components/Form';
import Modal from '../../components/Modal';

import {
	destroyChildren,
	editChildren,
	requestChildrenTranslation,
	updateChildren,
	updateChildrenDataManually,
	updateChildrenFormData
} from '../../actions/children';

class ChildForm extends JSXComponent {
	attached() {
		const {props} = this;

		const {watsonChildId} = props;

		if (watsonChildId) {
			props.editChildren(watsonChildId);
		}

		Router.router().on('beforeNavigate', this.handleBeforeLeave);

		this.handleClearFormData();
	}

	created() {
		bindAll(
			this,
			'handleBeforeLeave',
			'handleCancel',
			'handleClearFormData',
			'handleClose',
			'handleDelete',
			'handleLeave',
			'handleTranslationRequest',
			'handleUpdateFormData'
		);
	}

	detached() {
		const {
			action,
			response,
			updateChildrenDataManually
		} = this.props;

		if (action !== 'create' && response && response.get('status') === 'success' && response.get('message')) {
			updateChildrenDataManually(
				{
					response: {
						message: null
					}
				}
			);
		}

		Router.router().off('beforeNavigate', this.handleBeforeLeave);
	}

	getConfig() {
		return [
			'id',
			'nameWatsonListTypeRels',
			'typeWatsonListTypeId',
			'sexWatsonListTypeId',
			'countryWatsonListTypeId',
			'ethnicityWatsonListTypeId',
			'birthCountryId',
			'parentNameWatsonListTypeRels',
			'guardianNameWatsonListTypeRels',
			'countryIDWatsonListTypeRels',
			'dateAccepted',
			'dateDischarged',
			'dischargeWatsonListTypeId',
			'dateFollowUp',
			'sourceWatsonListTypeId',
			'sourceSubtypeWatsonListTypeId',
			'source',
			'activitiesInvolvedWatsonListTypeRels',
			'vocationalTrainingWatsonListTypeRels',
			'watsonRelationships'
		];
	};

	handleBeforeLeave(data) {
		const {
			formData = {},
			storeData,
			watsonChildId
		} = this.props;

		const {unlockNavigate} = this.state;

		if (watsonChildId > 0) {
			if (!isEmpty(formData) && (!isEmpty(storeData))) {
				const originalData = convertMapToObject(storeData);

				if (!unlockNavigate && !deepCompareIsEqual(formData, originalData)) {
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
		}
	}

	handleCancel() {
		this.handleClearFormData();

		Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/index`);
	}

	handleClearFormData() {
		this.handleUpdateFormData({});
	}

	handleClose() {
		this.setState({showLeaveModal: false});
	}

	handleDelete() {
		const {watsonChildId} = this.props;

		if (watsonChildId) {
			this.props.destroyChildren(watsonChildId);

			Router.router().navigate(`${WatsonConstants.urls.baseURL}/children/index`);
		}
	}

	handleLeave() {
		this.handleClearFormData();

		this.handleClose();

		this.setState({unlockNavigate: true});

		Router.router().navigate(this.state.navigateAwayPath);
	}

	handleTranslationRequest() {
		const {props} = this;

		const {model, requestChildrenTranslation, watsonChildId} = props;

		const translationURL = `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/translate`;

		const translationRequestData = {
			model,
			translationURL,
			watsonPrimaryKey: watsonChildId
		};

		requestChildrenTranslation(translationRequestData);
	}

	handleUpdateFormData(formData) {
		const {
			updateChildrenFormData,
			watsonChildId
		} = this.props;

		updateChildrenFormData(formData, watsonChildId);
	}

	render() {
		const {props} = this;

		const {
			action,
			button,
			buttonLabel,
			cancelMethod,
			disabled,
			errors,
			formData,
			loading,
			model,
			response,
			storeData = props.data,
			submitMethod = props.updateChildren,
			watsonChildId
		} = props;

		let {
			headerStringLeft = Liferay.Language.get('create-child'),
			headerStringRight
		} = props;

		const {
			showLeaveModal
		} = this.state;

		let deleteMethod;
		let requestTranslationMethod;
		let translateHref;

		if (action === 'edit') {
			deleteMethod = disabled ? undefined : this.handleDelete;

			headerStringLeft = storeData.get('name') || Liferay.Language.get('edit-child');
			headerStringRight = getModifiedMoment(storeData.get('modifiedUserName'), storeData.get('modifiedDateTimeStamp'));

			requestTranslationMethod = this.handleTranslationRequest;

			translateHref = (disabled || !WatsonConstants.currentUser.translatorRole) ? undefined : `${WatsonConstants.urls.baseURL}/children/${watsonChildId}/translate`;
		}

		const modalFooter = [
			<Button className="modal-button" key="btn-action" label={Liferay.Language.get('watson-leave')} onClick={this.handleLeave} />,
			<Button className="modal-button" key="btn-cancel" label={Liferay.Language.get('stay')} onclick={this.handleClose} />
		];

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={headerStringLeft} headerStringRight={headerStringRight} />
				</div>

				{showLeaveModal &&
					<Modal body={Liferay.Language.get('you-have-unsaved-changes-that-will-be-lost-if-you-continue')} close={this.handleClose} footer={modalFooter} header={Liferay.Language.get('do-you-want-to-leave-this-page')} />
				}

				<div class="content">
					<Form
						action={action}
						button={button}
						buttonLabel={buttonLabel}
						cancelMethod={cancelMethod}
						deleteMethod={deleteMethod}
						disabled={disabled}
						errors={errors}
						fieldConfig={WatsonConstants.inputConfig.children.inputs}
						formConfig={this.getConfig()}
						formData={formData}
						loading={loading}
						model={model}
						requestTranslationMethod={requestTranslationMethod}
						response={response}
						storeData={storeData}
						submitMethod={submitMethod}
						translateHref={translateHref}
						updateFormData={this.handleUpdateFormData}
						watsonChildId={watsonChildId}
						watsonPrimaryKey={watsonChildId}
					/>
				</div>
			</div>
		);
	}

	rendered() {
		const {storeData} = this.props;

		const childName = sub(Liferay.Language.get('child-x'), storeData.get('id') || '');

		updateDOMTitle(childName);
	}
}

ChildForm.PROPS = {
	action: Config.string().value(''),
	button: Config.value(null),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	formData: Config.object().value({}),
	loading: Config.bool().value(false),
	model: Config.string().value('children'),
	response: Config.object(),
	storeData: Config.value(null),
	watsonChildId: Config.value('')
};

ChildForm.STATE = {
	navigateAwayPath: Config.value(null),
	showLeaveModal: Config.bool().value(false),
	unlockNavigate: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {watsonChildId = 0} = props;

	const errors = state.getIn(['children', 'errors']) || new Map();

	return {
		errors,
		loading: state.getIn(
			[
				'children',
				'loading'
			]
		),
		response: state.getIn(
			[
				'children',
				'response'
			]
		),
		watsonChildId
	};
}

function mapDispatchToProps(dispatch) {
	return {
		destroyChildren: watsonChildId => {
			dispatch(
				destroyChildren(watsonChildId)
			);
		},
		editChildren: watsonChildId => {
			dispatch(
				editChildren(watsonChildId)
			);
		},
		requestChildrenTranslation: data => {
			dispatch(
				requestChildrenTranslation(data)
			);
		},
		updateChildren: data => {
			dispatch(
				updateChildren(data)
			);
		},
		updateChildrenDataManually: data => {
			dispatch(
				updateChildrenDataManually(data)
			);
		},
		updateChildrenFormData: (formData, watsonChildId = 0) => {
			const data = {
				formData,
				watsonChildId
			};

			dispatch(
				updateChildrenFormData(data)
			);
		}
	};
}

export default connect(mapStateToProps, mapDispatchToProps)(ChildForm);