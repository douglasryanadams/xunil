/**
 * Created on 7/21/16.
 */

var KeyMaster = {};

KeyMaster.generateKeys = function () {
    var pair = sjcl.ecc.elGamal.generateKeys(256);
    return {
        "publicKey": pair.pub,
        "privateKey": pair.sec
    }
};

KeyMaster.encryptMessage = function (publicKey, message) {
    return sjcl.encrypt(publicKey, message);
};

KeyMaster.decryptMessage = function (privateKey, encryptedMessage) {
    return sjcl.decrypt(privateKey, encryptedMessage);
};

KeyMaster.getPublicKeyAsString = function (publicKeyBits) {
    return sjcl.codec.base64.fromBits(publicKeyBits.get().x.concat(publicKeyBits.get().y))
};

KeyMaster.getPublicKeyAsBits = function (publicKeyString) {
    return new sjcl.ecc.elGamal.publicKey(
        sjcl.ecc.curves.c256,
        sjcl.codec.base64.toBits(publicKeyString)
    );
};
