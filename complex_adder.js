(function() {
2	"use strict";
3
4	var header = $('#header');
5	var content = $('#content');
6	var input = $('#input');
7	var status = $('#status');
8	var myName = false;
9	var author = null;
10	var logged = false;
11	var socket = atmosphere;
12	var subSocket;
13	var transport = 'websocket';
14
15	// We are now ready to cut the request
16	var request = {
17		url : '/chat',
18		contentType : "application/json",
19		logLevel : 'debug',
20		transport : transport,
21		trackMessageLength : true,
22		reconnectInterval : 5000
23	};
24
25	request.onOpen = function(response) {
26		content.html($('<p>', {
27			text : 'Atmosphere connected using ' + response.transport
28		}));
29		input.removeAttr('disabled').focus();
30		status.text('Choose name:');
31		transport = response.transport;
32
33		// Carry the UUID. This is required if you want to call
34		// subscribe(request) again.
35		request.uuid = response.request.uuid;
36	};
37
38	request.onClientTimeout = function(r) {
39		content
40				.html($(
41						'<p>',
42						{
43							text : 'Client closed the connection after a timeout. Reconnecting in '
44									+ request.reconnectInterval
45						}));
46		subSocket
47				.push(atmosphere.util
48						.stringifyJSON({
49							author : author,
50							message : 'is inactive and closed the connection. Will reconnect in '
51									+ request.reconnectInterval
52						}));
53		input.attr('disabled', 'disabled');
54		setTimeout(function() {
55			subSocket = socket.subscribe(request);
56		}, request.reconnectInterval);
57	};
58
59	request.onReopen = function(response) {
60		input.removeAttr('disabled').focus();
61		content.html($('<p>', {
62			text : 'Atmosphere re-connected using ' + response.transport
63		}));
64	};
65
66	// For demonstration of how you can customize the fallbackTransport using
67	// the onTransportFailure function
68	request.onTransportFailure = function(errorMsg, request) {
69		atmosphere.util.info(errorMsg);
70		request.fallbackTransport = "long-polling";
71		header
72				.html($(
73						'<h3>',
74						{
75							text : 'Atmosphere Chat. Default transport is WebSocket, fallback is '
76									+ request.fallbackTransport
77						}));
78	};
79
Function with high complexity (count = 8): onMessage
80	request.onMessage = function(response) {
81
82		var message = response.responseBody;
83		try {
84			var json = atmosphere.util.parseJSON(message);
85		} catch (e) {
86			console.log('This doesn\'t look like a valid JSON: ', message);
87			return;
88		}
89
90		input.removeAttr('disabled').focus();
91		if (!logged && myName) {
92			logged = true;
93			status.text(myName + ': ').css('color', 'blue');
94		} else {
95			var me = json.author == author;
96			var date = typeof (json.time) == 'string' ? parseInt(json.time)
97					: json.time;
98			addMessage(json.author, json.message, me ? 'blue' : 'black',
99					new Date(date));
100		}
101	};
102
103	request.onClose = function(response) {
104		content.html($('<p>', {
105			text : 'Server closed the connection after a timeout'
106		}));
107		if (subSocket) {
108			subSocket.push(atmosphere.util.stringifyJSON({
109				author : author,
110				message : 'disconnecting'
111			}));
112		}
113		input.attr('disabled', 'disabled');
114	};
115
116	request.onError = function(response) {
117		content.html($('<p>', {
118			text : 'Sorry, but there\'s some problem with your '
119					+ 'socket or the server is down'
120		}));
121		logged = false;
122	};
123
124	request.onReconnect = function(request, response) {
125		content.html($('<p>', {
126			text : 'Connection lost, trying to reconnect. Trying to reconnect '
127					+ request.reconnectInterval
128		}));
129		input.attr('disabled', 'disabled');
130	};
131
132	subSocket = socket.subscribe(request);
133
134	input.keydown(function(e) {
135		if (e.keyCode === 13) {
136			var msg = $(this).val();
137
138			// First message is always the author's name
139			if (author == null) {
140				author = msg;
141			}
142
143			subSocket.push(atmosphere.util.stringifyJSON({
144				author : author,
145				message : msg
146			}));
147			$(this).val('');
148
149			input.attr('disabled', 'disabled');
150			if (myName === false) {
151				myName = msg;
152			}
153		}
154	});
155
Function with many parameters (count = 4): addMessage
156	function addMessage(author, message, color, datetime) {
157		content.append('<p><span style="color:'
158				+ color
159				+ '">'
160				+ author
161				+ '</span> @ '
162				+ +(datetime.getHours() < 10 ? '0' + datetime.getHours()
163						: datetime.getHours())
164				+ ':'
165				+ (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes()
166						: datetime.getMinutes()) + ': ' + message + '</p>');
167	}
168});
169
