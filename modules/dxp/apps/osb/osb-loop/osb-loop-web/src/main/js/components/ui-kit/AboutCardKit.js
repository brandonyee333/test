import Component from 'metal-jsx';

import AboutCard from '../AboutCard';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class AboutCardKit extends Component {
	render() {
		return (
			<Kit card={false} header="AboutCard">
				<ElementDisplay description="With details">
					<AboutCard
						details={{
							Birthday: '11-12-1955',
							Name: 'Test Test',
							Title: 'Tester'
						}}
						message={'Ipsum asperiores deserunt ea sint perferendis laboriosam laborum Atque omnis incidunt rerum explicabo culpa, vitae? Dolorum alias recusandae vitae ducimus.'.repeat(10)}
					/>
				</ElementDisplay>

				<ElementDisplay description="Without details">
					<AboutCard
						message={'Ipsum asperiores deserunt ea sint perferendis laboriosam laborum Atque omnis incidunt rerum explicabo culpa, vitae? Dolorum alias recusandae vitae ducimus.'.repeat(10)}
					/>
				</ElementDisplay>
			</Kit>
		);
	}
}

export default AboutCardKit;