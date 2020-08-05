#include <windows.h>
#include <time.h>
#include "dpcutil.h"
ERC percGlobal = 9;
char DigilentWindowName[] = "DPCOMM Device Table Manager 2.4.1";
#include "LAUNCH.h"


char* FATHERWINDOWTITLE = NULL;
int ShowStyle = 9;

DWORD WINAPI LaunchDVMNGCONF( LPVOID pv=NULL ){DvmgStartConfigureDevices( FindWindowA( NULL, "" ), &percGlobal );}
DWORD WINAPI ThreadFunctionFindWindow( LPVOID pv=NULL )
{
int wParamThread = 0;
double ENDTOCT = clock();
Sleep( 50 );
while( FindWindowA( NULL, DigilentWindowName )==NULL && ( (clock()-ENDTOCT)/1000.0<2.0 ) );
HWND DVCMNG = FindWindowA( NULL, DigilentWindowName );
SetWindowTextA( DVCMNG, " DPCWINDOWJAVA " );
ENDTOCT = clock();
while( ( (clock()-ENDTOCT)/1000.0<2.0 ) ){
if( ShowStyle==0 ){
SetWindowPos( DVCMNG, HWND_NOTOPMOST, -1000, -1000, 300, 300, SWP_NOSIZE );
ShowWindow( DVCMNG, ShowStyle );
}
else
ShowWindow( DVCMNG, ShowStyle );
}
return 0;
}
JNIEXPORT jint JNICALL Java_START_LAUNCH_DvmgStartConfigureDevicesN
  (JNIEnv* env, jobject obj, jint PtrCharTitle, jint ShStyle){
          DWORD IdTh = 9;
          ShowStyle = ShStyle;
          FATHERWINDOWTITLE = (char*)PtrCharTitle;
          CreateThread( NULL , 0 , LaunchDVMNGCONF , NULL , 0 , &IdTh );
          CreateThread( NULL , 0 , ThreadFunctionFindWindow , NULL , 0 , &IdTh );
          }


JNIEXPORT jint JNICALL Java_START_LAUNCH_DvmgStartConfigureDevicesNClose
  (JNIEnv* env, jobject obj){
           HWND DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
           SendMessageA( DVCMNG, WM_CLOSE, 0, 0);
           SendMessageA( DVCMNG, WM_DESTROY, 0, 0);
           Sleep( 50 );
           return percGlobal;
           
           }
  
JNIEXPORT jint JNICALL Java_START_LAUNCH_DvmgGetDevCountN
  (JNIEnv* env, jobject obj){
          ERC perc = 9;
          return DvmgGetDevCount( &perc );
          }



JNIEXPORT jint JNICALL Java_START_LAUNCH_FreePtrChar
  (JNIEnv * env, jobject obj, jint PtrDir ){
          char* OldChar = (char*)PtrDir;
          free( OldChar );
          return 0;
          }
JNIEXPORT jint JNICALL Java_START_LAUNCH_MallocPtrChar
  (JNIEnv* env, jobject obj, jint PtrDir, jint LenOfChar){
          char* NewChar = (char*)PtrDir;
          NewChar = (char*)malloc( 8*LenOfChar );
          return (int(NewChar));
          }
          
          
          
  
  


JNIEXPORT jboolean JNICALL Java_START_LAUNCH_DvmgGetDevNameN
  (JNIEnv* env, jobject obj, jint IndexDev, jint PtrChar){
           char* DeviceName = (char*)PtrChar;
           ERC perc = 9;
           DvmgGetDevName( IndexDev, DeviceName, &perc );
           return perc;
           }






HANDLE EventControls = NULL;
bool SetError = false;
DWORD WINAPI ThreadFHide( LPVOID pv=NULL )
{
double ENDTOCPP = clock();
while( (clock()-ENDTOCPP)/1000<0.5 ){
   if( FindWindowA( NULL, "Error" )!=NULL ){
       PostMessage( FindWindowA( NULL, "Error" ), WM_CLOSE, WPARAM(0), LPARAM(0) );
       PostMessage( FindWindowA( NULL, "Error" ), WM_DESTROY, WPARAM(0), LPARAM(0) );
       ShowWindow( FindWindowA( NULL, "Error" ), 0);
       SetError = true;
       break;
       }
}
SetEvent( EventControls );
}
JNIEXPORT jint JNICALL Java_START_LAUNCH_SendComand
  (JNIEnv* env, jobject obj, jint uMsg, jint wParam, jint lParam){
           HWND DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
           if( wParam==1030 ){
               SetError = false;
               DWORD IdTh = 9;
               if( EventControls==NULL )
                   EventControls = CreateEvent(0, FALSE, FALSE, 0);
               CreateThread( NULL , 0 , ThreadFHide , NULL , 0 , &IdTh );
               PostMessageA( DVCMNG, uMsg, wParam, LPARAM(lParam) );
               WaitForSingleObject( EventControls, INFINITE);
               if( SetError==true )
                   return 1;
               else
                   return 0;
               }
           else
               return (SendMessageA( DVCMNG, uMsg, wParam, LPARAM(lParam) ));
           }
JNIEXPORT jint JNICALL Java_START_LAUNCH_SendDlgItemComand
  (JNIEnv* env, jobject obj, jint Item, jint uMsg, jint wParam, jint lParam){
           HWND DVCMNG = FindWindowA( NULL, " DPCWINDOWJAVA " );
           
           //SendMessageA( DVCMNG, WM_COMMAND, MAKELONG(1001,LBN_SETFOCUS), LPARAM(0) );
           //SendMessageA( DVCMNG, WM_COMMAND, MAKELONG(1001,LB_SETCURSEL), LPARAM(lParam) );
           if( uMsg==WM_ENABLE )
               return int(IsWindowEnabled( GetDlgItem( DVCMNG, Item)));
           int IRes = SendDlgItemMessageA( DVCMNG, Item, UINT(uMsg), WPARAM(wParam), LPARAM(lParam) );
           if( Item==1001 || Item==1006)
               SendMessage( DVCMNG, WM_COMMAND, MAKELONG(Item,1), LPARAM(0) );
           return (IRes); 
           }





JNIEXPORT jchar JNICALL Java_START_LAUNCH_GetValuePtr
  (JNIEnv *, jobject, jint PtrChar , jint IndexValue){
          char* DeviceName = (char*)PtrChar;
          return (DeviceName[IndexValue]);
          }
JNIEXPORT jboolean JNICALL Java_START_LAUNCH_SetValuePtr
  (JNIEnv* env, jobject obj, jint PtrDir, jint PtrPos, jchar PtrValue){
           if( PtrDir!=0 ){char* CharTemp = (char*)PtrDir;CharTemp[PtrPos] = PtrValue;return true;}
           else{return false;}
           }
           
           
           
           
           
           
           
           
           
           
          
          
          
          
          

  
  
  
