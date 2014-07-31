# TouchVG Demo for Android

This is a unit test and example project for [TouchVG](https://github.com/touchvg/vgandroid), which is a lightweight 2D vector drawing framework for Android.

![arch](http://touchvg.github.io/images/arch.svg)

## How to Compile

- Don't want to build libtouchvg.so and libdemocmds.so ?

  1. Download the [prebuilt libraries](https://github.com/touchvg/vgandroid/archive/prebuilt.zip).
  2. Extract `touchvg_libs` in the zip package to `vgandroid/TouchVG/libs`.
  3. Extract `democmds_libs` in the zip package to `DemoCmds/android/DemoCmds/libs`.

- Enter the directory of this project, then type `./build.sh` to clone and build libraries needed.
  
  - Need to add the [NDK](http://developer.android.com/tools/sdk/ndk/index.html) installation location to PATH.
  
  - If the error `build/gmsl/__gmsl:512: *** non-numeric second argument to wordlist function` occurs, then open the `build/gmsl/__gmsl` file in the NDK installation directory, and change line 512 to:
     `int_encode = $(__gmsl_tr1)$(wordlist 1,$(words $1),$(__gmsl_input_int))`

   - MSYS is recommended on Windows to run UNIX commands.

- Import all projects (touchvg, democmds, vgdemo1 and VGTest) in eclipse, then run `VGTest` or `vgdemo1` project to view the demonstration.

  - Android SDK version of the projects may need to modify according to your installation.
  
  - Recommend using the newer [ADT Bundle](http://developer.android.com/sdk/index.html) to avoid complex configuration.

-  Regenerate libtouchvg.so and JNI classes:

   - Type `./build.sh -B` to rebuild the native libraries.
   
   - Type `./build.sh APP_ABI=x86` to build for the x86 (Intel Atom) Emulator.
   
   - Type `./build.sh -swig` to regenerate the kernel JNI classes.
   
   - Need to install the lastest version of [SWIG](http://sourceforge.net/projects/swig/files/) 3.0, and add the location to PATH on Windows. SWIG 2.x may can't parse UTF-8 header files on Windows.

## How to Debug native code

  - Add `#include "mglog.h"` and use `LOGD("your message %d", someint)` in the C++ files needed to debug.
  
  - Set LogCat filter in Eclipse: `tag:dalvikvm|AndroidRuntime|vgjni|touchvg|vgstack|libc|DEBUG`.
  
  - Print JNI functions to locate problems of libc crash:
    1. Add `python addlog.py` in `TouchVG/jni/build.sh`.
    2. Type `./build.sh -swig` to add log in all JNI entry functions, or remove `touchvg_java_wrap.cpp` and type `./build.sh`.
 
## Add more shapes and commands

- Do not want to write C++ code? Please reference to [test/src/vgtest/testview/shape](test/src/vgtest/testview/shape) package to write your own shape and command classes.

- You can create library project containing your own shapes and commands. So the TouchVG and TouchVGCore libraries does not require changes.

  - Checkout and enter [DemoCmds](https://github.com/touchvg/DemoCmds) directory, then type `python newproj.py YourCmds`:

     ```shell
     git clone https://github.com/touchvg/DemoCmds.git
     cd DemoCmds
     python newproj.py MyCmds
     ```

- You can customize the drawing behavior via implement your CmdObserver class (see the example in [DemoCmds](https://github.com/touchvg/DemoCmds) ).

## License

This is an open source [LGPL 2.1](LICENSE.md) licensed project. It uses the following open source projects:

- [TouchVG](https://github.com/touchvg/vgandroid) (LGPL): Vector drawing framework for Android.
- [TouchVGCore](https://github.com/touchvg/vgcore) (LGPL): Cross-platform vector drawing libraries using C++.
- [DemoCmds](https://github.com/touchvg/DemoCmds): A template and example project containing customized shape and command classes.

## How to Contribute

Contributors and sponsors are welcome. You may translate, commit issues or pull requests on this Github site.
To contribute, please follow the branching model outlined here: [A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/).

Welcome to the Chinese QQ group `192093613` to discuss and share.