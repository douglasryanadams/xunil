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
            $("#user_roster").html(roster.join("<br>"));
            break;
        default:
            console.log("ERROR: invalid message type: " + message["type"]);
    }
};

