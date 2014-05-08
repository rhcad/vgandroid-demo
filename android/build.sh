#!/bin/sh
# Type './build.sh' to make Android native libraries for the armeabi devices.
# Type './build.sh -B' to rebuild the native libraries.
# Type `./build.sh -swig` to re-generate JNI classes too.
# Type `./build.sh APP_ABI=x86` or `./build.sh APP_ABI=x86 -B` to build for the x86 Emulator.
# Type `./build.sh -swig APP_ABI=x86` to re-generate JNI classes and build for the x86 Emulator.
#
cd ../thirdparty/TouchVG/android; sh build.sh $1 $2; cd ../../../android
cd ../thirdparty/DemoCmds/android; sh build.sh $1 $2; cd ../../../android

cp -vR ../thirdparty/TouchVG/android/TouchVG/libs/armeabi   test/libs
cp -vR ../thirdparty/TouchVG/android/TouchVG/libs/x86       test/libs

cp -vR ../thirdparty/DemoCmds/android/DemoCmds/libs/armeabi test/libs
cp -vR ../thirdparty/DemoCmds/android/DemoCmds/libs/x86     test/libs

cp -vR ../thirdparty/TouchVG/android/TouchVG/libs/armeabi   vgdemo1/libs
cp -vR ../thirdparty/TouchVG/android/TouchVG/libs/x86       vgdemo1/libs
