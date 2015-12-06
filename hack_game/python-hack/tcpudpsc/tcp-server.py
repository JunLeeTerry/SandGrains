#!/usr/bin/env python

import socket
import threading

bindip = "0.0.0.0"
bindport = 9999

tcpserver = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

tcpserver.bind((bindip,bindport))
##----the largest number of connection------
tcpserver.listen(5)

print '[*] Listening on %s:%d' % (bindip,bindport)

def handle_client(client_socket):
    request = client_socket.recv(1024)    
    print '[*] Received: %s' % request
    client_socket.send("ACK!")
    client_socket.close()

while True:
    client,addr = tcpserver.accept()
    print '[*] Accepted connection from %s:%d' % (addr[0],addr[1])

    clienthandler = threading.Thread(target=handle_client,args=(client,))
    clienthandler.start()

