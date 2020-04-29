package com.geekban.geekbandandroidproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Administrator on 2020/3/1.
 */

public class TestWidget extends AppWidgetProvider {

    public static final String WIDGET_BUTTON_ACTION = "widget_button_action";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null && TextUtils.equals(intent.getAction(),WIDGET_BUTTON_ACTION));
        Log.i(WIDGET_BUTTON_ACTION,"be clicked");
        //点击后要做的事情
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.layout_widget);
        remoteView.setTextViewText(R.id.widget_text_view,"我被点击了");
        remoteView.setTextColor(R.id.widget_text_view, Color.RED);
        //更新数据
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context,TestWidget.class);
        appWidgetManager.updateAppWidget(componentName,remoteView);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        //发送广播
        Intent intent = new Intent();
        intent.setClass(context,TestWidget.class);//设置广播类
        intent.setAction(WIDGET_BUTTON_ACTION);//设置接收动作标识
        //接收广播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.layout_widget);
        remoteView.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
        //更新
        appWidgetManager.updateAppWidget(appWidgetIds,remoteView);
    }
}
