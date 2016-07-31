$(document).ready(function () {
    var domain = config["hostname"] + ":" + config["port"];
    var chatSocket = new ChatSocket(domain);
    var rosterSocket = new RosterSocket(domain);
    var configController = new ConfigController(rosterSocket);
    var keyMaster = new KeyMaster();
    var rest = new RestClient(domain);

    var submit_event = function () {
        var chatInput = $('#chat_input');
        var chatContent = chatInput.val();
        var message = {
            "type" : "chat",
            "to": $('#message_recipient').val(),
            "content": chatContent
        };
        chatSocket.sendMessage(JSON.stringify(message));
        chatInput.val("");

        if (clientMemory["showMyOwnMessages"]) {
            var chatDisplay = $("#chat_display");
            chatDisplay.append(
                "<span class='my_own_message'>" + chatContent + "</span><br>"
            );
            chatDisplay.scrollTop(chatDisplay.prop("scrollHeight"));
        }
    };

    var registrationSuccessCallback = function (data) {
        console.log("Registration Successful");
        $("#my_id").html(data["uuid"]);
        clientMemory["id"] = data["uuid"];

        chatSocket.connect();
        rosterSocket.connect();
        configController.init();

        $('#chat_submit').click(submit_event);
    };

    rest.register(keyMaster.publicKey, registrationSuccessCallback);
});
