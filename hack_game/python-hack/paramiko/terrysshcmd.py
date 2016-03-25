#!/usr/bin/env python
import paramiko

def ssh_command(ip,user,passwd,command):
    sshclient = paramiko.SSHClient()
    sshclient.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    sshclient.connect(ip,username=user,password=passwd)
    clientsession = sshclient.get_trasport().open_session()
    if clientsession.active:
        clientsession.exec_command(command)
        print sshclient.recv(1024)

    return

if __name__ == "__main__":
    #ssh_command()
