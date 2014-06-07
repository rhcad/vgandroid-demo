set destpath=..\all.tmp\touchvg

del /Q/S  %destpath%

xcopy ..\..\..\TouchVGCore\core\include\*.* %destpath%\ /Y/S

xcopy ..\..\..\TouchVGCore\core\src\geom\*.* %destpath%\geom\ /Y
xcopy ..\..\..\TouchVGCore\core\src\graph\*.* %destpath%\graph\ /Y
xcopy ..\..\..\TouchVGCore\core\src\shape\*.* %destpath%\shape\ /Y
xcopy ..\..\..\TouchVGCore\core\src\cmdbase\*.* %destpath%\cmdbase\ /Y

xcopy ..\..\..\TouchVGCore\core\src\shapedoc\*.* %destpath%\shapedoc\ /Y
xcopy ..\..\..\TouchVGCore\core\src\cmdbasic\*.* %destpath%\cmdbasic\ /Y
xcopy ..\..\..\TouchVGCore\core\src\cmdmgr\*.* %destpath%\cmdmgr\ /Y
xcopy ..\..\..\TouchVGCore\core\src\jsonstorage\*.* %destpath%\jsonstorage\ /Y
xcopy ..\..\..\TouchVGCore\core\src\export\*.* %destpath%\export\ /Y
xcopy ..\..\..\TouchVGCore\core\src\record\*.* %destpath%\record\ /Y
xcopy ..\..\..\TouchVGCore\core\src\view\*.* %destpath%\view\ /Y

xcopy ..\..\..\TouchVGPlay\core\*.* %destpath%\play\ /Y
xcopy ..\..\..\TouchVGPlay\ios\include\*.* %destpath%\iosplay\ /Y
xcopy ..\..\..\TouchVGPlay\ios\src\*.* %destpath%\iosplay\ /Y

xcopy ..\..\..\DemoCmds\core\gate\*.* %destpath%\democmds\ /Y
xcopy ..\..\..\DemoCmds\core\cmds\*.* %destpath%\democmds\ /Y

del /Q/S %destpath%\cmdobserver
rem del /Q/S %destpath%\canvas
del /Q/S %destpath%\storage

xcopy ..\..\..\TouchVG\win32\include\canvas\*.* %destpath%\winview\ /Y
xcopy ..\..\..\TouchVG\win32\src\canvas\*.* %destpath%\winview\ /Y
xcopy ..\..\..\TouchVG\win32\include\view\*.* %destpath%\winview\ /Y
xcopy ..\..\..\TouchVG\win32\src\view\*.* %destpath%\winview\ /Y

xcopy ..\..\..\TouchVG\ios\include\*.* %destpath%\iosview\ /Y
xcopy ..\..\..\TouchVG\ios\src\*.* %destpath%\iosview\ /Y

xcopy ..\..\..\TouchVG\android\TouchVG\src\rhcad\touchvg\*.java %destpath%\andrview\ /Y
xcopy ..\..\..\TouchVG\android\TouchVG\src\rhcad\touchvg\view\*.* %destpath%\andrview\ /Y/S

xcopy ..\..\..\TouchVG\wpf\touchvglib\view\*.cs %destpath%\wpfview\ /Y
copy dummy.cpp %destpath%\andrview\
copy dummy.cpp %destpath%\wpfview\
