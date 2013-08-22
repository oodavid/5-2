== SUBLIME-TEXT ANDROID ==

	How to create a new project

		http://developer.android.com/tools/projects/projects-cmdline.html#CreatingAProject

			# ti config -- should give us all the variables we need for this

			# cd ~/Documents/
			# ~/Development/android-sdk-linux/tools/android create project --target 1 --name MyAndroidApp --path /home/david/Documents/5-2 --activity MyAndroidAppActivity --package com.example.myandroid

	Building in debug mode

		http://developer.android.com/tools/building/building-cmdline.html#DebugMode

			# ant debug

	Installing on a device

		http://developer.android.com/tools/building/building-cmdline.html#RunningOnDevice

			# ~/Development/android-sdk-linux/platform-tools/adb -d install -r ./bin/MyAndroidApp-debug.apk

	Opening the app

		http://stackoverflow.com/questions/4567904/how-to-start-an-application-using-android-adb-tools

			# ~/Development/android-sdk-linux/platform-tools/adb shell am start -n com.example.myandroid/.MyAndroidAppActivity

== NOTES FOR AUTOMATION ==

# To build
ant debug

# To install
~/Development/android-sdk-linux/platform-tools/adb -d install -r ./bin/MyAndroidApp-debug.apk

# To open
~/Development/android-sdk-linux/platform-tools/adb shell am start -n com.example.myandroid/.MyAndroidAppActivity

# Open Debugger if not already open
if ps -e | grep "monitor$" > /dev/null
then
    echo "Running"
else
    echo "Stopped"
fi