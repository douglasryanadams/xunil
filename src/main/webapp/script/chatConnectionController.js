/**
 * Created on 7/31/16.
 */

var ChatConnectionController = function (restClient, chatSocket) {
    this.rest = restClient;
    this.chatSocket = chatSocket;

};

ChatConnectionController.prototype.init = function () {
    var self = this;
    var connectEventSuccessCallback = function (data) {
        var answer = data["answer"];
        if (answer == "accepted") {
            clientMemory["recipientPublicKey"] = KeyMaster.getPublicKeyAsBits(data["publicKey"]);
            clientMemory["connectedTo"] = data["connectedTo"];
            ChatController.displaySuccessMessage("Connection Accepted from: " + clientMemory["connectedTo"]);
            $("#recipient_connect_submit").hide();
            $("#recipient_disconnect_submit").show();
        } else {
            $("#message_recipient").prop("disabled", false);
            ChatController.displayErrorMessage("Connection Rejected from: " + clientMemory["connectedTo"]);
            clientMemory["publicKey"] = "";
            clientMemory["connectedTo"] = "";
        }
    };

    var connectEvent = function () {
        $("#message_recipient").prop("disabled", true);
        var chatWithId = $("#message_recipient").val();
        clientMemory["connectedTo"] = chatWithId;
        ChatController.displayInfoMessage("Requesting connection from: " + chatWithId);
        self.rest.chatConnect(clientMemory["id"], chatWithId, connectEventSuccessCallback)
    };

    $("#recipient_connect_submit").click(connectEvent);

    var replyToChatConnect = function (messageType) {
        var message = {
            "type": messageType,
            "from": clientMemory["id"],
            "to": clientMemory["connectedTo"]
        };
        self.chatSocket.sendMessage(JSON.stringify(message));
    };

    var disconnectEvent = function () {
        ChatController.displayInfoMessage("Disconnected from chat Session");
        replyToChatConnect("disconnectChat");
        $("#recipient_disconnect_submit").hide();
        $("#recipient_connect_submit").show();
    };
    $("#recipient_disconnect_submit").click(disconnectEvent);

    var acceptEvent = function () {
        ChatController.displaySuccessMessage("Accepted Connection Request!");
        replyToChatConnect("acceptChat");
        $("#recipient_connect_accept").hide();
        $("#recipient_connect_reject").hide();
        $("#recipient_disconnect_submit").show();
    };
    $("#recipient_connect_accept").click(acceptEvent);

    var rejectEvent = function () {
        ChatController.displayErrorMessage("Rejected Connection Request!");
        replyToChatConnect("rejectChat");
        $("#recipient_connect_accept").hide();
        $("#recipient_connect_reject").hide();
        $("#recipient_connect_submit").show();
    };
    $("#recipient_connect_reject").click(rejectEvent);
};
