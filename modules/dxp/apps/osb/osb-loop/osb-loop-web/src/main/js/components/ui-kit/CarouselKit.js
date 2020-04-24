import Component from 'metal-jsx';
import {noop, range} from 'lodash';

import Carousel from '../Carousel';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

const styles = {
	border: '1px solid',
	flexGrow: 1,
	padding: '20px',
	textAlign: 'center'
};

function renderItem(index) {
	return (
		<Carousel.Item key={`child${index}`}>
			<span style={styles}>{index}</span>
		</Carousel.Item>
	);
}

class CarouselKit extends Component {
	render() {
		return (
			<Kit header="Carousel">
				<ElementContainer>
					<Carousel onSeeAll={noop} title={'6 items'}>
						{range(1, 7).map(renderItem)}
					</Carousel>
				</ElementContainer>

				<ElementContainer>
					<Carousel title={'1 item'}>
						{renderItem(1)}
					</Carousel>
				</ElementContainer>
			</Kit>
		);
	}
}

export default CarouselKit;