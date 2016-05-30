var Socket = function () {
    this.socket = null;
};

Socket.prototype.connect = function (domain) {
    this.socket = new WebSocket("ws://" + domain + "/chat");
    this.socket.onmessage = function (event) {
        var message = JSON.parse(event.data);
        console.log("Message: ");
        console.log(message);
        $('#chat_display').append(
            message["content"] + "<br><br>"
        );
        if (message["to"]) {
            $('#my_id').val(message["to"]);
        }
    }
};

Socket.prototype.sendMessage = function (message) {
    this.socket.send(message);
};

var ChatForm = function (socket) {
    this.socket = socket;
};

ChatForm.prototype.init = function () {
    var socket = this.socket;
    socket.connect("localhost:8080");
    var submit_event = function () {
        var message = {
            "content": $('#chat_input').val(),
            "to": $('#message_recipient').val()
        };
        socket.sendMessage(JSON.stringify(message));
    };

    $('#chat_form').submit(submit_event);
};

$(document).ready(function () {
    var s = new Socket();
    var cf = new ChatForm(s);
    cf.init();
});
