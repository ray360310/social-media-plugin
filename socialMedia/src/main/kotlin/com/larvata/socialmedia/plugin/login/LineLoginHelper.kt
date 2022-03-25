package com.larvata.socialmedia.plugin.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.linecorp.linesdk.LineApiResponseCode
import com.linecorp.linesdk.Scope
import com.linecorp.linesdk.auth.LineAuthenticationParams
import com.linecorp.linesdk.auth.LineLoginApi
import com.linecorp.linesdk.auth.LineLoginResult

object LineLoginHelper {

    fun login(context: Context, channelID: String, activityResultLauncher: ActivityResultLauncher<Intent>){
        val intent = LineLoginApi.getLoginIntent(
            context,
            channelID,
            LineAuthenticationParams.Builder().scopes(listOf(Scope.PROFILE)).build()
        )
        activityResultLauncher.launch(intent)
    }

    fun processResult(result: ActivityResult): LineLoginResult?{
        val lineLoginResult = LineLoginApi.getLoginResultFromIntent(result.data)
        Log.d("line", lineLoginResult.toString())
        return when (lineLoginResult.responseCode) {
            LineApiResponseCode.SUCCESS -> {
                lineLoginResult
            }
            LineApiResponseCode.CANCEL -> {
                null
            }
            else -> {
                null
            }
        }
    }

}