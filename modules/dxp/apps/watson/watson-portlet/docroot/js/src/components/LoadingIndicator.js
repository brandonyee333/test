import {connect} from 'metal-redux';
import JSXComponent, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';

import Spinner from './Spinner';

class LoadingIndicator extends JSXComponent {
	handleOnClick() {
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
					<a class="request-failure-error" onClick={this.handleOnClick}>
						<span class="failure-text">
							{Liferay.Language.get('sorry-we-encountered-an-error-please-try-again')}
						</span>
						<span class="retry">
							{Liferay.Language.get('click-to-return')}
						</span>
					</a>
				}

				{requestForbidden &&
					<a class="request-forbidden-error" onClick={this.handleOnClick}>
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

function mapStateToProps(state) {
	const activitiesLoading = state.getIn(['activities', 'loading']);
	const addressesLoading = state.getIn(['addresses', 'loading']);
	const historiesLoading = state.getIn(['histories', 'loading']);
	const incidentsLoading = state.getIn(['incidents', 'loading']);
	const peopleLoading = state.getIn(['people', 'loading']);
	const relationshipsLoading = state.getIn(['relationships', 'loading']);
	const resourcesLoading = state.getIn(['resources', 'loading']);
	const vehiclesLoading = state.getIn(['vehicles', 'loading']);

	const loading = activitiesLoading || addressesLoading || historiesLoading || incidentsLoading || peopleLoading || relationshipsLoading || resourcesLoading || vehiclesLoading;

	const activitiesRequestFailure = state.getIn(['activities', 'response', 'failure']);
	const addressesRequestFailure = state.getIn(['addresses', 'response', 'failure']);
	const historiesRequestFailure = state.getIn(['histories', 'response', 'failure']);
	const incidentsRequestFailure = state.getIn(['incidents', 'response', 'failure']);
	const listTypesRequestFailure = state.getIn(['list_types', 'response', 'failure']);
	const peopleRequestFailure = state.getIn(['people', 'response', 'failure']);
	const relationshipsRequestFailure = state.getIn(['relationships', 'response', 'failure']);
	const resourcesRequestFailure = state.getIn(['resources', 'response', 'failure']);
	const vehiclesRequestFailure = state.getIn(['vehicles', 'response', 'failure']);

	const requestFailure = activitiesRequestFailure || addressesRequestFailure || historiesRequestFailure || incidentsRequestFailure || listTypesRequestFailure || peopleRequestFailure || relationshipsRequestFailure || resourcesRequestFailure || vehiclesRequestFailure;

	const activitiesRequestForbidden = state.getIn(['activities', 'response', 'forbidden']);
	const addressesRequestForbidden = state.getIn(['addresses', 'response', 'forbidden']);
	const historiesRequestForbidden = state.getIn(['histories', 'response', 'forbidden']);
	const incidentsRequestForbidden = state.getIn(['incidents', 'response', 'forbidden']);
	const listTypesRequestForbidden = state.getIn(['list_types', 'response', 'forbidden']);
	const peopleRequestForbidden = state.getIn(['people', 'response', 'forbidden']);
	const relationshipsRequestForbidden = state.getIn(['relationships', 'response', 'forbidden']);
	const resourcesRequestForbidden = state.getIn(['resources', 'response', 'forbidden']);
	const vehiclesRequestForbidden = state.getIn(['vehicles', 'response', 'forbidden']);

	const requestForbidden = activitiesRequestForbidden || addressesRequestForbidden || historiesRequestForbidden || incidentsRequestForbidden || listTypesRequestForbidden || peopleRequestForbidden || relationshipsRequestForbidden || resourcesRequestForbidden || vehiclesRequestForbidden;

	const hideLoadingOverlay = state.getIn(['display', 'hideLoadingOverlay']);

	return {
		hideLoadingOverlay,
		loading,
		requestFailure,
		requestForbidden
	};
}

export default connect(mapStateToProps)(LoadingIndicator);