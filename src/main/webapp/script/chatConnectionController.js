/**
 * Created on 7/31/16.
 */

var ChatConnectionController = function (restClient, chatSocket) {
    this.rest = restClient;
    this.chatSocket = chatSocket;

};

ChatConnectionController.prototype.init = function () {
    var connectEventSuccessCallback = function (data)  {
        var answer = data["answer"];
        if (answer == "accepted") {
            clientMemory["publicKey"] = data["publicKey"];
            clientMemory["connectedTo"] = data["connectedTo"];
            $("#message_recipient").prop("disabled", true);
            ChatController.displaySuccessMessage("Connection Accepted from: " + clientMemory["connectedTo"]);
        } else {
            ChatController.displayErrorMessage("Connection Rejected from: " + data["connectedTo"]);
        }
    };

    var connectEvent = function () {
        var chatWithId = $("#message_recipient").val();
        this.rest.chatConnect(clientMemory["id"], chatWithId, connectEventSuccessCallback)
    };

    $("#recipient_connect_submit").click(connectEvent);

    var replyToChatConnect = function (messageType) {
        var message = {
            "type" : messageType
        };
        this.chatSocket.send(JSON.stringify(message));
    };

    var disconnectEvent = function () {
        replyToChatConnect("disconnectChat");
    };
    $("#recipient_disconnect_submit").click(disconnectEvent);

    var acceptEvent = function () {
        replyToChatConnect("acceptChat");
    };
    $("#recipient_connect_accept").click(acceptEvent);

    var rejectEvent = function () {
        replyToChatConnect("rejectChat");
    };
    $("#recipient_connect_reject").click(rejectEvent);
};
