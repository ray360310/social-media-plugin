package com.larvata.socialmedia.plugin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri

object SocialMediaFuncs {

    fun openFacebook(context: Context, pageId: String) {
        val pageUrl = "https://www.facebook.com/$pageId"
        try {
            val packageManager = context.packageManager
            val applicationInfo: ApplicationInfo =
                packageManager.getApplicationInfo("com.facebook.katana", 0)
            if (applicationInfo.enabled) {
                val versionCode: Int =
                    packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
                val url: String = if (versionCode >= 3002850) {
                    "fb://facewebmodal/f?href=$pageUrl"
                } else {
                    "fb://page/$pageId"
                }
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } else {
                throw java.lang.Exception("Facebook is disabled")
            }
        } catch (e: java.lang.Exception) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl)))
        }
    }

    fun openLine(context: Context, lineId: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://line.me/R/ti/p/@$lineId")
            )
        )
    }

    fun openInstagram(context: Context, pageId: String) {
        val appUri = Uri.parse("https://instagram.com/_u/$pageId")
        val browserUri = Uri.parse("https://instagram.com/$pageId")
        try {
            val packageManager = context.packageManager
            val appIntent: Intent? =
                packageManager.getLaunchIntentForPackage("com.instagram.android")
            if (appIntent != null) {
                appIntent.action = Intent.ACTION_VIEW
                appIntent.data = appUri
                context.startActivity(appIntent)
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
                context.startActivity(browserIntent)
            }
        } catch (ignored: PackageManager.NameNotFoundException) {
            val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
            context.startActivity(browserIntent)
        }
    }

    fun openYoutube(context: Context, youtubeChannel: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/channel/$youtubeChannel")
            )
        )
    }

    fun shareUrl(context: Context, url: String, content: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        context.startActivity(Intent.createChooser(shareIntent, content))
    }

    fun openWebSite(context: Context, url: String) {
        val webpage = Uri.parse(url)
        val myIntent = Intent(Intent.ACTION_VIEW, webpage)
        context.startActivity(myIntent)
    }

    fun dial(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        context.startActivity(intent)
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sendCustomerServiceEmail(context: Context, uriMail: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$uriMail")
        intent.putExtra(Intent.EXTRA_SUBJECT, "")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun sharePlainText(context: Context, shareContent: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, shareContent)
        intent.type = "text/plain"
        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
    }

    fun openGooglePlayReleaseApp(context: Context, packageName: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=${packageName}")
        context.startActivity(intent)
    }

    fun openMapToNavigate(context: Context, latitude: String, longitude: String){
        try {
            val googleMapNavigationUri = Uri.parse("google.navigation:q=$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, googleMapNavigationUri)
            intent.setPackage("com.google.android.apps.maps")
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            val intent = Intent().setData(Uri.parse("baidumap://map/geocoder?location=$latitude,$longitude&src=andr.baidu.openAPIdemo"))
            context.startActivity(intent)
        }
    }
}