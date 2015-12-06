#!/usr/bin/env python
import sys
import getopt
import socket
import subprocess
import threading

listen                   = False
command                  = False
upload                   = False
execute                  = ''
target                   = ''
upload_destination       = ''
port                     = 0

def usage():
    print "Terry Net Tool"
    print 
    print "Usage:terrynet.py -t target_host -p port"
    print "-l --listen                             - listen on [host]:[port] for incoming connections"
    print "-e --execute=file_to_run                - execute the given file upon receiving aconnecting"
    print "-c --command                            - initialize a command shell"
    print "-u --upload=destination                 - upon receiving connection upload a file and write to [destination]"
    print 
    print 
    print "Examples: "
    print "terrynet.py -t 192.168.0.1 -p 5555 -l -c"
    print "terrynet.py -t 192.168.0.1 -p 5555 -l -u=c:\\target.exe"
    print "terrynet.py -t 192.168.0.1 -p 5555 -l -e=\"cat /etc/passwd\""
    print "echo 'ABCDEFG' | ./terrynet.py -t 192.168.11.12 -p 135"
    sys.exit(0)
    
def main():
    global listen
    global command
    global execute
    global target
    global upload_destination
    global port

    ##---------methods part----------
    def client_sender(buffer):
        client = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        try:
            print len(target)
            client.connect((target,port))
            
            if len(buffer):
                client.send(buffer)
            
            while True:
                recv_len = 1
                response = ''
                while recv_len:
                    data = client.recv(4096)
                    recv_len = len(data)
                    response += data
                    if recv_len<4096:
                        break
                print response,
                    
                #buffer = sys.stdin.read()
                buffer = raw_input("")
                buffer += "\n\r"
                client.send(buffer)

        except:
            print '[*] Exception! Exiting...'


    def server_loop():
        global target

        server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        if not len(target):
            target = '0.0.0.0'
            
        server.bind((target,port))
        server.listen(5)

        while True:
            client,addr = server.accept()
            client_thread = threading.Thread(target=client_handler,args=(client,))
            client_thread.start()

    def run_command(command):
        command = command.rstrip()
        try:
            output = subprocess.check_output(command,stderr=subprocess.STDOUT,shell=True)
        except:
            output = "Failed to execute command. \r\n"
        return output
    
    def client_handler(clientsocket):
        global upload
        global execute
        global command 
        
        if len(upload_destination):
            filebuffer = ''
            while True:
                data = clientsocket.revc(1024)
                               
                if not data:
                    break
                else:
                    filebuffer += data
            
            try:
                file_descriptor = open(upload_destination,'wb')
                file_descriptor.write(filebuffer)
                file_descriptor.close()

                clientsocket.send("Successfully save file to %s\r\n" % upload_destination)
                
            except:
                clientsocket.send("Failed to save file to %s\r\n" % upload_destination)
                
        if len(execute):
            ouput = run_command(execute)
            clientsocket.send(outout)
        
        #print command
        if command:
            while True:
                clientsocket.send("<TERRY:#> ")
                
                cmd_buffer = ""
                while "\n" not in cmd_buffer:
                    cmd_buffer += clientsocket.recv(1024)
                response = run_command(cmd_buffer)
                clientsocket.send(response)

    ##---------------------------------

    if not len(sys.argv[1:]):
        usage()

    try:
        opts,args = getopt.getopt(sys.argv[1:],'hle:cu:t:p:',['help','listen','execute','command','upload','target=','port='])
        #print args
        #print opts
    except getopt.GetoptError as err:
        print str(err)
        usage()

    for o,a in opts:
        if o in ('-h','--help'):
            usage()
        elif o in ('-l','--listen'):
            listen = True
        elif o in ('-e','--execute'):
            execute = a
        elif o in ('-c','--command'):
            command = True
        elif o in ('-u','--upload'):
            upload_destination = a
        elif o in ('-t','--target'):
            target = a
        elif o in ('-p','--port'):
            port = int(a)
        else:
            assert False,"Unhandled Options."

    if not listen and len(target) and port > 0:
        buffer = sys.stdin.read()
        client_sender(buffer)
    if listen:
        server_loop()

    
        
            


if __name__=="__main__":
    main()


