import React from 'react';
import TestRenderer from 'react-test-renderer';

import Button from '../Button';

describe('Button', () => {
	it('renders correctly', () => {
		const tree = TestRenderer.create(<Button>Button</Button>).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders as disabled correctly', () => {
		const tree = TestRenderer.create(
			<Button disabled>Disabled Button</Button>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders as primary style correctly', () => {
		const tree = TestRenderer.create(
			<Button display="primary">Primary Button</Button>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders as a link correctly', () => {
		const tree = TestRenderer.create(<Button href="/">Link</Button>).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders as an icon correctly', () => {
		const tree = TestRenderer.create(<Button icon>+</Button>).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders as a certain size correctly', () => {
		const tree = TestRenderer.create(
			<Button size="lg">Large Button</Button>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders as a certain type correctly', () => {
		const tree = TestRenderer.create(
			<Button type="submit">Submit Button</Button>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders with a value correctly', () => {
		const tree = TestRenderer.create(
			<Button value="1">Value of 1 Button</Button>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});