$(document).ready(function () {
    var domain = config["hostname"] + ":" + config["port"];
    var chatSocket = new ChatSocket(domain);
    var rosterSocket = new RosterSocket(domain);
    var configController = new ConfigController(rosterSocket);
    var keyMaster = new KeyMaster();
    var rest = new RestClient(domain);

    var submit_event = function () {
        var chat_content = $('#chat_input');
        var message = {
            "type" : "chat",
            "to": $('#message_recipient').val(),
            "content": chat_content.val()
        };
        chat_content.val("");
        chatSocket.sendMessage(JSON.stringify(message));
    };

    var registrationSuccessCallback = function (data) {
        console.log("Registration Successful");
        $("#my_id").html(data["uuid"]);
        clientMemory["id"] = data["uuid"];

        chatSocket.connect();
        rosterSocket.connect();
        configController.init();

        $('#chat_form').submit(submit_event);
    };

    rest.register(keyMaster.publicKey, registrationSuccessCallback);
});
