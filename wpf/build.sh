#!/bin/sh
# Type './build.sh' to generate C# classes.

cd ../thirdparty/TouchVG/wpf; sh build.sh $1; cd ../../../wpf
cd ../thirdparty/DemoCmds/win; sh build.sh $1; cd ../../../wpf
