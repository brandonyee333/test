import * as d3 from 'd3';
import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, isArray, isNumber, noop} from 'lodash';

import Icon from '../Icon';
import LoopConstants from '../../lib/loop-constants';
import {getPlaceholderColorIndex} from '../../lib/util';
import {getPluralMessage} from '../../lib/lang-util';

const {types} = LoopConstants;

function defaultOrCategory(defaultVal, categoryVal) {
	return d => {
		const {type} = d.data;

		return type === types.department || type === types.root ? defaultVal : categoryVal;
	};
}

function truncateTextNode() {
	let text = this.innerHTML;
	let textLength = this.getComputedTextLength();

	while (textLength > 220) {
		text = text.slice(0, -3);

		this.innerHTML = `${text}...`;

		textLength = this.getComputedTextLength();
	}
}

class OrgChart extends Component {
	created() {
		bindAll(
			this,
			'collapse',
			'handleNodeClick',
			'zoomIn',
			'zoomOut'
		);

		this._avatarRadius = 24;
		this._categoryHeight = 48;
		this._categoryWidth = 184;
		this._currentScale = 1;
		this._duration = 750;
		this._idCount = 0;
		this._margin = 24;
		this._nodeCenterOffset = 100;
		this._nodeDepth = 400;
		this._nodeHeight = 72;
		this._nodePadding = 12;
		this._nodeWidth = 310;
		this._selectedIdPath = [this.props.selectedId];
		this._svgHeight = '100%';
		this._svgWidth = '100%';
	}

	attached() {
		const {data, selectedId} = this.props;

		if (data) {
			this._svg = this.createSVG();
			this._tree = this.setUpTree();

			this._root = this.setUpRoot(data);

			this.updateChart(this._root);

			const nodes = this._tree(this._root).descendants();

			const selectedNode = nodes.find(
				d => d.data.entityClassPK === selectedId
			);

			this.updateChart(selectedNode);

			this.props.onLoaded();
		}
	}

	addNewNode(newData, parentNode) {
		const newNode = d3.hierarchy(newData, d => d.children);

		newNode.parent = parentNode;
		newNode.depth = parentNode.depth + 1;

		if (newNode.children) {
			newNode._children = newNode.children.map(
				child => ({
					...child,
					depth: parentNode.depth + 2
				})
			);

			newNode.children = null;
		}

		if (!isArray(parentNode._children)) {
			parentNode._children = [];
		}

		parentNode._children = [...parentNode._children, newNode];
	}

	collapse(item) {
		if (item.children) {
			item._children = item.children;

			item._children.forEach(this.collapse);

			item.children = null;
		}
	}

	createAvatarNodes(gNode) {
		const avatarNode = gNode.filter(d => defaultOrCategory(true, false)(d)).append('g');

		const avatarDiameter = this._avatarRadius * 2;

		const patternNode = avatarNode.append('defs').append('pattern');

		patternNode.attr('height', 1);
		patternNode.attr('id', ({data}) => `org_chart_pic_${data.entityClassPK}`);
		patternNode.attr('width', 1);

		const imageNode = patternNode.append('image');

		imageNode.attr('height', avatarDiameter);
		imageNode.attr('width', avatarDiameter);
		imageNode.attr(
			'href',
			({data}) => data.profileImageData.imageURL_web
		);

		const circleNode = avatarNode.append('circle');

		circleNode.attr(
			'class',
			({data}) => `color-${getPlaceholderColorIndex(data.entityClassPK)}`
		);

		circleNode.attr('cx', this._nodePadding + this._avatarRadius);

		circleNode.style(
			'fill',
			({data}) => {
				return data.profileImageData.imageURL_web ? `url('#org_chart_pic_${data.entityClassPK}')` : '';
			}
		);

		circleNode.attr('r', this._avatarRadius);

		const useNode = avatarNode.filter(({data}) => !data.profileImageData.imageURL_web).append('svg');

		useNode.attr('class', 'avatar-icon');
		useNode.attr('height', this._avatarRadius);
		useNode.attr('width', this._avatarRadius);
		useNode.attr('x', this._avatarRadius);
		useNode.attr('xlink:href', '#loop-icon-person-card-16');
		useNode.attr('y', -(this._avatarRadius / 2));
	}

	createConectingLine(s, u, node) {
		const y = u.y + defaultOrCategory(this._nodeWidth, this._categoryWidth)(node.parent);

		return `M ${s.y} ${s.x} C ${(s.y + y) / 2} ${s.x}, ${(s.y + y) / 2} ${u.x}, ${y} ${u.x}`;
	}

