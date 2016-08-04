/**
 * Created on 7/21/16.
 */

var RosterSocket = function (domain) {
    Socket.call(this);
    this.domain = domain;
    this.uri = "roster";
};

RosterSocket.prototype = Object.create(Socket.prototype);

RosterSocket.prototype.messageHandler = function (event) {
    var message = JSON.parse(event.data);
    switch (message["type"]) {
        case "rosterUpdate":
            var roster = message["visibleRoster"];
            $("#user_roster").html("<div class='roster_item'>" + roster.join("</div><div class='roster_item'>") + "</div>");
            $(".roster_item").unbind('click');
            $(".roster_item").click(function () {
                var thisUserId = $(this).html();
                $("#message_recipient").val(thisUserId);
            });
            break;
        default:
            console.log("ERROR: invalid message type: " + message["type"]);
    }
};

