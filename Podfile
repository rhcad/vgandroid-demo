platform :ios, '6.0'
xcodeproj 'ios/tests/TestView/TestView.xcodeproj'

target "TestView" do
  pod 'TouchVG', '~> 1.1.10'
  pod 'DemoCmds', :podspec => 'ios/podspec/DemoCmds.podspec'
  target "TestView-SVG" do
    pod 'SVGKit', :git => 'https://github.com/SVGKit/SVGKit.git'
  end
end
