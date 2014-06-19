#!/bin/sh
# Type './build.sh' to generate C# classes.

cd ..; sh clone.sh; cd wpf
cd ../../TouchVG/wpf; sh build.sh $1; cd ../../TouchVGTest/wpf
cd ../../DemoCmds/win; sh build.sh $1; cd ../../TouchVGTest/wpf
