#!/bin/sh
# Type './newproj.sh PrjName' or './newproj.sh' to create a project based on DemoCmds project.
#
cd DemoCmds; python newproj.py $1; cd ..