	createNewNodes(nodeEnter, source) {
		const gNode = nodeEnter.append('g').lower();

		gNode.attr('class', 'node');
		gNode.attr('transform', () => this.getTranslateString(source.y0, source.x0));
		gNode.on('click', this.handleNodeClick);

		const rectNode = gNode.append('rect');

		const categoryHeightHalf = this._categoryHeight / 2;
		const nodeHeightHalf = this._nodeHeight / 2;

		rectNode.attr('height', defaultOrCategory(this._nodeHeight, this._categoryHeight));
		rectNode.attr('rx', defaultOrCategory(nodeHeightHalf, categoryHeightHalf));
		rectNode.attr('ry', defaultOrCategory(nodeHeightHalf, categoryHeightHalf));
		rectNode.attr('width', defaultOrCategory(this._nodeWidth, this._categoryWidth));
		rectNode.attr('y', defaultOrCategory(-nodeHeightHalf, -categoryHeightHalf));

		const nameNode = gNode.append('text');

		const textRight = (this._avatarRadius + this._nodePadding) * 2;

		nameNode.attr('class', 'name');
		nameNode.attr('x', defaultOrCategory(textRight, this._nodePadding * 2));
		nameNode.attr(
			'y',
			({data}) => {
				const {childLoopDivisionsCount, type} = data;

				return type === types.department && childLoopDivisionsCount ? 0 : 5;
			}
		);
		nameNode.text(d => d.data.name);
		nameNode.each(truncateTextNode);

		this.createAvatarNodes(gNode);

		const secondaryInfoNode = gNode.append('text');

		secondaryInfoNode.attr('class', 'secondary-info');
		secondaryInfoNode.attr('x', defaultOrCategory(textRight, this._nodePadding * 2));
		secondaryInfoNode.attr('y', 16);
		secondaryInfoNode.text(
			({data}) => {
				const {childLoopDivisionsCount, type} = data;

				return childLoopDivisionsCount && type !== types.root ? getPluralMessage(Liferay.Language.get('x-subdepartment'), Liferay.Language.get('x-subdepartments'), childLoopDivisionsCount, true) : '';
			}
		);

		return gNode;
	}

	createSVG() {
		const {chartSVG, tree} = this.refs;

		const height = this._svgHeight;
		const width = this._svgWidth;

		const svg = d3.select(chartSVG);

		svg.attr('class', 'org-chart');
		svg.attr('height', height);
		svg.attr('viewBox', null);
		svg.attr('width', width);

		const treeNode = d3.select(tree);

		treeNode.attr('transform', this.getTranslateString(this._margin, this._nodeHeight * 2));

		this._zoom = d3.zoom().scaleExtent([0.25, 2]).on(
			'zoom',
			() => this.handleZoom(svg, treeNode)
		);

		this._zoomInterface = d3.select(chartSVG);

		this._zoomInterface.call(this._zoom);

		return treeNode;
	}

	getTranslateString(x, y) {
		return `translate(${x}, ${y})`;
	}

	handleNodeClick(d) {
		const {onNodeClick, requestChildren} = this.props;

		const {_children, children, data = {}} = d;

		const {entityClassPK} = data;

		const lastActive = this._selectedIdPath[this._selectedIdPath.length - 1] === entityClassPK;

		this._selectedIdPath = this.setSelectedId(d);

		onNodeClick(isNumber(entityClassPK) ? entityClassPK : undefined);

		if (defaultOrCategory(true, false)(d) && !(children || _children)) {
			requestChildren(entityClassPK).then(
				results => {
					if (isArray(results)) {
						results.forEach(
							item => this.addNewNode(item, d)
						);
					}

					this.updateChildren(d, lastActive);
				}
			);
		}
		else {
			this.updateChildren(d, lastActive);
		}
	}

	handleNodesEntering(node, source) {
		return this.createNewNodes(node.enter(), source);
	}

	handleNodesExiting(node, source) {
		const exitNode = node.exit();

		exitNode.transition().duration(this._duration).attr(
			'transform',
			() => this.getTranslateString(source.y, source.x)
		).remove();
	}

	handleNodesUpdating(nodeEnter, node) {
		const nodeUpdating = nodeEnter.merge(node);

		const rectNodes = nodeUpdating.selectAll('rect');

		rectNodes.attr(
			'class',
			d => getCN(
				'node',
				{
					highlight: this._selectedIdPath[d.depth] === d.data.entityClassPK,
					selected: this._selectedIdPath[this._selectedIdPath.length - 1] === d.data.entityClassPK
				}
			)
		);

		nodeUpdating.transition().duration(this._duration).attr(
			'transform',
			({x, y}) => this.getTranslateString(y, x)
		);
	}

