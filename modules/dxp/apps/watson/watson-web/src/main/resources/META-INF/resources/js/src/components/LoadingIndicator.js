import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';

import Spinner from './Spinner';

class LoadingIndicator extends JSXComponent {
	_handleOnClick() {
		window.location.href = WatsonConstants.urls.baseURL;
	}

	render() {
		const {hideLoadingOverlay, loading, requestFailure, requestForbidden} = this.props;

		return (
			<div class="loading-ui-outer">
				{((loading && !hideLoadingOverlay) || requestFailure || requestForbidden) &&
					this.renderLoader(loading, requestFailure, requestForbidden)
				}
			</div>
		);
	}

	renderLoader(loading, requestFailure, requestForbidden) {
		return (
			<div class="loading-ui-inner">
				<Transition name="transition">
					<div class="loading-overlay" />
				</Transition>

				{loading &&
					<Transition name="transition">
						<div class="loading-spinner">
							<Spinner size="medium" />
						</div>
					</Transition>
				}

				{requestFailure &&
					<a class="request-failure-error" onClick={this._handleOnClick}>
						<span class="failure-text">
							{Liferay.Language.get('sorry-we-encountered-an-error-please-try-again')}
						</span>
						<span class="retry">
							{Liferay.Language.get('click-to-return')}
						</span>
					</a>
				}

				{requestForbidden &&
					<a class="request-forbidden-error" onClick={this._handleOnClick}>
						<span class="failure-text">
							{Liferay.Language.get('you-do-not-have-the-required-permissions-to-access-this-content')}
						</span>
						<span class="retry">
							{Liferay.Language.get('click-to-return')}
						</span>
					</a>
				}
			</div>
		);
	}
}

LoadingIndicator.PROPS = {
	hideLoadingOverlay: Config.bool().value(false),
	loading: Config.bool().value(false),
	requestFailure: Config.bool().value(false),
	requestForbidden: Config.bool().value(false)
};

