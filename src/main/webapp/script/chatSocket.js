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
            var encryptedChatContent = message["content"];
            var chatContent = Keymaster.decryptMessage(clientMemory["privateKey"], encryptedChatContent);
            ChatController.displayMessage(chatContent);
            break;
        case "connectionRequest":
            $("#recipient_connect_submit").hide();
            $("#recipient_connect_accept").show();
            $("#recipient_connect_reject").show();
            var messageRecipient = $("#message_recipient");
            messageRecipient.prop("disabled", true);
            messageRecipient.val(message["from"]);
            ChatController.displayInfoMessage("Chat Request from: " + message["from"]);
            ChatController.displayInfoMessage("You may accept or reject the request below");
            break;
        case "connectionClosed":
            ChatController.displayInfoMessage("Chat session closed by: " + message["from"]);
            var messageRecipient = $("#message_recipient");
            messageRecipient.val("");
            messageRecipient.prop("disabled", false);
            $("#recipient_disconnect_submit").hide();
            $("#recipient_connect_submit").show();
            break;
        case "registration":
            var msg = { "type" : "registration", "content" : clientMemory["id"]};
            // 'this' has become the socket itself here
            this.send(JSON.stringify(msg));
            break;
        default:
            console.log("ERROR: invalid message type: " + message["type"]);
    }
}