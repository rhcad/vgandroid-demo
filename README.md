# TouchVGTest

This is a unit test project for [TouchVG](https://github.com/touchvg/TouchVG), which is a lightweight 2D vector drawing framework for iOS, Android and Windows.

![arch](http://touchvg.github.io/images/arch.svg)

## License

This is an open source [LGPL 2.1](LICENSE.md) licensed project. It uses the following open source projects:

- [TouchVG](https://github.com/touchvg/TouchVG) (LGPL): Vector drawing framework for iOS, Android and Windows.
- [TouchVGCore](https://github.com/touchvg/TouchVGCore) (LGPL): Cross-platform vector drawing libraries using C++.
- [SVGKit](https://github.com/SVGKit/SVGKit) (MIT): Display and interact with SVG Images with CoreAnimation on iOS.
- [DemoCmds](https://github.com/touchvg/DemoCmds): A template and example project containing customized shape and command classes.

# How to Compile

## Compile for Android

- Import all projects of this project in eclipse, then run `VGTest` or `vgdemo1` project to view the demonstration.

  - Android SDK version of the projects may need to modify according to your installation.
  - Recommend using the newer [ADT Bundle](http://developer.android.com/sdk/index.html) to avoid complex configuration.

-  To regenerate libtouchvg.so and libdemocmds.so, please enter `android` directory of this project, then type `./build.sh`
(Need to add the [NDK](http://developer.android.com/tools/sdk/ndk/index.html) installation location to your PATH environment variable).

   - Type `./build.sh -B` to rebuild the native libraries.
   
   - Type `./build.sh APP_ABI=x86` or `./build.sh -B APP_ABI=x86` to build for the x86 (Intel Atom) Emulator.

   - If the error `build/gmsl/__gmsl:512: *** non-numeric second argument to wordlist function` occurs, then open the `build/gmsl/__gmsl` file in the NDK installation directory, and change line 512 to:
     `int_encode = $(__gmsl_tr1)$(wordlist 1,$(words $1),$(__gmsl_input_int))`

   - MSYS and TDM-GCC(a MinGW distribution) are recommended on Windows.

   - To regenerate the kernel JNI classes, type `./build.sh-swig`
(Need to install [SWIG](http://sourceforge.net/projects/swig/files/), and add the location to PATH).

## Compile for iOS

-  Open `ios/TestVG.xcworkspace` in Xcode, then run the `TestView` demo app.

   - The `TestView` project has two targets:
   
     - `TestView` target using `libTouchVG.a` does not support SVG display.
     - `TestView-SVG` target using `libTouchVG-SVG.a` and `SVGKit` can display SVG shapes.

   - Static libraries required can be compiled in two ways:
   
        - Enter `ios` directory, then type `./build.sh` (Need to configure Xcode command line environment) to compile all static libraries to the `ios/output` directory.
          - Type `./build.sh -arch arm64` to make for iOS 64-bit.
          - Type `./build.sh clean` to remove object files.
        - Or select and build each library project in Xcode IDE.

   - To run on device, you may need to change the Bundle Identifier of the demo application, such as "com.yourcompany.TestView", and choose your own development certificate (Code Signing).

## Compile for Windows

- Open `wpf/Test_cs10.sln` in Visual Studio 2010 (Need VC++ and C#), then run the`WpfDemo` application. Or open `wpf/Test_cs9.sln` in VS2008.

- To regenerate `wpf/touchvglib/core/*.cs`, please enter `wpf` directory and type `./build.sh`
(Need to install [SWIG](http://sourceforge.net/projects/swig/files/), and add the location to PATH).

## Compile for other platform

- You can compile TouchVG for Python, Perl or Java applications on Linux, MinGW or Mac OS X.

  - Enter `core` directory which contains Makefile, then type the following make command:

     - `Make all install`: compile C + + static library .
     - `Make java`: Jar package and generate dynamic libraries for Java programs.
     - `Make python`, `make perl`: namely Python, Perl , etc. to generate class files and dynamic libraries.
     - `Make clean java.clean python.clean`: delete these temporary files compiled out .

   - MSYS and TDM-GCC(a MinGW distribution) are recommended on Windows.
 
# Add more shapes and commands

- Do not want to write C++ code? Please reference to [android/test/src/vgtest/testview/shape](android/test/src/vgtest/testview/shape) package to write your own shapes and commands.

- You can use thirdparty/newproj.sh to create library project (Recommended as GIT submodule) containing your own shapes and commands. So the TouchVG and TouchVGCore libraries does not require changes.

  - Enter `thirdparty` directory and type `./newproj.sh YourCmds`.
    
  - Need to install python to run the script.
 
- You can customize the drawing behavior via implement your CmdObserver class (see the example in [DemoCmds](https://github.com/touchvg/DemoCmds) ).
