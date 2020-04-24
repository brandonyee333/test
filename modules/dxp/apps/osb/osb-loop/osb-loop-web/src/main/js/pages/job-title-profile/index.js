import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AboutCard from '../../components/AboutCard';
import BaseLayout from '../BaseLayout';
import ErrorWrapper from '../../components/ErrorWrapper';
import InactiveCard from '../../components/InactiveCard';
import LoopConstants from '../../lib/loop-constants';
import Profile from '../Profile';
import {ABOUT} from '../../lib/router-constants';
import {addAlert, alertTypes} from '../../actions/alerts';
import {fetchJobTitle} from '../../actions/job-titles';
import {getId} from '../../lib/router-util';
import {setPageTitle} from '../../actions/toolbar';

const {classNameIds} = LoopConstants;

const TABS_MAP = {
	[ABOUT]: (id, jobTitleDataIMap) => {
		const {description, name, status} = jobTitleDataIMap.toJS();

		return [
			<BaseLayout.NineColumn key={id}>
				{status === LoopConstants.status.inactive &&
					<InactiveCard name={name} />
				}

				<AboutCard key="aboutCard" message={description ? description : Liferay.Language.get('no-description')} />
			</BaseLayout.NineColumn>
		];
	}
};

class JobTitleProfile extends Component {
	fetchJobTitleData_() {
		const {
			addAlert,
			fetchJobTitle,
			id,
			setPageTitle
		} = this.props;

		fetchJobTitle(id).then(
			({data}) => {
				setPageTitle(data.name);

				this.state.error_ = false;
			}
		).catch(
			({message}) => {
				this.setState(
					{
						error_: true
					},
					() => addAlert(
						{
							alertType: alertTypes.ERROR,
							message
						}
					)
				);
			}
		);
	}

	syncRouter(newVal, prevVal) {
		if (!prevVal || prevVal.params.entityId !== newVal.params.entityId) {
			this.fetchJobTitleData_();
		}
	}

	render() {
		const {
			props: {
				id,
				jobTitleDataIMap,
				loading,
				router
			},
			state: {error_}
		} = this;

		const {subNavId = ABOUT} = router.params;

		return (
			<ErrorWrapper error={error_} loading={loading && jobTitleDataIMap.isEmpty()}>
				<Profile classNameId={classNameIds.jobTitles} id={id}>
					<BaseLayout
						content={TABS_MAP[subNavId](id, jobTitleDataIMap)}
						navItemsIList={
							List(
								[
									{
										href: `${jobTitleDataIMap.get('displayURL')}/${ABOUT}`,
										label: Liferay.Language.get('about'),
										selected: subNavId === ABOUT
									}
								]
							)
						}
					/>
				</Profile>
			</ErrorWrapper>
		);
	}
}

const STORE = {
	fetchJobTitle: Config.func(),
	id: Config.oneOfType([Config.number(), Config.string()]),
	jobTitleDataIMap: Config.instanceOf(Map),
	loading: Config.bool(),
	setPageTitle: Config.func()
};

JobTitleProfile.PROPS = {
	...STORE,
	router: Config.object()
};

JobTitleProfile.STATE = {
	error_: Config.bool().value(false)
};

export default connect(
	(state, {router}) => {
		const jobTitlesIMap = state.get('jobTitles', Map());

		const id = getId(jobTitlesIMap, router.params.entityId);

		return {
			id,
			jobTitleDataIMap: jobTitlesIMap.getIn([id, 'data'], Map()),
			loading: jobTitlesIMap.getIn([id, 'loading'], true)
		};
	},
	{
		addAlert,
		fetchJobTitle,
		setPageTitle
	}
)(JobTitleProfile);