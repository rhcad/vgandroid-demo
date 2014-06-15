#!/bin/sh
# Type './clone.sh' to clone libraries needed.

if [ ! -f ../TouchVGCore/ios/build.sh ] ; then
    git clone https://github.com/touchvg/TouchVGCore ../TouchVGCore
fi
if [ ! -f ../TouchVG/ios/build.sh ] ; then
    git clone https://github.com/touchvg/TouchVG ../TouchVG
fi
if [ ! -f ../DemoCmds/ios/build.sh ] ; then
    git clone https://github.com/touchvg/DemoCmds ../DemoCmds
fi
#if [ ! -f ../SVGKit/SVGKit.podspec ] ; then
#git clone https://github.com/SVGKit/SVGKit ../SVGKit
#fi
