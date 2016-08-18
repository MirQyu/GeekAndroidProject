package com.geekband.Test01;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * Created by MirQ on 16/7/19.
 */
public class TestWidget extends AppWidgetProvider {

    public static final String WIDGET_BUTTON_ACTION = "widget_button_action";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null && TextUtils.equals(intent.getAction(), WIDGET_BUTTON_ACTION)) {
            Log.i(WIDGET_BUTTON_ACTION, "be clicked");
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            remoteViews.setTextViewText(R.id.widget_text_view, "be clicked");
            remoteViews.setTextColor(R.id.widget_text_view, Color.RED);

            ComponentName componentName = new ComponentName(context, TestWidget.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //一个界面
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        // 将来 执行的意图
        Intent intent = new Intent();
        intent.setClass(context, TestWidget.class);
        intent.setAction(WIDGET_BUTTON_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_button, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }
}
