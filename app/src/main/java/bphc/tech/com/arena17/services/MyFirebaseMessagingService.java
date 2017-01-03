package bphc.tech.com.arena17.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import bphc.tech.com.arena17.HomeActivity;
import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.database.DBHelper;

/**
 * Created by tejeshwar on 18/12/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG = "firebase notification";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("Arena 2017");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

        DBHelper helper = new DBHelper(this);
        long success = helper.addFeedRow(remoteMessage.getMessageId(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),remoteMessage.getSentTime());
        Log.e(TAG,success+"");
    }
}
