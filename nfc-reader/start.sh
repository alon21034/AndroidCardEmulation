sudo kill `sudo launchctl list | grep pcscd | grep '^[0-9]\+' -o -m 1`
sudo ./test
