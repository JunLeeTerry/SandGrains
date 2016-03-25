#!/usr/bin/env python 
import sys
import threading 
import getopt
import socket




        

def main():

    ##-----inner methods part ---------
    def hexdump(src,length=16):
        result = []
        digits = 4 if isinstance(src,unicode) else 2
    
        for i in xrange(0,len(src),length):
            s = src[i:i+length]
            hexa = b' '.join(["%0*X" % (digits,ord(x)) for x in s])
            text = b''.join([x if 0x20 <= ord(x) < 0x7F else b'.' for x in s])
            result.append(b"%04X  %-*s  %s" % (i,length*(digits+1),hexa,text))
            print b'\n'.join(result)
            
    def receive_from(connection):
        buffer = ""
        connection.settimeout(2)
        try:
            while True:
                data = connection.recv(4096)
                
                if not data:
                    break

                buffer += data

        except e:
            print "[!!] Can not receive data!!"
            
        return buffer

    def response_handler(remote_buffer):
        return remote_buffer

    def request_handler(client_buffer):
        return client_buffer

    def proxy_handler(clientsocket,remotehost,remoteport,receive_first):
        remotesocket = socket.socke(socket.AF_INET,socket.SOCK_STREAM)
        remotesocket.connect((remotehost,remoteport))
        if receive_first:
            remote_buffer = receive_from(remotesocket)
            hexdump(remote_buffer)

            remote_buffer = response_handler(remote_buffer)
            
            if len(remote_buffer):
                print "[<==] Sending %d bytes to localhost." % len(remote_buffer)
                clientsocket.send(remote_buffer)
                
            while True:
                local_buffer = receive_from(clientsocket)
                if len(local_buffer):
                    print "[==>] Received %d bytes from localhost." % len(local_buffer)
                    hexdump(local_buffer)
                    
                    local_buffer = requset_handler(local_buffer)
                    remotesocket.send(local_buffer)
                    print "[==>] Send to Remote."

                remote_buffer = receive_from(remotesocket)
                if len(remote_buffer):
                    print "[<==] Sending %d bytes to localhost." % len(remote_buffer)
                    hexdump(remote_buffer)
                    
                    remote_buffer = response_hanler(remote_buffer)
                    clientsocket.send(remote_buffer)
                    print "[<==] Send to localhost."
                    
                if not len(local_buffer) or not len(remote_buffer):
                    clientsocket.close()
                    remotesocket.close()
                    print "[*] No more data. Closing the connections."
                    break;
            
                

    def server_loop(localhost,localport,remotehost,remoteport,received_first):
        server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        try:
            server.bind((localhost,localport))
        except:
            print "[!!] Failed to listen on %s:%d" % (localhost,localport)
            print "[!!] Check for another socket or correct permissions!"
            sys.exit(0)
        print "[*] Listening on %s:%d" % (localhost,localport)

        server.listen(5)
    
        while True:
            clientsocket,addrs = server.accept()
        
            print "[==>] Received incoming connection from %s:%d" % (addrs[0],addrs[1])
        
            proxythread = threading.Thread(target=proxy_hendler,args=(clientsocket,remotehost,remoteport,receive_first))
            proxythread.start()

    ##---------------------------------

    if len(sys.argv[1:]) != 5:
        print "Usage: ./tcpproxy.py [localhost] [localport] [remotehost] [remoteport] [receive_first]"
        print "Example: ./tcpproxy.py 127.0.0.1 9000 12.12.132.1 9000 True"
        sys.exit(0)

    localhost = sys.argv[1]
    localport = int(sys.argv[2])

    remotehost = sys.argv[3]
    remoteport = int(sys.argv[4])

    receive_first = sys.argv[5]

    if "Ture" in receive_first:
        receive_first = True
    else:
        receive_first = False

    server_loop(localhost,localport,remotehost,remoteport,receive_first)


if __name__ == "__main__":
    main()