	handleZoom(svg, g) {
		const {k} = d3.event.transform;

		this._currentScale = k;

		this.updateZoomValue();

		g.attr('transform', d3.event.transform);
	}

	setOldPositions(nodes) {
		nodes.forEach(
			d => {
				d.x0 = d.x;
				d.y0 = d.y;
			}
		);
	}

	setSelectedId(node, arr = []) {
		const {parent} = node;

		if (parent) {
			arr = this.setSelectedId(parent, arr);
		}

		return [...arr, node.data.entityClassPK];
	}

	setUpRoot(data) {
		const root = d3.hierarchy(data, d => d.children);

		const {height, width} = this.refs.chartSVG.getBBox();

		root.x0 = height / 2;
		root.y0 = (width / 2) - this._nodeCenterOffset - this._nodeWidth;

		return root;
	}

	setUpTree() {
		return d3.tree().nodeSize([this._nodeHeight + this._margin, this._nodeWidth]);
	}

	shouldUpdate() {
		return false;
	}

	updateChart(source) {
		const treeData = this._tree(this._root);

		const nodes = treeData.descendants();

		nodes.forEach(
			d => {
				d.y = d.depth * this._nodeDepth;
			}
		);

		const gNodes = this._svg.selectAll('g.node').data(nodes, d => d.id || (d.id = ++this._idCount));

		const nodeEnter = this.handleNodesEntering(gNodes, source);

		this.handleNodesUpdating(nodeEnter, gNodes);

		this.handleNodesExiting(gNodes, source);

		this.updateLinks(source, treeData);

		this.setOldPositions(nodes);

		this.updateTransform(source);
	}

	updateChildren(d, collapse) {
		if (d.children && collapse) {
			d._children = d.children;
			d.children = null;
		}
		else if (d._children) {
			d.children = d._children;
			d._children = null;
		}

		this.updateChart(d);
	}

	updateLinks(source, treeData) {
		const links = treeData.descendants().slice(1);

		const linkNodes = this._svg.selectAll('path.link').data(links, d => d.id);

		const gNode = linkNodes.enter().insert('path', 'g');

		gNode.attr(
			'd',
			d => {
				const o = {x: source.x0, y: source.y0};

				return this.createConectingLine(o, o, d);
			}
		);

		const linkUpdate = gNode.merge(linkNodes);

		linkUpdate.attr(
			'class',
			d => getCN(
				'link',
				{
					highlight: this._selectedIdPath[d.depth] === d.data.entityClassPK
				}
			)
		);

		linkUpdate.transition().duration(this._duration).attr(
			'd',
			d => this.createConectingLine(d, d.parent, d)
		);

		const exitNode = linkNodes.exit();

		exitNode.transition().duration(this._duration).attr(
			'd',
			d => {
				const o = {x: source.x, y: source.y};

				return this.createConectingLine(o, o, d);
			}
		);

		exitNode.remove();
	}

	updateTransform(source) {
		const {height, width} = this.refs.chartSVG.getBoundingClientRect();

		const k = this._currentScale;
		const offset = this._nodeCenterOffset + this._nodeWidth;

		let y0 = source.y0;

		if (!source.children && source.parent) {
			y0 = source.parent.y0;
		}

		const x = -(y0 + offset) * k + width / 2;
		const y = -source.x0 * k + height / 2;

		this._svg.transition().duration(this._duration).attr(
			'transform',
			`${this.getTranslateString(x, y)} scale(${k})`
		);

		this._zoomInterface.transition().duration(this._duration).call(
			this._zoom.transform,
			d3.zoomIdentity.translate(x, y).scale(k)
		);
	}

	updateZoomValue() {
		this.refs.zoomPercent.innerHTML = `${Math.round(this._currentScale * 100)}%`;
	}

	zoomIn() {
		this._zoomInterface.transition().duration(this._duration).call(this._zoom.scaleBy, 2);
	}

	zoomOut() {
		this._zoomInterface.transition().duration(this._duration).call(this._zoom.scaleBy, 0.5);
	}

	render() {
		return (
			<div class="org-chart-container">
				<div class="svg-wrapper">
					<svg ref="chartSVG">
						<g ref="tree" />
					</svg>
				</div>

				<div class="zoom-controls">
					<Icon name="plus" onClick={this.zoomIn} size="small" />

					<span ref="zoomPercent">{'100%'}</span>

					<Icon name="minus" onClick={this.zoomOut} size="small" />
				</div>
			</div>
		);
	}
}

OrgChart.PROPS = {
	data: Config.object().value({}),
	requestChildren: Config.func().value(noop),
	selectedId: Config.number()
};

export default OrgChart;