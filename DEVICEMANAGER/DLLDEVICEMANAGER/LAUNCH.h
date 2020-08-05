/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class START_LAUNCH */

#ifndef _Included_START_LAUNCH
#define _Included_START_LAUNCH
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     START_LAUNCH
 * Method:    DvmgStartConfigureDevicesN
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_DvmgStartConfigureDevicesN
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     START_LAUNCH
 * Method:    DvmgStartConfigureDevicesNClose
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_DvmgStartConfigureDevicesNClose
  (JNIEnv *, jobject);

/*
 * Class:     START_LAUNCH
 * Method:    DvmgGetDevCountN
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_DvmgGetDevCountN
  (JNIEnv *, jobject);

/*
 * Class:     START_LAUNCH
 * Method:    DvmgGetDevNameN
 * Signature: (II)Z
 */
JNIEXPORT jboolean JNICALL Java_START_LAUNCH_DvmgGetDevNameN
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     START_LAUNCH
 * Method:    SendComand
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_SendComand
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     START_LAUNCH
 * Method:    SendDlgItemComand
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_SendDlgItemComand
  (JNIEnv *, jobject, jint, jint, jint, jint);

/*
 * Class:     START_LAUNCH
 * Method:    FreePtrChar
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_FreePtrChar
  (JNIEnv *, jobject, jint);

/*
 * Class:     START_LAUNCH
 * Method:    MallocPtrChar
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_START_LAUNCH_MallocPtrChar
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     START_LAUNCH
 * Method:    GetValuePtr
 * Signature: (II)C
 */
JNIEXPORT jchar JNICALL Java_START_LAUNCH_GetValuePtr
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     START_LAUNCH
 * Method:    SetValuePtr
 * Signature: (IIC)Z
 */
JNIEXPORT jboolean JNICALL Java_START_LAUNCH_SetValuePtr
  (JNIEnv *, jobject, jint, jint, jchar);

#ifdef __cplusplus
}
#endif
#endif
/* Header for class START_LAUNCH_DvmgStartConfigureDevicesPrivate */

#ifndef _Included_START_LAUNCH_DvmgStartConfigureDevicesPrivate
#define _Included_START_LAUNCH_DvmgStartConfigureDevicesPrivate
#ifdef __cplusplus
extern "C" {
#endif
#undef START_LAUNCH_DvmgStartConfigureDevicesPrivate_MIN_PRIORITY
#define START_LAUNCH_DvmgStartConfigureDevicesPrivate_MIN_PRIORITY 1L
#undef START_LAUNCH_DvmgStartConfigureDevicesPrivate_NORM_PRIORITY
#define START_LAUNCH_DvmgStartConfigureDevicesPrivate_NORM_PRIORITY 5L
#undef START_LAUNCH_DvmgStartConfigureDevicesPrivate_MAX_PRIORITY
#define START_LAUNCH_DvmgStartConfigureDevicesPrivate_MAX_PRIORITY 10L
#ifdef __cplusplus
}
#endif
#endif
