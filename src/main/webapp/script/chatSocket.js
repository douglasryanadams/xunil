/**
 * Created on 7/21/16.
 */

var ChatSocket = function (domain) {
    Socket.call(this);
    this.domain = domain;
    this.uri = "chat";
};

ChatSocket.prototype = Object.create(Socket.prototype);

ChatSocket.prototype.messageHandler = function (event) {
    var message = JSON.parse(event.data);
    switch (message["type"]) {
        case "chat":
            var chatDisplay = $("#chat_display");
            chatDisplay.append(
                message["content"] + "<br>"
            );
            chatDisplay.scrollTop(chatDisplay.prop("scrollHeight"));
            break;
        case "registration":
            var msg = { "type" : "registration", "content" : clientMemory["id"]};
            // this has become the socket itself here
            this.send(JSON.stringify(msg));
            break;
        default:
            console.log("ERROR: invalid message type: " + message["type"]);
    }
}