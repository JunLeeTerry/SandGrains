#!/usr/bin/env python
import socket

serverhost = "127.0.0.1"
serverport = 9999

tcpclient = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
tcpclient.connect((serverhost,serverport))

tcpclient.send("GET / HTTP1.1\r\nHOST: baidu.com\r\n\r\n")
response = tcpclient.recv(4096)

print response
