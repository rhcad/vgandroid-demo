#!/bin/sh
# Type './build.sh' to make Android native libraries for the armeabi devices.
# Type './build.sh -B' to rebuild the native libraries.
# Type `./build.sh -swig` to re-generate JNI classes too.
# Type `./build.sh APP_ABI=x86` or `./build.sh APP_ABI=x86 -B` to build for the x86 Emulator.
# Type `./build.sh -swig APP_ABI=x86` to re-generate JNI classes and build for the x86 Emulator.
#

if [ ! -f ../vgcore/android/build.sh ] ; then
    git clone https://github.com/touchvg/vgcore ../vgcore
fi
if [ ! -f ../vgandroid/build.sh ] ; then
    git clone https://github.com/touchvg/vgandroid ../vgandroid
fi
if [ ! -f ../DemoCmds/android/build.sh ] ; then
    git clone https://github.com/touchvg/DemoCmds ../DemoCmds
fi

cd ../vgandroid; sh build.sh $1 $2; cd ../vgandroid-demo
cd ../DemoCmds/android; sh build.sh $1 $2; cd ../../vgandroid-demo

#mkdir -p test/libs
#cp -vR ../vgandroid/TouchVG/libs/armeabi                test/libs
#cp -vR ../vgandroid/TouchVG/libs/x86                    test/libs
#cp -vR ../DemoCmds/android/DemoCmds/libs/armeabi        test/libs
#cp -vR ../DemoCmds/android/DemoCmds/libs/x86            test/libs

#mkdir -p vgdemo1/libs
#cp -vR ../vgandroid/TouchVG/libs/armeabi                vgdemo1/libs
#cp -vR ../vgandroid/TouchVG/libs/x86                    vgdemo1/libs

mkdir -p output/armeabi
cp -vR ../vgandroid/TouchVG/bin/touchvg.jar             output
cp -vR ../vgandroid/TouchVG/libs/armeabi                output
cp -vR ../vgandroid/TouchVG/libs/x86                    output
cp -vR ../DemoCmds/android/DemoCmds/bin/democmds.jar    output
cp -vR ../DemoCmds/android/DemoCmds/libs/armeabi        output
cp -vR ../DemoCmds/android/DemoCmds/libs/x86            output
