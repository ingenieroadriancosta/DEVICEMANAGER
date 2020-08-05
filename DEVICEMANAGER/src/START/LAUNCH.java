package START;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class LAUNCH{
    private native int DvmgStartConfigureDevicesN( int PtrcharTitle, int ShowStyle );
    private native int DvmgStartConfigureDevicesNClose();
    private native int  DvmgGetDevCountN();
    private native boolean DvmgGetDevNameN( int idvc, int PtrszdvcTemp );
    private native int SendComand( int uMsg, int wParam, int lParam );
    private native int SendDlgItemComand( int Item, int uMsg, int wParam, int lParam );
    private native int FreePtrChar( int PtrDir );
    private native int MallocPtrChar( int PtrDir, int LenOfPtr );
    private native char GetValuePtr( int PtrDir, int PosOfPtr );
    private native boolean SetValuePtr( int PtrDir, int PosOfPtr, char ByVal );

    
    private JDialog DPCDEVICEMANAGER = new JDialog();
    private JFrame FatherMainFrameP;
    private JButton ADD_DVC = new JButton();
    private JButton REMOVE_DVC = new JButton();
    private JButton SAVE = new JButton();
    private JButton ENUMWRATE = new JButton();
    private JList DeviceInList = new JList();
    private JList ConnectedDevices = new JList();
    private JScrollPane jscrollpane = new JScrollPane();
    private JScrollPane jscrollpaneConnectedDevices = new JScrollPane();

    private JTextField ALIAS = new JTextField();
    private JTextField CONNECT = new JTextField();
    
    JRadioButton USB = new JRadioButton();
    JRadioButton NET = new JRadioButton();
    JRadioButton PAR = new JRadioButton();
    JRadioButton SER = new JRadioButton();

    private int ErrResult = 0;
    private String ListDevice[] = null;
    private DvmgStartConfigureDevicesPrivate NEWTRHEAD = new DvmgStartConfigureDevicesPrivate();



    public static int main( JFrame FatherMainFrame ) {
        // TODO code application logic here
        if( FatherMainFrame==null || FatherMainFrame.getTitle()==null ){
            JOptionPane.showMessageDialog( null, "Debe especificar el un Frame correspondiente\n" +
                                            "que tenga un título",
                                            "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        LAUNCH application = new LAUNCH();
        application.WriteFile( null );

        application.FatherMainFrameP = FatherMainFrame;

        application.DIALOGDVCMNG();
        return application.ErrResult;
    }
    
    private int DIALOGDVCMNG(){
        //DPCDEVICEMANAGER.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        DPCDEVICEMANAGER.addWindowListener(new WindowAdapter() {
            @Override
           public void windowClosing(WindowEvent e) {
               ErrResult = DvmgStartConfigureDevicesNClose();
               File AAA = new File( "ICODIGILENT.png" );
               AAA.delete();
               NEWTRHEAD.stop();
               NEWTRHEAD.ThreadActivo = false;
               DPCDEVICEMANAGER.dispose();
           }});
        DPCDEVICEMANAGER.setSize( 599, 265);
        DPCDEVICEMANAGER.setLocation(300 , 300 );
        DPCDEVICEMANAGER.setResizable( false );
        DPCDEVICEMANAGER.setModal( true );
        DPCDEVICEMANAGER.setTitle( " DPCOMM Device Table Manager 2.4.1 " );
        DPCDEVICEMANAGER.setIconImage( Toolkit.getDefaultToolkit().getImage("ICODIGILENT.png") );
        //FatherMainFrameP.gett

        ListInChange = true;


        JButton DeviceManagerD = new JButton( "Boton" );
        DeviceManagerD.setSize( 120, 40 );
        DeviceManagerD.setLocation( 0, 0);
        DeviceManagerD.setVisible( true );
        //DPCDEVICEMANAGER.add( DeviceManagerD );
        try{
            //System.load( System.getProperty( "java.home" )  + "/DEVICEMANAGER.dll" );
            System.load( "E:/ENTERPRISE/JAVA/EJERCICIOS/DEVICEMANAGER/DLLDEVICEMANAGER/DEVICEMANAGER.dll" );
        }
        catch( UnsatisfiedLinkError e1){
            System.out.println( "Error alcargar la libreria DEVICEMANAGER.dll." );
            System.out.println( e1.toString() );
        }








        /*
        int KKK = DvmgGetDevCountN();
        String DeveName = new String( DvmgGetDevName(0) );
        JOptionPane.showMessageDialog(  DPCDEVICEMANAGER, "Device In List,\n" + KKK + "\n\nDeveName\n" + DeveName );
        //*/
        Point DVCINTBL = new Point();


        
        DVCINTBL.x = 390;
        DVCINTBL.y = 10;
        JLabel LabelConnectedDevices = new JLabel( "Conneced Devices" );
        LabelConnectedDevices.setLocation( DVCINTBL );
        LabelConnectedDevices.setSize( 125, 18);
        LabelConnectedDevices.setVisible( true );
        DPCDEVICEMANAGER.add( LabelConnectedDevices );
        ConnectedDevices.setVisible( true );
        DVCINTBL.y = DVCINTBL.y + 20;
        ConnectedDevices.setLocation( DVCINTBL );
        final int WWWCONNDVC = 170;
        final int HHHCONNDVC = 150;
        ConnectedDevices.setSize( WWWCONNDVC, HHHCONNDVC );
        DPCDEVICEMANAGER.add( ConnectedDevices );
        ConnectedDevices.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        

        ConnectedDevices.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt){IndexDeviceConn();}});
        jscrollpaneConnectedDevices.setVisible( true );
        jscrollpaneConnectedDevices.setSize( WWWCONNDVC, HHHCONNDVC);
        jscrollpaneConnectedDevices.setLocation( DVCINTBL );
        DPCDEVICEMANAGER.add( jscrollpaneConnectedDevices );
        jscrollpaneConnectedDevices.setViewportView(ConnectedDevices);
        ConnectedDevices.setSelectedIndex( 0 );


        SAVE.setVisible( true );
        DPCDEVICEMANAGER.add( SAVE );

        ENUMWRATE.setText( "Enumerate" );
        ENUMWRATE.setSize( 120, 25 );
        ENUMWRATE.setLocation( ConnectedDevices.getX() + 22,
                                        ConnectedDevices.getY() + ConnectedDevices.getHeight() + 10 );
        ENUMWRATE.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){ENUMERATEFUNC();} });
        ENUMWRATE.setVisible( true );
        DPCDEVICEMANAGER.add( ENUMWRATE );
        //*/





        final int WWWCONN = 50;
        Point ALIASCONNECT = new Point();
        ALIASCONNECT.x = 225;
        ALIASCONNECT.y = 30;

        JLabel LabelALIAS = new JLabel( "Alias:" );
        LabelALIAS.setSize( WWWCONN, 20);
        LabelALIAS.setLocation( ALIASCONNECT.x-LabelALIAS.getWidth(), ALIASCONNECT.y );
        LabelALIAS.setVisible( true );
        DPCDEVICEMANAGER.add( LabelALIAS );
        ALIAS.setSize( 150 , 30 );
        ALIAS.setVisible( true );
        ALIAS.setLocation( ALIASCONNECT );

        //  keyTyped
        //  keyReleased
        ALIAS.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ALIASFUNC();
            }
        });
        /*
        ALIAS.addActionListener(new java.awt.event.ActionEvent() {
            public void actionPerformed(java.awt.event.KeyEvent evt) {
                ALIASFUNC();
            }
        });
        //*/

        DPCDEVICEMANAGER.add( ALIAS );


        
        
        ALIASCONNECT.y = ALIASCONNECT.y + 40;
        JLabel LabelCONNECT = new JLabel( "Connect:" );
        LabelCONNECT.setSize( WWWCONN, 20);
        LabelCONNECT.setLocation( ALIASCONNECT.x-LabelALIAS.getWidth(), ALIASCONNECT.y );
        LabelCONNECT.setVisible( true );
        DPCDEVICEMANAGER.add( LabelCONNECT );
        CONNECT.setSize( 150 , 30 );
        CONNECT.setVisible( true );
        CONNECT.setLocation( ALIASCONNECT );
        CONNECT.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CONNECTFUNC();
            }
        });
        DPCDEVICEMANAGER.add( CONNECT );




        
        USB.setLocation( ALIASCONNECT.x, ALIASCONNECT.y+60);
        USB.setSize( 60, 20 );
        USB.setText( "USB" );
        //USB.setSelected( true );
        USB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendComand( WM_COMMAND, WM_USB, 0 );
                USB.setSelected( true );
            }
        });
        









        USB.setVisible( true );
        DPCDEVICEMANAGER.add( USB );

        NET.setLocation( ALIASCONNECT.x + 70, ALIASCONNECT.y+60);
        NET.setSize( 60, 20 );
        NET.setText( "NET" );
        NET.setEnabled( false );
        NET.setVisible( true );
        DPCDEVICEMANAGER.add( NET );


        PAR.setLocation( ALIASCONNECT.x, ALIASCONNECT.y+60 + 30);
        PAR.setSize( 60, 20 );
        PAR.setText( "PAR" );
        PAR.setEnabled( false );
        PAR.setVisible( true );
        DPCDEVICEMANAGER.add( PAR );

        SER.setLocation( ALIASCONNECT.x + 70, ALIASCONNECT.y+60 + 30);
        SER.setSize( 60, 20 );
        SER.setText( "SER" );
        SER.setEnabled( false );
        SER.setVisible( true );
        DPCDEVICEMANAGER.add( SER );
        
        




        










        ALIASCONNECT.y = ALIASCONNECT.y + 30;




        
        
        DVCINTBL.x = 10;
        DVCINTBL.y = 10;
        JLabel LabelDevConn = new JLabel( "Device Table" );
        LabelDevConn.setLocation( DVCINTBL );
        LabelDevConn.setSize( 125, 18);
        LabelDevConn.setVisible( true );
        DPCDEVICEMANAGER.add( LabelDevConn );
        DeviceInList.setVisible( true );
        DVCINTBL.y = DVCINTBL.y + 20;
        DeviceInList.setLocation( DVCINTBL );
        DeviceInList.setSize( 155, 111 );
        DPCDEVICEMANAGER.add( DeviceInList );
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            }
        DeviceInList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        
        
        DeviceInList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt){IndexDeviceInList();}});
        jscrollpane.setVisible( true );
        jscrollpane.setSize( 155, 111);
        jscrollpane.setLocation( DVCINTBL );
        DPCDEVICEMANAGER.add( jscrollpane );
        jscrollpane.setViewportView(DeviceInList);
        //DeviceInList.setSelectedIndex( 0 );



        ADD_DVC.setText( "Add Dvc" );
        ADD_DVC.setSize( 120, 25 );
        DVCINTBL.y = DVCINTBL.y + 120;
        DVCINTBL.x = DVCINTBL.x + 16;
        ADD_DVC.setLocation( DVCINTBL );
        ADD_DVC.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){ADDDVCFunc();} });
        ADD_DVC.setVisible( true );
        DPCDEVICEMANAGER.add( ADD_DVC );


        REMOVE_DVC.setText( "Remove Dvc" );
        REMOVE_DVC.setSize( 120, 25 );
        DVCINTBL.y = DVCINTBL.y + 30;
        REMOVE_DVC.setLocation( DVCINTBL );
        REMOVE_DVC.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){REMOVEDVCFunc();} });
        REMOVE_DVC.setVisible( true );
        DPCDEVICEMANAGER.add( REMOVE_DVC );

        SAVE.setText( "Save" );
        SAVE.setSize( 120, 25 );
        DVCINTBL.y = DVCINTBL.y + 30;
        SAVE.setLocation( DVCINTBL );


        SAVE.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event ){SAVEFunc();} });


        
        


        
        DPCDEVICEMANAGER.add( ENUMWRATE );

        
        JPanel ControlPanel = new JPanel();
        DPCDEVICEMANAGER.add( ControlPanel );



        
        NEWTRHEAD = new DvmgStartConfigureDevicesPrivate();
        NEWTRHEAD.start();




        ListInChange = false;
        DPCDEVICEMANAGER.setVisible( true );    
        return 0;
    }



    private String DvmgGetDevName( int Idvc ){
        int PtrszDevName = MallocPtrChar( 0, 1024);
        DvmgGetDevNameN( Idvc, PtrszDevName );
        int i=0;
        for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
        char[] szVersion;
        szVersion = new char[i];
        for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
        String VersionR;
        VersionR = String.copyValueOf(szVersion, 0, i);
        FreePtrChar( PtrszDevName );
        return VersionR;
    }
