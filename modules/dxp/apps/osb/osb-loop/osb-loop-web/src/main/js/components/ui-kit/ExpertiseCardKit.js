import Component, {Config} from 'metal-jsx';
import {range} from 'lodash';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import {ExpertiseCard} from '../ExpertiseCard';

const styles = {
	width: '300px'
};

const topics = range(3).map(LoopAssets.getTopic);

topics.push(LoopAssets.getTopic('WithAVeryLongNameThatShouldWrapAtSomePoint'));

class ExpertiseCardKit extends Component {
	created() {
		this.toggleLoading_ = this.toggleLoading_.bind(this);
	}

	toggleLoading_() {
		this.state.loading_ = !this.state.loading_;
	}

	render() {
		const {loading_} = this.state;

		return (
			<Kit card={false} header="ExpertiseCard">
				<ElementContainer>
					<Button onClick={this.toggleLoading_} role="primary">{'Toggle Loading'}</Button>
				</ElementContainer>

				<ElementContainer header="None with permissions" style={styles}>
					<ExpertiseCard
						editURL="#"
						loading={loading_}
						seeMoreURL="#"
						topics={[]}
					/>
				</ElementContainer>

				<ElementContainer header="None without permissions" style={styles}>
					<ExpertiseCard
						editURL={null}
						loading={loading_}
						seeMoreURL="#"
						topics={[]}
					/>
				</ElementContainer>

				<ElementContainer header="A Few" style={styles}>
					<ExpertiseCard
						editURL="#"
						loading={loading_}
						seeMoreURL="#"
						topics={range(3).map(LoopAssets.getTopic)}
					/>
				</ElementContainer>

				<ElementContainer header="More than a few" style={styles}>
					<ExpertiseCard
						editURL="#"
						loading={loading_}
						seeMoreURL="#"
						topics={range(9).map(LoopAssets.getTopic)}
					/>
				</ElementContainer>

				<ElementContainer header="More than a few with long topic names" style={styles}>
					<ExpertiseCard
						editURL="#"
						loading={loading_}
						seeMoreURL="#"
						topics={topics}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

ExpertiseCardKit.STATE = {
	loading_: Config.value(false)
};

export default ExpertiseCardKit;