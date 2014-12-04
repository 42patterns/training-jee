<!DOCTYPE html>
<html>
<head>
    <title>WebSocket reports logging</title>
    <style type="text/css">
        #connect-container {
            width: 400px
        }
 
        #connect-container div {
            padding: 5px;
        }
 
        #console-container {
            margin-left: 15px;
            width: 900px;
        }
 
        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 170px;
            overflow-y: scroll;
            padding: 5px;
            width: 100%;
        }
 
        #console p {
            padding: 0;
            margin: 0;
        }
    </style>
    <script type="text/javascript">
        var ws = null;
 
        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
        }
 
        function connect() {
            if ('WebSocket' in window) {
                ws = new WebSocket('ws://localhost:8080/feeds-reports/log');
            } else if ('MozWebSocket' in window) {
                ws = new MozWebSocket('ws://localhost:8080/feeds-reports/log');
            } else {
                alert('WebSocket is not supported by this browser.');
                return;
            }
            ws.onopen = function () {
                setConnected(true);
                log('Info: WebSocket connection opened.');
            };
            ws.onmessage = function (event) {
                log('Received: ' + event.data);
            };
            ws.onclose = function () {
                setConnected(false);
                log('Info: WebSocket connection closed.');
            };
        }
 
        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
        }
 
        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }
    </script>
</head>
<body>
    <noscript>
        <h2 style="color: #ff0000">
            Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled.
            Please enable Javascript and reload this page!
        </h2>
    </noscript>
<div>
    <div id="connect-container">
        <div>
            <button id="connect" onclick="connect();">Connect</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
            <button id="echo" onclick="echo();" disabled="disabled">Echo message</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>
</html>