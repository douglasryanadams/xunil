/**
 * Created on 7/31/16.
 *
 * I would have preferred to keep this a more conventional class, requiring instantiation
 * so that I could implement it differently in a testing scenario, but I'm sacrificing
 * that for ease of use and to get around a confusing memory situation inside
 * ChatSocket.messageHandler where 'this' becomes the socket itself, not the ChatSocket
 * class and makes it difficult (perhaps impossible?) to reference any class attributes of
 * ChatSocket instances from within messageHandler.
 */


var ChatController = {};

ChatController.displayMessage = function (message) {
    var chatDisplay = $("#chat_display");
    chatDisplay.append(
        message + "<br>"
    );
    chatDisplay.scrollTop(chatDisplay.prop("scrollHeight"));
};

ChatController.displayMyMessage = function (message) {
    this.displayMessage("<span class='my_own_message'>" + message + "</span>");
};

ChatController.displayInfoMessage = function (message) {
    this.displayMessage("<span class='info_message'>" + message + "</span>");
};

ChatController.displaySuccessMessage = function (message) {
    this.displayMessage("<span class='success_message'>" + message + "</span>");
};

ChatController.displayErrorMessage = function (message) {
    this.displayMessage("<span class='error_message'>" + message + "</span>");
};
