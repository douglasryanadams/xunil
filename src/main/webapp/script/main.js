$(document).ready(function () {
    var domain = config["hostname"] + ":" + config["port"];
    var chatSocket = new ChatSocket(domain);
    var rosterSocket = new RosterSocket(domain);
    var configController = new ConfigController(rosterSocket);
    var rest = new RestClient(domain);
    var chatConnectionController = new ChatConnectionController(rest, chatSocket);
    var keys = KeyMaster.generateKeys();
    clientMemory["privateKey"] = keys["privateKey"];

    var submitEvent = function () {
        var chatInput = $('#chat_input');
        var chatContent = chatInput.val();
        var encryptedChatContent = KeyMaster.encryptMessage(clientMemory["recipientPublicKey"], chatContent);
        var message = {
            "type": "chat",
            "to": clientMemory["connectedTo"],
            "content": encryptedChatContent
        };
        chatSocket.sendMessage(JSON.stringify(message));
        chatInput.val("");

        if (clientMemory["showMyOwnMessages"]) {
            ChatController.displayMyMessage(chatContent);
        }
    };

    var registrationSuccessCallback = function (data) {
        console.log("Registration Successful");
        $("#my_id").html(data["uuid"]);
        clientMemory["id"] = data["uuid"];

        chatSocket.connect();
        rosterSocket.connect();
        configController.init();
        chatConnectionController.init();

        $("#chat_submit").click(submitEvent);
    };

    rest.register(keys["publicKey"], registrationSuccessCallback);
});
