#!/usr/bin/env python
import paramiko
import threading
import socket
import sys

hostkey = ""

class Server(paramiko.ServerInterface):
    def __init__(self):
        self.event = threading.Event()
    def check_channel_request(self,kind,chanid):
        if kind == "session":
            return paramiko.OPEN_SUCCEEDED
        else:
            return paramiko.OPEN_FAILED_ADMINISTRATIVELY_PROHIBITED
    def check_auth_password(self,username,password):
        if (username == "terry") and (password == "123456"):
            return paramiko.AUTH_SUCCESSFULLY
        else:
            return paramiko.FAILED

server = sys.argv[1]
ssh_port = int(sys.argv[2])

try:
    sock = socket.socket(socket.AF_INET,socket.AF_STREAM)
    sock.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
    sock.bind((server,sshport))
    sock.listen(100)
    print "[+] Listening from connection ..."
    client,addr = sock.accept()
except Exception,e:
    print "[-] Listen failed: %s" % str(e)
    sys.exit(1)
print "[+] Got a connection !!"

try:
    session = paramiko.Transport(client)
    session.add_server_key(hostkey)
    server = Server()
    try:
        session.start_server(session=session)
    except paramiko.SSHException,e:
        print "[-] SSH negotiation failed"
    chan = session.accept(20)
    print "[+] Aurhenticated!"
    print chan.recv(1024)
    chan.send("Welcome to bh_ssh")
    
    while True:
        try:
            command = raw_input("Enter the command: ").strip('\n')
            if command != "exit":
                chan.send(command)
                print chan.recv(1024) + '\n'
            else:
                chan.send("exit")
                print "exiting"
                session.close()
                raise Exception ('exit')
        except KeyboardInterrupt:
            session.close()
except Exception,e:
    print "[-] Caught exception: " + str(e)
    try:
        session.close()
    except:
        pass
    sys.exit(1)

                
