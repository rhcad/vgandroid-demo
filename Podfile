platform :ios, '6.0'
xcodeproj 'ios/tests/TestView/TestView.xcodeproj'

target "TestView" do
  pod 'TouchVG', :podspec => 'https://raw.githubusercontent.com/touchvg/TouchVG/develop/TouchVG.podspec'
  pod 'DemoCmds', :podspec => 'ios/podspec/DemoCmds.podspec'
  target "TestView-SVG" do
    pod 'SVGKit', :podspec => 'https://raw.githubusercontent.com/SVGKit/SVGKit/1.x/SVGKit.podspec'
  end
end
