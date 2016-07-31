/**
 * Created on 7/21/16.
 */

var KeyMaster = {};

KeyMaster.generateKeys = function () {
    var keys = sjcl.ecc.ecdsa.generateKeys(521);
    return {
        "publicKey" : keys["pub"],
        "privateKey" : keys["sec"]
    }
};

KeyMaster.encryptMessage = function (publicKey, message) {
    return sjcl.encrypt(publicKey, message);
};

KeyMaster.decryptMessage = function (privateKey, encryptedMessage) {
    return sjcl.decrypt(privateKey, encryptedMessage);
};
