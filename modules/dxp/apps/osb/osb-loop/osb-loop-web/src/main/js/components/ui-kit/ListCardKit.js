import Component, {Config} from 'metal-jsx';
import {range} from 'lodash';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import EntityPillList from '../EntityPillList';
import Kit from './Kit';
import ListCard from '../ListCard';
import LoopAssets from '../../tests/loop-assets';

const topics = range(5).map(item => LoopAssets.getTopic(item));

topics.push(LoopAssets.getTopic('ReallyLongNameThatShouldWrap'));

const contactInfo = [
	{
		content: 'test.test@liferay.com',
		icon: 'mail',
		label: 'Mail',
		url: 'mailto:test.test@liferay.com'
	},
	{
		content: 'test.test@gmail.com',
		icon: 'mail',
		label: 'Mail',
		url: 'mailto:test.test@gmail.com'
	},
	{
		content: '123-123-1234',
		icon: 'phone',
		label: 'Phone',
		url: 'tel:123-123-1234'
	},
	{
		content: '321-321-3212',
		icon: 'phone',
		label: 'Phone',
		url: 'tel:321-321-3212'
	},
	{
		content: 'testest',
		icon: 'skype',
		label: 'Skype',
		url: 'skype:testtest?chat'
	}
];

const elsewhereInfo = [
	{
		content: 'testtest',
		icon: 'facebook',
		url: '#'
	},
	{
		content: 'testtest',
		icon: 'github',
		url: '#'
	},
	{
		content: '@testest',
		icon: 'twitter',
		url: '#'
	}
];

const profileDetail = [
	{
		content: '07/04/1776',
		label: 'Birthday'
	},
	{
		content: 'Liferay',
		icon: 'github',
		label: 'Github',
		url: '#'
	},
	{
		content: 'Liferay@liferay.com',
		icon: 'mail',
		label: 'Mail',
		url: 'mailto:Liferay@liferay.com'
	},
	{
		content: 'Liferay.Skype',
		icon: 'skype',
		label: 'Skype',
		url: 'skype:Liferay.Skype?chat'
	},
	{
		content: <EntityPillList entities={topics} />,
		label: 'Topics'
	}
];

const workInfo = [
	{
		content: <EntityPillList entities={topics} />,
		label: 'Expertise'
	},
	{
		content: 'English (USA), Spanish',
		label: 'Languages'
	},
	{
		content: 'Diamond Bar, USA',
		label: 'Location',
		url: '#'
	},
	{
		content: 'Product Manager',
		label: 'Job Title',
		url: '#'
	}
];

const styles = {width: '400px'};

class ListCardKit extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		this.state.loading_ = !this.state.loading_;
	}

	render() {
		const {loading_} = this.state;

		return (
			<Kit card={false} header="ListCard">
				<ElementContainer>
					<Button onClick={this.handleClick_} role="secondary">{'Toggle Loading'}</Button>
				</ElementContainer>

				<ElementContainer header="ListCard without Category Labels">
					<ListCard
						items={elsewhereInfo}
						loading={loading_}
						style={styles}
						title="Elsewhere"
					/>
				</ElementContainer>

				<ElementContainer header="ListCard with Category Labels">
					<ListCard
						items={contactInfo}
						loading={loading_}
						style={styles}
						title="Contacts"
					/>
				</ElementContainer>

				<ElementContainer header="ListCard for Profile Details">
					<ListCard
						items={profileDetail}
						loading={loading_}
						style={styles}
						title="Profile Details"
					/>
				</ElementContainer>

				<ElementContainer header="ListCard for Work Details">
					<ListCard
						items={workInfo}
						loading={loading_}
						style={styles}
						title="Work"
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

ListCardKit.STATE = {
	loading_: Config.bool().value(false)
};

export default ListCardKit;