//*/



    private final int WM_COMMAND = 273;
    private final int WM_ENABLE = 10;

    
    private final int LB_SETCURSEL = 390;
    private final int LB_GETCOUNT = 395;
    private final int LB_GETTEXT = 393;

    
    private final int WM_GETTEXT = 13;
    private final int WM_SETTEXT = 12;
    private final int BM_GETCHECK = 240;
    private final int BST_CHECKED = 1;


    

    private final int WM_ENUMERATE = 1027;
    private final int WM_REMOVE = 1028;
    private final int WM_ADD = 1030;
    private final int WM_SAVE = 1022;
    private final int WM_USB = 1007;
    private final int WM_DEVICEINLIST = 1001;
    private final int WM_DEVICECONN = 1006;
    private final int WM_ALIAS = 1005;
    private final int WM_CONNECT = 1004;


    
    private void SAVEFunc(){
        //JOptionPane.showMessageDialog(  null, "SAVEFunc\n" );
        SendComand( WM_COMMAND, WM_SAVE, 0 );
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListInChange = true;
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            ListInChange = false;
            }
    }

    private boolean ADDDVCFunc(){
        //JOptionPane.showMessageDialog(  null, "SAVEFunc\n" );
        int ResMess = SendComand( WM_COMMAND, WM_ADD, 0 );
        if( ResMess!=0 ){
            JOptionPane.showMessageDialog(  null, "Failed to add device to device table",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListInChange = true;
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            ListInChange = false;
            }
       return true;
    }

    private void REMOVEDVCFunc(){
        SendComand( WM_COMMAND, WM_REMOVE, 0 );
        int NdevicesCon = DvmgGetDevCountN();
        if( NdevicesCon>0 ){
            ListInChange = true;
            ListDevice = new String[NdevicesCon];
            for( int i=0; i<NdevicesCon; i++ ){ListDevice[i] = DvmgGetDevName(i);}
            DeviceInList.setListData( ListDevice );
            ListInChange = false;
            }
        int Result = SendDlgItemComand( WM_USB, BM_GETCHECK, 0, 0);
        USB.setSelected( Result==BST_CHECKED );
        int PtrszDevName = MallocPtrChar( 0, 1024);
            SendDlgItemComand( WM_ALIAS, WM_GETTEXT, 256, PtrszDevName);
            int i=0;
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            char[] szVersion;
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            String VersionR;
            VersionR = String.copyValueOf(szVersion, 0, i);
            ALIAS.setText(VersionR);


            SendDlgItemComand( WM_CONNECT, WM_GETTEXT, 256, PtrszDevName);
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            VersionR = String.copyValueOf(szVersion, 0, i);
            CONNECT.setText(VersionR);
            FreePtrChar( PtrszDevName );

    }


    private void ENUMERATEFUNC(){
        //JOptionPane.showMessageDialog(  null, "SAVEFunc\n" );
        // ConnectedDevices
        SAVE.setEnabled( false );
        REMOVE_DVC.setEnabled( false );
        SendComand( WM_COMMAND, WM_ENUMERATE, 0 );
        while( SendDlgItemComand( WM_SAVE, WM_ENABLE, 0, 0)==0 ){
            SAVE.setEnabled( false );
            REMOVE_DVC.setEnabled( false );
        }
        SAVE.setEnabled( true );
        REMOVE_DVC.setEnabled( true );

        int PtrszDevName = MallocPtrChar( 0, 1024);
        int NDevConn = SendDlgItemComand( WM_DEVICECONN, LB_GETCOUNT, 0, 0);
        String ConnDevice[] = new String[NDevConn];
        
        for( int i=0; i<NDevConn; i++ ){
            SendDlgItemComand( WM_DEVICECONN, LB_GETTEXT, i, PtrszDevName);
            int C = 9;
            for( C=0; (C<256)&&(GetValuePtr( PtrszDevName, C)!=0); C++ ){}
            char[] szVersion;
            szVersion = new char[C];
            for( int N=0; N<C; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            ConnDevice[i] = String.copyValueOf(szVersion, 0, C);
        }
        ConnectedDevices.setListData(ConnDevice);
        //JOptionPane.showMessageDialog(  null, "WM_DEVICECONN\n" + NDevConn + "\n" + ConnDevice[0] );

        
        FreePtrChar( PtrszDevName );
    }

    






    private void ALIASFUNC(){
        int PtrszDevName = MallocPtrChar( 0, 1024);
        String Texto = new String( ALIAS.getText() );
        for( int i=0; i<Texto.length(); i++)
            SetValuePtr( PtrszDevName, i, Texto.charAt(i));
        //
        SendDlgItemComand( WM_ALIAS, WM_SETTEXT, 256, PtrszDevName);


        FreePtrChar( PtrszDevName );
    }

    private void CONNECTFUNC(){
        int PtrszDevName = MallocPtrChar( 0, 1024);
        String Texto = new String( CONNECT.getText() );
        for( int i=0; i<Texto.length(); i++){SetValuePtr( PtrszDevName, i, Texto.charAt(i));}
        SendDlgItemComand( WM_CONNECT, WM_SETTEXT, 256, PtrszDevName);
        FreePtrChar( PtrszDevName );
    }

    



    private boolean ListInChange = false;
    private void IndexDeviceInList(){
        if( ListInChange==false ){
            int SelectedIndex = DeviceInList.getSelectedIndex();
            SendDlgItemComand( WM_DEVICEINLIST, LB_SETCURSEL, SelectedIndex, 0);
            int Result = SendDlgItemComand( WM_USB, BM_GETCHECK, SelectedIndex, 0);
            USB.setSelected( Result==BST_CHECKED );
            int PtrszDevName = MallocPtrChar( 0, 1024);
            SendDlgItemComand( WM_ALIAS, WM_GETTEXT, 256, PtrszDevName);
            int i=0;
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            char[] szVersion;
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            String VersionR;
            VersionR = String.copyValueOf(szVersion, 0, i);
            ALIAS.setText(VersionR);


            SendDlgItemComand( WM_CONNECT, WM_GETTEXT, 256, PtrszDevName);
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            VersionR = String.copyValueOf(szVersion, 0, i);
            CONNECT.setText(VersionR);
            FreePtrChar( PtrszDevName );
        }
    }


    

    private void IndexDeviceConn(){
        if( ListInChange==false ){
            int SelectedIndex = ConnectedDevices.getSelectedIndex();
            SendDlgItemComand( WM_DEVICECONN, LB_SETCURSEL, SelectedIndex, 0);
            int Result = SendDlgItemComand( WM_USB, BM_GETCHECK, SelectedIndex, 0);
            USB.setSelected( Result==BST_CHECKED );
            int PtrszDevName = MallocPtrChar( 0, 1024);
            SendDlgItemComand( WM_ALIAS, WM_GETTEXT, 256, PtrszDevName);
            int i=0;
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            char[] szVersion;
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            String VersionR;
            VersionR = String.copyValueOf(szVersion, 0, i);
            ALIAS.setText(VersionR);
            SendDlgItemComand( WM_CONNECT, WM_GETTEXT, 256, PtrszDevName);
            for( i=0; (i<256)&&(GetValuePtr( PtrszDevName, i)!=0); i++ ){}
            szVersion = new char[i];
            for( int N=0; N<i; N++ ){szVersion[N] = GetValuePtr( PtrszDevName, N);}
            VersionR = String.copyValueOf(szVersion, 0, i);
            CONNECT.setText(VersionR);
            FreePtrChar( PtrszDevName );
        }
    }



    private class DvmgStartConfigureDevicesPrivate extends Thread {
        public boolean ThreadActivo = false;
        @Override
        public void run(){
            String TitleMainF = new String( FatherMainFrameP.getTitle() );
            int LenOfTitle = TitleMainF.length();
            int PtrszDevName = MallocPtrChar( 0, LenOfTitle );
            
            for( int i=0; i<LenOfTitle; i++ ){
                SetValuePtr( PtrszDevName, i, TitleMainF.charAt(i) );
            }
            DvmgStartConfigureDevicesN( PtrszDevName, 0 );
        }
    }





















    private static int ICODIGILENTVAR[] = { 137,80,78,71,13,10,26,10,0,0,0,13,73,72,68,82,0,0,0,32,0,0,0,32,8,6,0,0,0,115,122,122,244,0,0,0,1,115,82,71,66,0,174,206,28,233,0,0,0,4,103,65,77,65,0,0,177,143,11,252,97,5,0,0,0,9,112,72,89,115,0,0,14,195,0,0,14,195,1,199,111,168,100,0,0,2,186,73,68,65,84,88,71,189,151,93,107,19,81,16,134,247,135,216,171,122,147,31,97,85,42,20,169,74,181,66,173,228,66,37,212,155,122,103,81,172,248,81,162,168,81,177,16,107,107,164,20,115,105,171,94,68,252,136,248,85,189,82,65,37,104,53,63,230,200,51,48,135,201,154,172,57,219,221,6,14,187,36,123,206,204,251,206,59,239,108,162,40,197,231,243,151,175,110,219,192,128,27,25,217,235,198,199,15,187,225,225,61,174,213,106,185,20,71,165,219,82,42,149,220,244,244,41,119,111,233,190,107,54,155,238,217,243,151,142,239,210,157,22,184,171,209,104,184,90,173,38,136,139,197,162,36,192,154,157,61,47,215,192,227,194,31,7,233,198,239,63,174,221,110,187,114,185,44,201,104,18,199,142,159,200,55,1,69,79,112,86,156,133,75,151,231,242,101,193,162,215,36,226,44,80,150,112,94,251,216,241,104,117,205,213,235,117,65,110,23,44,28,153,60,234,203,64,66,48,213,199,145,97,143,88,244,63,127,109,136,242,183,140,5,144,91,244,119,230,171,226,3,248,129,106,193,178,112,229,234,245,108,89,176,232,161,124,116,116,159,116,192,233,153,153,252,89,0,57,245,87,186,43,149,155,18,184,90,173,74,34,189,88,224,57,246,133,21,186,203,211,160,183,109,71,80,130,179,176,98,28,81,127,191,112,113,174,195,23,54,221,17,152,140,69,175,180,19,252,214,237,121,55,49,49,41,9,228,198,66,18,122,130,235,0,178,90,192,146,173,59,166,102,129,67,30,63,121,218,33,178,51,103,207,9,245,160,183,195,7,102,44,11,36,167,246,76,199,160,163,96,45,36,161,103,4,199,199,111,166,44,128,30,55,83,113,65,43,11,244,168,187,219,232,133,133,245,143,159,188,47,88,22,216,23,196,130,69,255,237,251,15,105,55,85,126,55,244,74,111,38,44,16,40,9,61,170,239,85,207,36,22,238,46,44,138,56,255,171,5,139,30,97,89,244,220,83,150,164,67,172,47,4,119,4,232,95,188,122,237,107,15,165,152,11,223,227,239,73,232,53,169,36,22,96,32,145,133,169,169,147,62,120,26,244,154,68,156,133,7,203,43,190,45,123,250,2,234,166,119,85,249,160,7,13,232,185,246,131,94,19,224,172,247,31,214,187,118,4,12,112,230,63,101,180,89,199,209,239,220,181,59,104,188,226,17,188,170,211,150,220,211,57,203,43,15,123,179,16,71,79,50,22,253,224,224,118,121,3,222,204,194,7,212,29,41,73,7,11,22,61,89,91,229,131,132,129,164,155,179,186,122,45,128,244,205,219,119,190,246,36,131,226,201,144,14,224,85,59,171,160,246,28,24,129,249,136,76,84,120,160,223,127,96,204,187,30,232,119,12,13,137,245,102,189,136,75,105,35,107,60,220,95,187,81,241,9,48,245,96,35,175,5,192,72,7,15,109,51,118,240,144,15,174,222,159,215,85,135,155,180,35,116,32,60,106,146,87,192,248,185,196,243,94,192,240,41,20,10,146,196,86,44,168,215,63,47,127,1,89,252,99,4,60,32,148,160,0,0,0,0,73,69,78,68,174,66,96,130};
    private String FileNamePrivate = new String( "ICODIGILENT.png" );
    private boolean WriteFile( String FilePath ){
        String FileName = null;
	if(FilePath==null){FileName = new String( "ICODIGILENT.png" );}
	else{FileName = new String( FilePath + "ICODIGILENT.png" );}
	try{
            DataOutputStream dos = new DataOutputStream( new BufferedOutputStream(
				new FileOutputStream( FileName )   )  );
            for( int i=0; i<ICODIGILENTVAR.length; i++ ){
                dos.writeByte(ICODIGILENTVAR[i]);
		}
            dos.close();
	}catch( java.io.IOException e){System.out.println( e );return false;}
	FileNamePrivate = new String( FileName );
	return true;
    }

    private String GetFileName(){return FileNamePrivate;}
}

