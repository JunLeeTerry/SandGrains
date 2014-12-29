### BEGIN INIT INFO
# Provides: tightvnc
# Required-Start: $remote_fs $syslog
# Required-Stop: $remote_fs $syslog
# Default-Start: 2 3 4 5
# Default-Stop: 0 1 6
# Short-Description: Start VNC Server as a service
# Description: Start VNC Server as a service.
### END INIT INFO
#!/bin/sh
# /etc/init.d/tightvncserver
# Customised by raspicndotcom
#http://www.penguintutor.com/linux/tightvnc
# Set the VNCUSER variable to the name of the user to start tightvncserver under
VNCUSER='pi'
eval cd ~$VNCUSER
case "$1" in
start)
   su $VNCUSER -c '/usr/bin/tightvncserver :1  -geometry 1024x640 -depth 16 -pixelformat rgb565'
   echo "Starting TightVNC server for $VNCUSER "
   ;;
stop)
   pkill Xtightvnc
   echo "Tightvncserver stopped"
   ;;
*)
   echo "Usage: /etc/init.d/tightvncserver {start|stop}"
   exit 1
   ;;
esac
exit 0
#

