set destpath=..\all.tmp\touchvg

del /Q/S  %destpath%

xcopy ..\..\..\TouchVGCore\core\include\*.* %destpath%\ /Y/S

xcopy ..\..\..\TouchVGCore\core\src\geom\*.* %destpath%\geom_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\graph\*.* %destpath%\graph_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\shape\*.* %destpath%\shape_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\cmdbase\*.* %destpath%\cmdbase_src\ /Y

xcopy ..\..\..\TouchVGCore\core\src\shapedoc\*.* %destpath%\shapedoc_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\cmdbasic\*.* %destpath%\cmdbasic_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\cmdmgr\*.* %destpath%\cmdmgr_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\jsonstorage\*.* %destpath%\jsonstorage_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\export\*.* %destpath%\export_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\record\*.* %destpath%\record_src\ /Y
xcopy ..\..\..\TouchVGCore\core\src\view\*.* %destpath%\view_src\ /Y

xcopy ..\..\..\TouchVGPlay\core\*.h %destpath%\play\ /Y
xcopy ..\..\..\TouchVGPlay\core\*.cpp %destpath%\play_src\ /Y
xcopy ..\..\..\TouchVGPlay\ios\include\*.* %destpath%\iosplay\ /Y
xcopy ..\..\..\TouchVGPlay\ios\src\*.* %destpath%\iosplay_src\ /Y

xcopy ..\..\..\DemoCmds\core\gate\*.* %destpath%\democmds\ /Y
xcopy ..\..\..\DemoCmds\core\cmds\*.* %destpath%\democmds\ /Y

xcopy ..\..\..\TouchVG\win32\include\canvas\*.* %destpath%\winview\ /Y
xcopy ..\..\..\TouchVG\win32\src\canvas\*.* %destpath%\winview_src\ /Y
xcopy ..\..\..\TouchVG\win32\include\view\*.* %destpath%\winview\ /Y
xcopy ..\..\..\TouchVG\win32\src\view\*.* %destpath%\winview_src\ /Y

xcopy ..\..\..\TouchVG\ios\include\*.* %destpath%\iosview\ /Y
xcopy ..\..\..\TouchVG\ios\src\*.* %destpath%\iosview_src\ /Y

xcopy ..\..\..\TouchVG\android\TouchVG\src\rhcad\touchvg\*.java %destpath%\andrview\ /Y
xcopy ..\..\..\TouchVG\android\TouchVG\src\rhcad\touchvg\view\*.* %destpath%\andrview\ /Y/S

xcopy ..\..\..\TouchVG\wpf\touchvglib\view\*.cs %destpath%\wpfview\ /Y
