/**
 * Created on 7/21/16.
 */

var ConfigController = function (rosterSocket) {
    this.rosterSocket = rosterSocket;
    this.showing_me = false;
};

ConfigController.prototype.init = function () {
    var thisConfig = this;
    $("#show_me").click(function () {
        var showMe = this.checked;
        var msg = {"senderId" : clientMemory["id"]};
        if (showMe) {
            msg["type"] = "showMe";
            thisConfig.showing_me = true;
        } else {
            msg["type"] = "hideMe";
            thisConfig.showing_me = false;
        }
        thisConfig.rosterSocket.sendMessage(JSON.stringify(msg));
    });
};
