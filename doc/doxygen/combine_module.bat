set destpath=..\all.tmp\touchvg

del /Q/S  %destpath%

xcopy ..\..\thirdparty\TouchVGCore\core\include\*.* %destpath%\ /Y/S

xcopy ..\..\thirdparty\TouchVGCore\core\src\geom\*.* %destpath%\geom\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\graph\*.* %destpath%\graph\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\shape\*.* %destpath%\shape\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\cmdbase\*.* %destpath%\cmdbase\ /Y

xcopy ..\..\thirdparty\TouchVGCore\core\src\shapedoc\*.* %destpath%\shapedoc\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\cmdbasic\*.* %destpath%\cmdbasic\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\cmdmgr\*.* %destpath%\cmdmgr\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\jsonstorage\*.* %destpath%\jsonstorage\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\export\*.* %destpath%\export\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\record\*.* %destpath%\record\ /Y
xcopy ..\..\thirdparty\TouchVGCore\core\src\view\*.* %destpath%\view\ /Y

xcopy ..\..\thirdparty\TouchVGPlay\core\*.* %destpath%\play\ /Y
xcopy ..\..\thirdparty\TouchVGPlay\ios\include\*.* %destpath%\iosplay\ /Y
xcopy ..\..\thirdparty\TouchVGPlay\ios\src\*.* %destpath%\iosplay\ /Y

xcopy ..\..\thirdparty\DemoCmds\core\gate\*.* %destpath%\democmds\ /Y
xcopy ..\..\thirdparty\DemoCmds\core\cmds\*.* %destpath%\democmds\ /Y

del /Q/S %destpath%\cmdobserver
rem del /Q/S %destpath%\canvas
del /Q/S %destpath%\storage

xcopy ..\..\thirdparty\TouchVG\win32\include\canvas\*.* %destpath%\winview\ /Y
xcopy ..\..\thirdparty\TouchVG\win32\src\canvas\*.* %destpath%\winview\ /Y
xcopy ..\..\thirdparty\TouchVG\win32\include\view\*.* %destpath%\winview\ /Y
xcopy ..\..\thirdparty\TouchVG\win32\src\view\*.* %destpath%\winview\ /Y

xcopy ..\..\thirdparty\TouchVG\ios\include\*.* %destpath%\iosview\ /Y
xcopy ..\..\thirdparty\TouchVG\ios\src\*.* %destpath%\iosview\ /Y

xcopy ..\..\thirdparty\TouchVG\android\TouchVG\src\rhcad\touchvg\*.java %destpath%\andrview\ /Y
xcopy ..\..\thirdparty\TouchVG\android\TouchVG\src\rhcad\touchvg\view\*.* %destpath%\andrview\ /Y/S

xcopy ..\..\thirdparty\TouchVG\wpf\touchvglib\view\*.cs %destpath%\wpfview\ /Y
copy dummy.cpp %destpath%\andrview\
copy dummy.cpp %destpath%\wpfview\
