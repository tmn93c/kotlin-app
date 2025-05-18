Pod::Spec.new do |s|
  s.name             = 'shared'
  s.version          = '1.0.0'
  s.summary          = 'Shared module for KMM'
  s.description      = 'This is the shared module for the Kotlin Multiplatform Mobile project'
  s.homepage         = 'https://example.com'
  s.license          = { :type => 'MIT', :file => 'LICENSE' }
  s.author           = { 'Your Name' => 'your.email@example.com' }
  s.source           = { :git => 'https://github.com/your/repo.git', :tag => '1.0.0' }
  s.platform         = :ios, '11.0'
  s.source_files     = 'shared/src/iosMain/kotlin/**/*.kt'
  s.dependency 'Kotlin', '~> 1.5'
end
