Unofficial Eclipse project
Uses symlinks to the android-studio project

To build the native part run  (ndk9 and ndk10 don't work)

sdkmanager 'ndk-bundle'

export NDK=$ANDROID_SDK/bundle
export ANDROID_NDK=$NDK
export ANDROID_NDK_HOME=$NDK
export PATH=$NDK:$PATH



