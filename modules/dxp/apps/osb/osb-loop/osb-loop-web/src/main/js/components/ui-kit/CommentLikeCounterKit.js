import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import CommentLikeCounter from '../CommentLikeCounter';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

const INITIAL_COUNT = 5;

class CommentLikeCounterKit extends Component {
	created() {
		bindAll(
			this,
			'handleClick_',
			'handleCountClick_'
		);
	}

	handleClick_() {
		const {count_, liked_} = this.state;

		this.setState(
			{
				count_: liked_ ? count_ - 1 : count_ + 1,
				liked_: !liked_
			}
		);
	}

	handleCountClick_() {
		alert('onCountClick');
	}

	render() {
		const {handleClick_, handleCountClick_} = this;

		const {count_, liked_} = this.state;

		return (
			<Kit header="CommentLikeCounter">
				<ElementContainer header="With Initial Count">
					<CommentLikeCounter
						count={count_}
						liked={liked_}
						onClick={handleClick_}
						onCountClick={handleCountClick_}
					/>
				</ElementContainer>

				<ElementContainer header="Starting At Zero">
					<CommentLikeCounter
						count={count_ - INITIAL_COUNT}
						liked={liked_}
						onClick={handleClick_}
						onCountClick={handleCountClick_}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

CommentLikeCounterKit.STATE = {
	count_: Config.value(INITIAL_COUNT),
	liked_: Config.value(false)
};

export default CommentLikeCounterKit;