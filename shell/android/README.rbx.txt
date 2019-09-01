Unofficial Eclipse project
Uses symlinks to the android-studio project

To build the native part run  (ndk9 and ndk10 don't work)

sdkmanager 'ndk-bundle'

export NDK=$ANDROID_HOME/ndk-bundle
export ANDROID_NDK=$NDK
export ANDROID_NDK_HOME=$NDK
export PATH=$NDK:$PATH

if needed, follow build steps on .travis.yml

