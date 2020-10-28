# JitsiPolestar

Jitsi [Link](https://jitsi.github.io/handbook/docs/dev-guide/dev-guide-android-sdk)


When i add Jitsi and Polestar together in android project, it starts causing issues during build time.
```
More than one file was found with OS independent path 'lib/x86/libc++_shared.so'. 
If you are using jniLibs and CMake IMPORTED targets, 
see https://developer.android.com/studio/preview/features#automatic_packaging_of_prebuilt_dependencies_used_by_cmake
```

Then i add below lines in my build.gradle file to keep only one
```
    packagingOptions {
        pickFirst 'lib/x86/libc++_shared.so'
        pickFirst 'lib/arm64-v8a/libc++_shared.so'
        pickFirst 'lib/x86_64/libc++_shared.so'
        pickFirst 'lib/armeabi-v7a/libc++_shared.so'
    }
```

It lets me build the project but then i start receiving run time exception
```
E/NaoContext: naojni : library not found! -- java.lang.UnsatisfiedLinkError: dlopen failed: cannot locate symbol "__emutls_get_address" referenced by "/data/app/au.com.rauland.internal.pigeon.dev-2/lib/arm/libnaojni.so"...
W/com.polestar.helpers.a: Device model : "Moto-G--4-"
E/art: No implementation found for com.polestar.naosdk.api.INAOServiceManager com.polestar.naosdk.api.INAOServiceManager.createInstance(com.polestar.naosdk.api.ISensorProxyFactory, com.polestar.naosdk.api.IPlatformThreadFactory, com.polestar.naosdk.api.ISynchroBroker, com.polestar.naosdk.api.IHttpHelper, com.polestar.naosdk.api.IPrefHelper, com.polestar.naosdk.api.IConnectivityHelper, com.polestar.naosdk.api.DeviceInfo, java.lang.String) (tried Java_com_polestar_naosdk_api_INAOServiceManager_createInstance and Java_com_polestar_naosdk_api_INAOServiceManager_createInstance__Lcom_polestar_naosdk_api_ISensorProxyFactory_2Lcom_polestar_naosdk_api_IPlatformThreadFactory_2Lcom_polestar_naosdk_api_ISynchroBroker_2Lcom_polestar_naosdk_api_IHttpHelper_2Lcom_polestar_naosdk_api_IPrefHelper_2Lcom_polestar_naosdk_api_IConnectivityHelper_2Lcom_polestar_naosdk_api_DeviceInfo_2Ljava_lang_String_2)
D/AndroidRuntime: Shutting down VM
E/AndroidRuntime: FATAL EXCEPTION: main
    Process: au.com.rauland.internal.pigeon.dev, PID: 26503
    java.lang.UnsatisfiedLinkError: No implementation found for com.polestar.naosdk.api.INAOServiceManager com.polestar.naosdk.api.INAOServiceManager.createInstance(com.polestar.naosdk.api.ISensorProxyFactory, com.polestar.naosdk.api.IPlatformThreadFactory, com.polestar.naosdk.api.ISynchroBroker, com.polestar.naosdk.api.IHttpHelper, com.polestar.naosdk.api.IPrefHelper, com.polestar.naosdk.api.IConnectivityHelper, com.polestar.naosdk.api.DeviceInfo, java.lang.String) (tried Java_com_polestar_naosdk_api_INAOServiceManager_createInstance and Java_com_polestar_naosdk_api_INAOServiceManager_createInstance__Lcom_polestar_naosdk_api_ISensorProxyFactory_2Lcom_polestar_naosdk_api_IPlatformThreadFactory_2Lcom_polestar_naosdk_api_ISynchroBroker_2Lcom_polestar_naosdk_api_IHttpHelper_2Lcom_polestar_naosdk_api_IPrefHelper_2Lcom_polestar_naosdk_api_IConnectivityHelper_2Lcom_polestar_naosdk_api_DeviceInfo_2Ljava_lang_String_2)
        at com.polestar.naosdk.api.INAOServiceManager.createInstance(Native Method)
        at com.polestar.naosdk.managers.NaoContext.<init>(Unknown Source)
        at com.polestar.naosdk.managers.NaoContext.<init>(Unknown Source)
        at com.polestar.naosdk.managers.NaoServiceManager.onBind(Unknown Source)
        at android.app.ActivityThread.handleBindService(ActivityThread.java:3210)
        at android.app.ActivityThread.-wrap3(ActivityThread.java)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1568)
        at android.os.Handler.dispatchMessage(Handler.java:102)
        at android.os.Looper.loop(Looper.java:154)
        at android.app.ActivityThread.main(ActivityThread.java:6123)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:867)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:757)
```
