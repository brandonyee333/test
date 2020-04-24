import Component from 'metal-jsx';
import {range} from 'lodash';
import {createStore} from 'redux';
import {fromJS, List, Map} from 'immutable';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LikeCounter from '../LikeCounter';
import LoopAssets from '../../tests/loop-assets';

const style = {
	maxWidth: '616px'
};

const store = createStore(
	s => s,
	fromJS(
		{
			people: range(5).reduce(
				(result, i) => result.setIn([i, 'data'], fromJS(LoopAssets.getPerson(i))),
				Map()
			)
		}
	)
);

const participantsIList = List(range(5));

class LikeCounterKit extends Component {
	created() {
		this.handleOtherClick_ = this.handleOtherClick_.bind(this);
	}

	handleOtherClick_() {
		alert('show modal');
	}

	render() {
		return (
			<Kit header="LikeCounter">
				<ElementContainer header="Single Like" style={style}>
					<LikeCounter
						count={1}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList.take(1)}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Without Extra Likes" style={style}>
					<LikeCounter
						count={3}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList.take(3)}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Extra Likes" style={style}>
					<LikeCounter
						count={9}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Many Extra Likes" style={style}>
					<LikeCounter
						count={321}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Smaller container" style={{maxWidth: '490px'}}>
					<LikeCounter
						count={17}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Yet Smaller container" style={{maxWidth: '430px'}}>
					<LikeCounter
						count={17}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Even Smaller container" style={{maxWidth: '380px'}}>
					<LikeCounter
						count={17}
						onOtherClick={this.handleOtherClick_}
						participantsIList={participantsIList}
						store={store}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default LikeCounterKit;