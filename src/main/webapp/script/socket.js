/**
 *  This is a base Socket class to be inherited by Socket types
 */

var Socket = function () {
    this.socket = null;
    this.domain = null;
    this.uri = null;
};

Socket.prototype.sendMessage = function (message) {
    this.socket.send(message);
};

Socket.prototype.messageHandler = function (event) {
    // Implement this method
    throw "Unimplemented Socket.messageHandler(). Message: " + event.data;
};

Socket.prototype.connect = function () {
    this.socket = new WebSocket("wss://" + this.domain + "/" + this.uri);
    this.socket.onmessage = this.messageHandler;
};