function mapStateToProps(state, props) {
	const {loading: forwardedLoading} = props;

	const activitiesLoading = state.getIn(['activities', 'loading']);
	const addressesLoading = state.getIn(['addresses', 'loading']);
	const authenticationLoading = state.getIn(['authentication', 'loading']);
	const caseworkActivitiesLoading = state.getIn(['casework_activities', 'loading']);
	const childrenLoading = state.getIn(['children', 'loading']);
	const counselingReportsLoading = state.getIn(['counseling_reports', 'loading']);
	const documentsLoading = state.getIn(['documents', 'loading']);
	const historiesLoading = state.getIn(['histories', 'loading']);
	const illnessesLoading = state.getIn(['illnesses', 'loading']);
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const legalsLoading = state.getIn(['legals', 'loading']);
	const peopleLoading = state.getIn(['people', 'loading']);
	const physicalExamsLoading = state.getIn(['physical_exams', 'loading']);
	const progressReportsLoading = state.getIn(['progress_reports', 'loading']);
	const relationshipsLoading = state.getIn(['relationships', 'loading']);
	const resourcesLoading = state.getIn(['resources', 'loading']);
	const vehiclesLoading = state.getIn(['vehicles', 'loading']);

	const loading = activitiesLoading || addressesLoading || authenticationLoading || caseworkActivitiesLoading || childrenLoading || counselingReportsLoading || documentsLoading || forwardedLoading || historiesLoading || illnessesLoading || incidentsLoading || legalsLoading || peopleLoading || physicalExamsLoading || progressReportsLoading || relationshipsLoading || resourcesLoading || vehiclesLoading;

	const activitiesRequestFailure = state.getIn(['activities', 'response', 'failure']);
	const addressesRequestFailure = state.getIn(['addresses', 'response', 'failure']);
	const authenticationRequestFailure = state.getIn(['authentication', 'response', 'failure']);
	const caseworkActivitiesFailure = state.getIn(['casework_activities', 'response', 'failure']);
	const childrenRequestFailure = state.getIn(['children', 'response', 'failure']);
	const counselingReportsFailure = state.getIn(['counseling_reports', 'response', 'failure']);
	const documentsRequestFailure = state.getIn(['documents', 'response', 'failure']);
	const historiesRequestFailure = state.getIn(['histories', 'response', 'failure']);
	const illnessesRequestFailure = state.getIn(['illnesses', 'response', 'failure']);
	const incidentsRequestFailure = state.getIn(['incidents', 'response', 'failure']);
	const legalsRequestFailure = state.getIn(['legals', 'response', 'failure']);
	const listTypesRequestFailure = state.getIn(['list_types', 'response', 'failure']);
	const peopleRequestFailure = state.getIn(['people', 'response', 'failure']);
	const physicalExamsRequestFailure = state.getIn(['physical_exams', 'response', 'failure']);
	const progressReportsRequestFailure = state.getIn(['progress_reports', 'response', 'failure']);
	const relationshipsRequestFailure = state.getIn(['relationships', 'response', 'failure']);
	const resourcesRequestFailure = state.getIn(['resources', 'response', 'failure']);
	const vehiclesRequestFailure = state.getIn(['vehicles', 'response', 'failure']);

	const requestFailure = activitiesRequestFailure || addressesRequestFailure || authenticationRequestFailure || caseworkActivitiesFailure || childrenRequestFailure || counselingReportsFailure || documentsRequestFailure || historiesRequestFailure || illnessesRequestFailure || incidentsRequestFailure || legalsRequestFailure || listTypesRequestFailure || peopleRequestFailure || physicalExamsRequestFailure || progressReportsRequestFailure || relationshipsRequestFailure || resourcesRequestFailure || vehiclesRequestFailure;

	const activitiesRequestForbidden = state.getIn(['activities', 'response', 'forbidden']);
	const addressesRequestForbidden = state.getIn(['addresses', 'response', 'forbidden']);
	const caseworkActivitiesRequestForbidden = state.getIn(['casework_activities', 'response', 'forbidden']);
	const childrenRequestForbidden = state.getIn(['children', 'response', 'forbidden']);
	const counselingReportsRequestForbidden = state.getIn(['counseling_reports', 'response', 'forbidden']);
	const documentsRequestForbidden = state.getIn(['documents', 'response', 'forbidden']);
	const historiesRequestForbidden = state.getIn(['histories', 'response', 'forbidden']);
	const illnessesRequestForbidden = state.getIn(['illnesses', 'response', 'forbidden']);
	const incidentsRequestForbidden = state.getIn(['incidents', 'response', 'forbidden']);
	const listTypesRequestForbidden = state.getIn(['list_types', 'response', 'forbidden']);
	const peopleRequestForbidden = state.getIn(['people', 'response', 'forbidden']);
	const physicalExamsRequestForbidden = state.getIn(['physical_exams', 'response', 'forbidden']);
	const progressReportsRequestForbidden = state.getIn(['progress_reports', 'response', 'forbidden']);
	const relationshipsRequestForbidden = state.getIn(['relationships', 'response', 'forbidden']);
	const resourcesRequestForbidden = state.getIn(['resources', 'response', 'forbidden']);
	const vehiclesRequestForbidden = state.getIn(['vehicles', 'response', 'forbidden']);

	const requestForbidden = activitiesRequestForbidden || addressesRequestForbidden || caseworkActivitiesRequestForbidden || childrenRequestForbidden || counselingReportsRequestForbidden || documentsRequestForbidden || historiesRequestForbidden || illnessesRequestForbidden || incidentsRequestForbidden || listTypesRequestForbidden || peopleRequestForbidden || physicalExamsRequestForbidden || progressReportsRequestForbidden || relationshipsRequestForbidden || resourcesRequestForbidden || vehiclesRequestForbidden;

	const hideLoadingOverlay = state.getIn(['display', 'hideLoadingOverlay']);

	return {
		hideLoadingOverlay,
		loading,
		requestFailure,
		requestForbidden
	};
}

export default connect(mapStateToProps)(LoadingIndicator);