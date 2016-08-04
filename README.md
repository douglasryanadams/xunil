Xunil.io wants to provide encrypted anonymous communication tools direct from a browser.

Reference Material
------------------

- https://github.com/bitwiseshiftleft/sjcl/wiki/Asymmetric-Crypto


Current Features:
-----------------

- Targeted Chat
- No usernames, no logins

Bugs:
-----

- [x] Roster not displayed on initial connect
- [x] Roster entries not correctly removed when browser window closes
- [ ] User in active chat connection closes the window, other user is not notified or unlocked

Roadmap:
--------

- [x] Encrypted chat (See communication details below)
    - [x] Generate asynchronous encryption Keys in Browser
    - [x] Enable handshake on server (provide public key to server)
    - [x] Distribute public key to client who chooses recipient
- Allow users to opt in for:
    - [x] Display ID on roster
    - [x] Display own messages in chat
    - [x] Hit enter to send
- Error Handling
    - [ ] Display error states to user with instructions
    - [ ] Alert user if sending to blank recipient
    - [ ] Prevent entering text in chat box until connection

Chat Communication Workflow:
----------------------------

- [x] Client loads page
- [x] Client Generates an Asynchronous Key pair
- [x] Client Generates UUID Seed
- [x] Client sends Public Key and UUID Seed in HTTP Message to Server
- [x] Server uses UUID Seed and its own Random Seed to generate UUID for this client
- [x] Server stores generated UUID and Public Key in associated Memory store (maybe H2 Database)
- [x] Server responds to Client with generated UUID
- [x] Client stores UUID in memory

-- HTTP Handshake Complete --

- [x] Client connects to WebSocket
- [x] Server responds to connection with message 'please register'
- [x] Client responds with previously retrieved UUID
- [x] Server stores client's WebSocket ID with UUID

-- WebSocket Handshake Complete --

-- *Handshake Complete* --

On Disconnect:

- [x] Server receives disconnect message
- [x] Server cleans up associated UUID and Public Key

Chat Client to Client Communication:
------------------------------------

- [x] Client provides ID of recipient via HTTP, asking for public key
- [x] Server asks recipient if they wish to share their public key with client (to 'connect')
- [x] If recipient responds 'Ok', server sends client the recipient's public key 
    - A recipient who accepts request to 'connect', receives an the client's public Key over Websocket
    - Server creates a bidirectional relationship in memory between two clients
- [x] If recipient responds 'no', server sends back 'request declined' message
- [x] If recipient does not exist, server waits random amount of time (1-30 seconds) and declines as above

-- Client to Client handshake Complete --

- [x] Client encrypts a message using the recipient's public key and sends it with the UUID of the recipient to the server
- [ ] The server validates client relationship and sends encrypted data to target recipient
- [x] Recipient uses their private  key to decrypt and display message

Hourly Audit:
-------------

- [ ] Run scheduled thread hourly looking for old connections
    - Clean up old Keys / UUID records
