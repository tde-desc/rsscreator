import FeedConfigs from './feedconfigs'
import NewFeedConfig from './newfeedconfig'

const React = require('react');
const ReactDOM = require('react-dom');

ReactDOM.render(
	<FeedConfigs />,
	document.getElementById('feedConfigs')
)
ReactDOM.render(
	<NewFeedConfig />,
	document.getElementById('newFeedConfig')
)