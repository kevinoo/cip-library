package it.lucichkevin.cip;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import it.lucichkevin.cip.preferencesmanager.PreferencesManager;

/**
    @author Kevin on 14/02/14.
    @since      0.0.1

    Singleton class of utility methods. Instantiate with Utils.init()
*/
public class Utils {

    /**
     * Public vars for logging
     */
    public static final int LOG_DEBUG = 0;
    public static final int LOG_INFO = 1;
    public static final int LOG_ERROR = 2;

    private static Context context = null;

    private static String APP_TAG = "";

    public static void init( Context context ){
        setContext(context);
        setAppTag();
    }
    public static void init( Context context, String app_tag ){
        setContext(context);
        setAppTag(app_tag);
    }

    //////////////////////////////////////////////
    //  Getters and Setters

    public static Context getContext() {
        return context;
    }
    protected static void setContext( Context _context ) {
        context = _context;
    }

    public static String getAppTag(){
        return APP_TAG;
    }
    protected static void setAppTag(){
        setAppTag(context.getPackageName());
    }
    private static void setAppTag(String app_tag ){
        APP_TAG = app_tag;
    }


    //////////////////////////////////////////////
    //  Methods

    public String pad( int c ){
        if( c >= 10 ) {
            return String.valueOf(c);
        }else{
            return "0"+ String.valueOf(c);
        }
    }

    /**
     * Prints log ONLY IF devel_log is enabled.
     * Please remember to set this parameter to FALSE when creating production APK
     * @param   message   Message to log
     * @param   loglevel  Level of the log
     *
     * @author  Marco Zanetti (on Github: MarKco)
     */
    public static void logger( String message, int loglevel ){
        if( PreferencesManager.isDebugLog() ){
            switch( loglevel ) {
                case LOG_DEBUG:
                    Log.d( getAppTag(), message );
                    break;
                case LOG_INFO:
                    Log.i( getAppTag(), message );
                    break;
                case LOG_ERROR:
                    Log.e( getAppTag(), message );
                    break;
                default:
                    Log.i( getAppTag(), message );
                    break;
            }
        }
    }



    ///////////////////////////////////////////////////////
    //  Toaster

    public static void Toaster( Context context, String msg ){
        if( PreferencesManager.isToasterToLogcat() ){
            Utils.logger( msg , LOG_INFO);
            return;
        }
        Toaster.showToast(context, msg);
    }

    public static void Toaster( Context context, String msg, int seconds ){
        if( seconds > 1 ){
            Toaster.showLongToast(context, msg, seconds);
        }else{
            Toaster.showToast(context, msg, seconds);
        }
    }

    /*
        Usage:  Utils.Toaster( [context], "test", Utils.Toaster.LENGTH_LONG );
    */
    public static class Toaster{

        public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
        public static final int LENGTH_LONG = Toast.LENGTH_SHORT;

        /**
         @deprecated
         @desc   Use Utils.Toaster (not need the "new")
         */
        public Toaster( Context context, String msg ){
            if( PreferencesManager.isToasterToLogcat() ){
                Utils.logger( msg , LOG_INFO);
                return;
            }
            Toaster.showToast(context, msg);
        }

        /**
         @deprecated
         @desc   Use Utils.Toaster (not need the "new")
         */
        public Toaster( Context context, String msg, int seconds ){
            if( seconds > 1 ){
                Toaster.showLongToast(context, msg, seconds);
            }else{
                Toaster.showToast(context, msg, seconds);
            }
        }

        private static void showToast( Context context, String msg ){
            Toast.makeText( context, msg, Toast.LENGTH_SHORT ).show();
        }

        private static void showToast( final Context context, final String msg, final int time ){

            if( context instanceof Activity ){
                ((Activity) context).runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText( (Activity) context, msg, time ).show();
                    }
                });
                return;
            }

            Toast.makeText( context, msg, time ).show();
        }

        private static void showLongToast(Context context, final String msg, final int seconds){

            final Toast toast = Toast.makeText( context, msg, Toast.LENGTH_SHORT );

            toast.show();

            new CountDownTimer( seconds, 1000 ){

                public void onTick( long millisUntilFinished ){
                    toast.show();
                }
                public void onFinish(){
                    toast.show();
                }

            }.start();

        }
    }

/*
    public static class Database{

        private static SQLiteDatabase db = null;
        private static DaoSession daoSession = null;

        public static SQLiteDatabase getDatabase(){
            if( db == null ){
                DaoMaster.OpenHelper helper = new DaoMaster.OpenHelper(context, "workitout", null) {
                    @Override
                    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ){
                        //  Do nothing...
                    }
                };
                // Access the database using the helper
                db = helper.getWritableDatabase();
            }
            return db;
        }


        public static DaoSession getDaoSession(){
            return getDaoSession(false);
        }

        public static DaoSession getDaoSession( boolean reliable_session ){

            if( daoSession == null || reliable_session ){
                // Construct the DaoMaster which brokers DAOs for the Domain Objects
                DaoMaster daoMaster = new DaoMaster( getDatabase() );
                daoSession = daoMaster.newSession();
            }

            return daoSession;
        }

    }
*/

}