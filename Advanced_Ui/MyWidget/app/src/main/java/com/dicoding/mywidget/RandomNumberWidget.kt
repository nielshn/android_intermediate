package com.dicoding.mywidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews

class RandomNumberWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Update all active widgets
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == WIDGET_CLICK) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context.packageName, R.layout.random_number_widget)
            val lastUpdate = "Random: " + NumberGenerator.generate(100)
            val appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0)
            views.setTextViewText(R.id.appwidget_text, lastUpdate)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    companion object {
        // Unique action for widget click
        const val WIDGET_CLICK = "com.dicoding.mywidget.action.WIDGET_CLICK"
        const val WIDGET_ID_EXTRA = "widget_id_extra"
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val intent = Intent(context, RandomNumberWidget::class.java).apply {
        action = RandomNumberWidget.WIDGET_CLICK
        putExtra(RandomNumberWidget.WIDGET_ID_EXTRA, appWidgetId)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context, appWidgetId, intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
    )

    val lastUpdate = "Random: " + NumberGenerator.generate(100)
    val views = RemoteViews(context.packageName, R.layout.random_number_widget)
    views.setTextViewText(R.id.appwidget_text, lastUpdate)
    views.setOnClickPendingIntent(R.id.btn_click, pendingIntent)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
