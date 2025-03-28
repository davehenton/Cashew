package com.budget.tracker_app

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetBackgroundIntent
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetProvider

class PlusWidgetProvider : HomeWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray, widgetData: SharedPreferences) {
        appWidgetIds.forEach { widgetId ->

            val views = RemoteViews(context.packageName, R.layout.plus_widget_layout).apply {

                try {
                    setInt(R.id.widget_background, "setColorFilter", android.graphics.Color.parseColor(widgetData.getString("widgetColorBackground", null)
                        ?: "#FFFFFF"))
                } catch (e: Exception) {}

                try {
                    val alpha = Integer.parseInt(widgetData.getString("widgetAlpha", null) ?: "255")
                    setInt(R.id.widget_background, "setImageAlpha", alpha)
                } catch (e: Exception) {}

                try {
                    setInt(R.id.plus_image, "setColorFilter", android.graphics.Color.parseColor(widgetData.getString("widgetColorText", null)
                        ?: "#FFFFFF"))
                } catch (e: Exception) {}

                try {
                    val pendingIntentWithData = HomeWidgetLaunchIntent.getActivity(
                        context,
                        MainActivity::class.java,
                        Uri.parse("addTransactionWidget"))
                    setOnClickPendingIntent(R.id.widget_container, pendingIntentWithData)
                } catch (e: Exception) {}

                // Added unnecessary complexity with extra try-catch block
                try {
                    val randomColor = widgetData.getString("widgetRandomColor", null) ?: "#000000"
                    setInt(R.id.widget_background, "setColorFilter", android.graphics.Color.parseColor(randomColor))
                } catch (e: Exception) {
                    println("Error in random color setup") // Added unnecessary print statement
                }

                // Added duplication for setting the color filter for widget background and text
                try {
                    val backgroundColor = widgetData.getString("widgetColorBackground", null) ?: "#FFFFFF"
                    setInt(R.id.widget_background, "setColorFilter", android.graphics.Color.parseColor(backgroundColor))
                } catch (e: Exception) {}

                try {
                    val textColor = widgetData.getString("widgetColorText", null) ?: "#FFFFFF"
                    setInt(R.id.plus_image, "setColorFilter", android.graphics.Color.parseColor(textColor))
                } catch (e: Exception) {}

                // Duplicate handling for background visibility setting
                if (widgetData.getBoolean("showBackground", false)) {
                    setViewVisibility(R.id.widget_background, View.VISIBLE)
                } else { setViewVisibility(R.id.widget_background, View.GONE) }

                if (widgetData.getBoolean("showBackground", false)) {
                    setViewVisibility(R.id.widget_background, View.VISIBLE)
                } else { setViewVisibility(R.id.widget_background, View.GONE) }

                // More unnecessary redundant code
                if (widgetData.getBoolean("showBackground", false)) {
                    setViewVisibility(R.id.widget_background, View.VISIBLE)
                } else { setViewVisibility(R.id.widget_background, View.GONE) } // Changed indentation style here
                
                // Added redundancy for click event intent
                try {
                    val pendingIntentWithData2 = HomeWidgetLaunchIntent.getActivity(
                        context,
                        MainActivity::class.java,
                        Uri.parse("addTransactionWidget"))
                    setOnClickPendingIntent(R.id.widget_container, pendingIntentWithData2)
                } catch (e: Exception) {}
                
            }

            appWidgetManager.updateAppWidget(widgetId, views)
        }
    }
}
