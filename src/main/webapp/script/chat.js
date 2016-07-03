var clientMemory = {};

var Socket = function () {
    this.socket = null;
};

Socket.prototype.sendMessage = function (message) {
    this.socket.send(message);
};

Socket.prototype.connect = function (domain) {
    var thisSocket = this;
    this.socket = new WebSocket("wss://" + domain + "/chat");
    this.socket.onmessage = function (event) {
        var message = JSON.parse(event.data);
        switch (message["type"]) {
            case "chat":
                $('#chat_display').append(
                    message["content"] + "<br>"
                );
                break;
            case "registration":
                var msg = { "type" : "registration", "content" : clientMemory["id"]};
                thisSocket.sendMessage(JSON.stringify(msg));
                break;
            default:
                console.log("ERROR: invalid message type: " + message["type"]);
        }
    }
};


var ChatForm = function (socket) {
    this.socket = socket;
};

var KeyMaster = function () {
    var keys = sjcl.ecc.ecdsa.generateKeys(521);
    this.publicKey = keys['pub'];
    this.privateKey = keys['sec'];
};

// TODO
// KeyMaster.prototype.encryptMessage = function (message) {
//     return encryptedMessage;
// };

var RestClient = function (domain) {
    this.baseUrl = "https://" + domain + "/api/";
};

RestClient.prototype.register = function (publicKey, socket) {
    var randomSeed = sjcl.codec.base64.fromBits(sjcl.random.randomWords(4));
    var pubKeyStringObject = {
        "xAsString": sjcl.codec.base64.fromBits(publicKey.get()['x']),
        "yAsString": sjcl.codec.base64.fromBits(publicKey.get()['y']),
    };
    var payload = {
        "randomSeed": randomSeed,
        "publicKey": pubKeyStringObject
    };

    var successCallback = function (data) {
        console.log("Success!");
        $("#my_id").html(data["uuid"]);
        clientMemory["id"] = data["uuid"];
        socket.connect(config["hostname"] + ":" + config["port"]);

        var submit_event = function () {
            var chat_content = $('#chat_input');
            var message = {
                "type" : "chat",
                "to": $('#message_recipient').val(),
                "content": chat_content.val()
            };
            chat_content.val("");
            socket.sendMessage(JSON.stringify(message));
        };

        $('#chat_form').submit(submit_event);
    };

    var errorCallback = function (xhr, status, error) {
        // TODO Show to user, make informative
        console.log("ERROR: " + status + " | " + error);
    };

    $.ajax({
        type: "POST",
        url: this.baseUrl + "chat",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: successCallback,
        error: errorCallback
    });
};

ChatForm.prototype.init = function () {
    var keyMaster = new KeyMaster();
    var rest = new RestClient(config["hostname"] + ":" + config["port"]);
    rest.register(keyMaster.publicKey, this.socket);
};

$(document).ready(function () {
    var s = new Socket();
    var cf = new ChatForm(s);
    cf.init();
});
