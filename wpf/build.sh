#!/bin/sh
# Type './build.sh' to generate C# classes.

cd ../../TouchVG/wpf; sh build.sh $1; cd ../../TouchVGTest/wpf
cd ../../DemoCmds/win; sh build.sh $1; cd ../../TouchVGTest/wpf
