import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';

import AboutCard from '../../components/AboutCard';
import BaseLayout from '../BaseLayout';
import ContactCard from '../../components/ContactCard';
import ElsewhereCard from '../../components/ElsewhereCard';
import EntityPillList from '../../components/EntityPillList';
import ListCard from '../../components/ListCard';
import PersonNetworkCard from '../../components/PersonNetworkCard';
import {addAlert, alertTypes} from '../../actions/alerts';
import {fetchExpertise} from '../../actions/people';
import {NETWORK} from '../../lib/router-constants';
import {getRel, personSelector} from '../../lib/selectors';

class AboutContent extends Component {
	created() {
		this._request = this.fetchExpertise_();
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	fetchExpertise_() {
		const {fetchExpertise, id} = this.props;

		return fetchExpertise(id).catch(
			({message}) => {
				if (message) {
					addAlert(
						{
							alertType: alertTypes.ERROR,
							message
						}
					);
				}
			}
		);
	}

	getWorkCardItems_(person) {
		const {
			employmentTypeLabel,
			hireDate,
			jobTitle,
			languages,
			locationName
		} = person;

		const {expertiseIList} = this.props;

		return [
			{
				content: jobTitle,
				label: Liferay.Language.get('job-title')
			},
			{
				content: locationName,
				label: Liferay.Language.get('location')
			},
			{
				content: hireDate,
				label: Liferay.Language.get('start-date')
			},
			{
				content: employmentTypeLabel,
				label: Liferay.Language.get('employment-type')
			},
			{
				content: expertiseIList.isEmpty() ? Liferay.Language.get('none') : <EntityPillList entities={expertiseIList.toJS()} />,
				label: Liferay.Language.get('expertise')
			},
			{
				content: languages && languages.length ? languages : Liferay.Language.get('none'),
				label: Liferay.Language.get('languages')
			}
		];
	}

	render() {
		const {
			expertiseIList,
			id,
			loading,
			personIMap
		} = this.props;

		const person = personIMap.toJS();

		const {
			age,
			birthday,
			descriptionMarkdownHTML,
			displayURL,
			male
		} = person;

		return (
			<BaseLayout.NineColumn>
				<BaseLayout.Row>
					<BaseLayout.SixColumn>
						<ListCard items={this.getWorkCardItems_(person)} loading={loading && expertiseIList.isEmpty()} title={Liferay.Language.get('work')} />

						<ContactCard id={id} showLabels={true} />

						<ElsewhereCard id={id} showLabels={true} />
					</BaseLayout.SixColumn>

					<BaseLayout.SixColumn>
						<PersonNetworkCard
							displayCount={8}
							displaySize="medium"
							id={id}
							seeMoreLink={`${displayURL}/${NETWORK}`}
						/>

						<AboutCard
							details={{
								[Liferay.Language.get('age')]: age,
								[Liferay.Language.get('birthday')]: birthday,
								[Liferay.Language.get('gender')]: male ? Liferay.Language.get('male') : Liferay.Language.get('female')
							}}
							message={descriptionMarkdownHTML}
						/>
					</BaseLayout.SixColumn>
				</BaseLayout.Row>
			</BaseLayout.NineColumn>
		);
	}
}

const STORE = {
	expertiseIList: Config.instanceOf(List),
	fetchExpertise: Config.func(),
	loading: Config.bool(),
	personIMap: Config.instanceOf(Map)
};

AboutContent.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, ownProps) => {
		const expertiseIMap = state.getIn(['people', ownProps.id, 'expertise'], Map());

		return {
			...personSelector(state, ownProps),
			expertiseIList: getRel('topics', state, expertiseIMap.get('data', OrderedSet())),
			loading: expertiseIMap.get('loading', true)
		};
	},
	{fetchExpertise}
)(AboutContent);